# Hauptmarschplan – Phasen, Meilensteine & To-Dos

Der Gesamtüberblick über Projekt-Fortschritt, Phasen und nächste Schritte.

---

## 🎯 Projekt-Übersicht

| Eckdatum | Phase | Status |
|----------|-------|--------|
| **2026-03-26** | Phase 1: MVP (Core) | 🟢 **60% Complete** |
| **2026-05-15** | Phase 2: Erweiterte Features | 🔵 **Geplant** |
| **2026-07-01** | Phase 3: Web-Interface | 🔵 **Geplant** |

**Aktueller Status:** Wir sind in Phase 1, basierend auf dem MVC-Grundgerüst. Die Kern-Features funktionieren, Refactoring & Tests stehen an.

---

## 📊 Phase 1: MVP – Core-Funktionalität

**Zeitrahmen:** 2026-03-26 → 2026-04-30  
**Ziel:** Stabile, dokumentierte Basis-Anwendung mit MVC + Unit Tests

### Meilensteine

#### M1.1: Architektur (✅ Abgeschlossen)
- [x] MVC-Struktur aufgesetzt
- [x] Entities (Club, Spieler, Spiel) definiert
- [x] AppController & AppManager implementiert
- [x] MainWindow (Swing GUI) funktionsfähig

#### M1.2: Kern-Features (🟢 75% Abgeschlossen)
- [x] Club-Verwaltung (speichern, ändern)
- [x] Spieler-Hinzufügung
- [x] Spiel-Dokumentation
- [x] Data Reset
- [ ] Spieler-Auflisten ← **Zu tun**
- [ ] Persistierung (JSON) ← **Zu tun**

#### M1.3: Quality & Tests (🟡 40% Abgeschlossen)
- [x] UnitTestMain.java aufgesetzt
- [ ] > 80% Test-Coverage ← **In Progress**
- [ ] Code-Review durchgeführt ← **Zu tun**
- [ ] Code-Refactoring (OOP-Optimierung) ← **In Progress**

#### M1.4: Dokumentation (🟡 70% Abgeschlossen)
- [x] README.md (Quick-Start)
- [x] ARCHITEKTUR.md
- [x] PFLICHTENHEFT.md
- [x] Handbuch-Struktur
- [ ] Prozess-Richtlinien (Review, Testing) ← **In Progress**
- [ ] Best-Practice Routinen ← **Zu tun**

#### M1.5: DevOps & Release (🔵 Geplant)
- [ ] Git-Workflow Setup (main + develop + feature/* branches)
- [ ] CI/CD Pipeline (GitHub Actions)
- [ ] Release v1.0 Tag setzen
- [ ] Changelog schreiben

### Zu-Tun-Liste (Priorisiert)

#### 🔴 KRITISCH (diese Woche)

| Task | Owner | Deadline | Status |
|------|-------|----------|--------|
| Refactor: Validierung in Controller zentralisieren | Dev | 2026-03-28 | ⏳ |
| Code-Duplikate entfernen (3 Methoden mit gleicher Struktur) | Dev | 2026-03-28 | ⏳ |
| Test-Coverage auf 85% erhöhen | Test | 2026-03-30 | ⏳ |
| Review-Prozess dokumentieren | Lead | 2026-03-28 | ⏳ |

#### 🟡 HOCH (diese Woche + nächste)

| Task | Owner | Deadline | Status |
|------|-------|----------|--------|
| Spieler-Auflisten-Feature (mit ListView) | Dev | 2026-04-02 | ⏳ |
| Persistierung auf JSON (Reader/Writer) | Dev | 2026-04-05 | ⏳ |
| Integration-Tests schreiben (4-5 Fälle) | Test | 2026-04-07 | ⏳ |
| Marschplan aktualisieren (wöchentlich!) | Lead | Jeden Fr. | ⏳ |

#### 🟢 MITTEL (nächste 2 Wochen)

| Task | Owner | Deadline | Status |
|------|-------|----------|--------|
| Mannschaft-Klasse testen & integrieren | Dev | 2026-04-15 | 🔵 |
| Fehlerbehandlung für Dateien | Dev | 2026-04-15 | 🔵 |
| Dokumentation der neuen Features | Doc | 2026-04-15 | 🔵 |

---

## 📅 Phase 2: Erweiterte Features (Geplant)

**Zeitrahmen:** 2026-05-01 → 2026-06-30  
**Ziel:** Spieler-Performance, Team-Management, Query-Features

| Feature | Beschreibung | Priorität | Aufwand |
|---------|-------------|-----------|--------|
| Spiel-Ergebnisse | Tore, Assists, Spieler-Statistiken | 🔴 Hoch | M |
| Team-Zusammensetzung | Mannschaft ↔ Spieler-Zuordnung | 🔴 Hoch | M |
| Suchfunktion | Nach Spieler, Club, Datum | 🟡 Mittel | S |
| Export zu CSV | Berichte erzeugen | 🟢 Niedrig | S |

---

## 📅 Phase 3: Web-Interface (Zukunft)

**Zeitrahmen:** 2026-07-01+ (abhängig von Phase 1 & 2)  
**Ziel:** HTML/CSS/JavaScript GUI (Moderne Alternative zu Swing)

| Task | Notizen |
|------|---------|
| REST API aufsetzen (Spring Boot?) | Neue Backend-Architektur |
| Frontend (HTML/CSS/JS) | Vue oder Vanilla JS |
| Datensynchronisierung | Zwischen Swing & Web |

---

## 📊 Status-Dashboard

### Gesamtfortschritt: **Phase 1 – 60%**

```
Phase 1 (MVP):     ████████░░░░░░░░░░░  60%
  ├─ Architektur:  ██████████░░░░░░░░░░ 100% ✓
  ├─ Features:     ███████░░░░░░░░░░░░░  75%
  ├─ Tests:        ████░░░░░░░░░░░░░░░░  40%
  └─ Docs:         ███████░░░░░░░░░░░░░  70%

Phase 2 (Extended): ░░░░░░░░░░░░░░░░░░░░   0%
Phase 3 (Web):     ░░░░░░░░░░░░░░░░░░░░   0%
```

---

## 🔄 Wöchentliche Status-Updates

### Diese Woche (KW 13, 26.03. – 02.04.)

**Was wurde erreicht:**
- ✅ Dokumentation (Handbuch, Architektur) fertiggestellt
- ✅ Projektvorlage-Struktur aufgesetzt
- ⏳ Validierung refaktoriert (Partner)

**Was kommt nächste Woche:**
- Spieler-Auflisten Feature
- Test-Coverage erhöhen
- Code-Review durchführen

**Blocker / Probleme:**
- Keine bekannt

**Ressourcen-Einsatz:**
- Entwicklung: 6 h
- Test: 4 h
- Admin: 2 h

---

## 🏆 Key Results (OKR-Style)

### Q2 2026 Ziele

**Objective 1:** Stabile, produktionsreife Beta-Version (Phase 1)
- KR1: 100% Core-Features implementiert & getestet
- KR2: Test-Coverage > 85%
- KR3: Null kritische Bugs vor Release

**Objective 2:** Team-Kultur (Learning & Best Practice)
- KR1: Jeder hatte mindestens 1 Code-Review
- KR2: Dokumentation 100% aktuell
- KR3: Keine Code-Duplikate

---

## 🔗 Abhängigkeiten & Kritische Pfade

```
Architektur (✓)
  ↓
Features (75%)
  ├─→ Tests → Code-Review → Release
  └─→ Persistierung → Phase 2

Dokumentation (70%) — laufend parallel
```

**Kritischer Pfad:** Features → Tests → Code-Review → Release (ca. 2 Wochen)

---

## 📋 Retrospektive & Learnings

### Was funktioniert gut?
- ✅ MVC-Architektur ist klar & wartbar
- ✅ Team versteht OOP-Prinzipien
- ✅ Dokumentation wird gerne gelesen

### Was braucht Verbesserung?
- ⚠ Test-Schreiben dauert länger als erwartet
- ⚠ Validierungslogik war redundant (jetzt refaktoriert)
- ⚠ Feature-Scope tendiert zu Creep (besser planen!)

### Action Items (für nächste Phase)
- Test-Zusammenhänge früher planen (TDD)
- Größere Code-Review-Sessions (peer learning)
- Wöchentliche Retrospektive statt monatlich

---

## 🔧 Tools & Monitoring

| Tool | Status | Nutzung |
|------|--------|---------|
| **Git** | ✅ In Nutzung | Versionskontrolle |
| **GitHub Issues** | 🔵 Zu integrieren | Bug/Task Tracking |
| **JUnit** | ✅ In Nutzung | Unit Tests |
| **JaCoCo** | 🔵 Geplant | Code Coverage |
| **Sonarqube** | 🔵 Geplant | Code Quality |

---

## 📞 Kontakt & Eskalation

| Frage | Kontakt | Slack |
|-------|---------|-------|
| Architektur-Entscheidung? | Lehrkraft | @arch-leads |
| Neues Feature? | Scrum Master | #backlog |
| Bug gefunden? | Dev Lead | #bugs |
| Dokumentation? | Doc Lead | #docs |

---

## 📎 Anhänge

- [PFLICHTENHEFT.md](PFLICHTENHEFT.md) – Detaillierte Anforderungen
- [ARCHITEKTUR.md](ARCHITEKTUR.md) – Technische Spezifikation
- [Review-Prozess](../prozesse/review-prozess.md) – Qualitätssicherung
- [Git-Workflow](../../../docs/git-workflow.md) – Branches & Merging

---

*Zuletzt aktualisiert: 2026-03-26*  
*Nächster Review: 2026-04-02*  
*Verantwortlich: Lehrkraft / Scrum Master*
