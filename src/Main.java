public class Main {
    public static void main(String[] args) {
        DocViewImpl docView = new DocViewImpl(new DocControllerImpl(new DocDatabaseStatic()));
        docView.setVisible(true);
    }
}
