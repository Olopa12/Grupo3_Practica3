package aplicacio.GUI;
import javax.swing.*;

import aplicacio.App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Subventana per gestionar l'opció de sortir de l'aplicació.
 * Mostra una finestra de diàleg que pregunta si es volen guardar els canvis abans de sortir.
 * 
 * @author Paolo
 */
public class SubventanaSortir extends JDialog{
    @SuppressWarnings("unused")
	public SubventanaSortir(JFrame parent) {
        super(parent, "Sortir de l'Aplicació", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // Títol
        JLabel messageLabel = new JLabel("Desitja guardar els canvis abans de sortir?", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);

        // Botons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton saveButton = new JButton("Sí");
        saveButton.addActionListener(new SaveAndExitListener());
        buttonPanel.add(saveButton);

        JButton noSaveButton = new JButton("No");
        noSaveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sortint sense guardar els canvis.", "Sortir", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });
        buttonPanel.add(noSaveButton);

        JButton cancelButton = new JButton("Cancel·lar");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Listener per guardar els canvis i sortir.
     */
    private class SaveAndExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Aquí es guarden les dades
                App.sortirAplicacio();
                JOptionPane.showMessageDialog(SubventanaSortir.this, "Dades guardades correctament.", "Guardar i Sortir", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(SubventanaSortir.this, "Error al guardar les dades: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
