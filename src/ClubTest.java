
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit Test Suite für die Club-Klasse.
 * Testet Validierung, Spieler-Verwaltung, Sortierung und Getter/Setter.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class ClubTest {
    private Club club;
    private Spieler spieler1;
    private Spieler spieler2;

    @Before
    public void setUp() {
        club = new Club("FC Bayern Munich", 1972, 1);
        spieler1 = new Spieler("Manuel", "Neuer", 1);
        spieler2 = new Spieler("Joshua", "Kimmich", 32);
    }

    // ===== KONSTRUKTOR TESTS =====

    @Test
    public void testValidKonstruktor() {
        assertEquals("FC Bayern Munich", club.getClubName());
        assertEquals("1972", club.getGruendungsjahr());
        assertEquals(1, club.getIdC());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNullClubName() {
        new Club(null, 1972, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitLeeremClubName() {
        new Club("", 1972, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNegativerID() {
        new Club("Test Club", 1972, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitIDNull() {
        new Club("Test Club", 1972, 0);
    }

    // ===== SETTER TESTS =====

    @Test
    public void testSetClubNameValid() {
        club.setClubName("Borussia Dortmund");
        assertEquals("Borussia Dortmund", club.getClubName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetClubNameNull() {
        club.setClubName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetClubNameLeer() {
        club.setClubName("");
    }

    @Test
    public void testSetGruendungsjahrValid() {
        club.setGruendungsjahr(1950);
        assertEquals("1950", club.getGruendungsjahr());
    }

    @Test
    public void testSetIdCValid() {
        club.setIdC(42);
        assertEquals(42, club.getIdC());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIdCNegativ() {
        club.setIdC(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIdCZero() {
        club.setIdC(0);
    }

    // ===== SPIELER VERWALTUNG TESTS =====

    @Test
    public void testAddSpieler() {
        club.addSpieler(spieler1);
        assertEquals(1, club.getAnzahlSpieler());
    }

    @Test
    public void testAddMultipleSpieler() {
        club.addSpieler(spieler1);
        club.addSpieler(spieler2);
        assertEquals(2, club.getAnzahlSpieler());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullSpieler() {
        club.addSpieler(null);
    }

    @Test
    public void testRemoveSpieler() {
        club.addSpieler(spieler1);
        club.addSpieler(spieler2);
        club.removeSpieler(spieler1);
        assertEquals(1, club.getAnzahlSpieler());
    }

    @Test
    public void testGetSpielerReturnsNewArrayList() {
        club.addSpieler(spieler1);
        List<Spieler> list1 = club.getSpieler();
        List<Spieler> list2 = club.getSpieler();
        assertNotSame(list1, list2);  // Unterschiedliche Instanzen
        assertEquals(list1.size(), list2.size());  // Aber gleicher Inhalt
    }

    @Test
    public void testModifyReturnedListDoesNotAffectClub() {
        club.addSpieler(spieler1);
        List<Spieler> list = club.getSpieler();
        list.clear();
        assertEquals(1, club.getAnzahlSpieler());  // Original unverändert
    }

    @Test
    public void testGetAnzahlSpieler() {
        assertEquals(0, club.getAnzahlSpieler());
        club.addSpieler(spieler1);
        assertEquals(1, club.getAnzahlSpieler());
        club.addSpieler(spieler2);
        assertEquals(2, club.getAnzahlSpieler());
    }

    // ===== SORTIERUNGS TESTS =====

    @Test
    public void testGetSpielerSortiertnachNachname() {
        club.addSpieler(spieler2);  // Joshua Kimmich
        club.addSpieler(spieler1);  // Manuel Neuer
        
        List<Spieler> sorted = club.getSpielerSortiertnachNachname();
        assertEquals("Kimmich", sorted.get(0).getNachname());
        assertEquals("Neuer", sorted.get(1).getNachname());
    }

    @Test
    public void testGetSpielerSortiertnachNummer() {
        club.addSpieler(spieler1);  // Nr. 1
        club.addSpieler(spieler2);  // Nr. 32
        
        List<Spieler> sorted = club.getSpielerSortiertnachNummer();
        assertEquals(1, sorted.get(0).getNummer());
        assertEquals(32, sorted.get(1).getNummer());
    }

    @Test
    public void testSortierungAlphabetischAufsteigend() {
        Spieler s3 = new Spieler("Leon", "Goretzka", 18);
        club.addSpieler(spieler2);  // Kimmich
        club.addSpieler(s3);        // Goretzka
        club.addSpieler(spieler1);  // Neuer
        
        List<Spieler> sorted = club.getSpielerSortiertnachNachname();
        assertEquals("Goretzka", sorted.get(0).getNachname());
        assertEquals("Kimmich", sorted.get(1).getNachname());
        assertEquals("Neuer", sorted.get(2).getNachname());
    }

    // ===== EQUALS / HASHCODE TESTS =====

    @Test
    public void testEqualsIdentical() {
        Club c2 = new Club("FC Bayern Munich", 1972, 1);
        assertEquals(club, c2);
    }

    @Test
    public void testEqualsVerschiedeneNamen() {
        Club c2 = new Club("Borussia Dortmund", 1972, 1);
        assertNotEquals(club, c2);
    }

    @Test
    public void testEqualsSelf() {
        assertEquals(club, club);
    }

    // ===== GETTER TESTS =====

    @Test
    public void testGetClubName() {
        assertEquals("FC Bayern Munich", club.getClubName());
    }

    @Test
    public void testGetGruendungsjahr() {
        assertEquals("1972", club.getGruendungsjahr());
    }

    @Test
    public void testGetIdC() {
        assertEquals(1, club.getIdC());
    }

    @Test
    public void testToString() {
        String result = club.toString();
        assertNotNull(result);
        assertTrue(result.contains("FC Bayern Munich"));
    }
}
