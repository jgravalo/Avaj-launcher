package com.jgravalo.avaj.simulator;

public class Balloon extends Aircraft {

    public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String msg;

        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                move(2, 0, 4);
                msg = "Let's enjoy the good weather and take some pics!";
                break;
            case "RAIN":
                move(0, 0, -5);
                msg = "Damn you rain! You messed up my balloon.";
                break;
            case "FOG":
                move(0, 0, -3);
                msg = "I can't see a thing! Are we in London or what?";
                break;
            case "SNOW":
                move(0, 0, -15);
                msg = "It's snowing. We're gonna crash.";
                break;
            default:
                msg = "What kind of weather is this?!";
        }

        report(msg);
    }
}
