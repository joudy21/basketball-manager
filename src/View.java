/**
 * View Interface für den MVC-Pattern.
 * Ermöglicht Test-Stubs ohne Swing-Initialisierung.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public interface View {
    void setController(AppController controller);
    void showMessage(String message);
    void clearAllFields();
    void clearOutput();
    void appendOutput(String message);
}
