package features.document.datasource;

/*
 *  Interface que define as operações básicas de um DocPublisher (Observer)
 */
public interface DocPublisher {
    void subscribe(DocListener observer);
}
