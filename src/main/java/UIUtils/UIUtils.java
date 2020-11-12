package UIUtils;

import java.awt.*;

public class UIUtils {

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();

    public static int getScreenWidth(){
        return toolkit.getScreenSize().width;
    }

    public static int getScreenHeight(){
        return toolkit.getScreenSize().height;
    }
}
