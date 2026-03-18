package avaj;

public class JetPlane extends Aircraft implements Weatherable {

    protected JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = WeatherTower.getInstance().getWeather(coordinates);
        String msg;

        switch (weather) {
            case "SUN":
                coordinates.latitude += 10;
                coordinates.height = clampHeight(coordinates.height + 2);
                msg = "Clear skies! Full throttle, let's gooo!";
                break;
            case "RAIN":
                coordinates.latitude += 5;
                msg = "It's raining. Better watch out for lightings.";
                break;
            case "FOG":
                coordinates.latitude += 1;
                msg = "Fog? No problem, that's why we have instruments.";
                break;
            case "SNOW":
                coordinates.height = clampHeight(coordinates.height - 7);
                msg = "OMG! Winter is coming!";
                break;
            default:
                msg = "Houston, we have an undefined weather problem.";
        }

        logMessage("JetPlane#" + name + "(" + id + "): " + msg);
        checkLanding();
    }

    private void checkLanding() {
        if (coordinates.height <= 0) {
            coordinates.height = 0;
            logMessage("JetPlane#" + name + "(" + id + ") landing.");
            WeatherTower.getInstance().unregister(this);
            logMessage("Tower says: JetPlane#" + name + "(" + id + ") unregistered from weather tower.");
        }
    }
}
