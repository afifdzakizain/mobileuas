package com.uas.mobileuas.ui.theme;

public class HotelItem {
    private int imageResId;
    private String name;
    private String location;
    private String price;

    public HotelItem(int imageResId, String name, String location, String price) {
        this.imageResId = imageResId;
        this.name = name;
        this.location = location;
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }
}
