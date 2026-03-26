
/**
 * Start: Application Entry Point
 * 
 * Initialisiert die Anwendung und startet die MVC-Schichten:
 * 1. Model (AppManager)
 * 2. View (MainWindow)
 * 3. Controller (AppController)
 * 
 * @author Basketball-Manager Projekt
 * @version 1.0
 */
public class Start {
    /**
     * Hauptmethode - Einstiegspunkt der Anwendung.
     * @param args Kommandozeilen-Argumente (unused)
     */
    public static void main(String[] args) {
        // 1. Model-Schicht initialisieren
        AppManager model = new AppManager();
        
        // 2. View-Schicht initialisieren
        MainWindow view = new MainWindow();
        
        // 3. Controller-Schicht initialisieren
        AppController controller = new AppController(model, view);
        controller.init();
        
        // 4. GUI anzeigen
        view.setVisible(true);
        
        // Startup-Info ans Log
        view.showMessage("SYSTEM: Anwendung gestartet - Willkommen!");
    }
}
