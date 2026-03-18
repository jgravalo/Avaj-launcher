package avaj;

public class AircraftFactory {

    private AircraftFactory() {}

    /**
     * Creates an aircraft of the given type.
     * Factory pattern: centralizes object creation logic.
     *
     * @param type      "Balloon", "JetPlane" or "Helicopter"
     * @param name      name of the aircraft
     * @param longitude starting longitude (positive integer)
     * @param latitude  starting latitude (positive integer)
     * @param height    starting height (0-100)
     * @return the created Aircraft instance
     * @throws IllegalArgumentException if type is unknown
     */
    public static Aircraft newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coords = new Coordinates(longitude, latitude, Math.min(height, 100));
        switch (type) {
            case "Balloon":
                return new Balloon(name, coords);
            case "JetPlane":
                return new JetPlane(name, coords);
            case "Helicopter":
                return new Helicopter(name, coords);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + type);
        }
    }
}
