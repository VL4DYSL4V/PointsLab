package ui.guiElement;

import enums.UIColor;
import enums.UIFont;

import javax.swing.*;

public class AppButton extends JButton {

    public AppButton(String text) {
        setFont(UIFont.BUTTON_FONT.getFont());
        setText(text);
        setBackground(UIColor.BUTTON_BACKGROUND.getColor());
        setForeground(UIColor.FOREGROUND.getColor());
    }
}
