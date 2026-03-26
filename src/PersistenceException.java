/**
 * PersistenceException wird geworfen, wenn beim Speichern oder Laden von Daten ein Fehler auftritt.
 * Beispiele: Datei nicht lesbar/schreibbar, JSON Parse-Fehler, I/O-Fehler.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class PersistenceException extends BasketballException {
    /**
     * Konstruktor mit Fehlermeldung.
     *
     * @param message Die Fehlermeldung
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Konstruktor mit Fehlermeldung und Root-Cause.
     *
     * @param message Die Fehlermeldung
     * @param cause   Die ursprüngliche Exception
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
