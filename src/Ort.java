package Start;

/**
 * Entität: Ort
 * Repräsentiert einen Basketball-Spielort (Stadion/Halle).
 * 
 * @author Schülerprojekt Basketball-Manager
 * @version 1.0
 */
public class Ort {
    private String stadion;

    /**
     * Erstellt einen neuen Spielort mit Validierung.
     * 
     * @param stadion Name des Stadions/der Halle
     * @throws IllegalArgumentException wenn stadion null oder leer
     */
    public Ort(String stadion) throws IllegalArgumentException {
        if (stadion == null || stadion.trim().isEmpty()) {
            throw new IllegalArgumentException("Stadion-Name darf nicht leer sein!");
        }
        this.stadion = stadion.trim();
    }

    /**
     * @return Der Name des Stadions
     */
    public String getStadion() {
        return stadion;
    }

    /**
     * Setzt einen neuen Stadion-Namen mit Validierung.
     * 
     * @param stadion neuer Stadion-Name
     * @throws IllegalArgumentException wenn leer
     */
    public void setStadion(String stadion) throws IllegalArgumentException {
        if (stadion == null || stadion.trim().isEmpty()) {
            throw new IllegalArgumentException("Stadion-Name darf nicht leer sein!");
        }
        this.stadion = stadion.trim();
    }

    @Override
    public String toString() {
        return "Spielort: " + stadion;
    }
}