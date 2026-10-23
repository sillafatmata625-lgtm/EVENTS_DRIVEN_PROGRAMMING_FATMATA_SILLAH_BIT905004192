import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

/**
 * PROG209 Event Driven Programming - Individual Assignment 1
 * Basic Interactive GUI using Java Swing.
 *
 * SwingAssignment is the CHILD class. It inherits from BaseWindow,
 * so it receives the title, width, height and the loadIcon() method.
 */
public class SwingAssignment extends BaseWindow {

    // Components required by the assignment brief
    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    // Extra components for the "Addition" part (change text and background)
    private JPanel controlPanel;
    private JTextField textInput;
    private JButton changeTextButton;
    private JButton changeColourButton;
    private JButton resetButton;

    private final Color defaultBackground = new Color(219, 234, 254); // light blue
    private final String defaultText =
            "Welcome to Java Swing First Individual Assignment by Sill";

    /**
     * Constructor of the child class.
     * super(...) calls the BaseWindow constructor first, then this
     * constructor builds the user interface.
     */
    public SwingAssignment() {
        super("Java Swing Assignment", 800, 800);

        // 1. Create (instantiate) the JFrame
        frame = new JFrame();

        // 2. Create (instantiate) the JPanel
        panel = new JPanel(new BorderLayout());

        // 3. Create (instantiate) the JLabel with the required text
        label = new JLabel(defaultText);

        setUpLabel();
        setUpControls();

        // 4. Add the label to the panel
        panel.add(label, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        // 5. Add the panel to the frame
        frame.add(panel);

        setUpFrame();
    }

    /** Styles the label and adds an ImageIcon above the text. */
    private void setUpLabel() {
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(new Color(30, 41, 59));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // ImageIcon shown on the label, scaled to 128 x 128 pixels
        ImageIcon original = loadIcon("app_icon.png");
        Image scaled = original.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setIconTextGap(24);

        panel.setBackground(defaultBackground);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 30, 30, 30));
    }

    /** Builds the text field and buttons, and connects their event listeners. */
    private void setUpControls() {
        controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setOpaque(false);

        textInput = new JTextField(22);
        textInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textInput.setToolTipText("Type new text for the label, then press Enter or click Change Text");

        changeTextButton = createButton("Change Text", new Color(37, 99, 235));
        changeColourButton = createButton("Change Background", new Color(22, 163, 74));
        resetButton = createButton("Reset", new Color(100, 116, 139));

        // EVENT: change the label text (button click or Enter key)
        changeTextButton.addActionListener(e -> changeLabelText());
        textInput.addActionListener(e -> changeLabelText());

        // EVENT: change the panel background using a colour chooser
        changeColourButton.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(frame, "Choose a background colour",
                    panel.getBackground());
            if (chosen != null) {
                panel.setBackground(chosen);
            }
        });

        // EVENT: reset label text and background to the defaults
        resetButton.addActionListener(e -> {
            label.setText(defaultText);
            panel.setBackground(defaultBackground);
            textInput.setText("");
        });

        controlPanel.add(textInput);
        controlPanel.add(changeTextButton);
        controlPanel.add(changeColourButton);
        controlPanel.add(resetButton);
    }

    /** Copies the text from the text field into the label. */
    private void changeLabelText() {
        String newText = textInput.getText().trim();
        if (newText.isEmpty()) {
            textInput.setToolTipText("Please type some text first");
            textInput.requestFocus();
            return;
        }
        label.setText(newText);
        textInput.setText("");
    }

    /** Helper that creates a styled button. */
    private JButton createButton(String text, Color colour) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 13));
        button.setBackground(colour);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    /** Sets the frame title, size, icon and close behaviour. */
    private void setUpFrame() {
        frame.setTitle(windowTitle);                 // "Java Swing Assignment" (from BaseWindow)
        frame.setSize(windowWidth, windowHeight);    // 800 x 800 (from BaseWindow)
        frame.setIconImage(loadIcon("app_icon.png").getImage()); // own application icon
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // closes when X is clicked
        frame.setLocationRelativeTo(null);           // centre on screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Start the GUI on the Event Dispatch Thread (the correct way in Swing)
        SwingUtilities.invokeLater(SwingAssignment::new);
    }
}
