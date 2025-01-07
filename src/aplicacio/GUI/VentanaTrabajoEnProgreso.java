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
        setTitle("Treball en progrés");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        // Crear el panell principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Missatge de treball en progrés
        JLabel mensaje = new JLabel("Treball en progrés", SwingConstants.CENTER);
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
}
