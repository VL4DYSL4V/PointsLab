package ui;

import UIUtils.UIUtils;
import algorithm.EndruJarvis;
import algorithm.KeylKircpatrik;
import dao.PointDAO;
import entity.ColorPoint;
import entity.Pair;
import entity.Point;
import entity.Point2D;
import enums.UIColor;
import enums.UIDimensionParameter;
import ui.guiElement.AppButton;
import ui.guiElement.MainControlPanel;
import ui.guiElement.MainWindowCanvas;
import ui.guiElement.MainRootPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.LinkedList;

/**Holds all content, must be not resizable
 * */
public class MainWindow extends JFrame {
    private final JPanel rootPanel = new MainRootPanel();
    private final JPanel controlPanel = new MainControlPanel();
    private final MainWindowCanvas canvas = new MainWindowCanvas();

    private final JButton generatePointSetButton = new AppButton("Generate points");
    private final JButton changeThemeButton = new AppButton("Change theme");

    private Color currTheme = UIColor.GREEN_THEME.getColor();

    public MainWindow(final PointDAO pointDAO) {
        frameTuning();
        constructWindow();
        ActionListener generatePointSetButtonListener =
                e -> {
                    Collection<ColorPoint> points = pointDAO.getColorPoints(8, currTheme);
                    drawPoints(points);
                };
        generatePointSetButton.addActionListener(generatePointSetButtonListener);
        ActionListener changeThemeButtonListener =
                e -> {
                    Collection<ColorPoint> points = canvas.getColorPoints();
                    Collection<ColorPoint> pointsSubstitute = new LinkedList<>();
                    changeTheme();
                    points.forEach(point-> pointsSubstitute.add(new ColorPoint(point.getPoint(), currTheme)));
                    drawPoints(pointsSubstitute);
                };
        changeThemeButton.addActionListener(changeThemeButtonListener);
    }

    private void constructWindow() {
        add(rootPanel);
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
        canvas.setColorPoints(points);
//        KeylKircpatrik keylKircpatrik = new KeylKircpatrik();
//        Collection<Point2D> point2DS = new LinkedList<>(points);
//        Collection<Pair<Point2D>> lines = keylKircpatrik.getLinesPoints(point2DS);
        EndruJarvis endruJarvis = new EndruJarvis();
        Collection<Point2D> point2DS = new LinkedList<>(points);
        Collection<Pair<Point2D>> lines = endruJarvis.getLinesPoints(point2DS);
        canvas.setLineDefinitionPoints(lines);
        canvas.repaint();
    }

    private void changeTheme(){
        currTheme = (currTheme == UIColor.GREEN_THEME.getColor())
                ? UIColor.RED_THEME.getColor()
                : UIColor.GREEN_THEME.getColor();
        canvas.repaint();
    }
}

