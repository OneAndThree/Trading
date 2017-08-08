package com.citi.training.model;

public class HistoryEquityData {
    private String prefix;

    private String symble;

    private String period;

    private String data;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    public String getSymble() {
        return symble;
    }

    public void setSymble(String symble) {
        this.symble = symble == null ? null : symble.trim();
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}