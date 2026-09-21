package com.jgravalo.avaj.simulator;

public class Aircraft extends Flyable {
    private static final int MAX_HEIGHT = 100;

    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    @Override
    public void updateConditions() {
    }

    /**
     * Moves the aircraft, keeping height in the [0, 100] range.
     * Lands and unregisters from the tower when height reaches 0.
     */
    protected void move(int p_longitude, int p_latitude, int p_height) {
        int height = coordinates.getHeight() + p_height;
        if (height > MAX_HEIGHT) height = MAX_HEIGHT;
        if (height < 0) height = 0;

        coordinates = new Coordinates(
            coordinates.getLongitude() + p_longitude,
            coordinates.getLatitude() + p_latitude,
            height
        );
    }

    /**
     * Logs the weather message, then lands the aircraft if it reached the ground.
     */
    protected void report(String p_message) {
        Logger.log(this + ": " + p_message);
        if (coordinates.getHeight() <= 0) {
            Logger.log(this + " landing.");
            weatherTower.unregister(this);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "#" + name + "(" + id + ")";
    }
}
