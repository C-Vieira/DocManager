import java.util.Calendar;
import java.util.List;

public class DocControllerImpl implements DocController {
    private DocView docView;
    private final DocDatabase docDatabase;

    public DocControllerImpl(DocDatabase docDatabase) {
        this.docDatabase = docDatabase;
    }

    @Override
    public void setView(DocView view) {
        this.docView = view;
    }

    @Override
    public void addDoc(String title) {
        if(title == null || title.isEmpty()) {
            title = "New Document";
        }
        docDatabase.insertDoc(title);
    }

    @Override
    public void updateDoc(Document doc) {

    }

    @Override
    public List<Document> getDocs() {
        return docDatabase.getDocs();
    }
}
