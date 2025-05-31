package features.document.datasource;

import features.document.model.Document;

import java.util.List;

/*
 *  Interface que define as operações básicas para uma DocDatabase
 */
public interface DocDatabase {
    void insertDoc(String title);
    void updateDoc(int id, String content);
    List<Document> getDocs();
    Document getDocAt(int id);
}