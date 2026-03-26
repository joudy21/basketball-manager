package Start;

/**
 * Entität: Spieler
 * Repräsentiert einen Basketballspieler mit Basis-Daten.
 * 
 * @author Schülerprojekt Basketball-Manager
 * @version 1.0
 */
public class Spieler {
    private String vorname;
    private String nachname;
    private int nummer;

    /**
     * Erstellt einen neuen Spieler mit Validierung.
     * 
     * @param vorname Spieler-Vorname (nicht null, nicht leer)
     * @param nachname Spieler-Nachname (nicht null, nicht leer)
     * @param nummer Rückennummer (1 bis 99)
     * @throws IllegalArgumentException wenn Input ungültig
     */
    public Spieler(String vorname, String nachname, int nummer) 
            throws IllegalArgumentException {
        
        // Validierung
        if (vorname == null || vorname.trim().isEmpty()) {
            throw new IllegalArgumentException("Vorname darf nicht leer sein!");
        }
        if (nachname == null || nachname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nachname darf nicht leer sein!");
        }
        if (nummer < 1 || nummer > 99) {
            throw new IllegalArgumentException(
                "Rückennummer muss zwischen 1 und 99 sein, erhalten: " + nummer);
        }
        
        // Zuweisung (sicher, weil validiert)
        this.vorname = vorname.trim();
        this.nachname = nachname.trim();
        this.nummer = nummer;
    }

    /**
     * @return Der Vorname des Spielers
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * @return Der Nachname des Spielers
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * @return Die Rückennummer des Spielers
     */
    public int getNummer() {
        return nummer;
    }

    /**
     * Setzt einen neuen Vornamen (mit Validierung).
     * 
     * @param vorname neuer Vorname
     * @throws IllegalArgumentException wenn leer
     */
    public void setVorname(String vorname) throws IllegalArgumentException {
        if (vorname == null || vorname.trim().isEmpty()) {
            throw new IllegalArgumentException("Vorname darf nicht leer sein!");
        }
        this.vorname = vorname.trim();
    }

    /**
     * Setzt einen neuen Nachnamen (mit Validierung).
     */
    public void setNachname(String nachname) throws IllegalArgumentException {
        if (nachname == null || nachname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nachname darf nicht leer sein!");
        }
        this.nachname = nachname.trim();
    }

    /**
     * Setzt eine neue Rückennummer (mit Validierung).
     * 
     * @param nummer neue Nummer (1-99)
     * @throws IllegalArgumentException wenn außer Range
     */
    public void setNummer(int nummer) throws IllegalArgumentException {
        if (nummer < 1 || nummer > 99) {
            throw new IllegalArgumentException(
                "Rückennummer muss zwischen 1 und 99 sein!");
        }
        this.nummer = nummer;
    }

    /**
     * @return String-Repräsentation: "Vorname Nachname (Nr. 7)"
     */
    @Override
    public String toString() {
        return vorname + " " + nachname + " (Nr. " + nummer + ")";
    }

    /**
     * Vergleicht zwei Spieler basierend auf Vorname, Nachname und Nummer.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spieler)) return false;
        Spieler spieler = (Spieler) o;
        return nummer == spieler.nummer &&
                vorname.equals(spieler.vorname) &&
                nachname.equals(spieler.nachname);
    }

    /**
     * @return Hash-Code für Verwendung in HashMaps, Sets, etc.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
        result = prime * result + ((nachname == null) ? 0 : nachname.hashCode());
        result = prime * result + nummer;
        return result;
    }
}