package features.document.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Document {
    private static int ID = -1;
    private int id;
    private String title;
    private String content;
    private String lastEdit;

    public Document(String title) {
        Document.ID++;
        this.id = ID;
        this.title = title;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.lastEdit = dateFormat.format(Calendar.getInstance().getTime());
    }

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }
}
