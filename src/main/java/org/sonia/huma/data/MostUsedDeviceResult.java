package org.sonia.huma.data;

public class MostUsedDeviceResult {
    String maxDeviceUsed="";
    Integer maxDevice=0;

    @Override
    public String toString() {
        return "MostUsedDeviceResult{" +
                "maxDeviceUsed='" + maxDeviceUsed + '\'' +
                '}';
    }

    public String getMaxDeviceUsed() {
        return maxDeviceUsed;
    }

    public void setMaxDeviceUsed(String maxDeviceUsed) {
        this.maxDeviceUsed = maxDeviceUsed;
    }

    public Integer getMaxDevice() {
        return maxDevice;
    }

    public void setMaxDevice(Integer maxDevice) {
        this.maxDevice = maxDevice;
    }
}
