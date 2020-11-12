package ui.guiElement;

import enums.UIColor;
import enums.UIDimensionParameter;

import javax.swing.*;
import java.awt.*;

public class AppButton extends JButton {

    public AppButton(String text) {
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, UIDimensionParameter.WIDTH.getValue() / 25));
        setText(text);
        setBackground(UIColor.BUTTON_BACKGROUND.getColor());
        setForeground(UIColor.FOREGROUND.getColor());
    }
}
