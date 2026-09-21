package com.jgravalo.avaj.simulator;

public class Helicopter extends Aircraft {

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String msg;

        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                move(10, 0, 2);
                msg = "This is hot. And I love it!";
                break;
            case "RAIN":
                move(5, 0, 0);
                msg = "Rain on my rotors, but I keep spinning!";
                break;
            case "FOG":
                move(1, 0, 0);
                msg = "Hovering blind through the fog... just another Tuesday.";
                break;
            case "SNOW":
                move(0, 0, -12);
                msg = "My rotor is going to freeze!";
                break;
            default:
                msg = "Unknown conditions. Hovering nervously.";
        }

        report(msg);
    }
}
