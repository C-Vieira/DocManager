import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocViewImpl extends JFrame {
    private JPanel headerPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JLabel headerLabel;
    private JButton addDocButton;
    private JButton editDocButton;
    private DefaultTableModel tableModel;

    public DocViewImpl() {
        setTitle("DocView");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        headerPanel = new JPanel();
        headerLabel = new JLabel("JNote: Document Manger");
        headerPanel.add(headerLabel);

        tablePanel = new JPanel();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Title", "LastEdited"}, 0);
        JTable docTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(docTable);
        tablePanel.add(scrollPane);

        buttonPanel = new JPanel(new GridLayout(2, 1));
        addDocButton = new JButton("New Document");
        addDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDoc();
            }
        });
        editDocButton = new JButton("Edit Document");
        editDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocEditViewImpl editDocView = new DocEditViewImpl();
                editDocView.setVisible(true);
            }
        });
        buttonPanel.add(addDocButton);
        buttonPanel.add(editDocButton);

        add(headerPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
    }

    private void addDoc() {

    }
}
