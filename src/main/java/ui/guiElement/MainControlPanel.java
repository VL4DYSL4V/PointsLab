package ui.guiElement;

import enums.UIColor;
import enums.UIDimensionParameter;

import javax.swing.*;

public class MainControlPanel extends JPanel {

    public MainControlPanel(){
        setBackground(UIColor.BACKGROUND.getColor());
        setForeground(UIColor.FOREGROUND.getColor());
        setSize(UIDimensionParameter.WIDTH.getValue(),
                Math.abs(UIDimensionParameter.WIDTH.getValue() - UIDimensionParameter.HEIGHT.getValue()));
        setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
