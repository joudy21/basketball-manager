# Prozess: Redundanz-Management

Wie du **Code-Duplikate**, **Abhängigkeiten** und **Single-Point-of-Failure** identifizierst und behebst.

---

## 🎯 Zweck

**DRY-Prinzip durchsetzen:** Don't Repeat Yourself.

Wenn Code mehrfach vorkommt:
- ❌ Wartbarkeit sinkt
- ❌ Bug-Fixes müssen überall gemacht werden
- ❌ Größeres Risiko von Inkonsistenzen

**Ziel:** Eine Quelle für eine Idee (SSOT).

---

## 🔍 So identifizierst du Duplikate

### Methode 1: Manuelles Code-Review

Lies Code & merke dir wiederkehrende Muster:

```java
// In AppController.java
public void saveClub(String name, String jahr, int id) {
    if(name.isEmpty()) { view.showMessage("Fehler: Name fehlt!"); return; }
    // ... rest
}

public void saveSpieler(String vn, String nn, int nr) {
    if(vn.isEmpty() || nn.isEmpty()) { view.showMessage("Fehler: Name unvollständig!"); return; }
    // ... rest
}

public void saveSpiel(String h, String g) {
    if(h.isEmpty() || g.isEmpty()) { view.showMessage("Fehler: Teams fehlen!"); return; }
    // ... rest
}
```

🔴 **Duplikat erkannt:** Validierungscode wiederholt sich 3x!

### Methode 2: Code-Analysis-Tools

```bash
# Einfache Suche nach gleichen Code-Mustern
grep -n "if(.*isEmpty())" src/*.java
# Liefert alle isEmpty-Validierungen

# Mit awk: häufigste Codebäume finden
find src -name "*.java" -exec grep -h "showMessage" {} \; | sort | uniq -c | sort -rn
```

### Methode 3: Architektur-Think

Frage dich:
- "Macht diese Klasse mehr als **eine** Sache?"
- "Ist diese Daten-Kopie notwendig?" (Model + View darf nicht beide Spieler-Liste halten!)
- "Ruft die Klasse mehrmals die gleiche Methode auf?"

---

## 🛠️ Refactoring-Strategien

### Strategie 1: Extract Method

**Problem:** Gleicher Code-Block in mehreren Methoden.

**Lösung:** In Hilfsmethode ziehen.

**Vorher:**
```java
public void saveClub(...) {
    if(name.isEmpty()) { view.showMessage("Club Name fehlt!"); return; }
    // ...
}

public void saveSpieler(...) {
    if(vn.isEmpty() || nn.isEmpty()) { view.showMessage("Spieler Namen fehlen!"); return; }
    // ...
}
```

**Nachher:**
```java
// Zentrale Validierungsmethode
private boolean validateNotEmpty(String value, String fieldName) {
    if (value == null || value.isEmpty()) {
        view.showMessage("Fehler: " + fieldName + " darf nicht leer sein!");
        return false;
    }
    return true;
}

// Wiederverwendet in allen Stellen
public void saveClub(String name, String jahr, int id) {
    if (!validateNotEmpty(name, "Club Name")) return;
    // ...
}

public void saveSpieler(String vn, String nn, int nr) {
    if (!validateNotEmpty(vn, "Vorname")) return;
    if (!validateNotEmpty(nn, "Nachname")) return;
    // ...
}
```

**Vorteil:** Validierungslogik an **einer Stelle** → einfacher zu updaten.

### Strategie 2: Extract Class / Single Responsibility

**Problem:** Eine Klasse macht zu viel.

**Beispiel (Antipattern):**
```java
public class MainWindow extends JFrame {
    // GUI-Komponenten
    private JTextField tfName;
    
    // + Validierungslogik
    private void validateClubName(String name) { ... }
    
    // + Datenverwaltung
    private Club club = new Club();
    
    // + Event-Handling
    // + Styling-Code
}
```

**Besser (MVC + Separation of Concerns):**
```java
// View: nur GUI
public class MainWindow extends JFrame { ... }

// Controller: Validierung + Orchestrierung
public class AppController {
    private boolean validateClubName(String name) { ... }
}

// Model: Daten
public class Club { ... }
```

### Strategie 3: Extract Superclass / Interface

**Problem:** Mehrere Klassen machen ähnliche Dinge.

**Beispiel:**
```java
// Antipattern: Code doppelt
public class Spieler {
    private String vorname, nachname;
    @Override public String toString() { return vorname + " " + nachname; }
}

public class Coach {
    private String vorname, nachname;
    @Override public String toString() { return vorname + " " + nachname; }
}
```

**Besserer Ansatz:**
```java
// Basis-Klasse mit gemeinsamer Logik
public abstract class Person {
    protected String vorname;
    protected String nachname;
    
    @Override 
    public String toString() { 
        return vorname + " " + nachname; 
    }
}

// Spezialisierungen
public class Spieler extends Person {
    private int nummer;
}

public class Coach extends Person {
    private String lizenz;
}
```

### Strategie 4: Single Source of Truth (SSOT)

**Problem:** Daten an mehreren Orten gespeichert.

**Antipattern:**
```java
public class AppManager {
    private ArrayList<Spieler> spieler; // Model
}

public class MainWindow {
    private ArrayList<Spieler> displaySpieler; // View - KOPIE!
}
```

❌ Wenn sich Model ändert, ist View out-of-sync!

**Besserer Ansatz:**
```java
public class MainWindow {
    // Hole Daten IMMER vom Model (live, keine Kopie)
    ArrayList<Spieler> spielerList = controller.getSpieler();
    // oder
    ArrayList<Spieler> spielerList = model.getClub().getSpieler();
}
```

---

## 🔗 Abhängigkeiten managen

### Enge Kopplung erkennen

❌ **Schlecht (tight coupling):**
```java
public class AppController {
    public void saveClub(String name) {
        // Controller greift direkt auf View zu (Abhängigkeit!)
        view.tfClubName.setText(""); // Private var öffentlich
        model.getClub().setName(name); // Model-Detail exposed
    }
}
```

### Lose Kopplung erreichen

✅ **Besser (loose coupling):**
```java
public interface IView {
    void clearClubNameField();
    void showMessage(String msg);
}

public class AppController {
    private IView view;
    
    public void saveClub(String name) {
        model.getClub().setName(name);
        view.clearClubNameField(); // Über Interface!
        view.showMessage("Club aktualisiert");
    }
}
```

**Vorteil:** View kann ausgetauscht werden (z. B. später Swing → HTML/CSS).

---

## 🔍 Redundanz-Audit (für Projekt)

### Checkliste

```
Redundanz-Audit für basketball-manager
========================================

Klasse: Spieler
- Duplikate: 0 (OK)
- Abhängigkeiten: Club (notwendig)
- SSOT: ✓ In Club-Liste

Klasse: MainWindow
- Duplikate: validateClubName() + validateSpieler() → 
             ⚠️ Zu Controller verschieben
- Abhängigkeiten: AppController, Club (OK)
- SSOT: ✓ Keine Daten-Kopien

Klasse: AppController
- Duplikate: if(isEmpty()) in 3 Methoden → 
             ⚠️ validateNotEmpty() extrahieren
- Abhängigkeiten: Model, View (OK, ist Job)
- SSOT: ✓

Gesamt-Status: 🟡 2 Refactoring-Oppurtunitäten
```

### Priorisierung

1. **P0 - Kritisch:** SSOT Verletzungen (Daten-Duplikate)
2. **P1 - Hoch:** Copy-Paste Code (> 5 Zeilen in 3+ Dateien)
3. **P2 - Mittel:** Extract Method (Validierung, Helpers)
4. **P3 - Niedrig:** Extract Class (größere Refactorings für späte Phase)

---

## 📋 Redundanz-Reduzierung: Action Items

Für basketball-manager (aktuell):

| Nr | Duplikat | Ort | Fix | Aufwand |
|----|----------|-----|-----|---------|
| 1 | validateEmpy-Code in 3 Methoden | AppController | Extract validateNotEmpty() | 30min |
| 2 | club-Panel, spieler-Panel gleiche Struktur | MainWindow | createPanel() Hilfsmethode | 30min |
| 3 | toString() Pattern in Spieler + Club | Spieler, Club | Abstract Person? (später) | Later |

---

## 🔄 Workflow: Redundanz beheben

1. **Identifizieren** – Wo ist das Problem?
2. **dokumentieren** – Issue/Task erstellen
3. **Branch** – `refactor/redundanz-validation` 
4. **Refaktor** – Extract Method / Klasse / Interface
5. **Tests** – Prüfe Funktionalität nicht ändert sich
6. **Review** – PR mit Erklärung
7. **Merge** – Fertig!

Siehe: [routine-aktualisieren.md](routine-aktualisieren.md)

---

## 📊 Metriken

Wie misst du Erfolg?

| Metrik | Ziel | Messung |
|--------|------|---------|
| Code-Duplikat-Ratio | < 5% | SonarQube Duplicate Lines % |
| Zyklomatische Komplexität | < 10 / Methode | JAR-Tool / IDE |
| Abhängigkeits-Zirkelbezüge | 0 | Dependency-Analyzer |
| SLOC (Source Lines of Code) | Nicht zu viel steigen | Wc -l src/*.java |

---

## 🔗 Siehe auch

- [ARCHITEKTUR.md](../ARCHITEKTUR.md) – Design-Prinzipien (DRY, SSOT, SOLID)
- [review-prozess.md](review-prozess.md) – Code-Review für Duplikate
- [neue-routine-erstellen.md](neue-routine-erstellen.md) – Refactoring als Task

---

*Zuletzt aktualisiert: 2026-03*
