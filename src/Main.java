public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        DocViewImpl docView = new DocViewImpl(new DocControllerImpl(new DocDatabaseStatic()));
        docView.setVisible(true);
    }
}
