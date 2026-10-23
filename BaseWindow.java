import javax.swing.ImageIcon;
import java.net.URL;

/**
 * BaseWindow is the PARENT class.
 * It holds the settings that every window in this application shares
 * (title, width, height) and a helper method for loading images.
 * SwingAssignment INHERITS from this class using the "extends" keyword.
 */
public class BaseWindow {

    // Protected fields can be used directly by any child class
    protected String windowTitle;
    protected int windowWidth;
    protected int windowHeight;

    /**
     * Constructor of the parent class.
     * It runs first whenever a child object is created (through super(...)).
     */
    public BaseWindow(String windowTitle, int windowWidth, int windowHeight) {
        this.windowTitle = windowTitle;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        System.out.println("BaseWindow constructor: settings loaded for \"" + windowTitle + "\"");
    }

    /**
     * Loads an image from the resources folder and returns it as an ImageIcon.
     * It first looks on the classpath, then falls back to the file path,
     * so it works in IntelliJ IDEA, VS Code and the command line.
     */
    protected ImageIcon loadIcon(String fileName) {
        URL url = getClass().getResource("/resources/" + fileName);
        if (url != null) {
            return new ImageIcon(url);
        }
        return new ImageIcon("src/resources/" + fileName);
    }
}
