package com.alphatrader.rest.util;

import com.google.gson.Gson;
import org.hildan.fxgson.FxGsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

/**
 * Wrapper class for our personalized version of the gson builder.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public final class PropertyGson {
    /**
     * @return the customized gson builder for this class.
     */
    @NotNull
    public Gson create() {
        return new FxGsonBuilder()
            .acceptNullPrimitives()
            .builder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer())
            .create();
    }
}
