package aplicacio.GUI;

import javax.swing.*;
import dades.Associacions.Associacio;
import dades.Associacions.LlistaAssociacions;
import dades.Membres.Alumnes;
import dades.Membres.Membres;
import dades.Membres.Professors;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe Opcio2GUI per mostrar els membres d'una associació seleccionada.
 * Permet seleccionar una associació pel seu nom i filtrar els membres segons el seu rol.
 * 
 * @author Paolo
 */
public class Opcio2GUI extends JFrame {
    private final LlistaAssociacions associacions;

    /**
     * Constructor per crear la finestra de l'opció 2.
     * 
     * @param associaciones Llista d'associacions disponible.
     * @param parent Finestra principal de l'aplicació.
     * @author Paolo
     */
    @SuppressWarnings("unused")
    public Opcio2GUI(LlistaAssociacions associaciones, JFrame parent) {
        this.associacions = associaciones;

        // Configuració bàsica de la finestra
        setTitle("Mostrar Membres d'una Associació");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panell principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Afegir el títol
        JLabel titleLabel = new JLabel("Selecciona una Associació i un Filtre", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Crear el panell d'entrada per introduir el nom de l'associació
        JPanel inputPanel = new JPanel(new FlowLayout());

        JLabel nameLabel = new JLabel("Nom de l'associació: ");
        JTextField nameField = new JTextField(15);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JButton searchButton = new JButton("Cercar");
        inputPanel.add(searchButton);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Crear el panell de filtres per als membres
        JPanel filterPanel = new JPanel(new FlowLayout());
        JButton professorsButton = new JButton("Professors");
        JButton alumnesButton = new JButton("Alumnes");
        JButton ambosButton = new JButton("Ambdós");
        JButton tornarButton = new JButton("Tornar");

        // Desactivar botons inicialment
        professorsButton.setEnabled(false);
        alumnesButton.setEnabled(false);
        ambosButton.setEnabled(false);

        filterPanel.add(professorsButton);
        filterPanel.add(alumnesButton);
        filterPanel.add(ambosButton);
        filterPanel.add(tornarButton);
        mainPanel.add(filterPanel, BorderLayout.SOUTH);

        // Crear l'àrea de text per mostrar resultats
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultats"));
        mainPanel.add(scrollPane, BorderLayout.EAST);

        // Afegir el panell principal a la finestra
        add(mainPanel);

        // Accions del botó "Cercar"
        searchButton.addActionListener(e -> {
            String nomAssociacio = nameField.getText().trim();
            Associacio associacio = associacions.buscarAssociacio(nomAssociacio);

            if (associacio == null) {
                // Mostrar error si no es troba l'associació
                JOptionPane.showMessageDialog(this, "No s'ha trobat cap associació amb el nom proporcionat.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                resultArea.setText("");
                professorsButton.setEnabled(false);
                alumnesButton.setEnabled(false);
                ambosButton.setEnabled(false);
            } else {
                // Mostrar associació trobada i habilitar botons
                resultArea.setText("Associació trobada: " + nomAssociacio);
                professorsButton.setEnabled(true);
                alumnesButton.setEnabled(true);
                ambosButton.setEnabled(true);

                // Afegir accions als botons de filtre
                professorsButton.addActionListener(new FilterActionListener(associacio, resultArea, 1));
                alumnesButton.addActionListener(new FilterActionListener(associacio, resultArea, 2));
                ambosButton.addActionListener(new FilterActionListener(associacio, resultArea, 3));
            }
        });

        // Acció del botó "Tornar"
        tornarButton.addActionListener(e -> {
            dispose();
            parent.setVisible(true);
        });
    }

    /**
     * Classe interna per gestionar les accions dels filtres.
     * Mostra els membres segons el filtre seleccionat (professors, alumnes o ambdós).
     * 
     * @author Paolo
     */
    private static class FilterActionListener implements ActionListener {
        private final Associacio associacio;
        private final JTextArea resultArea;
        private final int filtre;

        /**
         * Constructor per inicialitzar el listener dels botons de filtre.
         * 
         * @param associacio Associació seleccionada.
         * @param resultArea Àrea de text on es mostraran els resultats.
         * @param filtre Tipus de filtre (1 = Professors, 2 = Alumnes, 3 = Ambdós).
         */
        public FilterActionListener(Associacio associacio, JTextArea resultArea, int filtre) {
            this.associacio = associacio;
            this.resultArea = resultArea;
            this.filtre = filtre;
        }

        /**
         * Mètode que s'executa quan es prem un botó de filtre.
         * Filtra els membres segons el tipus seleccionat.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            resultArea.setText("");
            Membres[] membres = associacio.getLlistaMembres();
            boolean membresTrobats = false;

            // Filtrar i mostrar els membres segons el filtre seleccionat
            switch (filtre) {
                case 1: // Professors
                    resultArea.append("\nProfessors:\n");
                    for (Membres membre : membres) {
                        if (membre instanceof Professors) {
                            resultArea.append(membre + "\n");
                            membresTrobats = true;
                        }
                    }
                    break;

                case 2: // Alumnes
                    resultArea.append("\nAlumnes:\n");
                    for (Membres membre : membres) {
                        if (membre instanceof Alumnes) {
                            resultArea.append(membre + "\n");
                            membresTrobats = true;
                        }
                    }
                    break;

                case 3: // Ambdós
                    resultArea.append("\nTots els membres:\n");
                    for (Membres membre : membres) {
                        if (membre != null) {
                            resultArea.append(membre + "\n");
                            membresTrobats = true;
                        }
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opció no vàlida.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Si no es troben membres, mostrar un missatge
            if (!membresTrobats) {
                resultArea.append("No s'han trobat membres amb aquest filtre.");
            }
        }
    }
}
