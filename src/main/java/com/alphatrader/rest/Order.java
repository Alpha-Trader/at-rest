package com.alphatrader.rest;

import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents an order in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Order {
    /**
     * The date and time the order was created
     */
    private final ObjectProperty<ZonedDateTime> creationDate = new SimpleObjectProperty<>();

    /**
     * Name of Security
     */
    private final ObjectProperty<Listing> listing = new SimpleObjectProperty<>();

    /**
     * Type of Security
     */
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>();

    /**
     * Unique securityIdentifier of security
     */
    private final StringProperty securityIdentifier = new SimpleStringProperty();

    /**
     * Number of shares entailed in Order
     */
    private final LongProperty numberOfShares = new SimpleLongProperty();

    /**
     * The name of the counter party.
     */
    private final StringProperty counterPartyName = new SimpleStringProperty();

    /**
     * The security account id of the counter party.
     */
    private final StringProperty counterParty = new SimpleStringProperty();

    /**
     * The type of action this order represents.
     */
    private final ObjectProperty<Action> action = new SimpleObjectProperty<>();

    /**
     * The committed cash of this order.
     */
    private final DoubleProperty committedCash = new SimpleDoubleProperty();

    /**
     * The price.
     */
    private final DoubleProperty price = new SimpleDoubleProperty();

    /**
     * The name of the owner.
     */
    private final StringProperty ownerName = new SimpleStringProperty();

    /**
     * The security account identifier of the owner.
     */
    private final StringProperty owner = new SimpleStringProperty();

    /**
     * The unique id of the order.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * Fetches all otc orders of the given company.
     *
     * @param securitiesAccountId the securities account id
     * @return all otc orders
     */
    @NotNull
    public static List<Order> getOtcOrders(String securitiesAccountId) {
        return getMultipleOrdersFromApi("securityorders/counterparty/" + securitiesAccountId);
    }

    /**
     * Fetches all otc orders of the given company.
     *
     * @param company the company
     * @return all otc orders
     */
    @NotNull
    public static List<Order> getOtcOrders(Company company) {
        return getOtcOrders(company.getSecuritiesAccountId());
    }

    /**
     * Fetches all orders concerning the given security identifier.
     *
     * @param securityIdentifier the security identifier
     * @return all orders concerning the given identifier
     */
    @NotNull
    public static List<Order> getOrdersForCompany(String securityIdentifier) {
        return getMultipleOrdersFromApi("orderlist/" + securityIdentifier);
    }

    /**
     * Fetches all orders concerning the given security identifier.
     *
     * @param company the company being traded
     * @return all orders concerning the given identifier
     */
    @NotNull
    public static List<Order> getOrdersForCompany(Company company) {
        return getOrdersForCompany(company.getListing().getSecurityIdentifier());
    }

    /**
     * Fetches all orders by the provided company.
     *
     * @param securitiesAccountId the securities account id
     * @return the list of orders
     */
    @NotNull
    public static List<Order> getOrders(String securitiesAccountId) {
        return getMultipleOrdersFromApi("securityorders/securitiesaccount/" + securitiesAccountId);
    }

    /**
     * Fetches all orders by the provided company.
     *
     * @param company the company looking to buy / sell
     * @return the list of orders
     */
    @NotNull
    public static List<Order> getOrders(Company company) {
        return getOrders(company.getSecuritiesAccountId());
    }

    /**
     * Generalized api request function.
     *
     * @param suffix the api suffix for the specific request
     * @return the requested list of orders
     */
    @NotNull
    private static List<Order> getMultipleOrdersFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Order.class, "/api/" + suffix);
    }

    /**
     * Fetches the order with the given id from the API.
     *
     * @param id the id of the order
     * @return the requested order
     */
    @Nullable
    public static Order getById(String id) {
        return Http.getSingleObjectFromApi(Order.class, "/api/securityorders/" + id);
    }

    /**
     * @return the name
     */
    public String getName() {
        return getListing().getName();
    }

    /**
     * return the date of creation
     */
    public ZonedDateTime getCreationDate() {
        return creationDate.getValue();
    }

    /**
     * @return the listing
     */
    public Listing getListing() {
        return listing.getValue();
    }

    /**
     * @return the type of security
     */
    public Type getType() {
        return type.getValue();
    }

    /**
     * @return the security identifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier.getValue();
    }

    /**
     * @return the number of shares
     */
    public Long getNumberOfShares() {
        return numberOfShares.getValue();
    }

    /**
     * @return the counter party name
     */
    public String getCounterPartyName() {
        return counterPartyName.getValue();
    }

    /**
     * @return the counter party
     */
    public String getCounterParty() {
        return counterParty.getValue();
    }

    /**
     * @return the action
     */
    public Action getAction() {
        return action.getValue();
    }

    /**
     * @return the committed cash
     */
    public Double getCommittedCash() {
        return committedCash.getValue();
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price.getValue();
    }

    /**
     * @return the owner name
     */
    public String getOwnerName() {
        return ownerName.getValue();
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner.getValue();
    }

    /**
     * @return the unique id
     */
    public String getId() {
        return id.getValue();
    }

    @Override
    public String toString() {
        return "Order{"
            + "creationDate=" + creationDate.getValue()
            + ", listing=" + listing.getValue()
            + ", type=" + type.getValue()
            + ", securityIdentifier='" + securityIdentifier.getValue() + '\''
            + ", numberOfShares=" + numberOfShares.getValue()
            + ", counterPartyName='" + counterPartyName.getValue() + '\''
            + ", counterParty='" + counterParty.getValue() + '\''
            + ", action=" + action.getValue()
            + ", committedCash=" + committedCash.getValue()
            + ", price=" + price.getValue()
            + ", ownerName='" + ownerName.getValue() + '\''
            + ", owner='" + owner.getValue() + '\''
            + ", id='" + id.getValue() + '\''
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return id.getValue() != null ? id.getValue().equals(order.id.getValue())
            : order.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }

    /**
     * The order type.
     */
    public enum Type {
        LIMIT,
        MARKET
    }

    /**
     * The requested action.
     */
    public enum Action {
        BUY,
        SELL
    }
}


    
    
    



