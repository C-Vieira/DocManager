package features.document.datasource;

import features.document.model.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DocDatabaseStatic implements DocDatabase, DocPublisher {
    private final List<Document> docs = new ArrayList<>();
    private final List<DocListener> listeners = new ArrayList<>();

    @Override
    public void subscribe(DocListener observer) {
        listeners.add(observer);
    }

    private void notifyDataChanged(){
        for(DocListener listener : listeners){
            listener.updateData();
        }
    }

    @Override
    public void insertDoc(String title) {
        Document doc = new Document(title);
        docs.add(doc);
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
