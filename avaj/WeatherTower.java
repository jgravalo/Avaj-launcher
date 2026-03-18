package avaj;

import java.util.ArrayList;
import java.util.List;

public class WeatherTower extends Tower {

    private static WeatherTower instance;
    private List<Weatherable> observers = new ArrayList<>();

    private static final String[] WEATHERS = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherTower() {}

    public static WeatherTower getInstance() {
        if (instance == null) {
            instance = new WeatherTower();
        }
        return instance;
    }

    /**
     * Generates weather based on coordinates.
     * Uses a simple deterministic formula so the same point always gets the same weather.
     */
    public String getWeather(Coordinates coords) {
        int index = (coords.longitude * 3 + coords.latitude * 7 + coords.height * 13) % 4;
        if (index < 0) index += 4;
        return WEATHERS[index];
    }

    /**
     * Registers an aircraft to receive weather updates.
     */
    public void register(Weatherable aircraft) {
        observers.add(aircraft);
    }

    /**
     * Unregisters an aircraft from weather updates.
     */
    public void unregister(Weatherable aircraft) {
        observers.remove(aircraft);
    }

    /**
     * Triggers a weather change event — notifies all registered aircraft.
     */
    public void changeWeather() {
        // Iterate over a copy to allow safe unregistration during iteration
        List<Weatherable> copy = new ArrayList<>(observers);
        for (Weatherable w : copy) {
            w.updateConditions();
        }
    }
}
