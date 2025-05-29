import javax.swing.*;
import java.awt.*;

public class DocEditViewImpl extends JFrame {
    private JPanel headerPanel;
    private JPanel mainPanel;
    private JLabel docTitleLabel;
    private JButton saveButton;
    private JTextArea editTextArea;


    public DocEditViewImpl() {
        setTitle("DocEditView");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        headerPanel = new JPanel();
        docTitleLabel = new JLabel("Doc Title");
        saveButton = new JButton("Save");
        headerPanel.add(docTitleLabel);
        headerPanel.add(saveButton);

        mainPanel = new JPanel();
        editTextArea = new JTextArea();
        editTextArea.setLineWrap(true);
        editTextArea.setWrapStyleWord(true);
        JScrollPane editScrollPane = new JScrollPane(editTextArea);
        editScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editScrollPane.setPreferredSize(new Dimension(580, 320));
        mainPanel.add(editScrollPane);

        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
}
