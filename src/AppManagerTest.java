
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/**
 * JUnit Test Suite für die AppManager-Klasse (Model Layer).
 * Testet Datenverwaltung, Geschäftslogik und Zustandverwaltung.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class AppManagerTest {
    private AppManager manager;
    private Club testClub;
    private Spieler spieler1;
    private Spieler spieler2;
    private Spiel spiel1;

    @Before
    public void setUp() {
        manager = new AppManager();
        testClub = new Club("Test Club", 2020, 1);
        spieler1 = new Spieler("Max", "Mustermann", 10);
        spieler2 = new Spieler("Anna", "Schmidt", 23);
        spiel1 = new Spiel("Team A", "Team B", 85, 80);
    }

    // ===== INITIALIZATION TESTS =====

    @Test
    public void testInitialState() {
        assertNotNull(manager.getClub());
        assertNotNull(manager.getSpiele());
        assertEquals(0, manager.getSpiele().size());
    }

    @Test
    public void testReset() {
        manager.addSpieler(spieler1);
        manager.addSpiel(spiel1);
        
        manager.reset();
        
        assertEquals(0, manager.getClub().getAnzahlSpieler());
        assertEquals(0, manager.getSpiele().size());
    }

    // ===== CLUB MANAGEMENT TESTS =====

    @Test
    public void testSetClub() {
        assertNotNull(manager.getClub());
        manager.setClub(testClub);
        assertEquals(testClub, manager.getClub());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetClubNull() {
        manager.setClub(null);
    }

    @Test
    public void testGetClub() {
        Club club = manager.getClub();
        assertNotNull(club);
    }

    // ===== SPIELER MANAGEMENT TESTS =====

    @Test
    public void testAddSpieler() {
        manager.addSpieler(spieler1);
        assertEquals(1, manager.getClub().getAnzahlSpieler());
    }

    @Test
    public void testAddMultipleSpieler() {
        manager.addSpieler(spieler1);
        manager.addSpieler(spieler2);
        assertEquals(2, manager.getClub().getAnzahlSpieler());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullSpieler() {
        manager.addSpieler(null);
    }

    @Test
    public void testAddDuplicateSpieler() {
        manager.addSpieler(spieler1);
        manager.addSpieler(spieler1);
        // Club sollte beide hinzufügen (kein Unique-Constraint)
        assertEquals(2, manager.getClub().getAnzahlSpieler());
    }

    @Test
    public void testGetSpieler() {
        manager.addSpieler(spieler1);
        manager.addSpieler(spieler2);
        
        List<Spieler> spieler = manager.getSpieler();
        assertEquals(2, spieler.size());
        assertTrue(spieler.contains(spieler1));
        assertTrue(spieler.contains(spieler2));
    }

    @Test
    public void testGetSpielerEmpty() {
        List<Spieler> spieler = manager.getSpieler();
        assertEquals(0, spieler.size());
    }

    // ===== SPIEL MANAGEMENT TESTS =====

    @Test
    public void testAddSpiel() {
        manager.addSpiel(spiel1);
        assertEquals(1, manager.getSpiele().size());
    }

    @Test
    public void testAddMultipleSpiele() {
        Spiel s2 = new Spiel("C", "D", 100, 95);
        manager.addSpiel(spiel1);
        manager.addSpiel(s2);
        assertEquals(2, manager.getSpiele().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullSpiel() {
        manager.addSpiel(null);
    }

    @Test
    public void testGetSpiele() {
        manager.addSpiel(spiel1);
        List<Spiel> spiele = manager.getSpiele();
        assertEquals(1, spiele.size());
        assertTrue(spiele.contains(spiel1));
    }

    @Test
    public void testGetSpieleEmpty() {
        List<Spiel> spiele = manager.getSpiele();
        assertEquals(0, spiele.size());
    }

    @Test
    public void testGetSpieleReturnsNewArrayList() {
        manager.addSpiel(spiel1);
        List<Spiel> list1 = manager.getSpiele();
        List<Spiel> list2 = manager.getSpiele();
        assertNotSame(list1, list2);  // Verschiedene Instanzen
        assertEquals(list1.size(), list2.size());  // Gleicher Inhalt
    }

    @Test
    public void testModifyReturnedSpielListDoesNotAffectManager() {
        manager.addSpiel(spiel1);
        List<Spiel> list = manager.getSpiele();
        list.clear();
        assertEquals(1, manager.getSpiele().size());  // Original unverändert
    }

    // ===== INTEGRATION TESTS =====

    @Test
    public void testCompleteWorkflow() {
        // 1. Spieler hinzufügen
        manager.addSpieler(spieler1);
        manager.addSpieler(spieler2);
        assertEquals(2, manager.getSpieler().size());
        
        // 2. Spiele hinzufügen
        manager.addSpiel(spiel1);
        assertEquals(1, manager.getSpiele().size());
        
        // 3. Reset
        manager.reset();
        assertEquals(0, manager.getSpieler().size());
        assertEquals(0, manager.getSpiele().size());
    }

    @Test
    public void testStatePersistenceBetweenCalls() {
        manager.addSpieler(spieler1);
        manager.addSpiel(spiel1);
        
        // Weitere Calls sollten beide Objekte enthalten
        assertEquals(1, manager.getSpieler().size());
        assertEquals(1, manager.getSpiele().size());
        
        // Neuer Call sollte noch immer beide enthalten
        assertEquals(1, manager.getSpieler().size());
        assertEquals(1, manager.getSpiele().size());
    }
}
