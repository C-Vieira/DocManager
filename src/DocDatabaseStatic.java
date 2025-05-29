import java.util.ArrayList;
import java.util.List;

public class DocDatabaseStatic implements DocDatabase {
    private final List<Document> docs = new ArrayList<Document>();

    @Override
    public void insertDoc(String title) {
        Document doc = new Document(title);
        docs.add(doc);
        System.out.println("Inserted " + title + " into the database on " + doc.getLastEdit());
    }

    @Override
    public void updateDoc(Document doc) {

    }

    @Override
    public List<Document> getDocs() {
        return docs;
    }
}
