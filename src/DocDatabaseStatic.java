import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DocDatabaseStatic implements DocDatabase {
    private final List<Document> docs = new ArrayList<>();

    @Override
    public void insertDoc(String title) {
        Document doc = new Document(title);
        docs.add(doc);
        System.out.println("Inserted " + doc.getTitle() + " into the database on " + doc.getLastEdit());
        System.out.println("List length: " + docs.size());
    }

    @Override
    public void updateDoc(int id, String content) {
        System.out.println("Id is: " + id + " with content: " + content);
        System.out.println("List length: " + docs.size());
        //if (docs.isEmpty()) return;
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
        if (docs.isEmpty())
            return null;
        return docs.get(id);
    }

    private String getNow() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
