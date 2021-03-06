package com.alphatrader.rest;

import javafx.beans.property.*;

/**
 * Represents a companies capabilities in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CompanyCapabilities {
    /**
     * True, if the company can request a banking license.
     */
    private final BooleanProperty bankReady = new SimpleBooleanProperty();

    /**
     * True, if the company is a bank.
     */
    private final BooleanProperty bank = new SimpleBooleanProperty();

    /**
     * The amount of central bank loans taken by this company.
     */
    private final DoubleProperty takenCentralBankLoans = new SimpleDoubleProperty();

    /**
     * True, if this company is a designated sponsor.
     */
    private final BooleanProperty designatedSponsor = new SimpleBooleanProperty();

    /**
     * The amount of reserves this company deposited.
     */
    private final DoubleProperty reserves = new SimpleDoubleProperty();

    /**
     * The maximum amount of central bank loans this company can request.
     */
    private final DoubleProperty maxCentralBankLoans = new SimpleDoubleProperty();

    /**
     * The company's net cash.
     */
    private final DoubleProperty netCash = new SimpleDoubleProperty();

    /**
     * @return true if the company may become a bank
     */
    @PublicAPI
    public Boolean isBankReady() {
        return bankReady.getValue();
    }

    /**
     * @return true if the company is a bank
     */
    @PublicAPI
    public Boolean isBank() {
        return bank.getValue();
    }

    /**
     * @return the amount of taken central bank loads
     */
    @PublicAPI
    public Double getTakenCentralBankLoans() {
        return takenCentralBankLoans.getValue();
    }

    /**
     * @return true, if the company is a designated sponsor
     */
    @PublicAPI
    public Boolean isDesignatedSponsor() {
        return designatedSponsor.getValue();
    }

    /**
     * @return the amount of deposited reserves
     */
    @PublicAPI
    public Double getReserves() {
        return reserves.getValue();
    }

    /**
     * @return the maximum central bank loans this company can take
     */
    @PublicAPI
    public Double getMaxCentralBankLoans() {
        return maxCentralBankLoans.getValue();
    }

    /**
     * @return the company's net cash
     */
    @PublicAPI
    public Double getNetCash() {
        return netCash.getValue();
    }

    /**
     * @return the bank ready property.
     */
    @PublicAPI
    public ReadOnlyBooleanProperty bankReadyProperty() {
        return bankReady;
    }

    /**
     * @return the bank property.
     */
    @PublicAPI
    public ReadOnlyBooleanProperty bankProperty() {
        return bank;
    }

    /**
     * @return the taken central loans property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty takenCentralBankLoansProperty() {
        return takenCentralBankLoans;
    }

    /**
     * @return the designated sponsor property.
     */
    @PublicAPI
    public ReadOnlyBooleanProperty designatedSponsorProperty() {
        return designatedSponsor;
    }

    /**
     * @return the reserves property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty reservesProperty() {
        return reserves;
    }

    /**
     * @return the max central bank loans property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty maxCentralBankLoansProperty() {
        return maxCentralBankLoans;
    }

    /**
     * @return the net cash property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty netCashProperty() {
        return netCash;
    }

    @Override
    public String toString() {
        return "CompanyCapabilities{"
            + "bankReady=" + bankReady.getValue()
            + ", bank=" + bank.getValue()
            + ", takenCentralBankLoans=" + takenCentralBankLoans.getValue()
            + ", designatedSponsor=" + designatedSponsor.getValue()
            + ", reserves=" + reserves.getValue()
            + ", maxCentralBankLoans=" + maxCentralBankLoans.getValue()
            + ", netCash=" + netCash.getValue()
            + '}';
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyCapabilities that = (CompanyCapabilities) o;

        if (bankReady.getValue() != null ? !bankReady.getValue().equals(that.bankReady.getValue())
            : that.bankReady.getValue() != null) {
            return false;
        }
        if (bank.getValue() != null ? !bank.getValue().equals(that.bank.getValue())
            : that.bank.getValue() != null) {
            return false;
        }
        if (takenCentralBankLoans.getValue() != null ? !takenCentralBankLoans.getValue().equals(
            that.takenCentralBankLoans.getValue()) : that.takenCentralBankLoans.getValue() != null) {
            return false;
        }
        if (designatedSponsor.getValue() != null ? !designatedSponsor.getValue().equals(
            that.designatedSponsor.getValue()) : that.designatedSponsor.getValue() != null) {
            return false;
        }
        if (reserves.getValue() != null ? !reserves.getValue().equals(that.reserves.getValue())
            : that.reserves.getValue() != null) {
            return false;
        }
        if (maxCentralBankLoans.getValue() != null ? !maxCentralBankLoans.getValue().equals(
            that.maxCentralBankLoans.getValue()) : that.maxCentralBankLoans.getValue() != null) {
            return false;
        }
        return netCash.getValue() != null ? netCash.getValue().equals(that.netCash.getValue())
            : that.netCash.getValue() == null;

    }

    @Override
    public int hashCode() {
        int result = bankReady.getValue() != null ? bankReady.getValue().hashCode() : 0;
        result = 31 * result + (bank.getValue() != null ? bank.getValue().hashCode() : 0);
        result = 31 * result + (takenCentralBankLoans.getValue() != null
            ? takenCentralBankLoans.getValue().hashCode() : 0);
        result = 31 * result + (designatedSponsor.getValue() != null
            ? designatedSponsor.getValue().hashCode() : 0);
        result = 31 * result + (reserves.getValue() != null ? reserves.getValue().hashCode() : 0);
        result = 31 * result + (maxCentralBankLoans.getValue() != null
            ? maxCentralBankLoans.getValue().hashCode() : 0);
        result = 31 * result + (netCash.getValue() != null ? netCash.getValue().hashCode() : 0);
        return result;
    }
}
