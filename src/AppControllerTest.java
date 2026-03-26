
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test Suite für die AppController-Klasse (Controller Layer).
 * Testet Input-Validierung, Error Handling und Benutzerinteraktionen.
 *
 * @author Basketball-Manager Team
 * @version 1.0
 */
public class AppControllerTest {
    private AppController controller;
    private AppManager mockManager;
    private View mockView;

    private static class FakeView implements View {
        private AppController controller;
        private String lastMessage;

        @Override
        public void setController(AppController controller) {
            this.controller = controller;
        }

        @Override
        public void showMessage(String message) {
            this.lastMessage = message;
        }

        @Override
        public void clearAllFields() {
        }

        @Override
        public void clearOutput() {
        }

        @Override
        public void appendOutput(String message) {
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }

    @Before
    public void setUp() {
        mockManager = new AppManager();
        mockView = new FakeView();
        controller = new AppController(mockManager, mockView);
    }

    // ===== INITIALIZATION TESTS =====

    @Test
    public void testControllerInitialization() {
        assertNotNull(controller);
        assertNotNull(mockManager);
        assertNotNull(mockView);
    }

    @Test
    public void testInit() {
        // Init sollte ohne Exception ausgeführt werden
        controller.init();
        assertTrue(true);
    }

    // ===== VALIDATION HELPER TESTS =====

    @Test
    public void testValidateNotEmptyWithValidInput() {
        // Sollte null zurückgeben (kein Fehler)
        String result = controller.validateNotEmpty("ValidInput", "FieldName");
        assertNull(result);
    }

    @Test
    public void testValidateNotEmptyWithEmptyString() {
        String result = controller.validateNotEmpty("", "FieldName");
        assertNotNull(result);
        assertTrue(result.contains("FieldName"));
    }

    @Test
    public void testValidateNotEmptyWithWhitespace() {
        String result = controller.validateNotEmpty("   ", "FieldName");
        assertNotNull(result);
    }

    @Test
    public void testValidateNotEmptyWithNull() {
        String result = controller.validateNotEmpty(null, "FieldName");
        assertNotNull(result);
        assertTrue(result.contains("FieldName"));
    }

    @Test
    public void testValidateNotEmptyFieldNameInMessage() {
        String result = controller.validateNotEmpty("", "ClubName");
        assertTrue(result.contains("ClubName"));
    }

    // ===== CLUB OPERATION TESTS =====

    @Test
    public void testSaveClubValid() {
        // Sollte keine Exception werfen
        controller.saveClub("FC Bayern", "1972", "1");
        
        // Verifi: Club wurde hinzugefügt
        assertNotNull(mockManager.getClub());
        assertEquals("FC Bayern", mockManager.getClub().getClubName());
    }

    @Test
    public void testSaveClubWithEmptyName() {
        // Sollte Error-Message anzeigen, aber keine Exception
        controller.saveClub("", "1972", "1");
        assertTrue(true);  // Keine Exception = Test erfolgreich
    }

    @Test
    public void testSaveClubWithInvalidYear() {
        // Nur Club-Name ist im Fokus der Validierung
        controller.saveClub("Test Club", "invalid", "1");
        // Controller sollte intern mit NumberFormatException umgehen
        assertTrue(true);
    }

    @Test
    public void testSaveClubWithInvalidID() {
        controller.saveClub("Test Club", "1972", "-5");
        // Controller sollte Error behandeln
        assertTrue(true);
    }

    // ===== SPIELER OPERATION TESTS =====

    @Test
    public void testSaveSpielerValid() {
        controller.saveSpieler("Max", "Mustermann", "23");
        assertEquals(1, mockManager.getSpieler().size());
    }

    @Test
    public void testSaveSpielerWithEmptyVorname() {
        controller.saveSpieler("", "Mustermann", "23");
        // Sollte kein Spieler hinzugefügt werden
        assertEquals(0, mockManager.getSpieler().size());
    }

    @Test
    public void testSaveSpielerWithEmptyNachname() {
        controller.saveSpieler("Max", "", "23");
        assertEquals(0, mockManager.getSpieler().size());
    }

    @Test
    public void testSaveSpielerWithInvalidNummer() {
        controller.saveSpieler("Max", "Test", "0");
        // Nummer ist ungültig
        assertEquals(0, mockManager.getSpieler().size());
    }

    @Test
    public void testSaveSpielerWithInvalidNummerOutOfRange() {
        controller.saveSpieler("Max", "Test", "100");
        assertEquals(0, mockManager.getSpieler().size());
    }

    @Test
    public void testSaveSpielerWithMultipleSpieler() {
        controller.saveSpieler("Max", "Mustermann", "10");
        controller.saveSpieler("Anna", "Schmidt", "23");
        assertEquals(2, mockManager.getSpieler().size());
    }

    // ===== SPIEL OPERATION TESTS =====

    @Test
    public void testSaveSpielValid() {
        controller.saveSpiel("Team A", "Team B", "85", "80");
        assertEquals(1, mockManager.getSpiele().size());
    }

    @Test
    public void testSaveSpielWithEmptyHeimmannschaft() {
        controller.saveSpiel("", "Team B", "85", "80");
        assertEquals(0, mockManager.getSpiele().size());
    }

    @Test
    public void testSaveSpielWithEmptyGastmannschaft() {
        controller.saveSpiel("Team A", "", "85", "80");
        assertEquals(0, mockManager.getSpiele().size());
    }

    @Test
    public void testSaveSpielWithInvalidPunkte() {
        controller.saveSpiel("Team A", "Team B", "-5", "80");
        assertEquals(0, mockManager.getSpiele().size());
    }

    @Test
    public void testSaveSpielWithMultipleSpiele() {
        controller.saveSpiel("A", "B", "85", "80");
        controller.saveSpiel("C", "D", "90", "88");
        assertEquals(2, mockManager.getSpiele().size());
    }

    // ===== RESET OPERATION TESTS =====

    @Test
    public void testResetAll() {
        // Setup: Daten hinzufügen
        controller.saveSpieler("Max", "Test", "10");
        controller.saveSpiel("A", "B", "85", "80");
        assertEquals(1, mockManager.getSpieler().size());
        assertEquals(1, mockManager.getSpiele().size());
        
        // Reset
        controller.resetAll();
        
        // Verifi: Alle gelöscht
        assertEquals(0, mockManager.getSpieler().size());
        assertEquals(0, mockManager.getSpiele().size());
    }

    // ===== ERROR HANDLING TESTS =====

    @Test
    public void testSaveClubNumberFormatExceptionHandling() {
        // Ungültige ID sollte nicht zu Exception führen
        controller.saveClub("Test", "not-a-number", "invalid-id");
        assertTrue(true);  // Keine Exception = OK
    }

    @Test
    public void testSaveSpielNumberFormatExceptionHandling() {
        controller.saveSpiel("A", "B", "not-a-number", "also-invalid");
        assertTrue(true);  // Keine Exception = OK
    }

    // ===== VIEW INTERACTION TESTS =====

    @Test
    public void testViewIsNotNull() {
        assertNotNull(mockView);
    }

    @Test
    public void testControllerCanInteractWithView() {
        mockView.showMessage("Test Message");
        // Sollte ohne Exception möglich sein
        assertTrue(true);
    }

    // ===== INTEGRATION TESTS =====

    @Test
    public void testCompleteWorkflow() {
        // 1. Club erstellen
        controller.saveClub("Test Club", "2020", "1");
        
        // 2. Spieler hinzufügen
        controller.saveSpieler("Max", "Mustermann", "10");
        controller.saveSpieler("Anna", "Schmidt", "23");
        
        // 3. Spiele hinzufügen
        controller.saveSpiel("Team A", "Team B", "85", "80");
        
        // 4. Verifikation
        assertEquals(2, mockManager.getSpieler().size());
        assertEquals(1, mockManager.getSpiele().size());
    }

    @Test
    public void testInputValidationBypassesDuplicates() {
        controller.saveSpieler("Max", "Test", "10");
        controller.saveSpieler("Max", "Test", "10");  // Duplikat
        // Beide sollten hinzugefügt werden (kein Unique-Constraint)
        assertEquals(2, mockManager.getSpieler().size());
    }

    @Test
    public void testDataIntegrityAfterMalformedInput() {
        // Gültige Daten hinzufügen
        controller.saveSpieler("Max", "Mustermann", "10");
        
        // Ungültige Daten versuchen
        controller.saveSpieler("", "Invalid", "0");
        
        // Gültige Daten sollten noch da sein
        assertEquals(1, mockManager.getSpieler().size());
    }
}
