package com.generated.app;

public class CounterModel {
    private long id;
    private String name;
    private String startDate; // YYYY-MM-DD
    private boolean isHistorical;

    public CounterModel(long id, String name, String startDate, boolean isHistorical) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.isHistorical = isHistorical;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isHistorical() {
        return isHistorical;
    }

    public void setHistorical(boolean historical) {
        isHistorical = historical;
    }
}
