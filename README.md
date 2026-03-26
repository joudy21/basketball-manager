# Basketball-Manager – Schulisches Projekt-Template

Eine **didaktische Java-Anwendung** zur Verwaltung von Basketball-Teams, Spielern und Spielen.  
Ein Projekt-Template für Schüler, das **OOP-Prinzipien, Clean Code und MVC-Architektur** praktiziert.

---

## 🚀 Quick-Start (5 Minuten)

### 1. Projekt-Aufbau verstehen
```bash
# Repo klonen
git clone <repo-url>
cd basketball-manager

# Struktur zeigen
tree -L 2 .
```

**Struktur:**
- `src/` – Java Quellcode (MVC + Entities)
- `docs/handbuch/` – Vollständige Dokumentation & Prozesse
- `LICENSE` – Lizenz
- `README.md` – Diese Datei

### 2. Java-Umgebung prüfen
```bash
java -version      # JDK 11+
javac -version     # Compiler verfügbar?
```

### 3. Kompilieren & Starten
```bash
cd src
javac *.java                    # Kompile alle .java Dateien
java Start.Start                # Starte die Anwendung
```

### 4. Tests ausführen
```bash
java Start.UnitTestMain         # Unit-Tests starten
```

### 5. Dokumentation lesen
Alle **neuen** sollten hier beginnen:

👉 **[Handbuch Quick-Start](docs/handbuch/README.md)** – 5 Minuten Einführung  
👉 **[Architektur-Überblick](docs/handbuch/ARCHITEKTUR.md)** – Wie's funktioniert  
👉 **[Vollständiges Index](docs/handbuch/INDEX.md)** – Alle Dokumentation  

---

## 📚 Dokumentation

Alles ist in `docs/handbuch/` organisiert:

| Datei | Für wen? | Inhalt |
|-------|---------|--------|
| [README](docs/handbuch/README.md) | **Anfänger** | Quick-Start + Best Practices |
| [INDEX](docs/handbuch/INDEX.md) | **Alle** | Vollständiges Inhaltsverzeichnis |
| [ARCHITEKTUR](docs/handbuch/ARCHITEKTUR.md) | **Entwickler** | Systemdesign (MVC, OOP) |
| [PFLICHTENHEFT](docs/handbuch/PFLICHTENHEFT.md) | **PM/Stakeholder** | Anforderungen & Scope |
| [Marschplan](docs/handbuch/marschplaene/HAUPTMARSCHPLAN.md) | **Projektleiter** | Phasen, Meilensteine, To-Dos |
| [Prozesse](docs/handbuch/prozesse/) | **Entwickler** | Workflows (neue Features, Reviews, etc.) |

---

## 💡 Mein erstes Projekt: Schritt-für-Schritt

### Phase 1: Grundlagen verstehen (Woche 1)
```bash
1. README.md (diese Datei) lesen ← du bist HIER
2. javac *.java && java Start.Start   ← App starten
3. docs/handbuch/README.md lesen      ← Details
4. docs/handbuch/ARCHITEKTUR.md       ← Wie es funktioniert
5. Quellcode in src/ anschauen        ← Code studieren
```

### Phase 2: Erstes Feature entwickeln (Woche 2-3)
```bash
1. docs/handbuch/prozesse/neue-routine-erstellen.md lesen
2. Feature-Branch erstellen: git checkout -b feature/...
3. Tests schreiben (TDD)
4. Code implementieren
5. Code-Review anfordern
6. Zu main mergen
```

### Phase 3: Code-Quality verbessern (Woche 3+)
```bash
1. docs/handbuch/prozesse/redundanz-management.md
2. docs/handbuch/prozesse/review-prozess.md
3. Refactoring durchführen
4. Best Practices anwenden
```

---

## 🎯 Kernkonzepte

Dieser Code lehrt:

### ✅ Objekt-Orientierte Programmierung (OOP)
- **Klassen & Objekte:** `Spieler`, `Club`, `Spiel`
- **Encapsulation:** Private Felder, Getter/Setter
- **Vererbung:** Entities erben von gemeinsamer Basis (später)
- **Polymorphismus:** Interface-basiertes Design

### ✅ Architektur (Model-View-Controller)
```
View (GUI)        ← MainWindow.java (Swing)
  ↕ (Events)
Controller        ← AppController.java (Geschäftslogik)
  ↕ (Data)
Model             ← AppManager.java + Entities (Zustand)
```

### ✅ Clean Code & Best Practices
- **DRY:** Don't Repeat Yourself
- **SSOT:** Single Source of Truth (eine Datenquelle)
- **SOLID Prinzipien:** Single Responsibility, Open/Closed, etc.
- **Dokumentation:** Javadoc, Markdown-Handbuch
- **Versionskontrolle:** Git Workflow mit Feature-Branches

### ✅ Testing & Quality
- **Unit Tests:** JUnit (in UnitTestMain.java)
- **Code Review:** Prozesse für PR-Genehmigung (siehe [review-prozess.md](docs/handbuch/prozesse/review-prozess.md))
- **Fehlerbehandlung:** try-catch, aussagekräftige Meldungen

---

## 🔧 Die Java-Struktur (src/)

```
src/
├── Start.java              ← main() – Einstiegspunkt
├── AppManager.java         ← Model (Daten + Geschäftslogik)
├── AppController.java      ← Controller (Orchestrierung)
├── MainWindow.java         ← View (Swing GUI)
├── Club.java               ← Entity: Verein
├── Spieler.java            ← Entity: Spieler
├── Spiel.java              ← Entity: Spiel
├── Mannschaft.java         ← Entity: Team-Zusammensetzung
├── Ort.java                ← Entity: Spielort
├── Zeitpunkt.java          ← Entity: Datum/Zeit
└── UnitTestMain.java       ← Tests
```

**Design-Pattern:** Model-View-Controller (MVC) mit Entity-Layer

---

## ⚙️ Anforderungen & Installation

### Voraussetzungen
- **Java:** JDK 11 oder höher
- **Git:** Für Versionskontrolle
- **Editor:** VS Code oder IntelliJ (beliebig)
- **OS:** Linux, macOS, oder Windows

### Installation (Schnell)
```bash
# Repo klonen
git clone <repo-url> && cd basketball-manager

# Kompiliere & Starte
cd src && javac *.java && java Start.Start

# (oder mit Makefile, wenn vorhanden)
# make run
```

### Installation (mit Docker – für später)
```bash
# Docker Image bauen
docker build -t basketball-manager .

# Container starten
docker run --rm -it basketball-manager
```

(Docker-Setup später, wenn Tests-Infrastruktur aufgesetzt wird)

---

## 🔄 Git Workflow für Schüler

Wenn du an neuen Features arbeiten möchtest:

### 1. Feature-Branch erstellen
```bash
git checkout main
git pull origin main
git checkout -b feature/dein-feature-name
```

### 2. Code ändern, Tests schreiben, Dokumentation updaten
```bash
# Arbeit... arbeit...
git add .
git commit -m "Feature: Deine Beschreibung"
git push origin feature/dein-feature-name
```

### 3. Pull Request erstellen
- Auf GitHub / GitLab PR erstellen
- Beschreibung hinzufügen (siehe [neue-routine-erstellen.md](docs/handbuch/prozesse/neue-routine-erstellen.md))
- Code Review anfordern

### 4. Nach Approval: Merge zu main
```bash
git checkout main
git pull origin main
git merge feature/dein-feature-name --no-ff
git push origin main
git push origin --delete feature/dein-feature-name
```

Detailliert: [docs/handbuch/prozesse/neue-routine-erstellen.md](docs/handbuch/prozesse/neue-routine-erstellen.md)

---

## 📊 Aktueller Status

**Phase:** MVP (Minimum Viable Product)  
**Fortschritt:** 60% Complete  
**Nächste Steps:**  
- [ ] Spieler-Auflisten-Feature
- [ ] Persistierung auf JSON
- [ ] Test-Coverage > 85%  
- [ ] Code-Refactoring

Vollständig: [Marschplan](docs/handbuch/marschplaene/HAUPTMARSCHPLAN.md)

---

## 🤝 Beiträge & Code of Conduct

### Wie kann ich beitragen?
1. **Bug gefunden?** → Issue öffnen + Fix als PR
2. **Feature-Idee?** → Issue diskutieren → Feature-Branch → PR
3. **Dokumentation?** → Direkt PRs zu docs/handbuch/

### Regeln
- ✅ Folge den Coding Standards (siehe [ARCHITEKTUR.md](docs/handbuch/ARCHITEKTUR.md))
- ✅ Schreib Tests für neuen Code
- ✅ Code-Review vor Merge zu main
- ✅ Dokumentation immer aktuell halten
- ✅ Sei respektvoll & hilfreich im Team

Siehe auch: [review-prozess.md](docs/handbuch/prozesse/review-prozess.md)

---

## ❓ FAQ & Häufige Probleme

### „Wie starte ich ein neues Feature?"
👉 [docs/handbuch/prozesse/neue-routine-erstellen.md](docs/handbuch/prozesse/neue-routine-erstellen.md)

### „Wie mache ich einen Code-Review?"
👉 [docs/handbuch/prozesse/review-prozess.md](docs/handbuch/prozesse/review-prozess.md)

### „Wo sind Code-Duplikate?"
👉 [docs/handbuch/prozesse/redundanz-management.md](docs/handbuch/prozesse/redundanz-management.md)

### „Wie verstehe ich die Architektur?"
👉 [docs/handbuch/ARCHITEKTUR.md](docs/handbuch/ARCHITEKTUR.md)

### „Wie aktualisiere ich bestehenden Code?"
👉 [docs/handbuch/prozesse/routine-aktualisieren.md](docs/handbuch/prozesse/routine-aktualisieren.md)

### Fragen generell?
1. **[Handbuch-Index](docs/handbuch/INDEX.md)** durchsuchen
2. **Lehrkraft** fragen oder Issue öffnen
3. **Team-Chat** (Slack/Teams) nutzen

---

## 📝 Lizenz

Dieses Projekt ist unter der **MIT Lizenz** lizenziert.  
Siehe [LICENSE](LICENSE) für Details.

---

## 👥 Beteiligte

- **Lehrkraft:** (Name)
- **Schüler:** (Namen)
- **Dokumentation:** (Namen)
- **Architektur-Lead:** (Name)

---

## 🔗 Schnell-Links

| Link | Für |
|------|-----|
| [Handbuch README](docs/handbuch/README.md) | Quick-Start |
| [Handbuch INDEX](docs/handbuch/INDEX.md) | Alle Docs durchsuchen |
| [ARCHITEKTUR](docs/handbuch/ARCHITEKTUR.md) | Zellweise wie's funktioniert |
| [Marschplan](docs/handbuch/marschplaene/HAUPTMARSCHPLAN.md) | Projekt-Status |
| [Neue Routine](docs/handbuch/prozesse/neue-routine-erstellen.md) | Neues Feature entwickeln |
| [Review-Prozess](docs/handbuch/prozesse/review-prozess.md) | Code-Review machen |
| [Git-Workflow](#-git-workflow-für-schüler) | Branching-Strategie |

---

## 🎓 Lernziele

Nach Durcharbeitung dieses Projekts kennst du:

- [ ] **OOP Fundamente** – Klassen, Vererbung, Abstraktion
- [ ] **Design Patterns** – Model-View-Controller
- [ ] **Clean Code** – DRY, SSOT, SOLID
- [ ] **Software-Architektur** – Komponenten, Schichten, Kopplung
- [ ] **Testing** – Unit Tests, Test-Coverage
- [ ] **Versionskontrolle** – Git Workflows, Branches, PRs
- [ ] **Code Review** – Gegenseitige Review-Prozesse
- [ ] **Dokumentation** – Technische Dokumentation schreiben

---

## 📞 Support

**Fragen oder Probleme?**
1. Schau ins [Handbuch-Index](docs/handbuch/INDEX.md)
2. Frag die Lehrkraft
3. Öffne ein Issue auf GitHub/GitLab
4. Slack/Teams Team-Chat nutzen

---

**Viel Spaß mit der Entwicklung!** 🚀

*Zuletzt aktualisiert: 2026-03-26*  
*Teil des Schulischen Projekt-Vorlagen-Repositories*
