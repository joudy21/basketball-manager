package Start;

/**
 * Entität: Mannschaft
 * Repräsentiert eine Team-Zusammensetzung mit einem Namen.
 * 
 * @author Schülerprojekt Basketball-Manager
 * @version 1.0
 */
public class Mannschaft {
    private String name;

    /**
     * Erstellt eine neue Mannschaft mit Validierung.
     * 
     * @param name Team-Name (nicht null, nicht leer)
     * @throws IllegalArgumentException wenn name ungültig
     */
    public Mannschaft(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Mannschaft-Name darf nicht leer sein!");
        }
        this.name = name.trim();
    }

    /**
     * @return Der Name der Mannschaft
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt einen neuen Namen mit Validierung.
     * 
     * @param name neuer Name
     * @throws IllegalArgumentException wenn leer
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Mannschaft-Name darf nicht leer sein!");
        }
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return "Mannschaft: " + name;
    }
}