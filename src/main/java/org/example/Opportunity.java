package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class Opportunity {
    Long originalEventTime;
    PositionUrlSegments positionUrlSegments;

    @Override
    public String toString() {
        return "Opportunity{" +
                "originalEventTime=" + originalEventTime +
                ", positionUrlSegments=" + positionUrlSegments +
                '}';
    }


    public void setOriginalEventTime(Long originalEventTime) {
        this.originalEventTime = originalEventTime;
    }

    public Long getOriginalEventTime() {
        return this.originalEventTime;
    }

    public PositionUrlSegments getPositionUrlSegments() {
        return positionUrlSegments;
    }

    public void setPositionUrlSegments(PositionUrlSegments positionUrlSegments) {
        this.positionUrlSegments = positionUrlSegments;
    }

}
