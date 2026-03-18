package avaj;

public class Coordinates {
    public int longitude;
    public int latitude;
    public int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    @Override
    public String toString() {
        return "(" + longitude + ", " + latitude + ", " + height + ")";
    }
}
