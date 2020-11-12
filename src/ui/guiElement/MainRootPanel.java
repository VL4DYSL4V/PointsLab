package ui.guiElement;

import enums.UIColor;
import enums.UIDimensionParameter;

import javax.swing.*;
import java.awt.*;

public class MainRootPanel extends JPanel {

    public MainRootPanel(){
        setBackground(UIColor.BACKGROUND.getColor());
        setForeground(UIColor.FOREGROUND.getColor());
        setSize(UIDimensionParameter.WIDTH.getValue(), UIDimensionParameter.HEIGHT.getValue());
        setLayout(new BorderLayout());
    }
}
