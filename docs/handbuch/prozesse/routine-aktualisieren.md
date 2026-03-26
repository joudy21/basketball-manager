# Prozess: Existing Routine aktualisieren

Wie du einen **bestehenden Feature** oder einen bestehenden **Code-Block** änderst (Bug-Fix, Refactoring, oder Improvement).

---

## 🎯 Ziel & Unterschied zu „neue-routine-erstellen"

**Unterschied:**
- **Neue Routine** = Ganz neues Feature (z. B. „Spieler-Export")
- **Routine aktualisieren** = Bestehendes Feature ändern (z. B. „Bug in Spieler-Validierung", oder „UI verbessern")

Diese Routine ist **schneller** als neu zu entwickeln, weil:
- Die Architektur schon da ist
- Tests schon geschrieben sind (ggf. anpassen)
- Review oft schneller

---

## 📋 Voraussetzungen

- [ ] Du weißt, **welcher Code** geändert werden soll
- [ ] Du hast die aktuelle Version lokal
- [ ] Du verstehst, **warum** die Änderung nötig ist (Ticket/Issue)

---

## 🔄 Prozess: 7 Schritte

### Schritt 1: Bestandsaufnahme (10 min)

**Verstehe den aktuellen Code:**

```bash
# Suche die Datei
find src -name "*Spieler*"
# Ergebnis: src/Spieler.java, src/SpielerListTest.java

# Lese sie
cat src/Spieler.java | head -30
```

**Frage dich:**
- Welche Methode/Klasse muss sich ändern?
- Welche Tests sind davon betroffen?
- Gibt es Abhängigkeiten (andere Klassen)?

**Beispiel:**
```
Zu ändern: Spieler.java – Konstruktor
Betroffen: SpielerListTest.java, MainWindow.java
Impact: Mittel (nur Spieler-Erstellung)
```

---

### Schritt 2: Backup & Branch erstellen (5 min)

Erstelle einen Bugfix/Refactor-Branch:

**Namenskonvention:** `bugfix/xxx` oder `refactor/xxx` oder `improve/xxx`

```bash
git checkout main
git pull origin main

# Für einen Bug:
git checkout -b bugfix/spieler-validierungsbug

# Oder für Refactoring:
git checkout -b refactor/spieler-konstruktor-vereinfacht

# Oder für ein Improvement:
git checkout -b improve/spieler-hashcode-implementierung
```

Verifiziere:
```bash
git branch
# * bugfix/spieler-validierungsbug
#   main
```

---

### Schritt 3: Betroffene Tests ausführen (10 min)

Prüfe, welche Tests aktuell noch grün sind:

```bash
cd src
javac -cp ".:../lib/*" *.java
java -cp ".:../lib/*" org.junit.runner.JUnitCore Start.SpielerListTest

# Output sollte sein:
# OK (alle Tests grün)
```

**Notiere dir die Baseline** (z. B. Anzahl Tests, Fehler):
```
BEFORE: 5 Tests, 0 Fehler, 100% Coverage
```

---

### Schritt 4: Ändere Code + angepasste Tests

**Beispiel:** Spieler-Konstruktor soll strenger Validieren

**ALT (buggy):**
```java
public Spieler(String vorname, String nachname, int nummer) {
    // Keine Validierung! Bugs möglich
    this.vorname = vorname;
    this.nachname = nachname;
    this.nummer = nummer;
}
```

**NEU (repariert):**
```java
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
        throw new IllegalArgumentException("Nummer muss zwischen 1-99 sein!");
    }
    
    this.vorname = vorname;
    this.nachname = nachname;
    this.nummer = nummer;
}
```

**Tests updaten** (SpielerListTest.java):

```java
@Test(expected = IllegalArgumentException.class)
public void testSpielerKonstruktor_VornameEmpty() {
    new Spieler("", "Müller", 7);
}

@Test(expected = IllegalArgumentException.class)
public void testSpielerKonstruktor_NumerUnterRange() {
    new Spieler("Max", "Müller", 0); // < 1
}

@Test(expected = IllegalArgumentException.class)
public void testSpielerKonstruktor_NumerOberrRange() {
    new Spieler("Max", "Müller", 100); // > 99
}
```

Committe die Änderungen:
```bash
git add src/Spieler.java src/SpielerListTest.java
git commit -m "Fix: Spieler-Konstruktor mit Validierung + Tests"
```

---

### Schritt 5: Alle betroffenen Tests anpassen & durchlaufen

Prüfe, dass deine Änderung **nicht breakt** andere Tests:

```bash
# Alle Klassen neu kompilieren
javac -cp ".:../lib/*" src/*.java 2>&1 | grep -i error

# Wenn Fehler: ggf. MainWindow.java & AppController.java anpassen,
# um die neue Exception zu behandeln

# ZB in AppController.saveSpiler():
if(vn.isEmpty() || nn.isEmpty()) { 
    view.showMessage("Fehler: Name unvollständig!"); 
    return; 
}
// Aber jetzt auch Exception abfangen:
try {
    Spieler s = new Spieler(vn, nn, nr);
    model.addSpieler(s);
} catch (IllegalArgumentException e) {
    view.showMessage("Fehler: " + e.getMessage());
}
```

Commit auch diese Updates:
```bash
git add src/AppController.java
git commit -m "Refactor: AppController passt neue Spieler-Exception an"
```

---

### Schritt 6: Dokumentation aktualisieren

**Aktualisiere relevante Routinen-Dateien:**

**Beispiel:** `docs/handbuch/routinen/mittelfristig/spieler-validierung.md`

```markdown
# Routine: Spieler-Validierung Verbesserung

## Änderung
Spieler-Konstruktor validiert jetzt Eingaben streng.

### Vorher
- Keine Validierung
- Ungültige Nummern (0, 100+) möglich
- Leere Namen akzeptiert

### Nachher
- Vorname & Nachname: nicht leer
- Nummer: 1 bis 99
- Exception bei ungültigem Input

## Auswirkung
- MainWindow & AppController angepasst (Exception-Handling)
- 3 neue Tests hinzugefügt
- Keine Bruch-Änderung (Breaking Change) für bestehende gültige Code

## Tests
- ✅ Alle alten Tests noch grün (+ 3 neue)
```

Commit:
```bash
git add docs/handbuch/routinen/mittelfristig/spieler-validierung.md
git commit -m "Docs: Spieler-Validierungs-Update dokumentiert"
```

---

### Schritt 7: Code-Review & Merge

**Pull Request erstellen:**

```markdown
## PR: Bugfix/spieler-konstruktor-validierung

### Beschreibung
Spieler-Konstruktor validiert jetzt Eingaben. Siehe Issue #42.

### Änderungen
- Spieler.java: throws + Validierungen
- SpielerListTest.java: +3 Exception-Tests
- AppController.java: Exception-Handling
- Dokumentation aktualisiert

### Impact
- ✅ Keine bestehenden Tests gebrochen
- ✅ 3 neue Tests grün
- ⚠️ AppController muss Exception abfangen (done)

### Review-Checkliste
- [ ] Änderungen sind Sinn
- [ ] Alle Tests grün
- [ ] Dokumentation aktualisiert
- [ ] Keine Code-Duplikate eingeführt
```

Nach Approval:
```bash
git checkout main
git pull origin main
git merge bugfix/spieler-validierung --no-ff \
  -m "Merge: Bugfix spieler-validierung → main"
git push origin main
git push origin --delete bugfix/spieler-validierung
```

---

## ⚠️ Besondere Fälle

### Fall 1: Großes Refactoring (z. B. große Umstrukturierung)

Wenn du viele Dateien änderst:
- Teile es in **mehrere kleine PRs** auf
- Jede PR = eine logische Änderung
- Einfacher zu Review

### Fall 2: Breaking Change (z. B. API-Signatur ändert sich)

Wenn du eine öffentliche Methode ändert:
- [ ] Dokumentiere die Änderung in CHANGELOG
- [ ] Update alle Aufrufer
- [ ] Markiere alte Version als deprecated (falls möglich)
- [ ] Schreibe Migration-Guide

### Fall 3: Performance-Verbesserung

Wenn du Speed optimiert:
- [ ] Beweise mit Benchmarks, dass es schneller ist
- [ ] Keine Funktionalität ändert sich (sollte)
- [ ] Tests müssen noch grün sein

---

## 🚨 Häufige Fehler

❌ **Fehler 1:** Zu viele Änderungen in einem Commit  
✅ **Lösung:** Ein logischer Change pro Commit (z. B. Validierung + Tests zusammen OK)

❌ **Fehler 2:** Updates vergessen (andere Klassen, die diesen Code nutzen)  
✅ **Lösung:** Bestandsaufnahme in Schritt 1!

❌ **Fehler 3:** Tests vergessen nach Code-Änderung  
✅ **Lösung:** Schritt 5 = **immer** alle Tests durchlaufen

---

## 📋 Checkliste

```
Existing Routine aktualisieren – Checkliste
===========================================

Schritt 1: Bestandsaufnahme
- [ ] Welcher Code ändert sich?
- [ ] Welche Tests sind betroffen?
- [ ] Abhängigkeiten identifiziert?

Schritt 2: Branch
- [ ] Branch erstellt: git checkout -b bugfix/…
- [ ] Richtig benannt (bugfix/, refactor/, improve/)

Schritt 3: Test-Baseline
- [ ] Aktuell alle Tests grün?
- [ ] Baseline notiert (Anzahl, Coverage)

Schritt 4: Code ändern
- [ ] Änderung vorgenommen
- [ ] Tests geschrieben/angepasst
- [ ] Committed mit guter Message

Schritt 5: Tests durchlaufen
- [ ] Alle Tests grün (alte + neue)
- [ ] Abhängige Klassen angepasst?
- [ ] Committed

Schritt 6: Dokumentation
- [ ] Routine-Datei aktualisiert
- [ ] Committed

Schritt 7: Review & Merge
- [ ] PR erstellt
- [ ] Review-feedback eingearbeitet
- [ ] Approved & gemergt
- [ ] Branch-delete
```

---

## 🔗 Siehe auch

- [neue-routine-erstellen.md](neue-routine-erstellen.md) – Für ganz neuen Code
- [review-prozess.md](review-prozess.md) – Was reviewer prüfen
- [ARCHITEKTUR.md](../ARCHITEKTUR.md) – Design-Prinzipien

---

*Zuletzt aktualisiert: 2026-03*
