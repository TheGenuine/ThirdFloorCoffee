package de.reneruck.thirdfeCoffee;

import android.location.Location;

/**
 * Created by Rene on 16/08/13.
 */
public class Place {

    private int id;
    private String name;
    private Location location;
    private String logo;
    private String web;
    private String twitter;
    private String foursquare;
    private String googlePlaces;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFoursquare() {
        return foursquare;
    }

    public void setFoursquare(String foursquare) {
        this.foursquare = foursquare;
    }

    public String getGooglePlaces() {
        return googlePlaces;
    }

    public void setGooglePlaces(String googlePlaces) {
        this.googlePlaces = googlePlaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
