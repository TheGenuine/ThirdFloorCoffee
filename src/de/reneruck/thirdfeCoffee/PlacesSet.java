package de.reneruck.thirdfeCoffee;

import java.util.List;

/**
 * Created by Rene on 16/08/13.
 */
public class PlacesSet {
    private long lastUpdate;
    private List<Place> places;

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
