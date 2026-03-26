# Prozess: Neue Routine erstellen

Schritt-für-Schritt Anleitung, wie du ein neues Feature, Task oder Document anbringt.

---

## 🎯 Ziel

Du möchtest ein **neues Feature** (z. B. „Spieler-Export zu CSV") oder einen **großeren Task** (z. B. „Dokumentation aktualisieren") ins Projekt bringen.

Diese Routine stellt sicher, dass:
- ✅ Deine Idee in die richtige Struktur passt
- ✅ Das Team Bescheid weiß
- ✅ Dein Code sauber & getestet ist
- ✅ Nichts vergessen wird

---

## 📋 Voraussetzungen

- [ ] Du hast Zugriff auf das Repository
- [ ] Du hast eine lokale Kopie (git clone)
- [ ] Die aktuelle Version läuft lokal
- [ ] Du verstehst die [ARCHITEKTUR.md](../ARCHITEKTUR.md)

---

## 🔄 Prozess: 9 Schritte

### Schritt 1: Idee klären (15 min)

**Frage dich:**
- Passt meine Idee ins Projekt-Scope (siehe [PFLICHTENHEFT.md](../PFLICHTENHEFT.md))?
- Ist das ein Task oder ein ganzes Feature?
- Wer muss ich konsultieren (Lehrkraft, Mitschüler)?

**Mach das:**
- [ ] Titel & kurze Beschreibung aufschreiben
- [ ] Mit Lehrkraft oder Code Lead absprechen
- [ ] In den [Marschplan](../marschplaene/HAUPTMARSCHPLAN.md) aufnehmen (To-Do Liste)

**Beispiel:**
```
Titel: Spieler-Auflisten Feature
Beschreibung: Spieler in einer Liste anzeigen, mit Sortierung nach Name/Nr
Scope: Feature (mittel, ca. 4h Aufwand)
Owner: Max Schein
Status: Genehmigt ✓
```

---

### Schritt 2: Issue erstellen (5 min)

Auf GitHub (falls vorhanden) oder im lokalen Projekt:

**Vorlage:**
```markdown
## Title: [Feature/Task] Spieler-Auflisten

### Description
Spieler in einer GUI-Tabelle anzeigen und nach Name/Nummer sortierbar machen.

### Acceptance Criteria
- [ ] JTable zeigt alle Spieler
- [ ] Sortierung nach Name funktioniert
- [ ] Sortierung nach Nummer funktioniert
- [ ] Tests geschrieben (> 80% Coverage)

### Related
- [ARCHITEKTUR.md](../ARCHITEKTUR.md) (MVC-Layer)
- [Template Routine](../templates/ROUTINE-TEMPLATE.md)

### Owner
@MaxSchein

### Priority
🟡 High
```

---

### Schritt 3: Feature-Branch erstellen (5 min)

**Konvention:** `feature/<feature-name>`

```bash
# Stelle sicher, dass du auf 'main' bist und aktuell
git checkout main
git pull origin main

# Erstelle einen neuen Branch
git checkout -b feature/spieler-auflisten

# Verifiziere
git branch
# * feature/spieler-auflisten
#   main
```

---

### Schritt 4: Dokumentation schreiben (WICHTIG! 30 min)

**Bevor** du Code schreibst, dokumentiere die Ausgangslösung!

**Erstelle:** `docs/handbuch/routinen/mittelfristig/spieler-auflisten.md`

**Nutze:** [ROUTINE-TEMPLATE.md](../templates/ROUTINE-TEMPLATE.md)

**Beispiel-Inhalt:**
```markdown
# Routine: Spieler-Auflisten Feature

## Ziel
Spieler in einer tabellarischen GUI anzeigen, sortierbar nach Name/Nummer.

## Anforderungen
- JTable mit Spalten: Name, Vorname, Nummer
- Sortierung nach Name (A-Z)
- Sortierung nach Nummer (1-99)

## Schritte (High-Level)
1. Neue JTable in MainWindow.spielerPanel() hinzufügen
2. Daten aus model.getClub().getSpieler() laden
3. Sortierer implementieren (Comparator)
4. Tests schreiben

## Abhängigkeiten
- MainWindow muss änderbar sein
- Club.getSpieler() muss verfügbar sein (✓ existiert)

## Erfolgskriterien
- [ ] Tests > 80% Coverage
- [ ] Code-Review bestanden
- [ ] Dokumentation aktualisiert
```

Committe diese Dokumentation GLEICH:
```bash
git add docs/handbuch/routinen/mittelfristig/spieler-auflisten.md
git commit -m "Docs: Spieler-Auflisten Feature dokumentiert"
```

---

### Schritt 5: Tests schreiben (TDD – Test-Driven Development)

**Bevor** du die Funktionalität schreibst, schreib die Tests!

**Erstelle:** `src/PlayierListTest.java`

```java
package Start;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpielerListTest {
    
    @Test
    public void testSortierungNachName() {
        Club club = new Club();
        club.addSpieler(new Spieler("Zebra", "Müller", 1));
        club.addSpieler(new Spieler("Alice", "Schmidt", 2));
        
        ArrayList<Spieler> sorted = club.sortiertnachName();
        
        assertEquals("Alice", sorted.get(0).getVorname());
        assertEquals("Zebra", sorted.get(1).getVorname());
    }
    
    @Test
    public void testSortierungNachNummer() {
        Club club = new Club();
        club.addSpieler(new Spieler("Max", "Müller", 99));
        club.addSpieler(new Spieler("Anna", "Schmidt", 7));
        
        ArrayList<Spieler> sorted = club.sortiertnachNummer();
        
        assertEquals(7, sorted.get(0).getNummer());
        assertEquals(99, sorted.get(1).getNummer());
    }
}
```

Commit:
```bash
git add src/SpielerListTest.java
git commit -m "Test: Spieler-Sortierung Tests eingefügt"
```

---

### Schritt 6: Feature implementieren

Jetzt schreib die Logik. Halte dich an [ARCHITEKTUR.md](../ARCHITEKTUR.md):

**Beispiel-Änderung in Club.java:**

```java
public ArrayList<Spieler> sortiertnachName() {
    ArrayList<Spieler> sorted = new ArrayList<>(spieler);
    sorted.sort((a, b) -> a.getNachname().compareTo(b.getNachname()));
    return sorted;
}

public ArrayList<Spieler> sortiertnachNummer() {
    ArrayList<Spieler> sorted = new ArrayList<>(spieler);
    sorted.sort((a, b) -> Integer.compare(a.getNummer(), b.getNummer()));
    return sorted;
}
```

Commit regelmäßig:
```bash
git add src/Club.java
git commit -m "Feature: Spieler-Sortierung implementiert"

git add src/MainWindow.java
git commit -m "UI: JTable für Spieler-Auflisten hinzugefügt"
```

---

### Schritt 7: Tests ausführen & Coverage prüfen

```bash
cd src
javac -cp ".:../lib/*" *.java
java -cp ".:../lib/*" org.junit.runner.JUnitCore Start.SpielerListTest

# Sollte OUTPUT sein:
# OK (2 tests, 0 failures)
```

**Abzielwert:** > 80% Code Coverage (hier z. B. 100% für diese Feature)

---

### Schritt 8: Code-Review anfordern

Vor dem Merge zu `main` MUSS dein Code reviewed werden!

**Erstelle ein Pull Request** (falls GitHub):

```markdown
## PR: Spieler-Auflisten Feature

### Beschreibung
Siehe Issue #42 – Implementiert Spieler-Sortierung nach Name & Nummer.

### Änderungen
- ✅ Club.sortiertnachName() & sortiertnachNummer() hinzugefügt
- ✅ MainWindow.spielerPanel() JTable integriert
- ✅ Tests > 100% Coverage
- ✅ Dokumentation in routinen/ aktualisiert

### Tests
```bash
All tests pass (2 new, 5 existing) ✓
Code Coverage: 100% (target > 80%) ✓
```

### Reviewer-Checkliste
- [ ] Code folgt Coding Standards (siehe ARCHITEKTUR.md)
- [ ] Tests vorhanden & grün
- [ ] Keine Code-Duplikate
- [ ] Dokumentation aktualisiert
```

Teile den PR mit anderen:
```bash
git push origin feature/spieler-auflisten
# GitHub: erstelle PR von feature/spieler-auflisten → main
```

**Mindestwarnungen zum Review:**
- Mindestens 1 anderer Entwickler muss „Approved" geben
- Alle Tests müssen grün sein
- Code Coverage darf nicht sinken

---

### Schritt 9: Merge zu main & Release

Nach erfolgreicher Review:

```bash
# Stelle sicher, dass main aktuell ist
git checkout main
git pull origin main

# Merge mit Commit-Nachricht
git merge feature/spieler-auflisten --no-ff \
  -m "Merge: Feature/spieler-auflisten → main (PR #42)"

# Pushe zu GitHub
git push origin main

# Lösche Feature-Branch (lokal)
git branch -d feature/spieler-auflisten

# Lösche Feature-Branch (remote)
git push origin --delete feature/spieler-auflisten
```

**Danach:**
- [ ] Issue schließen (→ „Merged")
- [ ] Marschplan aktualisieren (Status zu Done)
- [ ] ggf. Release-Notes schreiben

---

## 🚨 Gängige Fehler

❌ **Fehler 1:** Code schreiben OHNE Tests  
✅ **Lösung:** TDD! Tests ZUERST, Code danach

❌ **Fehler 2:** Nicht dokumentiert!  
✅ **Lösung:** Dokumentation BEVOR Code, als Planung

❌ **Fehler 3:** Ganzes Feature in einem großen Commit  
✅ **Lösung:** Logische, kleine Commits (eine Änderung pro Commit)

❌ **Fehler 4:** Zu lange keine Reviews einholen  
✅ **Lösung:** PR nach Schritt 6, nicht erst nach Schritt 8

---

## 📋 Checkliste zum Abhaken

```
Neue Routine erstellen – Checkliste
===================================

Schritt 1: Idee klären
- [ ] Mit Lehrkraft besprochen
- [ ] In Marschplan eingetragen

Schritt 2: Issue/Task erstellen
- [ ] GitHub-Issue erstellt (oder im lokalen Tracking)

Schritt 3: Feature-Branch
- [ ] Branch lokal erstellt: git checkout -b feature/xxx
- [ ] Richtig benannt (feature/…)

Schritt 4: Dokumentation
- [ ] Routine-Datei in routinen/ erstellt (ROUTINE-TEMPLATE)
- [ ] Committed: git commit -m "Docs: …"

Schritt 5: Tests
- [ ] Test-Datei erstellt
- [ ] Tests schreiben (bevor Code!)
- [ ] Committed: git commit -m "Test: …"

Schritt 6: Feature implementieren
- [ ] Code geschrieben (mit Kommentaren)
- [ ] Regelmäßig committed (kleine Schritte)

Schritt 7: Tests ausführen
- [ ] Alle Tests grün
- [ ] Coverage > 80%

Schritt 8: Code-Review
- [ ] Pull Request erstellt
- [ ] Reviewed-feedback eingearbeitet
- [ ] Approval von Mind. 1 Reviewer erhalten

Schritt 9: Merge & Release
- [ ] Zu main gemergt
- [ ] Feature-Branch gelöscht
- [ ] Issue geschlossen
- [ ] Marschplan aktualisiert
```

---

## 🔗 Siehe auch

- [ROUTINE-TEMPLATE.md](../templates/ROUTINE-TEMPLATE.md) – Vorlage für Routine-Dokumentation
- [review-prozess.md](review-prozess.md) – Was reviewer checken
- [ARCHITEKTUR.md](../ARCHITEKTUR.md) – Designprinzipien
- [Git-Workflow](../../../docs/git-workflow.md) – Branch-Strategie

---

*Zuletzt aktualisiert: 2026-03*
