package rain;

public enum Direction {
    LEFT(-1), RIGHT(1);

    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    private final int value;
}
