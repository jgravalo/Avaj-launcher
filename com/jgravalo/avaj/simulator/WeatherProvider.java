package com.jgravalo.avaj.simulator;

public class WeatherProvider {
    private static final WeatherProvider instance = new WeatherProvider();

    private String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getInstance() {
        return instance;
    }

    /**
     * Deterministic weather: the same point always gets the same weather.
     */
    public String getCurrentWeather(Coordinates p_coordinates) {
        int index = (p_coordinates.getLongitude() * 3
                   + p_coordinates.getLatitude() * 7
                   + p_coordinates.getHeight() * 13) % weather.length;
        if (index < 0) index += weather.length;
        return weather[index];
    }
}
