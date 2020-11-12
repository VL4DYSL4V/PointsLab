package application;

import dao.HardCodePointDAO;
import dao.PointDAO;
import entity.ApplicationContext;
import ui.MainWindow;

import javax.swing.*;

public class Application {

    public void start() {
        PointDAO pointDAO = new HardCodePointDAO();
        SwingUtilities.invokeLater(() -> {
            ApplicationContext applicationContext = new ApplicationContext(pointDAO);
            MainWindow mainWindow = new MainWindow(applicationContext);
            mainWindow.setVisible(true);
        });

    }

}
