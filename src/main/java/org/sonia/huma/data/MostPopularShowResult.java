package org.sonia.huma.data;

import java.util.List;

public class MostPopularShowResult {


    private List<String> showNames;
    private Integer max;

    public List<String> getShowNames() {
        return showNames;
    }

    public void setShowNames(List<String> showNames) {
        this.showNames = showNames;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
