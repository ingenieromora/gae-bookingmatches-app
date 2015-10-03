package org.utn.edu.ar.util;

public class Coordinates {

    private Double latitude;
    private Double longitude;

    public Coordinates(){}

    public Coordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }

    @Override
    public int hashCode() { return latitude.hashCode() ^ longitude.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinates)) return false;
        Coordinates pairo = (Coordinates) o;
        return this.latitude.equals(pairo.getLatitude()) &&
                this.longitude.equals(pairo.getLongitude());
    }

}
