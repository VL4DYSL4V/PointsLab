package ui;

import UIUtils.UIUtils;
import entity.ApplicationContext;
import entity.ColorPoint;
import util.Pair;
import entity.Point2D;
import enums.UIColor;
import enums.UIDimensionParameter;
import ui.guiElement.*;
import util.StringToAlgorithmConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Holds all content, must be not resizable
 */
public class MainWindow extends JFrame {
    private final JPanel rootPanel = new MainRootPanel();
    private final JPanel controlPanel = new MainControlPanel();
    private final MainWindowCanvas canvas;

    private final JButton generatePointSetButton = new AppButton("Generate points");
    private final JButton changeThemeButton = new AppButton("Change theme");
    private final JButton executeButton = new AppButton("Execute");

    private final JComboBox<String> algorithmChooser = new MainJComboBox(new String[]{
            "Forchun", "Delone", "Kirkpatrik", "Jarvis", "Graham", "Recursive"
    });

    private final ApplicationContext applicationContext;

    public MainWindow(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.canvas = MainWindowCanvas.getInstance(applicationContext);
        frameTuning();
        constructWindow();
        configureGeneratePointSetButton();
        configureChangeThemeButton();
        configureExecuteButton();
        configureAlgorithmChooser();
    }

    private void configureAlgorithmChooser() {
        ActionListener algorithmChooserListener =
                e -> {
                    JComboBox<String> box = (JComboBox<String>) e.getSource();
                    applicationContext.setPointConnector(
                            StringToAlgorithmConverter.convert((String) box.getSelectedItem())
                    );
                };
        algorithmChooser.addActionListener(algorithmChooserListener);
    }

    private void configureExecuteButton(){
        ActionListener executeButtonListener =
                e ->{
                    Collection<ColorPoint> points = applicationContext.getPoints();
                    if (!(points == null || points.isEmpty())) {
                        Collection<Pair<Point2D>> lines =
                                    applicationContext.getPointConnector().getLinesPoints(points);
                        applicationContext.setLines(lines);
                        canvas.repaint();
                    }
                };
        executeButton.addActionListener(executeButtonListener);
    }

    private void configureChangeThemeButton() {
        ActionListener changeThemeButtonListener =
                e -> {
                    Collection<ColorPoint> points = applicationContext.getPoints();
                    if (!(points == null || points.isEmpty())) {
                        Collection<ColorPoint> pointsSubstitute = new LinkedList<>();
                        changeTheme();
                        points.forEach(point -> pointsSubstitute.add(new ColorPoint(point.getPoint(),
                                applicationContext.getPointTheme())));
                        drawPoints(pointsSubstitute);
                    }
                };
        changeThemeButton.addActionListener(changeThemeButtonListener);
    }

    private void configureGeneratePointSetButton() {
        ActionListener generatePointSetButtonListener =
                e -> {
                    Collection<ColorPoint> points = applicationContext.getPointDAO()
                                                .getColorPoints(8, applicationContext.getPointTheme());
                    drawPoints(points);
                };
        generatePointSetButton.addActionListener(generatePointSetButtonListener);
    }

    private void constructWindow() {
        add(rootPanel);
        controlPanel.add(algorithmChooser);
        controlPanel.add(executeButton);
        controlPanel.add(generatePointSetButton);
        controlPanel.add(changeThemeButton);
        rootPanel.add(controlPanel);
        rootPanel.add(canvas, BorderLayout.SOUTH);
    }

    private void frameTuning() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation((UIUtils.getScreenWidth() - UIDimensionParameter.WIDTH.getValue()) / 2,
                (UIUtils.getScreenHeight() - UIDimensionParameter.HEIGHT.getValue()) / 2);
        setSize(UIDimensionParameter.WIDTH.getValue(), UIDimensionParameter.HEIGHT.getValue());
        setResizable(false);
        setTitle("Lab-3 OOP");
    }

    private void drawPoints(Collection<ColorPoint> points) {
        applicationContext.setPoints(points);
        applicationContext.setLines(new LinkedList<>());
        canvas.repaint();
    }

    private void changeTheme() {
        Color neuTheme = (applicationContext.getPointTheme() == UIColor.GREEN_THEME.getColor())
                ? UIColor.RED_THEME.getColor()
                : UIColor.GREEN_THEME.getColor();
        applicationContext.setPointTheme(neuTheme);
        canvas.repaint();
    }
}

