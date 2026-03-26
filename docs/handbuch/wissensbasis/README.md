# Wissensbasis

**Status:** 🔄 IN PROGRESS - Phase 1.4  
**Letzte Aktualisierung:** 23.03.2026

---

## FAQ - HÄUFIG GESTELLTE FRAGEN

### Q1: Wie starte ich die Anwendung?
**A:** Siehe [AppManager.java](../api/README.md) - Hauptklasse mit main() Methode  
**Status:** ⏳ Detaillierte Anleitung ausstehend

### Q2: Wie füge ich einen neuen Spieler hinzu?
**A:** Nutze die Spieler-Klasse und AppManager Koordination  
**Status:** ⏳ Code-Beispiel fehlt

### Q3: Wie werden Spiele verwaltet?
**A:** Basketball.java enthält die Spiel-Logik  
**Status:** ⏳ Dokumentation erforderlich

---

## BEST PRACTICES

### Code-Organisation
- [ ] Dokumentieren: Jede öffentliche Methode muss Javadoc haben
- [ ] DRY-Prinzip: Code keine nicht wiederholen
- [ ] Single Responsibility: Eine Klasse = eine Aufgabe
- [ ] Naming: Aussagekräftige Namen für Klassen/Methoden

### Fehlerbehandlung
- [ ] Exceptions dokumentieren
- [ ] Try-Catch mit aussagekräftigen Meldungen
- [ ] Fehler an Benutzer rückgeben

### Testing
- [ ] Unit Tests für kritische Funktionen
- [ ] Integration Tests für Workflows
- [ ] Edge Cases testen

---

## DESIGN PATTERNS

### Pattern 1: MVC (Model-View-Controller)
- **Model:** Spieler, Club, Mannschaft (Domain Models)
- **View:** MainWindow (GUI)
- **Controller:** AppManager (Geschäftslogik)

### Pattern 2: Singleton (Vermutung)
- AppManager ist vermutlich ein Singleton für zentrale Verwaltung
- Status: ⏳ Zu bestätigen

### Pattern 3: Entity Pattern
- Domain Objects mit Eigenschaften und Verhalten
- Status: ✅ Erkannt

---

## TROUBLESHOOTING

### Problem: GUI lädt nicht
**Lösung:** 
1. Überprüfe MainWindow.java Initialisierung
2. Prüfe [AppManager](../api/README.md) Startup-Code
3. Siehe Logs (TBD)

**Status:** ⏳ Detaillierte Troubleshooting-Guide ausstehend

---

## GLOSSAR

| Begriff | Definition |
|---|---|
| **Spieler** | Ein Basketballspieler mit Eigenschaften wie Name, Position, Nummer |
| **Club** | Ein Basketballclub mit Spielern und Mannschaften |
| **Mannschaft** | Ein Team aus mehreren Spielern |
| **Spiel** | Ein Basketball-Match zwischen zwei Mannschaften |
| **Basketball** | Die Spiel-Logik und Regel-Engine |

---

## NÄCHSTE SCHRITTE (Phase 1.4)

- [ ] FAQ erweitern mit echten Fragen
- [ ] Best Practices dokumentieren
- [ ] Design Patterns detaillieren
- [ ] Troubleshooting-Guide vervollständigen
- [ ] Glossar erweitern

---

**Status:** 🔴 INITIAL DRAFT  
**Deadline:** 07.04.2026  
**Nächste Überprüfung:** 07.04.2026
