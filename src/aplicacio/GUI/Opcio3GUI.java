package aplicacio.GUI;

import javax.swing.*;

import dades.Associacions.Associacio;
import dades.Associacions.LlistaAssociacions;
import dades.Membres.Alumnes;
import dades.Membres.Membres;
import dades.Membres.Professors;
import dades.Persistencia.DataManager;

import java.awt.*;


public class Opcio3GUI extends JFrame{
    @SuppressWarnings("unused")
    public Opcio3GUI() {
        setTitle("Mostrar Membres Actius");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el layout principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Títol
        JLabel titleLabel = new JLabel("Selecciona un filtre per mostrar membres actius", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Botons per als filtres
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton professorsButton = new JButton("Professors");
        JButton alumnesButton = new JButton("Alumnes");
        JButton ambosButton = new JButton("Ambdós");
        buttonPanel.add(professorsButton);
        buttonPanel.add(alumnesButton);
        buttonPanel.add(ambosButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Àrea de text per mostrar els resultats
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Afegir el panell principal a la finestra
        add(panel);

        // Accions dels botons
        professorsButton.addActionListener(e -> mostrarMembresActius(1, resultArea));
        alumnesButton.addActionListener(e -> mostrarMembresActius(2, resultArea));
        ambosButton.addActionListener(e -> mostrarMembresActius(3, resultArea));
    }

    /**
     * Mostra els membres actius segons el filtre seleccionat.
     *
     * @param filtre    Filtre seleccionat: 1=Professors, 2=Alumnes, 3=Ambdós.
     * @param resultArea Àrea de text on es mostraran els resultats.
     */
    private void mostrarMembresActius(int filtre, JTextArea resultArea) {
        resultArea.setText("");
        boolean membresTrobats = false;

        resultArea.append("Membres actius segons el filtre seleccionat:\n");

        LlistaAssociacions associacionsInicials = DataManager.getInstance().getAssociacionsInicials();

        for (int i = 0; i < associacionsInicials.getNumAssociacions(); i++) {
            Associacio associacio = associacionsInicials.copia()[i];
            Membres[] membres = associacio.getLlistaMembres();

            for (Membres membre : membres) {
                if (membre != null && membre.getDataBaixa() == null) {
                    switch (filtre) {
                        case 1:
                            if (membre instanceof Professors) {
                                resultArea.append(membre + "\n");
                                membresTrobats = true;
                            }
                            break;
                        case 2:
                            if (membre instanceof Alumnes) {
                                resultArea.append(membre + "\n");
                                membresTrobats = true;
                            }
                            break;
                        case 3:
                            resultArea.append(membre + "\n");
                            membresTrobats = true;
                            break;
                    }
                }
            }
        }

        if (!membresTrobats) {
            resultArea.append("No s'han trobat membres actius amb aquest filtre.\n");
        }
    }
}
