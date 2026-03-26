# Prozess: Code-Review durchführen

Standard-Ablauf für Code-Reviews, damit alle PRs sauber ins Projekt kommen.

---

## 🎯 Zweck

1. **Qualität sichern:** Code folgt Standards
2. **Wissen teilen:** Team lernt voneinander
3. **Bugs früh finden:** Vier-Augen-Prinzip
4. **Architektur schützen:** Neue Änderungen passen ins Design

---

## 📋 Reviewer-Checkliste

### Akzeptanz (Gating)

Vor du tieferes Review machst, prüfe diese Basics:

- [ ] **PR hat aussagekräftige Beschreibung** (nicht leer!)
- [ ] **Branch folgt Namenskonvention** (feature/…, bugfix/…)
- [ ] **Tests sind vorhanden** (für neue Features/Bugfixes)
- [ ] **Alle Tests sind grün** (CI/CD green, oder lokal verifiziert)
- [ ] **Code kompiliert** (keine Syntax-Fehler)

**Wenn eines fehlt:** Comment schreiben & um die Behebung bitten.

```markdown
❌ **Bitte beachten:**
- [ ] Tests fehlen für Spieler.java-Änderungen
- [ ] Branch hieß "master-spieler-zeug" statt "feature/spieler-…"

Bitte nächstes Mal:
1. Zuerst Tests schreiben (TDD)
2. Branch mit konvention benennen
```

### Logik & Design (tieferes Review)

Wenn Basics passen:

| Prüfpunkt | Fragen | Beispiel-Comment |
|-----------|--------|-----------------|
| **Funktionalität** | Macht der Code das, was er soll? Hat test alle edge cases? | ✅ gute Tests für Nummer-Validierung (0, 1, 99, 100) |
| **Architektur** | Passt es ins MVC/OOP-Design? | ⚠️ Diese Logik gehört in Club-Klasse, nicht ins MainWindow |
| **Code-Qualität** | DRY? SSOT? Verständlich? | 🔴 Dieser validateString-Code wiederholt sich 3x. Bitte zentralisieren |
| **Performance** | Ist es effizient gemacht? | ✅ ArrayList.sort() ist O(n log n), OK |
| **Fehlerbehandlung** | Sind Error-Cases behandelt? | ⚠️ Was passiert wenn Club() null ist? |

### Best-Practice Checks

| Prinzip | Audit-Frage | Aktion |
|---------|------------|--------|
| **DRY** | Gibt es duplizierter Code? | Code-Segment in Hilfsmethode paken |
| **SSOT** | Sind Daten mehrfach gespeichert? | Nur eine Quelle (Model) nutzen |
| **Kohäsion** | Gehört diese Logik in diese Klasse? | Umstrukturieren falls nötig |
| **Kopplung** | Hängt diese Klasse zu sehr von anderen ab? | Interfaces nutzen | Dependency Inj. |

### Style-Checks

- [ ] Code-Stil ist konsistent (camelCase, Einrückung)
- [ ] Variablennamen sind aussagekräftig (`spieler` ja, `x` nein)
- [ ] Zeilenlänge < 100 Zeichen
- [ ] Keine vielen levels von Nesting (max 3-4)
- [ ] Kommentare erklären **WARUM**, nicht **WAS**

**Schlecht:**
```java
int x = 5; // x ist 5
```

**Gut:**
```java
int maxSpielerProTeam = 5; // Regel: max 5 Spieler pro Team
```

### Dokumentation

- [ ] JavDoc für alle public Methods/Classes vorhanden?
- [ ] Handbuch aktualisiert (falls Architektur ändert)?
- [ ] Routine-Dokument zu dieser Änderung vorhanden?
- [ ] Keine TODO/FIXME ohne Kontext hinterlassen

---

## 🔍 Beispiel-Review (1. Durchgang)

**PR:** #42 - Spieler-Auflisten Feature  
**Author:** Max Schein  
**Branch:** feature/spieler-auflisten

### Code-Snippet zu reviewen:

```java
// MainWindow.java
private JPanel spielerPanel() {
    JPanel p = new JPanel(new GridLayout(4, 2, 10, 10));
    p.setBorder(new EmptyBorder(20, 20, 20, 20));
    
    tfVorname = new JTextField();
    tfNachname = new JTextField();
    spNummer = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
    
    JButton btn = new JButton("Spieler speichern");
    btn.addActionListener(e -> {
        try {
            int nr = (Integer) spNummer.getValue();
            controller.saveSpieler(tfVorname.getText(), 
                                   tfNachname.getText(), 
                                   nr);
            tfVorname.setText("");
            tfNachname.setText("");
            spNummer.setValue(1);
        } catch (NumberFormatException ex) {
            showMessage("FEHLER: Nummer ungültig!");
        }
    });
    
    p.add(new JLabel("Vorname:")); p.add(tfVorname);
    p.add(new JLabel("Nachname:")); p.add(tfNachname);
    p.add(new JLabel("Nummer:")); p.add(spNummer);
    p.add(new JLabel("")); p.add(btn);
    
    return p;
}
```

### Review-Kommentar:

```markdown
✅ **Allgemein gut!** Code ist verständlich und Tests sind vorhanden.

**Aber ein par Punkte:**

1️⃣ **Code-Duplikatpotenzial:** (Architektur)
   Der Clear-Fields-Code nach speichern wiederholt sich im Club-Panel.
   
   💡 Vorschlag: Extrahiere zu `clearFieldsForSpieler()` Methode.

2️⃣ **Fehlerbehandlung:** (Quality)
   ```java
   } catch (NumberFormatException ex) {
       showMessage("FEHLER: Nummer ungültig!");
   }
   ```
   Aber: `spNummer` ist ein JSpinner mit Model, werft keine NFE!
   Kannst den catch entfernen.

3️⃣ **Test-Question:** (Tests)
   Gibt es einen Integrations-Test, dass:
   - Spieler tatsächlich in model.getClub() landet?
   - Felder tatsächlich geleert werden?

**Kleine Anpassung -> dann Approved** ✓

Alle anderen Tests grün? ✅
```

---

## 💬 Review-Kommunikation Best Practice

### Positiv formulieren

❌ **Schlecht:**
```
Das ist falsch gemacht.
```

✅ **Gut:**
```
Hier könnte man einen etwas anderen Approach versuchen:
Statt mehrfach zu validieren, zentralisiere die Validierung 
in einer Hilfsmethode -> mehr DRY!
```

### Priorisieren

**🔴 Blocker** – Muss behoben sein:
- Breaking Design
- Sicherheitslecks
- Tests grün

**🟡 Sollte** – Best Practice, aber nicht kritisch:
- Code-Duplikate
- Style-Verbesserungen

**🟢 Optional** – Diskussions-Stoff:
- Alternative Approaches
- Nice-to-have Refactoring

---

## 🔄 Review-Zyklus

### 1. Reviewer submitted Comment

```markdown
**Review Status:** 🔴 Requesting Changes

Siehe mein obiges Feedback zum Punkt 1 & 2.
```

### 2. Author fixt Probleme

Pusht neue Commits auf Feature-Branch.

GitHub auto-notifies reviewer → Review nochmal ansehen.

### 3. Reviewer checked Fixes

```markdown
**Review Status:** ✅ Approved

Danke für die schnellen Fixes! Commits sehen gut aus.
Hier noch freigegeben für Merge zu main.
```

### 4. Code gemergt

Reviewer OR Author mergt zu main (siehe [neue-routine-erstellen.md](neue-routine-erstellen.md)).

---

## 🏆 Gute Review-Praktiken

### ✅ DO's

- ✅ Sei konstruktiv & hilfreiche (nicht kritisch oder verletzend)
- ✅ Stelle Fragen statt Befehle ("Warum hier Arrays statt AL?" üfaut "Nutze ArrayList")
- ✅ Erkläre **warum** eine Änderung besser wäre
- ✅ Lobe gute Ansätze ("Nice pattern hier mit dem Comparator!")
- ✅ Review zeitnah (max 24h), nicht Tage später
- ✅ Selbst Code schreiben -> Verstehen für Anfängerfehler haben

### ❌ DON'Ts

- ❌ Nicht persönlich werden ("Dummer Code", "Das hättest du wissen müssen")
- ❌ Nicht einfach ablehnen ohne Erklärung
- ❌ Nicht alle Smaller Style-Issues blockieren (defer non-critical stuff)
- ❌ Nicht Stunden lang von Review abwenden
- ❌ Nicht dein Ego in Vordergrund stellen

---

## 📋 Review-Checkliste (kurzform)

```
Code-Review Checkliste
======================

□ Tests - Alle grün?
□ Kompiliert - Syntax OK?
□ Funktionalität - Macht's was es soll?
□ Architektur - Passt ins Design?
□ DRY - Keine Duplikate?
□ SSOT - Eine Quelle?
□ Error Handling - Edges cases covered?
□ Performance - Kein offensichtliche Bottleneck?
□ Dokumentation - Aktualisiert?
□ Style - Konsistent?
□ Sicherheit - Keine Sicherheitslecks?
□ OOP - Gutes Design?

Ergebnis:
□ Approved
□ Requesting Changes (mit Feedback)
```

---

## 🔗 Siehe auch

- [neue-routine-erstellen.md](neue-routine-erstellen.md) – PR-Erstellen
- [ARCHITEKTUR.md](../ARCHITEKTUR.md) – Design-Prinzipien
- [redundanz-management.md](redundanz-management.md) – DRY & Duplikate

---

*Zuletzt aktualisiert: 2026-03*
