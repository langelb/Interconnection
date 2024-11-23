package model.data_structures;

public class Country implements Comparable<Country> {
    private String countryName;
    private String capitalName;
    private double latitude;
    private double longitude;
    private String code;
    private String continentName;
    private float population;
    private double users;
    private double distlan;

    public Country(String countryName, String capitalName, double latitude, double longitude, String code, String continentName, float population, double users) {
        this.countryName = countryName;
        this.capitalName = capitalName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.code = code;
        this.continentName = continentName;
        this.population = population;
        this.users = users;
        this.distlan = 0;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getPopulation() {
        return population;
    }

    public double getUsers() {
        return users;
    }

    @Override
    public int compareTo(Country o) {
        return this.countryName.compareTo(o.countryName);
    }
}
