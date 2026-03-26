
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * MainWindow: View Layer
 * 
 * Verantwortung:
 * - GUI-Darstellung (Swing)
 * - User-Input Entgegennahme
 * - Event-Handling (Klicks, Text-Eingabe)
 * - Feedback anzeigen (Meldungen, Logs)
 * - Keine Geschaeftslogik!
 * 
 * @author Basketball-Manager Projekt
 * @version 1.0
 */
public class MainWindow extends JFrame implements View {
    private AppController controller;
    
    // Club-Panel Komponenten
    private JTextField tfClubName, tfJahr, tfId;
    
    // Spieler-Panel Komponenten
    private JTextField tfVorname, tfNachname;
    private JSpinner spNummer;
    
    // Spiel-Panel Komponenten
    private JTextField tfHeim, tfGast;
    
    // Output/Log
    private JTextArea output;

    /**
     * Erstellt das Hauptfenster mit allen Tabs und Komponenten.
     */
    public MainWindow() {
        setTitle("Vereinsverwaltung Pro 2026");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabs erstellen
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Club", clubPanel());
        tabs.addTab("Spieler", spielerPanel());
        tabs.addTab("Spiel", spielPanel());
        add(tabs, BorderLayout.CENTER);

        // Unteren Bereich mit Log und Reset-Button
        add(createSouthPanel(), BorderLayout.SOUTH);
    }

    /**
     * Setzt den Controller (wird von aussen aufgerufen).
     * @param controller der Controller
     */
    public void setController(AppController controller) {
        this.controller = controller;
    }

    /**
     * Erstellt den Club-Tab Panel.
     * @return JPanel fuer Club-Verwaltung
     */
    private JPanel clubPanel() {
        JPanel p = new JPanel(new GridLayout(4, 2, 10, 10));
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        tfClubName = new JTextField();
        tfJahr = new JTextField();
        tfId = new JTextField();
        JButton btn = new JButton("Club speichern");

        p.add(new JLabel("Club Name:")); p.add(tfClubName);
        p.add(new JLabel("Gruendungsjahr:")); p.add(tfJahr);
        p.add(new JLabel("ID Nummer:")); p.add(tfId);
        p.add(new JLabel("")); p.add(btn);

        btn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(tfId.getText());
                controller.saveClub(tfClubName.getText(), tfJahr.getText(), id);
            } catch (NumberFormatException ex) {
                showMessage("FEHLER: ID muss eine Zahl sein!");
            }
        });
        
        return p;
    }

    /**
     * Erstellt den Spieler-Tab Panel.
     * @return JPanel fuer Spieler-Verwaltung
     */
    private JPanel spielerPanel() {
        JPanel p = new JPanel(new GridLayout(4, 2, 10, 10));
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        tfVorname = new JTextField();
        tfNachname = new JTextField();
        spNummer = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        JButton btn = new JButton("Spieler hinzufuegen");

        p.add(new JLabel("Vorname:")); p.add(tfVorname);
        p.add(new JLabel("Nachname:")); p.add(tfNachname);
        p.add(new JLabel("Trikot-Nr:")); p.add(spNummer);
        p.add(new JLabel("")); p.add(btn);

        btn.addActionListener(e -> 
            controller.saveSpieler(tfVorname.getText(), tfNachname.getText(), 
                                   (int)spNummer.getValue())
        );
        
        return p;
    }

    /**
     * Erstellt den Spiel-Tab Panel.
     * @return JPanel fuer Spiel-Verwaltung
     */
    private JPanel spielPanel() {
        JPanel p = new JPanel(new GridLayout(3, 2, 10, 10));
        p.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        tfHeim = new JTextField();
        tfGast = new JTextField();
        JButton btn = new JButton("Spiel planen");

        p.add(new JLabel("Heimteam:")); p.add(tfHeim);
        p.add(new JLabel("Gastteam:")); p.add(tfGast);
        p.add(new JLabel("")); p.add(btn);

        btn.addActionListener(e -> 
            controller.saveSpiel(tfHeim.getText(), tfGast.getText())
        );
        
        return p;
    }

    /**
     * Erstellt den unteren Bereich mit Log und Reset-Button.
     * @return JPanel fuer South-Area
     */
    private JPanel createSouthPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        output = new JTextArea(8, 40);
        output.setEditable(false);
        output.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(output);

        JButton btnReset = new JButton("Alle Daten loeschen");
        btnReset.setBackground(new Color(220, 53, 69));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Arial", Font.BOLD, 12));
        btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnReset.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Wirklich alles loeschen?", "Bestaetigen", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                controller.resetAll();
            }
        });

        southPanel.add(new JLabel("Aktivitaeten:"));
        southPanel.add(scrollPane);
        southPanel.add(Box.createVerticalStrut(10));
        southPanel.add(btnReset);

        return southPanel;
    }

    /**
     * Leert alle Eingabefelder.
     */
    public void clearAllFields() {
        tfClubName.setText("");
        tfJahr.setText("");
        tfId.setText("");
        tfVorname.setText("");
        tfNachname.setText("");
        spNummer.setValue(1);
        tfHeim.setText("");
        tfGast.setText("");
    }

    /**
     * Leert das Log-Ausgabefeld.
     */
    public void clearOutput() {
        output.setText("");
    }

    /**
     * Schreibt eine Nachricht ins Log.
     * @param msg die Nachricht
     */
    public void showMessage(String msg) {
        output.append("> " + msg + "\n");
    }

    /**
     * Hängt eine Nachricht ins Log (in Tests genutzt).
     * @param msg die Nachricht
     */
    public void appendOutput(String msg) {
        output.append(msg + "\n");
    }
}

