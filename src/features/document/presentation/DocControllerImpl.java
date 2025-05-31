package features.document.presentation;

import features.document.datasource.DocDatabase;
import features.document.model.Document;

import java.util.List;

/*
 *  Classe que controla o acesso aos dados de uma DocDatabase
 *  -   Recebe requisições de uma DocView e as propaga para
 *      uma DocDatabase
 */
public class DocControllerImpl implements DocController {
    // Referências para uma instância de DocView e DocDatabase (Mediator)
    private DocView docView;
    private final DocDatabase docDatabase;

    // Injeção de dependência via construtor: DocDatabase
    public DocControllerImpl(DocDatabase docDatabase) {
        this.docDatabase = docDatabase;
    }

    // Injeção de dependência via método: DocView
    @Override
    public void setView(DocView view) {
        this.docView = view;
    }

    // Método que propaga a requisição de DocView para DocDatabase (Mediator)
    @Override
    public void addDoc(String title) {
        if (title == null) {
            return;
        } else if (title.isEmpty()) {
            docView.showError("Title cannot be empty");
        } else {
            docDatabase.insertDoc(title);
        }
    }

    // Método que propaga a requisição de DocView para DocDatabase (Mediator)
    @Override
    public void updateDoc(int id, String content) {
        docDatabase.updateDoc(id, content);
    }

    // Método que propaga a requisição de DocView para DocDatabase (Mediator)
    @Override
    public List<Document> getDocs() {
        return docDatabase.getDocs();
    }

    // Método que propaga a requisição de DocView para DocDatabase (Mediator)
    @Override
    public Document getDocAt(int id) {
        return docDatabase.getDocAt(id);
    }
}
