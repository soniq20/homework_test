package org.sonia.huma.data;

import java.util.Date;

public class WeeklyShowsResult {
    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    Long date;

    String showId= "";

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }


}
