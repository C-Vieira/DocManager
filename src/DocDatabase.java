import java.util.List;

public interface DocDatabase {
    void insertDoc(String title);
    void updateDoc(int id, String content);
    List<Document> getDocs();
    Document getDocAt(int id);
}