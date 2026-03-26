/**
 * PersistenceManager für JSON-Speicherung und -Laden mithilfe von Gson.
 *
 * Anforderungen: 1a.3 Persistierung von Anwenderdaten
 *
 * Author: Basketball-Manager Team
 * Version: 1.0
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenceManager {
    private final Gson gson;

    public PersistenceManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void save(AppManager manager, String filePath) throws PersistenceException {
        if (manager == null) {
            throw new PersistenceException("AppManager darf nicht null sein.");
        }
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(manager, writer);
        } catch (IOException e) {
            throw new PersistenceException("Fehler beim Speichern der Datei: " + e.getMessage(), e);
        }
    }

    public void load(AppManager manager, String filePath) throws PersistenceException {
        if (manager == null) {
            throw new PersistenceException("AppManager darf nicht null sein.");
        }
        try (FileReader reader = new FileReader(filePath)) {
            AppManager loaded = gson.fromJson(reader, AppManager.class);
            if (loaded == null || loaded.getClub() == null) {
                throw new PersistenceException("Ungültige Daten in Persistenzdatei.");
            }
            manager.reset();
            manager.setClub(loaded.getClub());
            for (Spiel spiel : loaded.getSpiele()) {
                manager.addSpiel(spiel);
            }
        } catch (IOException e) {
            throw new PersistenceException("Fehler beim Laden der Datei: " + e.getMessage(), e);
        }
    }
}
