package org.sonia.huma.data;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PositionUrlSegments {
    @JsonProperty("aw_0_ais.adBreakIndex")
    List<String> adBreakIndex;

    public List<String> getAdBreakIndex() {
        return adBreakIndex;
    }

    public void setAdBreakIndex(List<String> adBreakIndex) {
        this.adBreakIndex = adBreakIndex;
    }

    @Override
    public String toString() {
        return "PositionUrlSegments{" +
                "adBreakIndex=" + adBreakIndex +
                '}';
    }
}