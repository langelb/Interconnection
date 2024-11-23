package model.data_structures;

public class Landing implements Comparable<Landing> {
    private String landingId;
    private String id;
    private String name;
    private String country;
    private double latitude;
    private double longitude;

    public Landing(String landingId, String id, String name, String country, double latitude, double longitude) {
        this.landingId = landingId;
        this.id = id;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLandingId() {
        return landingId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int compareTo(Landing o) {
        return this.name.compareTo(o.name);
    }
}
