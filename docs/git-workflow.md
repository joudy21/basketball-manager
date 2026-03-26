# Git Workflow & Versionsstrategie

Eine detaillierte Anleitung zum Branching, Merging und Releasing.

---

## 🎯 Ziel

Strukturierter, nachvollziehbarer Git-Workflow, damit:
- ✅ Mehrere Entwickler ohne Konflikte arbeiten können
- ✅ Jede Änderung rückverfolgbar ist
- ✅ Main immer **produktiv** bleibt (keine WIP-Code)
- ✅ Releases sauber und versioniert sind

---

## 🌳 Branch-Strategie: Git Flow (vereinfacht)

Wir nutzen eine angepasste **Git Flow** Strategie:

```
main          ← 🔴 PRODUCTION (immer stabil!)
  ↑ (Releases)
develop       ← 🟡 STAGING (aktuellster stabiler Code)
  ↑ (Feature PRs)
feature/*     ← 🟢 ENTWICKLUNG (individuelle Features)
bugfix/*      ← 🟡 BUGFIXE (Reparaturen)
refactor/*    ← 🔵 REFACTORING (Code-Verbesserungen)
```

---

## 🔄 Branches erklärt

### `main` – Die Produktions-Branch
- ✅ **Immer stabil & getestet**
- ✅ **Nur Releases** werden hier gemergt (via develop)
- ✅ **Keine direkt Commits** (außer Release-Tags)
- 🔒 **Geschützt:** Mindestens 1 Review vor Merge

**Permission:**
```yaml
Direkter Commit: Lehrkraft nur
Merge via PR: Lehrkraft + mindest 1 Review
```

### `develop` – Die Integration-Branch
- 🟡 **Staging-Bereich für getestete Features**
- 📦 **Feature-Branches mergen hier hin** (nach Review)
- 📌 **Werden später zu main** (für Releases)

**Permission:**
```yaml
Merge von feature/*: Min. 1 Review + CIgreen
Merge von develop → main: Lehrkraft
```

### `feature/*` – Feature-Entwicklung
- 🟢 **Jedes neue Feature = eigener Branch**
- 📝 **Namenskonvention:** `feature/spieler-auflisten`, `feature/json-persistierung`
- 🔄 **Lokal arbeit, dann PR erstellen**
- 🗑️ **Nach Merge gelöscht**

**Beispiel:**
```bash
git checkout -b feature/spieler-auflisten
# ... arbeit ...
git push origin feature/spieler-auflisten
# → PR erstellen auf GitHub
```

### `bugfix/*` – Bug-Reparationen
- 🐛 **Schnelle Fixes für bekannte Bugs**
- 📝 **Namenskonvention:** `bugfix/spieler-validierung`, `bugfix/ui-crash`
- ⚡ **Höhere Priorität** als Features

**Beispiel:**
```bash
git checkout -b bugfix/spieler-validierung
# → Schneller Merge nach Review
```

### `refactor/*` – Code-Verbesserungen
- 🔧 **Wartbarkeit verbessern (ohne Funktionalität zu ändern)**
- 📝 **Namenskonvention:** `refactor/controller-vereinfachen`, `refactor/duplikate-entfernen`

**Beispiel:**
```bash
git checkout -b refactor/controller-vereinfachen
# → PR mit "Before/After" Erklärung
```

---

## 📋 Typical Workflow: Ein neues Feature

### Schritt 1: Für main & develop aktualisieren
```bash
git checkout main
git pull origin main

git checkout develop
git pull origin develop
```

### Schritt 2: Feature-Branch erstellen
```bash
git checkout -b feature/spieler-auflisten develop
# oder
git checkout develop && git pull && git checkout -b feature/spieler-auflisten
```

### Schritt 3: Development & Commits
```bash
# ... arbeiten, Code schreiben ...

git add src/Club.java src/MainWindow.java
git commit -m "Feature: JTable für Spieler-Auflisten hinzugefügt"

git add src/SpielerListTest.java
git commit -m "Test: Spieler-Sortierung Tests"

# Arbeite, bis Features fertig
```

### Schritt 4: Push zu GitHub
```bash
git push origin feature/spieler-auflisten
```

### Schritt 5: Pull Request erstellen
Auf GitHub:
1. Klick „Create Pull Request"
2. **Base:** `develop` (NICHT main!)
3. **Compare:** `feature/spieler-auflisten`
4. **Titel:** `Feature: Spieler-Auflisten mit Sortierung`
5. **Description:** (siehe Vorlage unten)

**PR-Vorlage:**
```markdown
## Feature: Spieler-Auflisten mit Sortierung

### Beschreibung
Nutzer können Spieler in einer Tabelle sehen und nach Name/Nummer sortieren.

### Änderungen
- JTable in MainWindow.spielerPanel()
- Sortierungs-Methoden in Club.java
- Tests: SpielerListTest.java (+5 Tests)

### Akzeptanzkriterien
- [x] Tests > 80% Coverage
- [x] Alle Tests grün
- [x] Dokumentation aktualisiert
- [x] Code-Review Checkliste erfüllt

### Related Issue
Closes #42

### Screenshot/Demo
(Falls GUI-Änderung: Screenshot hier)
```

### Schritt 6: Code-Review
- 1-2 Reviewer approved
- CI/CD: Tests grün
- Feedback einarbeiten (ggf. neue Commits)

### Schritt 7: Merge zu develop
```bash
# Vom Reviewer oder Autor:
git checkout develop
git pull origin develop
git merge --no-ff feature/spieler-auflisten \
  -m "Merge: Feature/spieler-auflisten → develop (PR #42)"
git push origin develop

# Branch löschen
git push origin --delete feature/spieler-auflisten
git branch -d feature/spieler-auflisten
```

### Schritt 8: (Später für Release) Merge develop → main
```bash
# Vom Lehrkraft, wenn Release bereit
git checkout main
git pull origin main
git merge --no-ff develop \
  -m "Release: v1.0 - Features xyz"

git tag -a v1.0 -m "Release v1.0: Core Features"
git push origin main
git push origin v1.0
```

---

## 🔖 Releases & Versionierung

### Versionieren: Semantic Versioning

Format: `v[MAJOR].[MINOR].[PATCH]`

- **MAJOR:** Breaking Changes (z. B. v1.0 → v2.0)
- **MINOR:** Neuen Features (rückwärts-kompatibel)
- **PATCH:** Bug-Fixes

**Beispiele:**
- `v1.0.0` – Erste stabile Version
- `v1.1.0` – Neue Features hinzugefügt
- `v1.1.1` – Bug-Fix
- `v2.0.0` – Großes Refactoring (nicht kompatibel)

### Release-Prozess

#### 1. Release vorbereiten (Lehrkraft)
```bash
# Stelle sicher, develop ist ready
git checkout develop
git pull origin develop

# Erstelle Release-Branch
git checkout -b release/v1.0
```

#### 2. Finale Änderungen (Version-Bump, Changelog)
```bash
# Datei aktualisieren: CHANGELOG.md
# z. B. neue Zeile ganz oben:
# "## v1.0 (2026-03-26) - Initial Release"
#   - Feature 1
#   - Feature 2
#   - Bug-Fixes

git add CHANGELOG.md
git commit -m "Release: v1.0 Changelog"
```

#### 3. Merge zu main & develop
```bash
# Zu main
git checkout main
git pull origin main
git merge --no-ff release/v1.0 -m "Release: v1.0"
git tag -a v1.0 -m "Release v1.0"
git push origin main
git push origin v1.0

# Zurück zu develop
git checkout develop
git pull origin develop
git merge --no-ff release/v1.0 -m "Merge release/v1.0 back to develop"
git push origin develop

# Release-branch löschen
git push origin --delete release/v1.0
git branch -d release/v1.0
```

#### 4. Release auf GitHub erstellen
Auf GitHub:
- Gehe zu „Releases"
- Klick „Create a new release"
- Tag: `v1.0`
- Title: `Release v1.0 - Initial Version`
- Description: (siehe CHANGELOG.md)

---

## 🐛 Bugfix-Workflow (schnell)

Wenn Produktion Fehler hat:

```bash
# Vom main
git checkout -b bugfix/spieler-validierung main
# ... fix code ...
git commit -m "Fix: Spieler-Validierung Bug"
git push origin bugfix/spieler-validierung

# PR mit `Base: main` (NOT develop!)
# → After Review & Approval:
git checkout main && git merge --no-ff bugfix/spieler-validierung
git push origin main

# Auch zu develop patchen
git checkout develop && git merge main
git push origin develop
```

---

## 📊 Branching Cheat Sheet

| Scenario | Command |
|----------|---------|
| Ein neues Feature starten | `git checkout -b feature/xxx develop` |
| Feature fertig, PR erstellen | `git push origin feature/xxx` + GitHub PR |
| PR akzeptiert, mergen | `git merge --no-ff feature/xxx develop` |
| Bug zu fixen | `git checkout -b bugfix/xxx main` |
| Release machen | `git checkout -b release/v1.0 develop` |
| Branches auflisten | `git branch -a` |
| Lokal aufräumen | `git branch -d feature/xxx` |
| Remote aufräumen | `git push origin --delete feature/xxx` |

---

## 🔐 Branch Schutz (Best Practice)

**GitHub Settings nutzen:**
- `main`: 🔒 Geschützt – Min. 1 Review vor Merge
- `develop`: 🔒 Geschützt – Min. 1 Review vor Merge
- `feature/*`: Offen – Direktes Pushen OK

```yaml
# GitHub Branch Protection Rules:
main:
  require_pull_request_reviews: true
  required_approving_review_count: 1
  require_status_checks_to_pass_before_merging: true  # CI/CD muss grün sein

develop:
  require_pull_request_reviews: true
  required_approving_review_count: 1
  require_status_checks_to_pass_before_merging: true
```

---

## 📝 Commit Message Best Practices

### Format
```
<type>(<scope>): <subject>

<body>

<footer>
```

### Beispiele

```
Feature: Spieler-Auflisten JTable hinzugefügt
```

```
Fix: NullPointerException in Club.addSpieler()

Ursache: getSpieler() konnte null zurückgeben.
Lösung: Null-check hinzugefügt.

Fixes #42
```

```
Refactor: Validierungscode in validateNotEmpty() extrahiert

- DRY principle eingehalten
- 3 Duplikate zusammengefasst
- Test-Coverage → 85%
```

### Typen
- **Feature:** Neues Feature
- **Fix:** Bug-Fix
- **Refactor:** Code-Verbesserung
- **Test:** Test hinzugefügt/geändert
- **Docs:** Dokumentation
- **CI:** CI/CD Änderung

---

## 🔍 Konflikt-Lösung

Wenn Merge-Konflikt:

```bash
# Merge starten
git merge develop

# Konflikte entstehen
# CONFLICT (content): Merge conflict in src/Club.java

# Konflikt-Datei öffnen
cat src/Club.java

# Manuell die Konflikte auflösen (<<<<<<, ======, >>>>>> markiert)
# Dann:
git add src/Club.java
git commit -m "Merge: Konflikte aufgelöst"
git push origin feature/xxx
```

---

## 📚 Weitere Commands

```bash
# Status sehen
git status

# Branches (lokal + remote)
git branch -a

# Logs ansehen
git log --oneline --graph --all

# Remote-Branches updaten
git fetch origin

# Lokale Änderungen verwerfen
git reset --hard

# Letzten Commit rückgängig machen
git revert HEAD

# Commits squashen (mehrere zu einem)
git rebase -i develop  # Interaktives Rebase
```

---

## 🎓 Learning Path

1. **Anfänger:** `main` → `develop` → `feature/*` verstehen
2. **Fortgeschritten:** Merge-Konflikte lösen, Rebase nutzen
3. **Experte:** Release-Management, Hotfixes, Git Hooks

---

## 🔗 Siehe auch

- [neue-routine-erstellen.md](docs/handbuch/prozesse/neue-routine-erstellen.md) – PR-Erstellen
- [review-prozess.md](docs/handbuch/prozesse/review-prozess.md) – Code-Review
- [Marschplan](docs/handbuch/marschplaene/HAUPTMARSCHPLAN.md) – Projekt-Status

---

*Zuletzt aktualisiert: 2026-03-26*
