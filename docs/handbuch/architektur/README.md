# Architektur & Systemdesign

**Status:** 🔄 IN PROGRESS  
**Letzte Aktualisierung:** 23.03.2026

---

## SYSTEMÜBERSICHT

Das Basketball Manager System ist strukturiert als eine Java-basierte Desktop-Anwendung mit folgenden Hauptkomponenten:

```
┌─────────────────────────────────────────────┐
│          Basketball Manager System          │
├─────────────────────────────────────────────┤
│                                             │
│  ┌──────────────┐    ┌──────────────────┐  │
│  │   MainWindow │────│  AppManager      │  │
│  │   (GUI)      │    │  (Controller)    │  │
│  └──────────────┘    └──────────────────┘  │
│         │                    │              │
│         └────────┬───────────┘              │
│                  │                         │
│    ┌─────────────┴──────────────┐          │
│    │                            │          │
│  ┌─────────────────┐   ┌──────────────┐   │
│  │  Domain Models  │   │  Game Logic  │   │
│  ├─────────────────┤   ├──────────────┤   │
│  │ • Spieler       │   │ • Basketball │   │
│  │ • Club          │   │ • Spiel      │   │
│  │ • Mannschaft    │   │              │   │
│  │ • Ort           │   │              │   │
│  │ • Zeitpunkt     │   │              │   │
│  └─────────────────┘   └──────────────┘   │
│                                             │
└─────────────────────────────────────────────┘
```

---

## KOMPONENTEN IM DETAIL

### 1. GUI-Schicht (Präsentation)
- **MainWindow.java** - Hauptfenster, User Interface
- Verantwortung: Benutzerinteraktion, Anzeige von Daten

### 2. Controller-Schicht (Geschäftslogik)
- **AppManager.java** - Zentrale Anwendungsverwaltung
- Verantwortung: Datenfluss, Event-Handling, Koordination

### 3. Domain Model-Schicht (Datenmodelle)
- **Spieler.java** - Spieler-Entität
- **Club.java** - Club-Entität
- **Mannschaft.java** - Team-Management
- **Spiel.java** - Spiel-Daten
- **Ort.java** - Standort-Information
- **Zeitpunkt.java** - Zeitstempel-Management

### 4. Business Logic-Schicht
- **Basketball.java** - Spiel-Logik und Spielregeln
- Verantwortung: Spielregeln, Scoring, Spielverwaltung

---

## DATENFLUSSARCHITEKTUR

```
Benutzer Input (GUI)
    │
    ▼
MainWindow (Präsentation)
    │
    ▼
AppManager (Koordination & Event Dispatch)
    │
    ├─────────────────────┬──────────────────┐
    │                     │                  │
    ▼                     ▼                  ▼
Domain Models        Basketball Logic   Data Persistence
(CRUD Operations)    (Game Rules)       (Files/DB)
```

---

## DESIGN PATTERNS (Erkannt)

- **MVC Pattern** - MainWindow (View), AppManager (Controller), Models (Model)
- **Singleton Pattern** - AppManager vermutlich Zentral-Instance
- **Entity Pattern** - Spieler, Club, Mannschaft als Entities

---

## SCHICHTEN & VERANTWORTUNGEN

| Schicht | Module | Verantwortung |
|---|---|---|
| **Presentation** | MainWindow | UI/UX, Benutzerinteraktion |
| **Controller** | AppManager | Geschäftslogik Orchestration |
| **Domain** | Spieler, Club, Mannschaft, Ort, Zeitpunkt | Datenmodelle |
| **Business** | Basketball, Spiel | Spielregeln, Validierung |

---

## ABHÄNGIGKEITEN (TBD)

Zu dokumentieren:
- Welche Klassen hängen voneinander ab?
- Gibt es zirkuläre Abhängigkeiten?
- Sind Dependencies richtig getrennt?

---

## NÄCHSTE SCHRITTE (Phase 1.3)

- [ ] UML Klassien-Diagramm erstellen
- [ ] Komponenten-Diagramm zeichnen
- [ ] Datenmodelle detailliert dokumentieren
- [ ] Abhängigkeiten visualisieren
- [ ] Designdokument finalisieren

---

**Status:** 🔄 INITIAL DRAFT  
**Nächste Überprüfung:** 07.04.2026
