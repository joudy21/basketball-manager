
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test Suite für die Spieler-Klasse.
 * Testet Validierung, Getter/Setter und Objektvergleich.
 *
 * Angestrebte Test-Coverage: > 95%
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class SpielerTest {
    private Spieler spieler;

    @Before
    public void setUp() {
        spieler = new Spieler("Max", "Mustermann", 23);
    }

    // ===== KONSTRUKTOR TESTS =====

    @Test
    public void testValidKonstruktor() {
        Spieler s = new Spieler("Anna", "Schmidt", 5);
        assertEquals("Anna", s.getVorname());
        assertEquals("Schmidt", s.getNachname());
        assertEquals(5, s.getNummer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNullVorname() {
        new Spieler(null, "Mustermann", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitLeeremVorname() {
        new Spieler("", "Mustermann", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitLeeremVornameWhitespace() {
        new Spieler("   ", "Mustermann", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNullNachname() {
        new Spieler("Max", null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitLeeremNachname() {
        new Spieler("Max", "", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitLeeremNachnameWhitespace() {
        new Spieler("Max", "   ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNummerZuKlein() {
        new Spieler("Max", "Mustermann", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNummerZuGross() {
        new Spieler("Max", "Mustermann", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKonstruktorMitNegativerNummer() {
        new Spieler("Max", "Mustermann", -5);
    }

    @Test
    public void testKonstruktorMitGrenzenNummer1() {
        Spieler s = new Spieler("Test", "Spieler", 1);
        assertEquals(1, s.getNummer());
    }

    @Test
    public void testKonstruktorMitGrenzenNummer99() {
        Spieler s = new Spieler("Test", "Spieler", 99);
        assertEquals(99, s.getNummer());
    }

    // ===== SETTER TESTS =====

    @Test
    public void testSetVornameValid() {
        spieler.setVorname("Peter");
        assertEquals("Peter", spieler.getVorname());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetVornameNull() {
        spieler.setVorname(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetVornameLeer() {
        spieler.setVorname("");
    }

    @Test
    public void testSetVornameWhitespace() {
        spieler.setVorname("   Julia   ");
        // trim() sollte angewendet sein
        assertEquals("Julia", spieler.getVorname());
    }

    @Test
    public void testSetNachnameValid() {
        spieler.setNachname("Müller");
        assertEquals("Müller", spieler.getNachname());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNachnameNull() {
        spieler.setNachname(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNamhnameLeer() {
        spieler.setNachname("");
    }

    @Test
    public void testSetNummerValid() {
        spieler.setNummer(42);
        assertEquals(42, spieler.getNummer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNummerZuKlein() {
        spieler.setNummer(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNummerZuGross() {
        spieler.setNummer(100);
    }

    // ===== EQUALS / HASHCODE TESTS =====

    @Test
    public void testEqualsIdentical() {
        Spieler s1 = new Spieler("Max", "Mustermann", 23);
        Spieler s2 = new Spieler("Max", "Mustermann", 23);
        assertEquals(s1, s2);
    }

    @Test
    public void testEqualsVerschiedeneVornamen() {
        Spieler s2 = new Spieler("Peter", "Mustermann", 23);
        assertNotEquals(spieler, s2);
    }

    @Test
    public void testEqualsVerschiedeneNachnamen() {
        Spieler s2 = new Spieler("Max", "Schmidt", 23);
        assertNotEquals(spieler, s2);
    }

    @Test
    public void testEqualsVerschiedeneNummern() {
        Spieler s2 = new Spieler("Max", "Mustermann", 5);
        assertNotEquals(spieler, s2);
    }

    @Test
    public void testEqualsSelf() {
        assertEquals(spieler, spieler);
    }

    @Test
    public void testEqualsNull() {
        assertNotEquals(spieler, null);
    }

    @Test
    public void testEqualsAndHashCodeConsistency() {
        Spieler s1 = new Spieler("Max", "Mustermann", 23);
        Spieler s2 = new Spieler("Max", "Mustermann", 23);
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    public void testHashCodeDifferentObjects() {
        Spieler s2 = new Spieler("Peter", "Schmidt", 5);
        // Diferentes Objekte sollten (wahrscheinlich) verschiedene Hashes haben
        assertNotEquals(spieler.hashCode(), s2.hashCode());
    }

    // ===== TOSTRING TEST =====

    @Test
    public void testToString() {
        String result = spieler.toString();
        assertNotNull(result);
        assertTrue(result.contains("Max"));
        assertTrue(result.contains("Mustermann"));
        assertTrue(result.contains("23"));
    }

    // ===== GETTER TESTS =====

    @Test
    public void testGetVorname() {
        assertEquals("Max", spieler.getVorname());
    }

    @Test
    public void testGetNachname() {
        assertEquals("Mustermann", spieler.getNachname());
    }

    @Test
    public void testGetNummer() {
        assertEquals(23, spieler.getNummer());
    }
}
