package org.sonia.huma.data;

import java.util.Date;

public class Opportunity {
    public Long getOriginalEventTime() {
        return originalEventTime;
    }

    public void setOriginalEventTime(Long originalEventTime) {
        this.originalEventTime = originalEventTime;
    }

    Long originalEventTime;
    PositionUrlSegments positionUrlSegments;


    @Override
    public String toString() {
        return "Opportunity{" +
                "originalEventTime=" + originalEventTime +
                ", positionUrlSegments=" + positionUrlSegments +
                '}';
    }





    public PositionUrlSegments getPositionUrlSegments() {
        return positionUrlSegments;
    }

    public void setPositionUrlSegments(PositionUrlSegments positionUrlSegments) {
        this.positionUrlSegments = positionUrlSegments;
    }

}
