package aplicacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Classe per implementar la GUI de l'aplicació App.
 * Mostra un menú principal amb botons per executar cada opció.
 * 
 * @author Paolo
 */
public class AppGUI extends JFrame{
    public AppGUI() {
        setTitle("Gestió d'Associacions");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el layout del menú
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        // Títol
        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);

        // Crear botons per a cada opció
        for (int i = 1; i <= 18; i++) {
            JButton button = new JButton("Opció " + i);
            button.addActionListener(new ButtonClickListener(i));
            panel.add(button);
        }

        // Afegir el panell a la finestra
        add(panel);
    }

    /**
     * Classe per gestionar els clics dels botons.
     */
    private class ButtonClickListener implements ActionListener {
        private final int opcio;

        public ButtonClickListener(int opcio) {
            this.opcio = opcio;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (opcio) {
                case 1:
                    App.opcio1();
                    break;
                case 2:
                    App.opcio2();
                    break;
                case 3:
                    App.opcio3();
                    break;
                // Afegir les altres opcions
                case 18:
                    SubventanaSortir sortirDialog = new SubventanaSortir(AppGUI.this);
                    sortirDialog.setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opció no implementada encara.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Punt d'entrada per a la GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppGUI gui = new AppGUI();
            gui.setVisible(true);
        });
    }
}
