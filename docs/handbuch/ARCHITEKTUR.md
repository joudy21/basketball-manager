# Architektur – Technisches Design

Damit du und alle Entwickler auf der gleichen Seite sind. Diese Datei dokumentiert das **WAS**, **WIE** und **WARUM** des technischen Systems.

---

## 📊 Systemkontext

### Zweck
Eine Java-Anwendung zur Verwaltung von Basketball-Teams, Spielern und Spielen. Ziel ist es, die grundlegenden OOP-Konzepte praktisch zu demonstrieren.

### Stack
- **Sprache:** Java JDK 11+
- **GUI:** Swing (javax.swing)
- **Datenbank:** Optional (JSON-Datei oder MySQL, in Zukunft)
- **Versionskontrolle:** Git

### High-Level Architektur
```
Benutzer
  ↓↑
[MainWindow (View)]
  ↓↑ (Events: Klicks, Eingaben)
[AppController (Controller)] ↔ Validierung & Orchestrierung
  ↓↑ (Daten-Anfragen & Updates)
[AppManager (Model)] ↔ Geschäftslogik & Zustand
  ↓↑
[Entity-Klassen: Club, Spieler, Spiel, Mannschaft, etc.]
```

---

## 🧩 Komponenten

### 1. **Entity-Layer (Datenmodelle)**

Jede Entität repräsentiert einen realen Gegenstand.

#### Club
```java
public class Club {
    private String clubName;
    private String gruendungsjahr;
    private int idC;
    private ArrayList<Spieler> spieler;
    private ArrayList<Mannschaft> mannschaften;
    // ... getter/setter, Logik für Team-Verwaltung
}
```
**Verantwortung:** Verwaltet Vereins-Daten und Team-Struktur.

#### Spieler
```java
public class Spieler {
    private String vorname;
    private String nachname;
    private int nummer;
    // ... getter/setter
}
```
**Verantwortung:** Repräsentiert einen Spieler mit Basis-Daten.

#### Spiel
```java
public class Spiel {
    private String heimTeam;
    private String gastTeam;
    // ... Punkte, Datum, Ergebnis
}
```
**Verantwortung:** Speichert ein Spiel (Heimteam vs Gastteam + Ergebnis).

#### Weitere
- `Mannschaft`: Team-Zusammensetzung
- `Ort`: Spielort/Halle
- `Zeitpunkt`: Datum/Zeit für Spiele

### 2. **Model-Layer (AppManager)**

Die Zentrale Verwaltungseinheit für Geschäftslogik.

```java
public class AppManager {
    private Club club;
    private ArrayList<Spiel> spiele;
    
    public void addSpieler(Spieler s) { /* ... */ }
    public void addSpiel(Spiel s) { /* ... */ }
    public Club getClub() { return club; }
}
```

**Verantwortung:**
- ✅ Speichert alle Daten im RAM
- ✅ Orchestriert Geschäftslogik
- ✅ Bietet eine konsistente API für den Controller

### 3. **Controller-Layer (AppController)**

Der „Verkehrskops" zwischen View und Model.

```java
public class AppController {
    private AppManager model;
    private MainWindow view;
    
    public void saveClub(String name, String jahr, int id) {
        // 1. Validierung
        if(name.isEmpty()) { 
            view.showMessage("Fehler: Name fehlt!"); 
            return; 
        }
        // 2. Geschäftslogik (delegieren an Model)
        model.getClub().setClubName(name);
        // 3. Feedback
        view.showMessage("Club aktualisiert");
    }
}
```

**Verantwortung:**
- ✅ Empfängt Benutzer-Events (Klicks, Eingaben)
- ✅ Validiert User-Input
- ✅ Koordiniert Aufrufe zwischen Model & View
- ✅ Fehlerbehandlung mit aussagekräftigen Meldungen

### 4. **View-Layer (MainWindow)**

Die Benutzeroberfläche – nur Darstellung & User-Interaction.

```java
public class MainWindow extends JFrame {
    private JTextField tfClubName;
    private AppController controller;
    
    // Event-Handler
    btnSave.addActionListener(e -> 
        controller.saveClub(tfClubName.getText(), ...)
    );
}
```

**Verantwortung:**
- ✅ Zeigt Daten an (Rendering)
- ✅ Empfängt User-Input (Buttons, Textfelder)
- ✅ Delegiert Action an Controller
- ✅ Zeigt Meldungen an (Feedback)

### 5. **Application Entry-Point (Start)**

```java
public class Start {
    public static void main(String[] args) {
        AppManager model = new AppManager();
        MainWindow view = new MainWindow();
        AppController controller = new AppController(model, view);
        controller.init();
        view.setVisible(true);
    }
}
```

**Verantwortung:**
- ✅ Initialisiert alle drei Schichten (MVC)
- ✅ Startet die Anwendung

---

## 🔄 Datenfluss (Beispiel: „Club speichern")

```
1. Benutzer tippt "FC Bayern" in Textfeld
2. Benutzer klickt "Speichern" Button
3. View: Button-Action triggert Controller.saveClub()
4. Controller: Prüft ob Name nicht leer
5. Controller: Ruft model.getClub().setClubName() auf
6. Model: Speichert Wert lokal
7. Controller: Ruft view.showMessage() auf
8. View: Zeigt "Club aktualisiert!"
9. Benutzer sieht die Bestätigung
```

---

## 🏗️ Designprinzipien

### DRY (Don't Repeat Yourself)
❌ **Schlecht:**
```java
// In saveClub()
if(name.isEmpty()) { view.showMessage("Fehler!"); return; }

// In saveSpieler() (wiederholter Code!)
if(vorname.isEmpty()) { view.showMessage("Fehler!"); return; }
```

✅ **Gut:**
```java
// Zentrale Validierungs-Methode
private boolean validateNotEmpty(String value, String fieldName) {
    if(value.isEmpty()) {
        view.showMessage("Fehler: " + fieldName + " fehlt!");
        return false;
    }
    return true;
}

// Wiederverwendbar
public void saveClub(...) {
    if (!validateNotEmpty(name, "Name")) return;
    // ...
}
```

### SSOT (Single Source of Truth)
**Regel:** Keine redundanten Kopien von Daten.

❌ **Schlecht:**
```java
// Model hat Spieler
ArrayList<Spieler> spielerListe;

// View hat auch Kopie (Duplikat!)
ArrayList<Spieler> displayedSpieler;
```

✅ **Gut:**
```java
// Nur eine Quelle (im Model)
public ArrayList<Spieler> getSpieler() {
    return model.getClub().getSpieler(); // Immer live-Daten
}
```

### Hohe Kohäsion
Zusammenhängende Logik gehört in eine Klasse.

✅ **Gut:**
```java
// Club verwaltet seine eigenen Spieler
public class Club {
    private ArrayList<Spieler> spieler;
    public void addSpieler(Spieler s) { spieler.add(s); }
}
```

### Lose Kopplung
Minimale gegenseitige Abhängigkeiten.

❌ **Schlecht:**
```java
controller.saveClub(...); // Controller greift direkt auf View zu
// View weiß zu viel über Controller & Model
```

✅ **Gut:**
```java
// Über definierte Schnittstellen kommunizieren
controller.saveClub(); // Button-Action
view.showMessage();     // View aktualisiert sich selbst
```

### Single Responsibility
Eine Klasse = **ein Zweck**.

✅ **Gut:**
```java
class Club { /* Verwaltung von Vereins-Daten */ }
class AppController { /* Koordination zw. View & Model */ }
class MainWindow { /* nur GUI-Rendering */ }
```

---

## 📋 Validierung & Fehlerbehandlung

**Stelle:** Controller  
**Regel:** Validierung VOR Datenspeicherung.

```java
public void saveSpieler(String vn, String nn, int nr) {
    // 1. Validierung
    if (vn.isEmpty() || nn.isEmpty()) {
        view.showMessage("Fehler: Name unvollständig!");
        return;
    }
    if (nr < 1 || nr > 99) {
        view.showMessage("Fehler: Nummer 1-99 erforderlich!");
        return;
    }
    
    // 2. Geschäftslogik (sicher, weil validiert)
    Spieler s = new Spieler(vn, nn, nr);
    model.addSpieler(s);
    
    // 3. Feedback
    view.showMessage("Spieler registriert: " + s);
}
```

---

## 🔐 Sicherheit & best Practice

| Problem | Lösung |
|---------|--------|
| String-Eingaben | Im Controller validieren (nicht-leer, Länge, Format) |
| Zahleneingaben | try-catch bei Integer.parseInt() |
| Null-Objekte | Null-Checks befor Zugriff (model != null) |
| Ungültige Zuständ | Invarianten in Konstruktoren prüfen |

---

## 🚀 Erweiterbarkeit

Damit das System zukunftsicher bleibt:

### Schicht-Erweiterung

Neue Features? Neue Entity-Klasse hinzufügen:
```java
public class Trainer {
    private String name;
    private String lizenz;
    // Es braucht KEINE Änderung in Controller/View!
}
```

### Persistierung
Später: Database-Layer zwischen Model & Entities hinzufügen.
```java
new DatabaseManager().saveSpieler(spieler); // Ersetzt ArrayList
```

---

## 🧪 Testen

**Unit Tests** sind in `UnitTestMain.java` dokumentiert.

Teste jede Klasse isoliert:
```java
@Test
public void testSpielerErstellung() {
    Spieler s = new Spieler("Max", "Schein", 7);
    assertEquals("Max", s.getVorname());
}
```

---

## 📈 Metriken & Qualität

| Metrik | Ziel | Status |
|--------|------|--------|
| Zyklomatische Komplexität | < 10 pro Methode | ✅ |
| Test-Coverage | > 80% | 🟡 |
| Dokumentation | 100% der Public API | ✅ |
| Code-Duplikate | < 5% | ✅ |

---

## 🔗 Weitere Lektüre

- [PFLICHTENHEFT.md](PFLICHTENHEFT.md) – Anforderungen
- [Marschplan](marschplaene/HAUPTMARSCHPLAN.md) – Meilensteine
- [Review-Prozess](prozesse/review-prozess.md) – Code-Quality

---

*Zuletzt aktualisiert: 2026-03*
