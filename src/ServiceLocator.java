public class ServiceLocator {
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }

    private DocDatabaseStatic docDatabaseStatic;

    private DocDatabaseStatic getDocDatabaseStatic() {
        if (docDatabaseStatic == null) {
            docDatabaseStatic = new DocDatabaseStatic();
        }

        return docDatabaseStatic;
    }

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
