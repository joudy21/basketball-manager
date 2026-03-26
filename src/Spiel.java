
/**
 * Entität: Spiel
 * Repräsentiert ein Basketball-Spiel zwischen zwei Teams mit Punkten.
 * 
 * @author Schülerprojekt Basketball-Manager
 * @version 1.0
 */
public class Spiel {
    private String heimTeam;
    private String gastTeam;
    private int punkteHeim;
    private int punkteGast;

    /**
     * Erstellt ein neues Spiel mit Validierung (nur Team-Namen).
     * 
     * @param heimTeam Name des Heimteams (nicht null, nicht leer)
     * @param gastTeam Name des Gastteams (nicht null, nicht leer)
     * @throws IllegalArgumentException wenn Teams ungültig
     */
    public Spiel(String heimTeam, String gastTeam) throws IllegalArgumentException {
        this(heimTeam, gastTeam, 0, 0);
    }

    /**
     * Erstellt ein neues Spiel mit Validierung (mit Punkten).
     * 
     * @param heimTeam Name des Heimteams (nicht null, nicht leer)
     * @param gastTeam Name des Gastteams (nicht null, nicht leer)
     * @param punkteHeim Punkte des Heimteams (>= 0)
     * @param punkteGast Punkte des Gastteams (>= 0)
     * @throws IllegalArgumentException wenn Teams ungültig oder Punkte negativ
     */
    public Spiel(String heimTeam, String gastTeam, int punkteHeim, int punkteGast) throws IllegalArgumentException {
        if (heimTeam == null || heimTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Heimteam darf nicht leer sein!");
        }
        if (gastTeam == null || gastTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Gastteam darf nicht leer sein!");
        }
        if (heimTeam.trim().equalsIgnoreCase(gastTeam.trim())) {
            throw new IllegalArgumentException("Heim- und Gastteam dürfen nicht gleich sein!");
        }
        if (punkteHeim < 0 || punkteGast < 0) {
            throw new IllegalArgumentException("Punkte dürfen nicht negativ sein!");
        }
        
        this.heimTeam = heimTeam.trim();
        this.gastTeam = gastTeam.trim();
        this.punkteHeim = punkteHeim;
        this.punkteGast = punkteGast;
    }

    /**
     * @return Das Heimteam
     */
    public String getHeimTeam() {
        return heimTeam;
    }

    /**
     * @return Das Gastteam
     */
    public String getGastTeam() {
        return gastTeam;
    }

    /**
     * Setzt das Heimteam.
     * @param heimTeam das neue Heimteam
     * @throws IllegalArgumentException wenn ungültig
     */
    public void setHeimmannschaft(String heimTeam) throws IllegalArgumentException {
        if (heimTeam == null || heimTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Heimteam darf nicht leer sein!");
        }
        this.heimTeam = heimTeam.trim();
    }

    /**
     * Setzt das Gastteam.
     * @param gastTeam das neue Gastteam
     * @throws IllegalArgumentException wenn ungültig
     */
    public void setGastmannschaft(String gastTeam) throws IllegalArgumentException {
        if (gastTeam == null || gastTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Gastteam darf nicht leer sein!");
        }
        this.gastTeam = gastTeam.trim();
    }

    /**
     * @return Punkte des Heimteams
     */
    public int getPunkteHeim() {
        return punkteHeim;
    }

    /**
     * Setzt Punkte des Heimteams.
     * @param punkte die Punkte (>= 0)
     * @throws IllegalArgumentException wenn negativ
     */
    public void setPunkteHeim(int punkte) throws IllegalArgumentException {
        if (punkte < 0) {
            throw new IllegalArgumentException("Punkte dürfen nicht negativ sein!");
        }
        this.punkteHeim = punkte;
    }

    /**
     * @return Punkte des Gastteams
     */
    public int getPunkteGast() {
        return punkteGast;
    }

    /**
     * Setzt Punkte des Gastteams.
     * @param punkte die Punkte (>= 0)
     * @throws IllegalArgumentException wenn negativ
     */
    public void setPunkteGast(int punkte) throws IllegalArgumentException {
        if (punkte < 0) {
            throw new IllegalArgumentException("Punkte dürfen nicht negativ sein!");
        }
        this.punkteGast = punkte;
    }

    /**
     * Prüft, ob das Heimteam gewonnen hat.
     * @return true wenn Heimteam mehr Punkte
     */
    public boolean isHeimWin() {
        return punkteHeim > punkteGast;
    }

    /**
     * Prüft, ob das Gastteam gewonnen hat.
     * @return true wenn Gastteam mehr Punkte
     */
    public boolean isGastWin() {
        return punkteGast > punkteHeim;
    }

    /**
     * @return String-Repräsentation: "TeamA (Punkte) vs TeamB (Punkte)"
     */
    @Override
    public String toString() {
        return heimTeam + " (" + punkteHeim + ") vs " + gastTeam + " (" + punkteGast + ")";
    }

    /**
     * Prüft, ob zwei Spiele identisch sind.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Spiel other = (Spiel) obj;
        return heimTeam.equals(other.heimTeam) && gastTeam.equals(other.gastTeam) 
            && punkteHeim == other.punkteHeim && punkteGast == other.punkteGast;
    }

    /**
     * Hashcode basierend auf Team-Namen und Punkten.
     */
    @Override
    public int hashCode() {
        return (heimTeam + gastTeam + punkteHeim + punkteGast).hashCode();
    }
}