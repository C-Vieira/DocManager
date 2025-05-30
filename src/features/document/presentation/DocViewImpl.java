package features.document.presentation;

import di.ServiceLocator;
import features.document.datasource.DocListener;
import features.document.datasource.DocPublisher;
import features.document.model.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DocViewImpl extends JFrame implements DocView, DocListener {
    private JPanel headerPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JLabel headerLabel;
    private JButton addDocButton;
    private JButton editDocButton;
    private DefaultTableModel tableModel;

    private final DocController docController;

    public DocViewImpl(DocPublisher publisher, DocController docController) {
        setTitle("DocView");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        publisher.subscribe(this);
        this.docController = docController;
        docController.setView(this);

        initializeUI();

        loadDocs();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        headerPanel = new JPanel();
        headerLabel = new JLabel("JNote: Document Manger");
        headerPanel.add(headerLabel);

        tablePanel = new JPanel();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Title", "LastEdited"}, 0);
        JTable docTable = new JTable(tableModel);
        docTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
                int selectedRow = docTable.getSelectedRow();

                if(selectedRow != -1) {
                    int docId = (int) tableModel.getValueAt(selectedRow, 0);
                    ServiceLocator.getInstance().getEditDocView(docId).open();
                }
            }
        });
        buttonPanel.add(addDocButton);
        buttonPanel.add(editDocButton);

        add(headerPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
    }

    private void loadDocs(){
        tableModel.setRowCount(0);

        List<Document> docs = docController.getDocs();
        for(Document doc : docs){
            tableModel.addRow(new Object[]{doc.getID(), doc.getTitle(), doc.getLastEdit()});
        }
    }

    private void addDoc() {
        String docTitle = JOptionPane.showInputDialog(this, "Enter title:");
        docController.addDoc(docTitle);
    }

    @Override
    public void open() {
        setVisible(true);
    }

    @Override
    public void showError(String error) {
        JOptionPane.showMessageDialog(DocViewImpl.this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void updateData() {
        loadDocs();
    }
}
