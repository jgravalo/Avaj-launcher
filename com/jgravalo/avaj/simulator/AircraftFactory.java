package com.jgravalo.avaj.simulator;

public class AircraftFactory {
    private static final AircraftFactory instance = new AircraftFactory();

    private long idCounter = 0;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        return instance;
    }

    public static boolean isValidType(String p_type) {
        return p_type.equals("Balloon") || p_type.equals("JetPlane") || p_type.equals("Helicopter");
    }

    /**
     * Creates an aircraft of the given type with a new unique id.
     *
     * @throws IllegalArgumentException if the type is unknown
     */
    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        switch (p_type) {
            case "Balloon":
                return new Balloon(++idCounter, p_name, p_coordinates);
            case "JetPlane":
                return new JetPlane(++idCounter, p_name, p_coordinates);
            case "Helicopter":
                return new Helicopter(++idCounter, p_name, p_coordinates);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + p_type);
        }
    }
}
