package di;

import features.document.datasource.DocDatabase;
import features.document.datasource.DocDatabaseStatic;
import features.document.datasource.DocPublisher;
import features.document.presentation.*;

/*
 *  Singleton que controla a criação de objetos: Views, Controllers e Databases
 *  assim como suas dependências
 */
public class ServiceLocator {
    // Instância para o Singleton: ServiceLocator
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }

    // Instância para o Singleton: DocDatabaseStatic
    private DocDatabaseStatic docDatabaseStatic;

    private DocDatabaseStatic getDocDatabaseStatic() {
        if (docDatabaseStatic == null) {
            docDatabaseStatic = new DocDatabaseStatic();
        }

        return docDatabaseStatic;
    }

    // Métodos para construção de objetos e suas dependências:

    public DocDatabase getDocDatabase() {
        return getDocDatabaseStatic();
    }

    public DocPublisher getDocPublisher() {
        return getDocDatabaseStatic();
    }

    public DocController getDocController() {
        return new DocControllerImpl(getDocDatabase());
    }

    public DocView getDocView() {
        return new DocViewImpl(getDocPublisher(), getDocController());
    }

    public DocView getEditDocView(int docId){
        return new DocEditViewImpl(getDocController(), docId);
    }

}
