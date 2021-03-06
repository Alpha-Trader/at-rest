package com.alphatrader.rest;

import com.alphatrader.rest.util.ApiLibConfig;
import com.alphatrader.rest.util.PropertyGson;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Wrapper to allow testing of all webservice classes.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
class Http {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Http.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new PropertyGson().create();

    /**
     * Singleton instance
     */
    private static Http instance = new Http();

    /**
     * The http request cache. Pretty simple for now, mainly caches answers.
     */
    private static final LoadingCache<String, HttpResponse<String>> httpAnswerCache = CacheBuilder
        .newBuilder()
        .maximumSize(1000)
        .expireAfterAccess(1, TimeUnit.DAYS)
        .build(new CacheLoader<String, HttpResponse<String>>() {
            @Override
            public HttpResponse<String> load(String suffix) throws Exception {
                return Http.getInstance().get(suffix);
            }
        });

    /**
     * The library configuration.
     */
    private final ApiLibConfig config = ApiLibConfig.getInstance();

    private final ScheduledExecutorService cacheRefresher = Executors.newSingleThreadScheduledExecutor();

    /**
     * Replaces the current instance with a different one. Use for testing only.
     *
     * @param atHttp the new instance to use
     */
    static void setInstance(Http atHttp) {
        instance = atHttp;
    }

    /**
     * @return the singleton instance
     */
    @NotNull
    static Http getInstance() {
        return instance;
    }

    /**
     * Fetches a specific object from the API.
     *
     * @param typeParameterClass the class of the type of object to return
     * @param suffix             the api endpoint suffix
     * @param <T>                the type of object to return
     * @return the requested object
     */
    @Nullable
    static <T> T getSingleObjectFromApi(Class<T> typeParameterClass, String suffix) {
        T myReturn = null;

        try {
            HttpResponse<String> response = httpAnswerCache.get(suffix);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody(), typeParameterClass);
            }
        }
        catch (ExecutionException ue) {
            handleException(ue, typeParameterClass);
        }

        return myReturn;
    }

    /**
     * Fetches a list of objects from the API.
     *
     * @param typeParameterClass the class of the type of objects to return
     * @param suffix             the api endpoint suffix
     * @param <T>                the type of object to return
     * @return the requested object
     */
    @NotNull
    static <T> List<T> getMultipleObjectFromApi(Class<T> typeParameterClass, String suffix) {
        List<T> myReturn = new ArrayList<>();

        try {
            HttpResponse<String> response = httpAnswerCache.get(suffix);

            if (response != null && response.getStatus() == 200) {
                myReturn.addAll(gson.fromJson(response.getBody(),
                    new ArrayListTypeToken<>(typeParameterClass)));
            }
        }
        catch (ExecutionException ue) {
            handleException(ue, typeParameterClass);
        }

        return myReturn;
    }

    /**
     * Creates a new http service object that automatically refreshes it's cache.
     */
    private Http() {
        cacheRefresher.scheduleWithFixedDelay(
                () -> {
                    for(String key : httpAnswerCache.asMap().keySet()) {
                        httpAnswerCache.refresh(key);
                    }
                },
                config.getRefreshInterval(),
                config.getRefreshInterval(),
                TimeUnit.MINUTES
        );
    }

    /**
     * Performs a get request to the REST API.
     *
     * @param url the URL to the API endpoint
     * @return the HttpResponse of the server
     * @throws UnirestException if anything goes wrong with the request
     */
    HttpResponse<String> get(String url) throws UnirestException {
        return decorateRequest(Unirest.get(config.getApiUrl() + url)).asString();
    }

    /**
     * Performs a post request to the REST API.
     *
     * @param url the URL to the API endpoint
     * @return the HttpResponse of the server
     * @throws UnirestException if anything goes wrong with the request
     */
    HttpResponse<String> post(String url) throws UnirestException {
        return decorateRequest(Unirest.post(config.getApiUrl() + url)).asString();
    }

    /**
     * Decorates the request with the necessary authorization headers.
     *
     * @param request the request to decorate
     * @return the decorated request
     */
    private HttpRequest decorateRequest(HttpRequest request) {
        return request.header("accept", "*/*").header("Authorization", "Bearer " + config.getUser()
            .getToken()).header("X-Authorization", config.getPartnerId());
    }

    /**
     * Logs any unirest exception thrown during an API request
     *
     * @param ue                 the exception thrown
     * @param typeParameterClass the class of object the request tried to fetch
     * @param <T>                the type of object the request tried to fetch
     */
    private static <T> void handleException(Exception ue, Class<T> typeParameterClass) {
        log.error("Error fetching " + typeParameterClass.getSimpleName() + "s: " + ue.getMessage());
        StringWriter stringWriter = new StringWriter();
        ue.printStackTrace(new PrintWriter(stringWriter));
        log.debug(stringWriter.toString());

    }

    /**
     * T token for array list. Necessary to avoid type erasure problems.
     *
     * @param <T> the type of object you want to store
     */
    private static class ArrayListTypeToken<T> implements ParameterizedType {
        private final Class<?> wrapped;

        ArrayListTypeToken(Class<T> wrapped) {
            this.wrapped = wrapped;
        }

        public Type[] getActualTypeArguments() {
            return new Type[] {
                wrapped};
        }

        public Type getRawType() {
            return ArrayList.class;
        }

        public Type getOwnerType() {
            return null;
        }
    }
}
