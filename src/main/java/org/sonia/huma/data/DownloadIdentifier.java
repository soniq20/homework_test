package org.sonia.huma.data;

public class DownloadIdentifier {
    String client;

    Integer publisher;

    String podcastId;
    String showId;
    String downloadId;
    String episodeId;

    public void setClient(String client) {
        this.client = client;
    }

    public void setPublisher(Integer publisher) {
        this.publisher = publisher;
    }

    public void setPodcastId(String podcastId) {
        this.podcastId = podcastId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public void setDownloadId(String downloadId) {
        this.downloadId = downloadId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getClient() {
        return client;
    }

    public Integer getPublisher() {
        return publisher;
    }

    public String getPodcastId() {
        return podcastId;
    }

    public String getShowId() {
        return showId;
    }

    public String getDownloadId() {
        return downloadId;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    @Override
    public String toString() {
        return "DownloadIdentifier{" +
                "client='" + client + '\'' +
                ", publisher=" + publisher +
                ", podcastId='" + podcastId + '\'' +
                ", showId='" + showId + '\'' +
                ", downloadId='" + downloadId + '\'' +
                ", episodeId='" + episodeId + '\'' +
                '}';
    }
}
