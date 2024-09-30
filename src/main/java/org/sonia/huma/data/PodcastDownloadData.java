package org.sonia.huma.data;

import org.sonia.huma.data.DownloadIdentifier;
import org.sonia.huma.data.Opportunity;

import java.util.List;

public class PodcastDownloadData {
    DownloadIdentifier downloadIdentifier;
    List<Opportunity> opportunities;
    Integer agency;
    String country;
    String city;
    String listenerId;
    String deviceType;

    @Override
    public String toString() {
        return "PodcastDownloadData{" +
                "downloadIdentifier=" + downloadIdentifier +
                ", opportunities=" + opportunities +
                ", agency=" + agency +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", listenerId='" + listenerId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                '}';
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public DownloadIdentifier getDownloadIdentifier() {
        return downloadIdentifier;
    }

    public void setDownloadIdentifier(DownloadIdentifier downloadIdentifier) {
        this.downloadIdentifier = downloadIdentifier;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setListenerId(String listenerId) {
        this.listenerId = listenerId;
    }

    public Integer getAgency() {
        return agency;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getListenerId() {
        return listenerId;
    }

    public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }
}
