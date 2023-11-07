import javax.swing.*;

public class Main {

    /**
     * Main method to run application
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterface frame = new UserInterface();
            frame.setVisible(true);
        });
    }
}