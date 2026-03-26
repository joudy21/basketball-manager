# Routine Beispiel: Spieler-Validierung verbessern

Ein komplettes Beispiel einer Routine, vom Ideation bis Done.

**Tipp:** Nutze diese Routine als Referenz für deine eigenen!

---

## 🎯 Ziel

Spieler-Konstruktor und Validierung verschärfen, damit ungültige Daten früh erkannt werden.

---

## 📋 Anforderungen

- Spieler-Konstruktor validiert Vorname & Nachname (nicht leer)
- Spieler-Konstruktor validiert Nummer (1 bis 99)
- Ungültige Input thrown `IllegalArgumentException`
- Tests decken alle edge cases ab

---

## 🔗 Abhängigkeiten

| Abhängigkeit | Typ | Notiz |
|--------------|-----|-------|
| Spieler.java | Änderung | Konstruktor + Validierung |
| SpielerListTest.java | Anpassung | Tests für Exception-Fälle |
| AppController.java | Anpassung | Exception-Handling beim saveSpieler() |
| MainWindow.java | Evlt. | Neue Fehlerbehandlung |

**WICHTIG:** AppController muss die neue Exception abfangen und dem User anzeigen!

---

## ✅ Erfolgskriterien

- [ ] Spieler-Konstruktor validiert Auf gemäß Anforderungen
- [ ] Throws IllegalArgumentException bei ungültigen Eingaben
- [ ] AppController fängt Exception ab & zeigt Meldung
- [ ] Tests vorhanden (3 negative Cases, alte Tests noch grün)
- [ ] Code-Review bestanden
- [ ] Keine Regression (alte Tests grün)
- [ ] Dokumentation aktualisiert (diese Datei, + Architektur)
- [ ] PR gemergt zu main

---

## 🔄 Schritte (Detailliert)

### 1. Dokumentation schreiben (diese Routine!)
- [ ] Diese Routine-Datei erstellen und füllen
- [ ] Mit Team absprechen
- [ ] Zustimmung erhalten

### 2. Feature-Branch erstellen
```bash
git checkout -b bugfix/spieler-validierung
```

### 3. Tests schreiben (TDD)
**Datei:** `src/SpielerListTest.java`

```java
@Test(expected = IllegalArgumentException.class)
public void testSpielerKonstruktor_VornameEmpty() {
    new Spieler("", "Müller", 7); // Sollte Exception werfen
}

@Test(expected = IllegalArgumentException.class)
public void testSpielerKonstruktor_NachnahmeEmpty() {
    new Spieler("Max", "", 7);
}

@Test(expected = IllegalArgumentException.class)
public void testSpielerKonstruktor_NumerUnterRange() {
    new Spieler("Max", "Müller", 0); // < 1 nicht erlaubt
}

@Test(expected = IllegalArgumentException.class)
public void testSpielerKonstruktor_NumerOberRange() {
    new Spieler("Max", "Müller", 100); // > 99 nicht erlaubt
}

@Test
public void testSpielerKonstruktor_Valid() {
    Spieler s = new Spieler("Max", "Müller", 7);
    assertNotNull(s);
    assertEquals("Max", s.getVorname());
    assertEquals("Müller", s.getNachname());
    assertEquals(7, s.getNummer());
}
```

### 4. Feature implementieren
**Datei:** `src/Spieler.java`

**Vorher:**
```java
public Spieler(String vorname, String nachname, int nummer) {
    this.vorname = vorname;
    this.nachname = nachname;
    this.nummer = nummer;
}
```

**Nachher:**
```java
/**
 * Erstellt einen neuen Spieler mit Validierung.
 * 
 * @param vorname Spieler-Vorname (nicht leer)
 * @param nachname Spieler-Nachname (nicht leer)
 * @param nummer Ruecennummer (1 bis 99)
 * @throws IllegalArgumentException wenn Input ungültig
 */
public Spieler(String vorname, String nachname, int nummer) 
    throws IllegalArgumentException {
    
    // Validierung
    if (vorname == null || vorname.isEmpty()) {
        throw new IllegalArgumentException("Vorname darf nicht leer sein!");
    }
    if (nachname == null || nachname.isEmpty()) {
        throw new IllegalArgumentException("Nachname darf nicht leer sein!");
    }
    if (nummer < 1 || nummer > 99) {
        throw new IllegalArgumentException(
            "Nummer muss zwischen 1 und 99 sein, erhalten: " + nummer
        );
    }
    
    // Zuweisung (sicher, weil validiert)
    this.vorname = vorname;
    this.nachname = nachname;
    this.nummer = nummer;
}
```

### 5. Exception-Handling anpassen
**Datei:** `src/AppController.java`

**Vorher:**
```java
public void saveSpieler(String vn, String nn, int nr) {
    if(vn.isEmpty() || nn.isEmpty()) { 
        view.showMessage("Fehler: Name unvollständig!"); 
        return; 
    }
    Spieler s = new Spieler(vn, nn, nr);
    model.addSpieler(s);
    view.showMessage("Spieler registriert: " + s);
}
```

**Nachher:**
```java
public void saveSpieler(String vn, String nn, int nr) {
    if(vn.isEmpty() || nn.isEmpty()) { 
        view.showMessage("Fehler: Name unvollständig!"); 
        return; 
    }
    
    try {
        Spieler s = new Spieler(vn, nn, nr); // Kann jetzt werfen!
        model.addSpieler(s);
        view.showMessage("Spieler registriert: " + s);
    } catch (IllegalArgumentException e) {
        view.showMessage("Fehler: " + e.getMessage());
    }
}
```

### 6. Tests ausführen
```bash
cd src
javac -cp ".:../lib/*" *.java
java -cp ".:../lib/*" org.junit.runner.JUnitCore Start.SpielerListTest

# Output:
# OK (5 tests run, 0 failures)
```

✅ **Alle Tests grün!**

### 7. Dokumentation aktualisieren
- [ ] Diese Routine-Datei: Status = Done
- [ ] ARCHITEKTUR.md: Validierungs-Sektion updaten
- [ ] Marschplan: Feature abhaken

### 8. Code-Review
PR erstellen:
```markdown
## PR: Bugfix/spieler-validierung

Feature-Branch: `bugfix/spieler-validierung`
Ziel: Main branch

### Beschreibung
Spieler-Konstruktor validiert jetzt streng.
Siehe Routine in docs/handbuch/routinen/mittelfristig/

### Änderungen
- Spieler.java: throws + Exception-Handling
- SpielerListTest.java: +5 neue Tests
- AppController.java: try-catch um Spieler-Erstellung

### Tests
```
Alle Tests grün: ✓
Cover: 100% für Spieler
```

### Reviewer-Checkliste
- [ ] Validierung macht Sinn
- [ ] Alle Tests grün
- [ ] Exception-Handling vollständig
- [ ] Dokumentation aktualisiert
```

### 9. Merge zu main
Nach Approval:
```bash
git checkout main
git pull
git merge bugfix/spieler-validierung --no-ff
git push origin main
```

---

## 🧪 Tests (Detailliert)

Siehe Schritt 3 & 6 oben.

**Test-Cover:**
- ✅ Leerer Vorname → Exception
- ✅ Leerer Nachname → Exception
- ✅ Nummer zu klein → Exception
- ✅ Nummer zu groß → Exception
- ✅ Valide Eingabe → kein Exception, Objekt erstellt

---

## 📊 Status Timeline

| Datum | Status | Notes |
|-------|--------|-------|
| 2026-03-26 | ✅ Done | Alles abgeschlossen & gemergt |
| 2026-03-25 | ⏳ In Review | PR eingereicht |
| 2026-03-24 | ⏳ In Progress | Tests OK, Feature implemnentiert |
| 2026-03-23 | 🔵 Startet | Tests geschrieben, Branch erstellt |

---

## 🎓 Learnings & Highlights

**Was funktionierte gut:**
- ✅ TDD Ansatz (Tests first) → schneller Dev
- ✅ Frühe Exception → Bug-Prävention
- ✅ Klare Validierungs-Logik

**Was könnte besser sein:**
- ⚠️ Hätte auch in Club.addSpieler() validiert werden können
- ⚠️ Mehr Integrations-Tests hätten geholfen

**Füture Improvements:**
- [ ] Evlt. Spieler-Builder für komplexere Erstellung
- [ ] Lokalisierung von Error-Messages (Deutsch/Englisch)

---

## 📚 Referenzen

- [Spieler-Klasse](../../src/Spieler.java) – Implementierung
- [ROUTINE-TEMPLATE](ROUTINE-TEMPLATE.md) – Diese Vorlage
- [ARCHITEKTUR.md](../ARCHITEKTUR.md) – Validierungs-Sektion
- [neue-routine-erstellen.md](../prozesse/neue-routine-erstellen.md) – Prozess

---

*Zuletzt aktualisiert: 2026-03-26*  
*Owner: Lehrkraft*  
*Status: ✅ Complete & Merged*
