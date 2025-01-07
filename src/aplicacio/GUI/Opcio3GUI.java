package aplicacio.GUI;

import javax.swing.*;

import dades.Associacions.Associacio;
import dades.Associacions.LlistaAssociacions;
import dades.Membres.Alumnes;
import dades.Membres.Membres;
import dades.Membres.Professors;
import dades.Persistencia.DataManager;

import java.awt.*;

/**
 * Classe Opcio3GUI per mostrar els membres actius d'una associació.
 * Permet filtrar els membres actius per professors, alumnes o ambdós.
 *
 * @author Paolo
 */
public class Opcio3GUI extends JFrame {
    /**
     * Constructor per inicialitzar la finestra de l'opció 3.
     *
     * @param parent La finestra principal des d'on es crida aquesta subfinestra.
     * @author Paolo
     */
    @SuppressWarnings("unused")
    public Opcio3GUI(JFrame parent) {
        // Configuració de la finestra
        setTitle("Mostrar Membres Actius");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        // Crear el panell principal amb un BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Afegir un títol a la finestra
        JLabel titleLabel = new JLabel("Selecciona un filtre per mostrar membres actius", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Crear el panell de botons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton professorsButton = new JButton("Professors"); // Botó per filtrar professors
        JButton alumnesButton = new JButton("Alumnes"); // Botó per filtrar alumnes
        JButton ambosButton = new JButton("Ambdós"); // Botó per mostrar tots els membres
        JButton tornarButton = new JButton("Tornar"); // Botó per tornar a la finestra principal

        // Afegir botons al panell de botons
        buttonPanel.add(professorsButton);
        buttonPanel.add(alumnesButton);
        buttonPanel.add(ambosButton);
        buttonPanel.add(tornarButton);

        // Afegir el panell de botons al costat esquerre
        mainPanel.add(buttonPanel, BorderLayout.WEST);

        // Crear un àrea de text per mostrar els resultats
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false); // Fer que el text sigui només de lectura
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea); // Afegir un scroll per a l'àrea de text
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultats")); // Afegir un títol al scroll
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Afegir el panell principal a la finestra
        add(mainPanel);

        // Assignar accions als botons
        professorsButton.addActionListener(e -> mostrarMembresActius(1, resultArea)); // Professors
        alumnesButton.addActionListener(e -> mostrarMembresActius(2, resultArea)); // Alumnes
        ambosButton.addActionListener(e -> mostrarMembresActius(3, resultArea)); // Ambdós

        // Acció del botó "Tornar" per tornar a la finestra principal
        tornarButton.addActionListener(e -> {
            dispose(); // Tanca la finestra actual
            parent.setVisible(true); // Torna a mostrar la finestra principal
        });
    }

    /**
     * Mostra els membres actius segons el filtre seleccionat.
     *
     * @param filtre    Filtre seleccionat: 1=Professors, 2=Alumnes, 3=Ambdós.
     * @param resultArea Àrea de text on es mostraran els resultats.
     * @author Paolo
     */
    private void mostrarMembresActius(int filtre, JTextArea resultArea) {
        resultArea.setText(""); // Neteja l'àrea de text
        boolean membresTrobats = false; // Indica si s'han trobat membres

        resultArea.append("Membres actius segons el filtre seleccionat:\n");

        // Obtenir la llista d'associacions des del DataManager
        LlistaAssociacions associacionsInicials = DataManager.getInstance().getAssociacionsInicials();

        // Recorre totes les associacions
        for (int i = 0; i < associacionsInicials.getNumAssociacions(); i++) {
            Associacio associacio = associacionsInicials.copia()[i];
            Membres[] membres = associacio.getLlistaMembres();

            // Recorre els membres de cada associació
            for (Membres membre : membres) {
                // Comprova si el membre està actiu
                if (membre != null && membre.getDataBaixa() == null) {
                    switch (filtre) {
                        case 1: // Professors
                            if (membre instanceof Professors) {
                                resultArea.append(membre + "\n"); // Mostra el professor
                                membresTrobats = true;
                            }
                            break;
                        case 2: // Alumnes
                            if (membre instanceof Alumnes) {
                                resultArea.append(membre + "\n"); // Mostra l'alumne
                                membresTrobats = true;
                            }
                            break;
                        case 3: // Ambdós
                            resultArea.append(membre + "\n"); // Mostra qualsevol membre actiu
                            membresTrobats = true;
                            break;
                    }
                }
            }
        }

        // Si no s'han trobat membres, mostrar un missatge
        if (!membresTrobats) {
            resultArea.append("No s'han trobat membres actius amb aquest filtre.\n");
        }
    }
}
