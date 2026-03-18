package avaj;

public class Balloon extends Aircraft implements Weatherable {

    protected Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = WeatherTower.getInstance().getWeather(coordinates);
        String msg;

        switch (weather) {
            case "SUN":
                coordinates.longitude += 2;
                coordinates.height = clampHeight(coordinates.height + 4);
                msg = "Let's enjoy the good weather and take some pics!";
                break;
            case "RAIN":
                coordinates.height = clampHeight(coordinates.height - 5);
                msg = "Damn you rain! You messed up my balloon.";
                break;
            case "FOG":
                coordinates.height = clampHeight(coordinates.height - 3);
                msg = "I can't see a thing! Are we in London or what?";
                break;
            case "SNOW":
                coordinates.height = clampHeight(coordinates.height - 15);
                msg = "It's snowing. We're gonna crash.";
                break;
            default:
                msg = "What kind of weather is this?!";
        }

        logMessage("Balloon#" + name + "(" + id + "): " + msg);
        checkLanding();
    }

    private void checkLanding() {
        if (coordinates.height <= 0) {
            coordinates.height = 0;
            logMessage("Balloon#" + name + "(" + id + ") landing.");
            WeatherTower.getInstance().unregister(this);
            logMessage("Tower says: Balloon#" + name + "(" + id + ") unregistered from weather tower.");
        }
    }
}
