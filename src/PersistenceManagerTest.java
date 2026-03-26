import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests für PersistenceManager (JSON) des Basketball Managers.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class PersistenceManagerTest {
    private AppManager manager;
    private PersistenceManager persistenceManager;
    private File tempFile;

    @Before
    public void setUp() {
        manager = new AppManager();
        persistenceManager = new PersistenceManager();
        tempFile = new File("./test_data.json");
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @After
    public void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testSaveAndLoad() throws PersistenceException {
        manager.reset();
        manager.getClub().setClubName("PersistClub");
        manager.getClub().setGruendungsjahr(1901);
        manager.getClub().setIdC(10);

        manager.addSpieler(new Spieler("Max", "Mustermann", 10));
        manager.addSpieler(new Spieler("Anna", "Schmidt", 20));
        manager.addSpiel(new Spiel("Team A", "Team B", 100, 95));

        persistenceManager.save(manager, tempFile.getAbsolutePath());

        AppManager loadedManager = new AppManager();
        persistenceManager.load(loadedManager, tempFile.getAbsolutePath());

        assertEquals("PersistClub", loadedManager.getClub().getClubName());
        assertEquals("1901", loadedManager.getClub().getGruendungsjahr());
        assertEquals(10, loadedManager.getClub().getIdC());

        List<Spieler> loadedSpieler = loadedManager.getSpieler();
        assertEquals(2, loadedSpieler.size());

        List<Spiel> loadedSpiele = loadedManager.getSpiele();
        assertEquals(1, loadedSpiele.size());
    }

    @Test(expected = PersistenceException.class)
    public void testSaveNullManager() throws PersistenceException {
        persistenceManager.save(null, tempFile.getAbsolutePath());
    }

    @Test(expected = PersistenceException.class)
    public void testLoadNonexistentFile() throws PersistenceException {
        persistenceManager.load(manager, "nicht_existierende_datei.json");
    }
}
