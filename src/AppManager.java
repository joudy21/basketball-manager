
import java.util.ArrayList;

/**
 * AppManager: Model Layer
 * Zentrale Verwaltung für alle Anwendungsdaten und Geschäftslogik.
 * 
 * Verantwortung:
 * - Club-Verwaltung (Grunddaten, Spieler)
 * - Spiel-Verwaltung (Dokumentation)
 * - Datenkonsistenz
 * 
 * @author Basketball-Manager Projekt
 * @version 1.0
 */
public class AppManager {
    private Club club;
    private ArrayList<Spiel> spiele;

    /**
     * Erstellt einen neuen AppManager mit leeren Datenstrukturen.
     */
    public AppManager() {
        reset();
    }

    /**
     * Setzt alle Daten zurueck (Reset).
     */
    public void reset() {
        this.club = new Club();
        this.spiele = new ArrayList<>();
    }

    /**
     * Gibt den verwalteten Club zurueck.
     * @return Club-Objekt
     */
    public Club getClub() {
        return club;
    }

    /**
     * Fuegt einen Spieler zum Club hinzu.
     * @param spieler der neue Spieler (nicht null)
     * @throws IllegalArgumentException wenn spieler invalid
     */
    public void addSpieler(Spieler spieler) throws IllegalArgumentException {
        if (spieler == null) {
            throw new IllegalArgumentException("Spieler darf nicht null sein!");
        }
        club.addSpieler(spieler);
    }

    /**
     * Fuegt ein Spiel zur Liste hinzu.
     * @param spiel das neue Spiel (nicht null)
     * @throws IllegalArgumentException wenn spiel invalid
     */
    public void addSpiel(Spiel spiel) throws IllegalArgumentException {
        if (spiel == null) {
            throw new IllegalArgumentException("Spiel darf nicht null sein!");
        }
        spiele.add(spiel);
    }

    /**
     * Gibt eine Kopie aller Spiele zurueck.
     * @return ArrayList mit Spielen
     */
    public ArrayList<Spiel> getSpiele() {
        return new ArrayList<>(spiele);
    }

    /**
     * Gibt die Anzahl der Spiele zurueck.
     * @return Anzahl der Spiele
     */
    public int getAnzahlSpiele() {
        return spiele.size();
    }

    /**
     * Gibt die Liste der Spieler des Clubs zurueck.
     * @return Liste der Spieler
     */
    public ArrayList<Spieler> getSpieler() {
        return club.getSpieler();
    }

    /**
     * Ersetzt den aktuellen Club durch einen neuen.
     * @param club neuer Club (nicht null)
     * @throws IllegalArgumentException wenn club null ist
     */
    public void setClub(Club club) throws IllegalArgumentException {
        if (club == null) {
            throw new IllegalArgumentException("Club darf nicht null sein!");
        }
        this.club = club;
    }
}
