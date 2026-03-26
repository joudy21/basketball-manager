
/**
 * AppController: Controller Layer
 * 
 * Verantwortung:
 * - Orchestrierung zwischen View (Eingabe) und Model (Daten)
 * - Input-Validierung
 * - Fehlerbehandlung mit aussagekraeftigen Meldungen
 * - Geschaeftslogik-Koordination
 * 
 * @author Basketball-Manager Projekt
 * @version 1.0
 */
public class AppController {
    private AppManager model;
    private View view;

    /**
     * Erstellt einen neuen Controller mit Model und View.
     * @param model das Model (Daten)
     * @param view die View (GUI oder Test-Stub)
     */
    public AppController(AppManager model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Initialisiert den Controller (Verbindung View <- Controller etablieren).
     */
    public void init() {
        view.setController(this);
    }

    /**
     * Speichert Club-Daten nach Validierung.
     * 
     * @param name Club-Name
     * @param jahr Gruendungsjahr
     * @param id Club-ID (muss Integer sein)
     */
    public void saveClub(String name, String jahr, int id) {
        String errorMsg = validateNotEmpty(name, "Club-Name");
        if (errorMsg != null) {
            view.showMessage("FEHLER: " + errorMsg);
            return;
        }

        try {
            model.getClub().setClubName(name);
            model.getClub().setGruendungsjahr(jahr);
            model.getClub().setIdC(id);
            view.showMessage("Club aktualisiert: " + model.getClub());
        } catch (IllegalArgumentException e) {
            view.showMessage("FEHLER: " + e.getMessage());
        }
    }

    /**
     * Speichert einen neuen Spieler nach Validierung.
     * 
     * @param vorname Spieler-Vorname
     * @param nachname Spieler-Nachname
     * @param nummer Rueckennummer
     */
    public void saveSpieler(String vorname, String nachname, int nummer) {
        String errorMsg = validateNotEmpty(vorname, "Vorname");
        if (errorMsg != null) {
            view.showMessage("FEHLER: " + errorMsg);
            return;
        }

        errorMsg = validateNotEmpty(nachname, "Nachname");
        if (errorMsg != null) {
            view.showMessage("FEHLER: " + errorMsg);
            return;
        }

        try {
            Spieler spieler = new Spieler(vorname, nachname, nummer);
            model.addSpieler(spieler);
            view.showMessage("Spieler registriert: " + spieler);
        } catch (IllegalArgumentException e) {
            view.showMessage("FEHLER: " + e.getMessage());
        }
    }

    /**
     * Speichert ein neues Spiel nach Validierung.
     * 
     * @param heimTeam das Heimteam
     * @param gastTeam das Gastteam
     */
    public void saveSpiel(String heimTeam, String gastTeam) {
        String errorMsg = validateNotEmpty(heimTeam, "Heimteam");
        if (errorMsg != null) {
            view.showMessage("FEHLER: " + errorMsg);
            return;
        }

        errorMsg = validateNotEmpty(gastTeam, "Gastteam");
        if (errorMsg != null) {
            view.showMessage("FEHLER: " + errorMsg);
            return;
        }

        try {
            Spiel spiel = new Spiel(heimTeam, gastTeam);
            model.addSpiel(spiel);
            view.showMessage("Spiel eingegeben: " + spiel);
        } catch (IllegalArgumentException e) {
            view.showMessage("FEHLER: " + e.getMessage());
        }
    }

    /**
     * Setzt alle Daten zurueck (mit Bestaetigung).
     */
    public void resetAll() {
        model.reset();
        view.clearAllFields();
        view.clearOutput();
        view.showMessage("SYSTEM: Alle Daten wurden geloescht.");
    }

    /**
     * Zentrale Validierungshilfe: Prueft auf leere Strings.
     * 
     * @param value zu pruefer Wert
     * @param fieldName Feldbezeichnung fuer Fehlermeldung
     * @return null wenn valid, sonst Fehlermeldung
     */
    public String validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            return fieldName + " darf nicht leer sein!";
        }
        return null;
    }

    /**
     * Ueberladung von saveClub fuer String-basierte Parameter (fuer Tests).
     * Konvertiert Strings in die korrekten Typen.
     * 
     * @param name Club-Name
     * @param jahr Gruendungsjahr als String
     * @param id Club-ID als String
     */
    public void saveClub(String name, String jahr, String id) {
        try {
            int idInt = Integer.parseInt(id);
            int jahrInt = Integer.parseInt(jahr);
            saveClub(name, jahr, idInt);
        } catch (NumberFormatException e) {
            view.showMessage("FEHLER: Ungültige Nummer eingegeben!");
        }
    }

    /**
     * Ueberladung von saveSpieler fuer String-basierte Parameter (fuer Tests).
     * Konvertiert Strings in die korrekten Typen.
     * 
     * @param vorname Vorname des Spielers
     * @param nachname Nachname des Spielers
     * @param nummer Jersey-Nummer als String
     */
    public void saveSpieler(String vorname, String nachname, String nummer) {
        try {
            int nummerInt = Integer.parseInt(nummer);
            saveSpieler(vorname, nachname, nummerInt);
        } catch (NumberFormatException e) {
            view.showMessage("FEHLER: Jersey-Nummer muss eine Zahl sein!");
        }
    }

    /**
     * Ueberladung von saveSpiel mit Punkte-Parametern (fuer Tests).
     * 
     * @param heimTeam Heim-Team-Name
     * @param gastTeam Gast-Team-Name
     * @param punkteHeim Heimteam-Punkte als String
     * @param punkteGast Gastteam-Punkte als String
     */
    public void saveSpiel(String heimTeam, String gastTeam, String punkteHeim, String punkteGast) {
        String errorMsg = validateNotEmpty(heimTeam, "Heimteam");
        if (errorMsg != null) {
            view.showMessage("FEHLER: " + errorMsg);
            return;
        }

        errorMsg = validateNotEmpty(gastTeam, "Gastteam");
        if (errorMsg != null) {
            view.showMessage("FEHLER: " + errorMsg);
            return;
        }

        try {
            int hPunkte = Integer.parseInt(punkteHeim);
            int gPunkte = Integer.parseInt(punkteGast);
            Spiel spiel = new Spiel(heimTeam, gastTeam, hPunkte, gPunkte);
            model.addSpiel(spiel);
            view.showMessage("Spiel eingegeben: " + spiel);
        } catch (NumberFormatException e) {
            view.showMessage("FEHLER: Punkte müssen Zahlen sein!");
        } catch (IllegalArgumentException e) {
            view.showMessage("FEHLER: " + e.getMessage());
        }
    }
}
