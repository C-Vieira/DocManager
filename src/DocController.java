import java.util.List;

public interface DocController {
    void setView(DocView view);
    void addDoc(String title);
    void updateDoc(Document doc);
    List<Document> getDocs();
}
