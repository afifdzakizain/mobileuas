package com.uas.mobileuas.ui.theme;

public class RecommendationItem {
    public int title;
    public int imageRes;
    private int imageResId;
    private String destinationName;
    private String description;
    private String price;

    public RecommendationItem(int imageResId, String destinationName, String description, String price) {
        this.imageResId = imageResId;
        this.destinationName = destinationName;
        this.description = description;
        this.price = price;
    }

    public int getImageResId() { return imageResId; }
    public String getDestinationName() { return destinationName; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
}
