package enums;

public enum CoordinateConstraints {

    MAX_X(9), MAX_Y(9), MIN_X(0),MIN_Y(0), MIDDLE_X((MAX_X.value + MIN_X.value) / 2),
    MIDDLE_Y((MAX_Y.value + MIN_Y.value) / 2), X_COORDS_AMOUNT(MAX_X.value + 1), Y_COORDS_AMOUNT(MAX_Y.value + 1);
    private final int value;

    CoordinateConstraints(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
