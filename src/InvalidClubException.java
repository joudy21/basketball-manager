/**
 * InvalidClubException wird geworfen, wenn ein Club-Objekt ungültige Daten hat.
 * Beispiele: Leerer Club-Name, ungültige Gründungsjahr, negative ID.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class InvalidClubException extends BasketballException {
    /**
     * Konstruktor mit Fehlermeldung.
     *
     * @param message Die Fehlermeldung
     */
    public InvalidClubException(String message) {
        super(message);
    }

    /**
     * Konstruktor mit Fehlermeldung und Root-Cause.
     *
     * @param message Die Fehlermeldung
     * @param cause   Die ursprüngliche Exception
     */
    public InvalidClubException(String message, Throwable cause) {
        super(message, cause);
    }
}
