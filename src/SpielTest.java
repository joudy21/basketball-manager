
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test Suite für die Spiel-Klasse.
 * Testet Validierung, Getter/Setter und Spielinformationen.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class SpielTest {
    private Spiel spiel;

    @Before
    public void setUp() {
        spiel = new Spiel("Bayern Munich", "Borussia Dortmund", 82, 79);
    }

    // ===== KONSTRUKTOR TESTS =====

    @Test
    public void testValidKonstruktor() {
        assertEquals("Bayern Munich", spiel.getHeimTeam());
        assertEquals("Borussia Dortmund", spiel.getGastTeam());
        assertEquals(82, spiel.getPunkteHeim());
        assertEquals(79, spiel.getPunkteGast());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNullHeim() {
        new Spiel(null, "BVB", 82, 79);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitLeeremHeim() {
        new Spiel("", "BVB", 82, 79);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNullGast() {
        new Spiel("Bayern", null, 82, 79);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitLeeremGast() {
        new Spiel("Bayern", "", 82, 79);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNegativenPunktenHeim() {
        new Spiel("Bayern", "BVB", -5, 79);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNegativenPunktenGast() {
        new Spiel("Bayern", "BVB", 82, -5);
    }

    @Test
    public void testKonstruktorMitNullPunkten() {
        Spiel s = new Spiel("A", "B", 0, 0);
        assertEquals(0, s.getPunkteHeim());
        assertEquals(0, s.getPunkteGast());
    }

    // ===== SETTER TESTS =====

    @Test
    public void testSetHeimmannschaftValid() {
        spiel.setHeimmannschaft("Real Madrid");
        assertEquals("Real Madrid", spiel.getHeimTeam());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHeimmannschaftNull() {
        spiel.setHeimmannschaft(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHeimmannschaftLeer() {
        spiel.setHeimmannschaft("");
    }

    @Test
    public void testSetGastmannschaftValid() {
        spiel.setGastmannschaft("Liverpool");
        assertEquals("Liverpool", spiel.getGastTeam());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetGastmannschaftNull() {
        spiel.setGastmannschaft(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetGastmannschaftLeer() {
        spiel.setGastmannschaft("");
    }

    @Test
    public void testSetPunkteHeimValid() {
        spiel.setPunkteHeim(90);
        assertEquals(90, spiel.getPunkteHeim());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPunkteHeimNegativ() {
        spiel.setPunkteHeim(-1);
    }

    @Test
    public void testSetPunkteGastValid() {
        spiel.setPunkteGast(85);
        assertEquals(85, spiel.getPunkteGast());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPunkteGastNegativ() {
        spiel.setPunkteGast(-1);
    }

    // ===== ERGEBNIS TESTS =====

    @Test
    public void testIsHeimWin() {
        assertTrue(spiel.isHeimWin());
    }

    @Test
    public void testIsHeimWinKnapp() {
        Spiel s = new Spiel("A", "B", 81, 80);
        assertTrue(s.isHeimWin());
    }

    @Test
    public void testIsNotHeimWin() {
        Spiel s = new Spiel("A", "B", 79, 82);
        assertFalse(s.isHeimWin());
    }

    @Test
    public void testIsUnentschieden() {
        Spiel s = new Spiel("A", "B", 80, 80);
        assertFalse(s.isHeimWin());
        assertFalse(s.isGastWin());
    }

    @Test
    public void testIsGastWin() {
        Spiel s = new Spiel("A", "B", 70, 75);
        assertTrue(s.isGastWin());
    }

    @Test
    public void testIsNotGastWin() {
        Spiel s = new Spiel("A", "B", 82, 79);
        assertFalse(s.isGastWin());
    }

    // ===== EQUALS / HASHCODE TESTS =====

    @Test
    public void testEqualsIdentical() {
        Spiel s2 = new Spiel("Bayern Munich", "Borussia Dortmund", 82, 79);
        assertEquals(spiel, s2);
    }

    @Test
    public void testEqualsVerschiedeneHeimmannschaft() {
        Spiel s2 = new Spiel("Real Madrid", "Borussia Dortmund", 82, 79);
        assertNotEquals(spiel, s2);
    }

    @Test
    public void testEqualsVerschiedeneGastmannschaft() {
        Spiel s2 = new Spiel("Bayern Munich", "Liverpool", 82, 79);
        assertNotEquals(spiel, s2);
    }

    @Test
    public void testEqualsSelf() {
        assertEquals(spiel, spiel);
    }

    @Test
    public void testEqualsNull() {
        assertNotEquals(spiel, null);
    }

    // ===== GETTER TESTS =====

    @Test
    public void testGetHeimmannschaft() {
        assertEquals("Bayern Munich", spiel.getHeimTeam());
    }

    @Test
    public void testGetGastmannschaft() {
        assertEquals("Borussia Dortmund", spiel.getGastTeam());
    }

    @Test
    public void testGetPunkteHeim() {
        assertEquals(82, spiel.getPunkteHeim());
    }

    @Test
    public void testGetPunkteGast() {
        assertEquals(79, spiel.getPunkteGast());
    }

    @Test
    public void testToString() {
        String result = spiel.toString();
        assertNotNull(result);
        assertTrue(result.contains("Bayern Munich"));
        assertTrue(result.contains("Borussia Dortmund"));
        assertTrue(result.contains("82"));
        assertTrue(result.contains("79"));
    }
}
