import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * UUID generator, press the button and the uuid will be copied to your clipboard, all u have to do is paste where you want it.
 * Tab uses picture in picture so it can just be kept in the corner when using
 * 
 * prints like, 
 * "uuid": "9f06e905-af72-4d9a-ae02-1ac9706335e8"
 */

public class UUIDGenerator {

    public static void main(String[] args) {
        // Create the frame (window)
        JFrame frame = new JFrame("UUID Generator (PiP)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(260, 70);  // Smaller window for PiP-like behavior

        // Make the window always on top
        frame.setAlwaysOnTop(true);

        // Create a panel to hold components
        JPanel panel = new JPanel();

        // Create a text field to display the generated UUID
        JTextField uuidField = new JTextField(10);  // Smaller field to match PiP
        uuidField.setEditable(false);  // Make the text field read-only

        // Create a button to generate the UUID
        JButton button = new JButton("Generate UUID");

        // Add action listener to the button to generate and copy the UUID
        button.addActionListener(e -> generateAndCopyUUID(uuidField));

        // Add components to the panel
        panel.add(button);
        panel.add(uuidField);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to generate and copy UUID to clipboard
    private static void generateAndCopyUUID(JTextField uuidField) {
        UUID uuid = UUID.randomUUID();
        
        // Create the formatted UUID string
        String uuidString = "\"uuid\": \"" + uuid.toString() + "\"";

        // Display the formatted UUID string in the text field
        uuidField.setText(uuidString);

        // Copy the formatted UUID to the clipboard
        StringSelection selection = new StringSelection(uuidString);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        System.out.println("UUID copied to clipboard!");
    }
}