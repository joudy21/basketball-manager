# ROUTINE-TEMPLATE – Vorlage für jede neue Aufgabe/Feature

Kopiere diese Vorlage und nutze sie als Basis für deine Routine-Dokumentation.

---

## 🎯 Ziel

**Eine Satz:** Was soll diese Routine erreichen?

*Beispiel: Spieler-Liste mit Sortierung nach Name und Nummer anzeigen.*

---

## 📋 Anforderungen

Detaillierte Liste:
- Anforderung 1
- Anforderung 2
- ...

*Beispiel:*
- JTable zeigt für jeden Spieler: Vorname, Nachname, Nummer
- Sortierung nach Name (A-Z)
- Sortierung nach Nummer (1-99)

---

## 🔗 Abhängigkeiten

Andere Klassen / Features, die diese Routine braucht oder beeinflusst:

| Abhängigkeit | Typ | Notiz |
|--------------|-----|-------|
| Club.java | Existiert | getSpieler() muss verfügbar sein |
| MainWindow.java | Änderung | spielerPanel() erweitern |
| AppController.java | Ggf. Anpassung | Neue Methode? |

---

## ✅ Erfolgskriterien

Definition of Done für dieser Routine:

- [ ] Code geschrieben & kompiliert
- [ ] Tests > 80% Coverage
- [ ] Code-Review bestanden
- [ ] Dokumentation aktualisiert
- [ ] Keine Regressionen (alte Tests noch grün)
- [ ] Merged zu main
- [ ] Issue geschlossen

---

## 🔄 Schritte (High-Level)

1. **Schritt 1:** Beschreibung
   - Sub-Task A
   - Sub-Task B
2. **Schritt 2:** Beschreibung
3. ...
4. **Final:** Integrieren & Testen

---

## 🧪 Tests

Welche Tests sollen geschrieben werden?

```java
// Placeholder: Beispiel-Test-Struktur
@Test
public void testXxx() {
    // Arrange
    
    // Act
    
    // Assert
}
```

---

## 📝 Notizen

- Besonderheiten?
- Konfigurationen?
- Achtung vor...?

---

## 👥 Owner & Status

| Feld | Wert |
|------|------|
| Owner | (Name) |
| Start-Datum | YYYY-MM-DD |
| End-Datum | YYYY-MM-DD (Ziel) |
| Status | 🔵 Geplant / ⏳ In Progress / 🟢 Done / ❌ Blocked |

---

## 🔗 Links

- [Neue Routine erstellen](../prozesse/neue-routine-erstellen.md)
- [Review-Prozess](../prozesse/review-prozess.md)
- [ARCHITEKTUR.md](../ARCHITEKTUR.md)

---

*Zuletzt aktualisiert: 2026-03*
