package avaj;

public class Helicopter extends Aircraft implements Weatherable {

    protected Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = WeatherTower.getInstance().getWeather(coordinates);
        String msg;

        switch (weather) {
            case "SUN":
                coordinates.longitude += 10;
                coordinates.height = clampHeight(coordinates.height + 2);
                msg = "This is hot. And I love it!";
                break;
            case "RAIN":
                coordinates.longitude += 5;
                msg = "Rain on my rotors, but I keep spinning!";
                break;
            case "FOG":
                coordinates.longitude += 1;
                msg = "Hovering blind through the fog... just another Tuesday.";
                break;
            case "SNOW":
                coordinates.height = clampHeight(coordinates.height - 12);
                msg = "My rotor is going to freeze!";
                break;
            default:
                msg = "Unknown conditions. Hovering nervously.";
        }

        logMessage("Helicopter#" + name + "(" + id + "): " + msg);
        checkLanding();
    }

    private void checkLanding() {
        if (coordinates.height <= 0) {
            coordinates.height = 0;
            logMessage("Helicopter#" + name + "(" + id + ") landing.");
            WeatherTower.getInstance().unregister(this);
            logMessage("Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower.");
        }
    }
}
