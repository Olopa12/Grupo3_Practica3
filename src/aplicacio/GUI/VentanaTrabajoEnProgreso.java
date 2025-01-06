package aplicacio.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe per a la finestra "Trabajo en Progreso".
 * Mostra un missatge i un botó per tornar al menú principal.
 * 
 * @author Paolo
 */
public class VentanaTrabajoEnProgreso extends JFrame{
    public VentanaTrabajoEnProgreso(JFrame parent) {
        setTitle("Trabajo en Progreso");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        // Crear el panell principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Missatge de treball en progrés
        JLabel mensaje = new JLabel("Trabajo en Progreso", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(mensaje, BorderLayout.CENTER);

        // Botó per tornar al menú principal
        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Tanca aquesta finestra
                parent.setVisible(true); // Mostra la finestra principal
            }
        });
        panel.add(botonVolver, BorderLayout.SOUTH);

        // Afegir el panell a la finestra
        add(panel);
    }

    /**
     * Exemple de com llançar la finestra de "Trabajo en Progreso".
     */
    public static void main(String[] args) {
        JFrame parent = new JFrame("Parent Window");
        parent.setSize(400, 300);
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        VentanaTrabajoEnProgreso ventana = new VentanaTrabajoEnProgreso(parent);
        ventana.setVisible(true);
    }
}
