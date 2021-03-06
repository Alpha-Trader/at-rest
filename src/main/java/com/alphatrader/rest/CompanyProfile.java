package com.alphatrader.rest;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a company profile in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CompanyProfile {
    /**
     * The ceo employment agreement.
     */
    private final ObjectProperty<EmploymentAgreement> ceoEmploymentAgreement
        = new SimpleObjectProperty<>();

    /**
     * The company capabilities.
     */
    private final ObjectProperty<CompanyCapabilities> companyCapabilities = new SimpleObjectProperty<>();

    /**
     * The issued bonds of this company.
     */
    private final ObservableList<Bond> issuedBonds = new SimpleListProperty<>();

    /**
     * The last trades made by this company.
     */
    private final ObservableList<SecurityOrderLog> lastTrades = new SimpleListProperty<>();

    /**
     * The sponsored listings.
     */
    private final ObservableList<SecuritySponsorship> sponsoredListings = new SimpleListProperty<>();

    /**
     * The current price spread.
     */
    private final ObjectProperty<PriceSpread> currentSpread = new SimpleObjectProperty<>();

    /**
     * The designated sponsors of this company.
     */
    private final ObservableList<SecuritySponsorship> designatedSponsors = new SimpleListProperty<>();

    /**
     * The last order of this company.
     */
    private final ObjectProperty<SecurityOrderLog> lastOrderLogEntry = new SimpleObjectProperty<>();

    /**
     * The last trading price of this company.
     */
    private final ObjectProperty<LastPrice> lastPrice = new SimpleObjectProperty<>();

    /**
     * The number of outstanding shares.
     */
    private final LongProperty outstandingShares = new SimpleLongProperty();

    /**
     * The trading prices of the company for the last 14 days.
     */
    private final LastPrice[] prices14d = new LastPrice[0];

    /**
     * The current market cap for the company.
     */
    private final DoubleProperty marketCap = new SimpleDoubleProperty();

    /**
     * The company's securities account id.
     */
    private final StringProperty securitiesAccountId = new SimpleStringProperty();

    /**
     * The company's bank account.
     */
    private final ObjectProperty<BankAccount> bankAccount = new SimpleObjectProperty<>();

    /**
     * The URL of the company logo.
     */
    private final ObjectProperty<URL> logoUrl = new SimpleObjectProperty<>();

    /**
     * The CEO of the company.
     */
    private final ObjectProperty<User> ceo = new SimpleObjectProperty<>();

    /**
     * The company listing.
     */
    private final ObjectProperty<Listing> listing = new SimpleObjectProperty<>();

    /**
     * The company name.
     */
    private final StringProperty name = new SimpleStringProperty();

    /**
     * The unique id.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * Returns the profile of the given company.
     *
     * @param company the company
     * @return the CompanyProfile requested or null of not found
     */
    @PublicAPI
    @Nullable
    public static CompanyProfile getByCompany(Company company) {
        return getByCompany(company.getId());
    }

    /**
     * Returns the profile of the given company.
     *
     * @param companyId the company id
     * @return the CompanyProfile requested or null of not found
     */
    @PublicAPI
    @Nullable
    public static CompanyProfile getByCompany(String companyId) {
        return Http.getSingleObjectFromApi(CompanyProfile.class, "/api/companyprofiles/" + companyId);
    }

    /**
     * @return the current CEO employment agreement
     */
    @PublicAPI
    public EmploymentAgreement getCeoEmploymentAgreement() {
        return ceoEmploymentAgreement.getValue();
    }

    /**
     * @return the company capabilities
     */
    @PublicAPI
    public CompanyCapabilities getCompanyCapabilities() {
        return companyCapabilities.getValue();
    }

    /**
     * @return the bonds issued by this company
     */
    @PublicAPI
    public ObservableList<Bond> getIssuedBonds() {
        return issuedBonds;
    }

    /**
     * @return the last trades
     */
    @PublicAPI
    public ObservableList<SecurityOrderLog> getLastTrades() {
        return lastTrades;
    }

    /**
     * @return the sponsored listings
     */
    @PublicAPI
    public ObservableList<SecuritySponsorship> getSponsoredListings() {
        return sponsoredListings;
    }

    /**
     * @return the current price spread
     */
    @PublicAPI
    public PriceSpread getCurrentSpread() {
        return currentSpread.getValue();
    }

    /**
     * @return the designated sponsors of this company
     */
    @PublicAPI
    public ObservableList<SecuritySponsorship> getDesignatedSponsors() {
        return designatedSponsors;
    }

    /**
     * @return the last order of this company
     */
    @PublicAPI
    public SecurityOrderLog getLastOrderLogEntry() {
        return lastOrderLogEntry.getValue();
    }

    /**
     * @return the last price of this company
     */
    @PublicAPI
    public LastPrice getLastPrice() {
        return lastPrice.getValue();
    }

    /**
     * @return the number of outstanding shares
     */
    @PublicAPI
    public Long getOutstandingShares() {
        return outstandingShares.getValue();
    }

    /**
     * @return the prices of this company for the last 14 days
     */
    @PublicAPI
    public List<LastPrice> getPrices14d() {
        return Arrays.asList(prices14d);
    }

    /**
     * @return the market cap
     */
    @PublicAPI
    public Double getMarketCap() {
        return marketCap.getValue();
    }

    /**
     * @return the securities account id
     */
    @PublicAPI
    public String getSecuritiesAccountId() {
        return securitiesAccountId.getValue();
    }

    /**
     * @return the bank account
     */
    @PublicAPI
    public BankAccount getBankAccount() {
        return bankAccount.getValue();
    }

    /**
     * @return the company logo URL
     */
    @PublicAPI
    public URL getLogoUrl() {
        return logoUrl.getValue();
    }

    /**
     * @return the current CEO
     */
    @PublicAPI
    public User getCeo() {
        return ceo.getValue();
    }

    /**
     * @return the company listing
     */
    @PublicAPI
    public Listing getListing() {
        return listing.getValue();
    }

    /**
     * @return the company name
     */
    @PublicAPI
    public String getName() {
        return name.getValue();
    }

    /**
     * @return the company id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the ceo employment agreement property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<EmploymentAgreement> ceoEmploymentAgreementProperty() {
        return ceoEmploymentAgreement;
    }

    /**
     * @return the company capabilities property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<CompanyCapabilities> companyCapabilitiesProperty() {
        return companyCapabilities;
    }

    /**
     * @return the current spread property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<PriceSpread> currentSpreadProperty() {
        return currentSpread;
    }

    /**
     * @return the last order log entry property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<SecurityOrderLog> lastOrderLogEntryProperty() {
        return lastOrderLogEntry;
    }

    /**
     * @return the last price property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<LastPrice> lastPriceProperty() {
        return lastPrice;
    }

    /**
     * @return the outstanding shares property.
     */
    @PublicAPI
    public ReadOnlyLongProperty outstandingSharesProperty() {
        return outstandingShares;
    }

    /**
     * @return the market cap property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty marketCapProperty() {
        return marketCap;
    }

    /**
     * @return the securities account id property.
     */
    @PublicAPI
    public ReadOnlyStringProperty securitiesAccountIdProperty() {
        return securitiesAccountId;
    }

    /**
     * @return the bank account property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<BankAccount> bankAccountProperty() {
        return bankAccount;
    }

    /**
     * @return the logo url property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<URL> logoUrlProperty() {
        return logoUrl;
    }

    /**
     * @return the ceo property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<User> ceoProperty() {
        return ceo;
    }

    /**
     * @return the listing property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Listing> listingProperty() {
        return listing;
    }

    /**
     * @return the name property.
     */
    @PublicAPI
    public ReadOnlyStringProperty nameProperty() {
        return name;
    }

    /**
     * @return the id property.
     */
    @PublicAPI
    public ReadOnlyStringProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "CompanyProfile{"
            + "ceoEmploymentAgreement=" + ceoEmploymentAgreement.getValue()
            + ", companyCapabilities=" + companyCapabilities.getValue()
            + ", issuedBonds=" + issuedBonds.toString()
            + ", lastTrades=" + lastTrades.toString()
            + ", sponsoredListings=" + sponsoredListings.toString()
            + ", currentSpread=" + currentSpread.getValue()
            + ", designatedSponsors=" + designatedSponsors.toString()
            + ", lastOrderLogEntry=" + lastOrderLogEntry.getValue()
            + ", lastPrice=" + lastPrice.getValue()
            + ", outstandingShares=" + outstandingShares.getValue()
            + ", prices14d=" + Arrays.toString(prices14d)
            + ", marketCap=" + marketCap.getValue()
            + ", securitiesAccountId='" + securitiesAccountId.getValue() + '\''
            + ", bankAccount=" + bankAccount.getValue()
            + ", logoUrl=" + logoUrl.getValue()
            + ", ceo=" + ceo.getValue()
            + ", listing=" + listing.getValue()
            + ", name='" + name.getValue() + '\''
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

        CompanyProfile that = (CompanyProfile) o;

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;
    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
