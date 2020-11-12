package ui.guiElement;

import enums.UIColor;
import enums.UIFont;

import javax.swing.*;

public class MainJComboBox extends JComboBox<String> {

    public MainJComboBox(String[] items){
        super(items);
        setFont(UIFont.TEXT_FONT.getFont());
        setBackground(UIColor.BUTTON_BACKGROUND.getColor());
        setForeground(UIColor.FOREGROUND.getColor());
    }

}
