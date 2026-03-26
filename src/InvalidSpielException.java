/**
 * InvalidSpielException wird geworfen, wenn ein Spiel-Objekt ungültige Daten hat.
 * Beispiele: Leere Team-Namen, ungültige Punkte, null-Werte.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class InvalidSpielException extends BasketballException {
    /**
     * Konstruktor mit Fehlermeldung.
     *
     * @param message Die Fehlermeldung
     */
    public InvalidSpielException(String message) {
        super(message);
    }

    /**
     * Konstruktor mit Fehlermeldung und Root-Cause.
     *
     * @param message Die Fehlermeldung
     * @param cause   Die ursprüngliche Exception
     */
    public InvalidSpielException(String message, Throwable cause) {
        super(message, cause);
    }
}
