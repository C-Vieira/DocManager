package features.document.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *  Classe que define uma tela de edição de documentos
 *  -   Recebe dados de entrada e faz requisições para
 *      uma classe mediadora (DocController)
 */
public class DocEditViewImpl extends JFrame implements DocView {
    private JPanel headerPanel;
    private JPanel mainPanel;
    private JLabel docTitleLabel;
    private JButton saveButton;
    private JTextArea editTextArea;

    // Referência para uma instância de DocController (Mediator)
    private final DocController docController;
    private int currentDocId;
    private String currentDocTitle;
    private String currentDocContent;

    // Injeção de dependência via construtor: DocController
    public DocEditViewImpl(DocController docController, int currentDocId) {
        setTitle("DocEditView");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.docController = docController;
        // Injeção de dependência via método para DocController
        docController.setView(this);

        this.currentDocId = currentDocId;
        this.currentDocTitle = docController.getDocAt(currentDocId).getTitle();
        if (docController.getDocAt(currentDocId) != null) {
            this.currentDocContent = docController.getDocAt(currentDocId).getContent();
        }

        System.out.println("Current Doc ID: " + currentDocId);

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        headerPanel = new JPanel();
        docTitleLabel = new JLabel(currentDocTitle);
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDocument();
            }
        });
        headerPanel.add(docTitleLabel);
        headerPanel.add(saveButton);

        mainPanel = new JPanel();
        editTextArea = new JTextArea(currentDocContent);
        editTextArea.setLineWrap(true);
        editTextArea.setWrapStyleWord(true);
        JScrollPane editScrollPane = new JScrollPane(editTextArea);
        editScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editScrollPane.setPreferredSize(new Dimension(580, 320));
        mainPanel.add(editScrollPane);

        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    // Método de comunicação com DocController (Mediator)
    private void saveDocument() {
        String newContent = editTextArea.getText();
        docController.updateDoc(currentDocId, newContent);
    }

    @Override
    public void open() {
        setVisible(true);
    }

    @Override
    public void showError(String error) {
        JOptionPane.showMessageDialog(DocEditViewImpl.this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
