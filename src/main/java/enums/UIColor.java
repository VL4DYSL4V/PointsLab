package enums;

import java.awt.*;

public enum UIColor {
    FOREGROUND(new Color(187, 187, 187)),
    BACKGROUND(new Color(53, 53, 53)),
    BUTTON_BACKGROUND(new Color(70, 70, 70)),
    GREEN_THEME(new Color(0, 187, 0)),
    RED_THEME(new Color(187, 0, 0)),
    BRIGHT_THEME(new Color(255, 255, 255)),
    GREEN_THEME_LINE(new Color(255, 195, 30));
    private final Color color;

    UIColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
