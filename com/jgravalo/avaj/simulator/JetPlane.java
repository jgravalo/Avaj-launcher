package com.jgravalo.avaj.simulator;

public class JetPlane extends Aircraft {

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String msg;

        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                move(0, 10, 2);
                msg = "Clear skies! Full throttle, let's gooo!";
                break;
            case "RAIN":
                move(0, 5, 0);
                msg = "It's raining. Better watch out for lightings.";
                break;
            case "FOG":
                move(0, 1, 0);
                msg = "Fog? No problem, that's why we have instruments.";
                break;
            case "SNOW":
                move(0, 0, -7);
                msg = "OMG! Winter is coming!";
                break;
            default:
                msg = "Houston, we have an undefined weather problem.";
        }

        report(msg);
    }
}
