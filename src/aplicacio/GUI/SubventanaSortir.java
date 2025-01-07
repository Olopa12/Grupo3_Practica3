package aplicacio.GUI;
import javax.swing.*;

import dades.Accions.LlistaAccions;
import dades.Associacions.LlistaAssociacions;
import dades.Membres.LlistaMembres;
import dades.Persistencia.GestorPersistencia;

import java.awt.*;
import java.io.IOException;

/**
 * Subventana per gestionar l'opció de sortir de l'aplicació.
 * Mostra una finestra de diàleg que pregunta si es volen guardar els canvis abans de sortir.
 * 
 * @author Paolo
 */
public class SubventanaSortir extends JDialog{
    private final LlistaAssociacions associacions;
    private final LlistaMembres membres;
    private final LlistaAccions accions;

     /**
     * Constructor per crear la finestra de sortida.
     * @param parent El JFrame principal.
     * @param associacions La llista d'associacions actual.
     * @param membres La llista de membres actual.
     * @param accions La llista d'accions actual.
     *  @author Paolo
     */
	public SubventanaSortir(JFrame parent, LlistaAssociacions associacions, LlistaMembres membres, LlistaAccions accions) {
        super(parent, "Sortir de l'Aplicació", true);
        this.associacions = associacions;
        this.membres = membres;
        this.accions = accions;
        configurarVentana();
    }

    /**
     * Configura els components de la finestra.
     *  @author Paolo
     */
    @SuppressWarnings("unused")
    private void configurarVentana() {
        setSize(300, 150);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));

        // Missatge
        JLabel messageLabel = new JLabel("Desitja guardar els canvis abans de sortir?", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);

        // Botons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton saveButton = new JButton("Sí");
        saveButton.addActionListener(e -> guardarICerrar());
        buttonPanel.add(saveButton);

        JButton noSaveButton = new JButton("No");
        noSaveButton.addActionListener(e -> sortirSenseGuardar());
        buttonPanel.add(noSaveButton);

        JButton cancelButton = new JButton("Cancel·lar");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Guarda les dades i tanca l'aplicació.
     *  @author Paolo
     */
    private void guardarICerrar() {
        final String FITXER_ASSOCIACIONS = "associacions.dat";
        final String FITXER_MEMBRES = "membres.txt";
        final String FITXER_ACCIONS = "accions.txt";

        try {
            GestorPersistencia.guardarDades(FITXER_ASSOCIACIONS, FITXER_MEMBRES, FITXER_ACCIONS, associacions, membres, accions);
            JOptionPane.showMessageDialog(this, "Dades guardades correctament.", "Guardar i Sortir", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar les dades: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Tanca l'aplicació sense guardar.
     *  @author Paolo
     */
    private void sortirSenseGuardar() {
        JOptionPane.showMessageDialog(this, "Sortint sense guardar els canvis.", "Sortir", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}