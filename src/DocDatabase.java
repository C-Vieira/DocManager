import java.util.List;

public interface DocDatabase {
    void insertDoc(String title);
    void updateDoc(Document doc);
    List<Document> getDocs();
}