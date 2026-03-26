/**
 * InvalidSpielerException wird geworfen, wenn ein Spieler-Objekt ungültige Daten hat.
 * Beispiele: Leerer Name, ungültige Nummer (nicht 1-99), null-Werte.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class InvalidSpielerException extends BasketballException {
    /**
     * Konstruktor mit Fehlermeldung.
     *
     * @param message Die Fehlermeldung
     */
    public InvalidSpielerException(String message) {
        super(message);
    }

    /**
     * Konstruktor mit Fehlermeldung und Root-Cause.
     *
     * @param message Die Fehlermeldung
     * @param cause   Die ursprüngliche Exception
     */
    public InvalidSpielerException(String message, Throwable cause) {
        super(message, cause);
    }
}
