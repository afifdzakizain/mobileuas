package com.uas.mobileuas;

public class HistoryItem {
    private String destination;
    private String date;
    private String status;

    public HistoryItem(String destination, String date, String status) {
        this.destination = destination;
        this.date = date;
        this.status = status;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}
