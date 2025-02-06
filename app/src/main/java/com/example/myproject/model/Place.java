package com.example.myproject.model;


import java.util.List;

public class Place {
    private String id;
    private String name;
    private String startingPrice;
    private String country;
    private String image;
    private List<String> additionalImages;
    private String availableFlight;
    private String description;

    public Place() {
        // Required for Firebase
    }

    public Place(String id, String name, String startingPrice, String country, String image,
                 List<String> additionalImages, String availableFlight, String description) {
        this.id = id;
        this.name = name;
        this.startingPrice = startingPrice;
        this.country = country;
        this.image = image;
        this.additionalImages = additionalImages;
        this.availableFlight = availableFlight;
        this.description = description;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getStartingPrice() { return startingPrice; }
    public String getCountry() { return country; }
    public String getImage() { return image; }
    public List<String> getAdditionalImages() { return additionalImages; }
    public String getAvailableFlight() { return availableFlight; }
    public String getDescription() { return description; }
}

