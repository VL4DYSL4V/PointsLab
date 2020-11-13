package enums;

public enum AlgorithmChoice {
    FORCHUN("Forchun"),
    DELONE("Delone"),
    KIRKPATRIK("Kirkpatrik"),
    JARVIS("Jarvis"),
    GRAHAM("Graham"),
    RECURSIVE("Recursive");
    private final String value;

    AlgorithmChoice(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
