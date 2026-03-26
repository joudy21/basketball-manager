
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Club: Entity Klasse
 * Verwaltet einen Basketball-Verein mit seinen Spielern, Teams und Daten.
 * 
 * Verantwortung:
 * - Vereins-Daten (Name, ID, Gruendungsjahr)
 * - Spieler-Verwaltung (hinzufuegen, entfernen, sortieren)
 * - Team-Zusammenstellung (Mannschaften)
 * 
 * @author Basketball-Manager Projekt
 * @version 1.0
 */
public class Club {
    private String clubName;
    private String gruendungsjahr;
    private int idC;
    private ArrayList<Spieler> spielerListe;

    /**
     * Erstellt einen neuen Club mit leeren Strukturen.
     */
    public Club() {
        this.clubName = "";
        this.gruendungsjahr = "";
        this.idC = 0;
        this.spielerListe = new ArrayList<>();
    }

    /**
     * Erstellt einen neuen Club mit Initialwerten (für Tests).
     * @param clubName der Club-Name
     * @param gruendungsjahr das Gründungsjahr
     * @param idC die Club-ID
     */
    public Club(String clubName, int gruendungsjahr, int idC) {
        if (clubName == null || clubName.trim().isEmpty()) {
            throw new IllegalArgumentException("Club-Name darf nicht leer sein!");
        }
        if (idC <= 0) {
            throw new IllegalArgumentException("Club-ID muss > 0 sein!");
        }
        this.clubName = clubName.trim();
        this.gruendungsjahr = String.valueOf(gruendungsjahr);
        this.idC = idC;
        this.spielerListe = new ArrayList<>();
    }

    /**
     * Erstellt einen neuen Club mit Initialwerten (Überlad für int vs String Jahr).
     * @param clubName der Club-Name
     * @param gruendungsjahr das Gründungsjahr als String
     * @param idC die Club-ID
     */
    public Club(String clubName, String gruendungsjahr, int idC) {
        if (clubName == null || clubName.trim().isEmpty()) {
            throw new IllegalArgumentException("Club-Name darf nicht leer sein!");
        }
        if (idC <= 0) {
            throw new IllegalArgumentException("Club-ID muss > 0 sein!");
        }
        this.clubName = clubName.trim();
        this.gruendungsjahr = gruendungsjahr != null ? gruendungsjahr.trim() : "";
        this.idC = idC;
        this.spielerListe = new ArrayList<>();
    }

    /**
     * Setzt den Club-Namen (mit Validierung).
     * @param clubName der neue Name
     * @throws IllegalArgumentException wenn leer
     */
    public void setClubName(String clubName) throws IllegalArgumentException {
        if (clubName == null || clubName.trim().isEmpty()) {
            throw new IllegalArgumentException("Club-Name darf nicht leer sein!");
        }
        this.clubName = clubName.trim();
    }

    /**
     * @return Der Name des Clubs
     */
    public String getClubName() {
        return clubName;
    }

    /**
     * Setzt das Gruendungsjahr.
     * @param gruendungsjahr das Jahr als String
     */
    public void setGruendungsjahr(String gruendungsjahr) {
        if (gruendungsjahr != null) {
            this.gruendungsjahr = gruendungsjahr.trim();
        }
    }

    /**
     * Setzt das Gruendungsjahr.
     * @param gruendungsjahr das Jahr als Ganzzahl
     */
    public void setGruendungsjahr(int gruendungsjahr) {
        this.gruendungsjahr = String.valueOf(gruendungsjahr);
    }

    /**
     * @return Das Gruendungsjahr
     */
    public String getGruendungsjahr() {
        return gruendungsjahr;
    }

    /**
     * Setzt die Club-ID (mit Validierung).
     * @param idC die neue ID (muss > 0 sein)
     * @throws IllegalArgumentException wenn ungueltig
     */
    public void setIdC(int idC) throws IllegalArgumentException {
        if (idC <= 0) {
            throw new IllegalArgumentException("Club-ID muss > 0 sein!");
        }
        this.idC = idC;
    }

    /**
     * @return Die Club-ID
     */
    public int getIdC() {
        return idC;
    }

    /**
     * Fuegt einen Spieler zum Club hinzu.
     * @param spieler der neue Spieler
     * @throws IllegalArgumentException wenn spieler null
     */
    public void addSpieler(Spieler spieler) throws IllegalArgumentException {
        if (spieler == null) {
            throw new IllegalArgumentException("Spieler darf nicht null sein!");
        }
        spielerListe.add(spieler);
    }

    /**
     * @return Eine Kopie der Spielerliste
     */
    public ArrayList<Spieler> getSpieler() {
        return new ArrayList<>(spielerListe);
    }

    /**
     * @return Anzahl der Spieler
     */
    public int getAnzahlSpieler() {
        return spielerListe.size();
    }

    /**
     * Entfernt einen Spieler aus dem Club.
     * @param spieler der zu entfernende Spieler
     * @return true wenn erfolgreich, false sonst
     */
    public boolean removeSpieler(Spieler spieler) {
        return spielerListe.remove(spieler);
    }

    /**
     * @return Spieler sortiert nach Nachname (alphabetisch)
     */
    public ArrayList<Spieler> getSpielerSortiertnachNachname() {
        ArrayList<Spieler> sorted = new ArrayList<>(spielerListe);
        sorted.sort(Comparator.comparing(Spieler::getNachname)
                .thenComparing(Spieler::getVorname));
        return sorted;
    }

    /**
     * @return Spieler sortiert nach Nummer (aufsteigend)
     */
    public ArrayList<Spieler> getSpielerSortiertnachNummer() {
        ArrayList<Spieler> sorted = new ArrayList<>(spielerListe);
        sorted.sort(Comparator.comparingInt(Spieler::getNummer));
        return sorted;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Club other = (Club) obj;
        return idC == other.idC
                && clubName.equals(other.clubName)
                && gruendungsjahr.equals(other.gruendungsjahr)
                && spielerListe.equals(other.spielerListe);
    }

    @Override
    public int hashCode() {
        int result = clubName.hashCode();
        result = 31 * result + gruendungsjahr.hashCode();
        result = 31 * result + Integer.hashCode(idC);
        result = 31 * result + spielerListe.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return clubName + " (" + gruendungsjahr + ")";
    }
}
