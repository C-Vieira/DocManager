package features.document.datasource;

import features.document.model.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 *  Classe que simula o funcionamento de um banco de dados
 *  -   Mantém uma lista de referências à DocListeners inscritos
 *      para receberem notificações de alterações nos dados
 */
public class DocDatabaseStatic implements DocDatabase, DocPublisher {
    private final List<Document> docs = new ArrayList<>();
    // Lista contendo instâncias inscritas nesta classe (Observer)
    private final List<DocListener> listeners = new ArrayList<>();

    // Método para inscrição de uma instância nesta classe (Observer)
    @Override
    public void subscribe(DocListener observer) {
        listeners.add(observer);
    }

    // Método de notificação às instâncias inscritas nesta classe (Observer)
    private void notifyDataChanged(){
        for(DocListener listener : listeners){
            listener.updateData();
        }
    }

    @Override
    public void insertDoc(String title) {
        Document doc = new Document(title);
        docs.add(doc);

        // Notificar instâncias inscritas sobre alterações
        notifyDataChanged();

        System.out.println("Inserted " + doc.getTitle() + " into the database on " + doc.getLastEdit());
        System.out.println("List length: " + docs.size());
    }

    @Override
    public void updateDoc(int id, String content) {
        System.out.println("Id is: " + id + " with content: " + content);
        System.out.println("List length: " + docs.size());

        docs.get(id).setContent(content);
        docs.get(id).setLastEdit(getNow());
        System.out.println("Updated " + docs.get(id).getTitle() + "'s content on " + docs.get(id).getLastEdit());
    }

    @Override
    public List<Document> getDocs() {
        return docs;
    }

    @Override
    public Document getDocAt(int id) {
        return docs.get(id);
    }

    private String getNow() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
