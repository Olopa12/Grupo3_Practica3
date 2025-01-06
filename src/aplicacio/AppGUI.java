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

        // Noms personalitzats per als botons
        String[] botons = {
            "Mostrar llista d'associacions",
            "Mostrar membres d'una associació",
            "Mostrar membres actius",
            "Mostrar llista d'accions",
            "Mostrar accions d'una associació",
            "Mostrar xerrades en dates",
            "Afegir nova associació",
            "Alta d'un membre",
            "Afegir nova xerrada",
            "Afegir nova demostració",
            "Consultar demostracions no actives",
            "Calcular la persona més activa",
            "Consultar xerrades amb molts assistents",
            "Valorar una xerrada",
            "Consultar millor xerrada",
            "Mostrar xerrades d'una persona",
            "Donar de baixa demostracions antigues",
            "Sortir de l'aplicació"
        };

        // Crear botons per a cada opció
        for (int i = 0; i < botons.length; i++) {
            JButton button = new JButton(botons[i]);
            button.addActionListener(new ButtonClickListener(i + 1)); // Opcions comencen en 1
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
