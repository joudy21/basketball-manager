# Index – Vollständiges Inhaltsverzeichnis

Schnelle Übersicht über alle Dokumentation in diesem Projekt.

---

## 📖 Hauptdokumente

| Datei | Zielgruppe | Thema |
|-------|-----------|--------|
| [README.md](README.md) | Alle | Willkommen & Quick-Start |
| [ARCHITEKTUR.md](ARCHITEKTUR.md) | Entwickler | Systemdesign & Komponenten |
| [PFLICHTENHEFT.md](PFLICHTENHEFT.md) | PM, Stakeholder | Anforderungen & Scope |

---

## 🗺️ Marschpläne (Phase & Meilensteine)

| Datei | Inhalt |
|-------|--------|
| [marschplaene/HAUPTMARSCHPLAN.md](marschplaene/HAUPTMARSCHPLAN.md) | Gesamtprojekt-Phasen, Milestones, To-Do-Liste, Status |

---

## 🔄 Prozessrichtlinien (Workflows)

| Datei | Workflow | Wann nutzen? |
|-------|----------|-------------|
| [prozesse/neue-routine-erstellen.md](prozesse/neue-routine-erstellen.md) | Feature-Entwicklung | Neuer Task/Feature geplant |
| [prozesse/routine-aktualisieren.md](prozesse/routine-aktualisieren.md) | Änderungen | Bestehendes Feature updaten |
| [prozesse/review-prozess.md](prozesse/review-prozess.md) | Code-Review | Vor Merge zu main |
| [prozesse/redundanz-management.md](prozesse/redundanz-management.md) | Refactoring | Duplikate/Abhängigkeiten klären |

---

## 📝 Templates (Vorlagen)

| Datei | Nutzen für |
|-------|-----------|
| [templates/ROUTINE-TEMPLATE.md](templates/ROUTINE-TEMPLATE.md) | Neue Aufgaben dokumentieren (Basis-Vorlage) |
| [templates/beispiel-routine.md](templates/beispiel-routine.md) | Beispiel: How-to mit Best Practices |

---

## 🎯 Routinen (Tag-zu-Tag Aufgaben)

Routinen sind in Zeitfenstern organisiert:

### Kurzfristig (täglich / wöchentlich)
📁 `routinen/kurzfristig/`  
Beispiele: Daily Standup, Code Review, Status Update

### Mittelfristig (monatlich)
📁 `routinen/mittelfristig/`  
Beispiele: Sprint Retrospective, Dokumentation updaten

### Langfristig (quartalsweise / jährlich)
📁 `routinen/langfristig/`  
Beispiele: Architektur-Review, Major Refactoring

---

## 🔍 Thematische Schnelllinks

### Von Anfang an
1. [README](README.md) – Start hier!
2. [ARCHITEKTUR](ARCHITEKTUR.md) – Das System verstehen
3. [PFLICHTENHEFT](PFLICHTENHEFT.md) – Was soll gebaut werden?
4. [Marschplan](marschplaene/HAUPTMARSCHPLAN.md) – Wo stehen wir?

### Feature entwickeln
1. [Neue Routine erstellen](prozesse/neue-routine-erstellen.md) – Schritt für Schritt
2. [ROUTINE-TEMPLATE](templates/ROUTINE-TEMPLATE.md) – Dokumentation-Struktur
3. [beispiel-routine](templates/beispiel-routine.md) – Lerne vom Beispiel

### Qualitätssicherung
1. [Review-Prozess](prozesse/review-prozess.md) – Code-Review Richtlinien
2. [Redundanzmanagement](prozesse/redundanz-management.md) – DRY & SSOT

### Laufender Betrieb
1. [Routine aktualisieren](prozesse/routine-aktualisieren.md) – Änderungen vornehmen
2. [Marschplan](marschplaene/HAUPTMARSCHPLAN.md) – Status tracking

---

## „Ich möchte..." – Schnellsuche

| Frage | Antwort |
|-------|--------|
| Wo fange ich an? | → [README.md](README.md) |
| Wie lädt das System? | → [ARCHITEKTUR.md](ARCHITEKTUR.md) |
| Was muss gebaut werden? | → [PFLICHTENHEFT.md](PFLICHTENHEFT.md) |
| Wo sind wir im Plan? | → [Marschplan](marschplaene/HAUPTMARSCHPLAN.md) |
| Wie schreibe ich Code hier? | → [ARCHITEKTUR.md](ARCHITEKTUR.md#designprinzipien) |
| Wie starte ich ein neues Feature? | → [neue-routine-erstellen.md](prozesse/neue-routine-erstellen.md) |
| Wie mache ich einen Pull Request? | → [review-prozess.md](prozesse/review-prozess.md) |
| Woher weiß ich, ob Code-Duplikate existieren? | → [redundanz-management.md](prozesse/redundanz-management.md) |
| Wie update ich bestehenden Code? | → [routine-aktualisieren.md](prozesse/routine-aktualisieren.md) |
| Was ist eine gute Dokumentation hier? | → [ROUTINE-TEMPLATE.md](templates/ROUTINE-TEMPLATE.md) |

---

## 📂 Physische Struktur

```
docs/handbuch/
├── README.md (Start hier)
├── INDEX.md (diese Datei)
├── ARCHITEKTUR.md
├── PFLICHTENHEFT.md
├── marschplaene/
│   └── HAUPTMARSCHPLAN.md
├── prozesse/
│   ├── neue-routine-erstellen.md
│   ├── routine-aktualisieren.md
│   ├── review-prozess.md
│   └── redundanz-management.md
├── routinen/
│   ├── kurzfristig/ (tgl/wöchentl)
│   ├── mittelfristig/ (monatl)
│   └── langfristig/ (quartal/jährlich)
└── templates/
    ├── ROUTINE-TEMPLATE.md
    └── beispiel-routine.md
```

---

## 🔄 Learning Path (empfohlen)

### Woche 1
- [ ] [README.md](README.md) lesen
- [ ] [ARCHITEKTUR.md](ARCHITEKTUR.md) überfliegen
- [ ] [PFLICHTENHEFT.md](PFLICHTENHEFT.md) lesen
- [ ] [Marschplan](marschplaene/HAUPTMARSCHPLAN.md) studieren

### Woche 2
- [ ] Ersten kleinen Task aus Marschplan aussuchen
- [ ] [neue-routine-erstellen.md](prozesse/neue-routine-erstellen.md) lesen
- [ ] Feature nach Vorlage dokumentieren

### Woche 3+
- [ ] [ARCHITEKTUR.md](ARCHITEKTUR.md) gründlich verstehen
- [ ] [review-prozess.md](prozesse/review-prozess.md) anwenden
- [ ] Code-Reviews von anderen annehmen & geben

---

*Zuletzt aktualisiert: 2026-03*  
*Fragen? Schreib der Lehrkraft oder öffne ein Issue.*
