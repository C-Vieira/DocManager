package features.document.presentation;

import features.document.model.Document;

import java.util.List;

/*
 *  Interface que define as operações básicas para um DocController
 */
public interface DocController {
    void setView(DocView view);
    void addDoc(String title);
    void updateDoc(int id, String content);
    List<Document> getDocs();
    Document getDocAt(int id);
}
