package application;

import dao.HardCodePointDAO;
import dao.PointDAO;
import ui.MainWindow;

import javax.swing.*;

public class Application {

    public void start() {
        PointDAO pointDAO = new HardCodePointDAO();
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow(pointDAO);
            mainWindow.setVisible(true);
        });

    }

}
