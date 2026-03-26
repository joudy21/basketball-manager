
/**
 * Entität: Zeitpunkt
 * Repräsentiert einen Zeitpunkt für ein Spiel (Datum und Uhrzeit).
 * 
 * @author Schülerprojekt Basketball-Manager
 * @version 1.0
 */
public class Zeitpunkt {
    private String datum;      // Format: YYYY-MM-DD
    private String uhrzeit;    // Format: HH:MM

    /**
     * Erstellt einen neuen Zeitpunkt mit Validierung.
     * 
     * @param datum Spieldatum (Format: YYYY-MM-DD)
     * @param uhrzeit Spielzeit (Format: HH:MM)
     * @throws IllegalArgumentException wenn Parameter ungültig
     */
    public Zeitpunkt(String datum, String uhrzeit) throws IllegalArgumentException {
        if (datum == null || datum.trim().isEmpty()) {
            throw new IllegalArgumentException("Datum darf nicht leer sein!");
        }
        if (uhrzeit == null || uhrzeit.trim().isEmpty()) {
            throw new IllegalArgumentException("Uhrzeit darf nicht leer sein!");
        }
        
        this.datum = datum.trim();
        this.uhrzeit = uhrzeit.trim();
    }

    /**
     * @return Das Spieldatum
     */
    public String getDatum() {
        return datum;
    }

    /**
     * @return Die Spielzeit
     */
    public String getUhrzeit() {
        return uhrzeit;
    }

    /**
     * Setzt ein neues Datum mit Validierung.
     * 
     * @param datum neues Datum (YYYY-MM-DD)
     * @throws IllegalArgumentException wenn leer
     */
    public void setDatum(String datum) throws IllegalArgumentException {
        if (datum == null || datum.trim().isEmpty()) {
            throw new IllegalArgumentException("Datum darf nicht leer sein!");
        }
        this.datum = datum.trim();
    }

    /**
     * Setzt eine neue Uhrzeit mit Validierung.
     * 
     * @param uhrzeit neue Uhrzeit (HH:MM)
     * @throws IllegalArgumentException wenn leer
     */
    public void setUhrzeit(String uhrzeit) throws IllegalArgumentException {
        if (uhrzeit == null || uhrzeit.trim().isEmpty()) {
            throw new IllegalArgumentException("Uhrzeit darf nicht leer sein!");
        }
        this.uhrzeit = uhrzeit.trim();
    }

    @Override
    public String toString() {
        return datum + " um " + uhrzeit + " Uhr";
    }
}