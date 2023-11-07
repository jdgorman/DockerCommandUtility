import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {
    public UserInterface() {
        setTitle("Docker Command Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a GridBagConstraints object for consistent layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create a panel to hold input components
        JPanel inputPanel = new JPanel(new GridBagLayout());

        // Create and add input labels and text areas
        addInputLabelAndTextArea(inputPanel, gbc, "Configuration file", 0, 0);
        JTextArea envConfigInputArea = addTextAreaWithScrollPane(inputPanel, gbc, 10, 50, 1, 0);

        addInputLabelAndTextArea(inputPanel, gbc, "Port number", 2, 0);
        JTextArea portConfigInputArea = addTextAreaWithScrollPane(inputPanel, gbc, 1, 50, 3, 0);

        addInputLabelAndTextArea(inputPanel, gbc, "App Name", 4, 0);
        JTextArea appNameInputArea = addTextAreaWithScrollPane(inputPanel, gbc, 1, 50, 5, 0);

        // Create a panel to hold output components
        JPanel outputPanel = new JPanel(new GridBagLayout());

        // Create and add output labels and text areas
        addInputLabelAndTextArea(outputPanel, gbc, "Build Command", 0, 0);
        JTextArea buildCommandOutputArea = addTextAreaWithScrollPane(outputPanel, gbc, 1, 50, 1, 0);
        buildCommandOutputArea.setEditable(false);

        addInputLabelAndTextArea(outputPanel, gbc, "Run Command", 2, 0);
        JTextArea runCommandOutputArea = addTextAreaWithScrollPane(outputPanel, gbc, 19, 50, 3, 0);
        runCommandOutputArea.setEditable(false);

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);

        // Add action listeners for the buttons
        submitButton.addActionListener(e -> {
            String envConfig = envConfigInputArea.getText().replaceAll("\\n", "");
            String port = portConfigInputArea.getText().replaceAll("\\n", "");
            String appName = appNameInputArea.getText();
            buildCommandOutputArea.setText("docker build -t " + appName + " . --no-cache");
            runCommandOutputArea.setText(InputProcessor.generateOutput(appName, envConfig, port));
        });

        clearButton.addActionListener(e -> {
            envConfigInputArea.setText("");
            portConfigInputArea.setText("");
            appNameInputArea.setText("");
            buildCommandOutputArea.setText("");
            runCommandOutputArea.setText("");
        });

        // Add panels to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);
    }

    // Helper method to add input labels with consistent formatting
    private void addInputLabelAndTextArea(JPanel panel, GridBagConstraints gbc, String labelName, int gridy, int gridx) {
        JLabel label = new JLabel(labelName);
        gbc.gridy = gridy;
        gbc.gridx = gridx;
        panel.add(label, gbc);
    }

    // Helper method to add text areas with scroll panes and consistent formatting
    private JTextArea addTextAreaWithScrollPane(JPanel panel, GridBagConstraints gbc, int rows, int columns, int gridy, int gridx) {
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        gbc.gridy = gridy;
        gbc.gridx = gridx;
        panel.add(scrollPane, gbc);
        return textArea;
    }
}