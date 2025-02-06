package com.example.myproject.model;


import java.util.List;

public class TopPlaces {

    String placeName;
    String countryName;
    String price;
    Integer imageUrl;
    List<Integer> additionalImages;

    public Integer getImageUrl() {
        return imageUrl;
    }

    public List<Integer> getAdditionalImages() { return additionalImages;}
    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TopPlaces(String placeName, String countryName, String price, Integer imageUrl) {
        this.placeName = placeName;
        this.countryName = countryName;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public TopPlaces(String placeName, String countryName, String price, Integer imageUrl, List<Integer> additionalImages) {
        this.placeName = placeName;
        this.countryName = countryName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.additionalImages = additionalImages;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}