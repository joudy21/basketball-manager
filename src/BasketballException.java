/**
 * BasketballException ist die Parent-Exception für alle Basketball-Manager-Fehler.
 * Diese Exception dient als Basis für spezifische Exception-Typen im Projekt.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class BasketballException extends Exception {
    /**
     * Konstruktor mit Fehlermeldung.
     *
     * @param message Die Fehlermeldung
     */
    public BasketballException(String message) {
        super(message);
    }

    /**
     * Konstruktor mit Fehlermeldung und Root-Cause.
     *
     * @param message Die Fehlermeldung
     * @param cause   Die ursprüngliche Exception
     */
    public BasketballException(String message, Throwable cause) {
        super(message, cause);
    }
}
