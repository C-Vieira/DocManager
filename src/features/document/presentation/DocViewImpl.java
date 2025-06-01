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

/*
 *  Classe que define a tela principal da aplicação
 *  -   Recebe dados de entrada e faz requisições para
 *      uma classe mediadora (DocController)
 *  -   Observa e reage à notificações de alterações nos
 *      dados da classe DocDatabase (DocDatabaseStatic)
 */
public class DocViewImpl extends JFrame implements DocView, DocListener {
    private JPanel headerPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JLabel headerLabel;
    private JButton addDocButton;
    private JButton editDocButton;
    private DefaultTableModel tableModel;

    // Referência para uma instância de DocController (Mediator)
    private final DocController docController;

    // Injeção de dependências via construtor: DocPublisher, DocController
    public DocViewImpl(DocPublisher publisher, DocController docController) {
        setTitle("DocView");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         *  Inscrever esta instância para ser notificada
         *  de alterações na classe observada (Observer)
         *  (DocPublisher -> DocDatabaseStatic)
         */
        publisher.subscribe(this);

        this.docController = docController;
        // Injeção de dependência via método para DocController
        docController.setView(this);

        initializeUI();

        loadDocs();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        headerPanel = new JPanel();
        headerLabel = new JLabel("JDoc: Document Manger");
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

    // Método de comunicação com DocController (Mediator)
    private void loadDocs(){
        tableModel.setRowCount(0);

        List<Document> docs = docController.getDocs();
        for(Document doc : docs){
            tableModel.addRow(new Object[]{doc.getID(), doc.getTitle(), doc.getLastEdit()});
        }
    }

    // Método de comunicação com DocController (Mediator)
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

    /*
     *  Método de resposta à notificações
     *  de classes inscritas (Observer)
     */
    @Override
    public void updateData() {
        loadDocs();
    }
}
