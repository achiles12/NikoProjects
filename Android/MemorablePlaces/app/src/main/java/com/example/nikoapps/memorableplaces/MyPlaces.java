package com.example.nikoapps.memorableplaces;

public class MyPlaces {

    String nameOfPlace;
    Double lat;
    Double lng;

    public MyPlaces(String nameOfPlace, Double lat, Double lng) {
        this.nameOfPlace = nameOfPlace;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNameOfPlace() {
        return nameOfPlace;
    }

    public void setNameOfPlace(String nameOfPlace) {
        this.nameOfPlace = nameOfPlace;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
