package com.jgravalo.avaj.simulator.weather;

import com.jgravalo.avaj.simulator.Flyable;
import com.jgravalo.avaj.simulator.Logger;
import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        if (observers.contains(p_flyable)) return;
        observers.add(p_flyable);
        Logger.log("Tower says: " + p_flyable + " registered to weather tower.");
    }

    public void unregister(Flyable p_flyable) {
        if (observers.remove(p_flyable)) {
            Logger.log("Tower says: " + p_flyable + " unregistered from weather tower.");
        }
    }

    protected void conditionChanged() {
        // Iterate over a copy so aircraft can unregister while being notified
        for (Flyable flyable : new ArrayList<>(observers)) {
            flyable.updateConditions();
        }
    }
}
