import di.ServiceLocator;
import javax.swing.*;

/*
 *  Ponto de entrada da aplicação
 */
public class Main {
    public static void main(String[] args) {
        // Recuperando a instância do ServiceLocator (Singleton)
        SwingUtilities.invokeLater(() -> ServiceLocator.getInstance().getDocView().open());
    }
}
