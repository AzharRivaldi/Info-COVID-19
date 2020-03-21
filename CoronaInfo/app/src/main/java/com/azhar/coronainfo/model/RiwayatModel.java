package com.azhar.coronainfo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Azhar Rivaldi on 20/03/2020.
 */

public class RiwayatModel {

    @SerializedName("countryRegion")
    private String countryRegion;
    @SerializedName("lastUpdate")
    private String lastUpdate;
    @SerializedName("confirmed")
    private String confirmed;
    @SerializedName("deaths")
    private String deaths;
    @SerializedName("recovered")
    private String recovered;

    public RiwayatModel(String countryRegion, String lastUpdate, String confirmed, String deaths, String recovered) {
        this.countryRegion = countryRegion;
        this.lastUpdate = lastUpdate;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}
