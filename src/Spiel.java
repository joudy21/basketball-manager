package Start;

/**
 * Entität: Spiel
 * Repräsentiert ein Basketball-Spiel zwischen zwei Teams.
 * 
 * @author Schülerprojekt Basketball-Manager
 * @version 1.0
 */
public class Spiel {
    private String heimTeam;
    private String gastTeam;

    /**
     * Erstellt ein neues Spiel mit Validierung.
     * 
     * @param heimTeam Name des Heimteams (nicht null, nicht leer)
     * @param gastTeam Name des Gastteams (nicht null, nicht leer)
     * @throws IllegalArgumentException wenn Teams ungültig
     */
    public Spiel(String heimTeam, String gastTeam) throws IllegalArgumentException {
        if (heimTeam == null || heimTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Heimteam darf nicht leer sein!");
        }
        if (gastTeam == null || gastTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Gastteam darf nicht leer sein!");
        }
        if (heimTeam.trim().equalsIgnoreCase(gastTeam.trim())) {
            throw new IllegalArgumentException("Heim- und Gastteam dürfen nicht gleich sein!");
        }
        
        this.heimTeam = heimTeam.trim();
        this.gastTeam = gastTeam.trim();
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
     * @return String-Repräsentation: "TeamA vs TeamB"
     */
    @Override
    public String toString() {
        return heimTeam + " vs " + gastTeam;
    }
}