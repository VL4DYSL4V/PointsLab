package enums;

public enum UIDimensionParameter {

    WIDTH(500), HEIGHT(640);

    private final int value;

    UIDimensionParameter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
