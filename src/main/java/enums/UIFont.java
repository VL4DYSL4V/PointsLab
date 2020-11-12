package enums;

import java.awt.*;

public enum UIFont {
    BUTTON_FONT(new Font(Font.SANS_SERIF, Font.BOLD, UIDimensionParameter.WIDTH.getValue() / 20)),
    TEXT_FONT(new Font(Font.SANS_SERIF, Font.BOLD, UIDimensionParameter.WIDTH.getValue() / 20));;
    private final Font font;

    UIFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}
