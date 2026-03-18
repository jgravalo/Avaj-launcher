package avaj;

public abstract class Aircraft extends Tower {

    private static int idCounter = 1;

    protected int id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = idCounter++;
        this.name = name;
        this.coordinates = coordinates;
    }

    /**
     * Clamps height to valid range [0, 100].
     */
    protected int clampHeight(int height) {
        if (height > 100) return 100;
        if (height < 0)   return 0;
        return height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
