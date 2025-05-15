package com.fetch.receipt_processor.model;

public class PointsResponse {
    private long points;

    public PointsResponse(long points) {
        this.points = points;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}