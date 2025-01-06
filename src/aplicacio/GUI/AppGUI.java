package aplicacio.GUI;

import javax.swing.*;

import aplicacio.App;
import dades.Accions.LlistaAccions;
import dades.Associacions.LlistaAssociacions;
import dades.Membres.LlistaMembres;
import dades.Persistencia.DataManager;
import dades.Persistencia.GestorPersistencia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * Classe per implementar la GUI de l'aplicació App.
 * Mostra un menú principal amb botons per executar cada opció.
 * 
 * @author Paolo
 */
public class AppGUI extends JFrame{
    LlistaAssociacions associacions = DataManager.getInstance().associacionsInicials;
    LlistaMembres membres = DataManager.getInstance().llistaMembres;
    LlistaAccions accions = DataManager.getInstance().llistaAccions;
    public AppGUI() {
        setTitle("Gestió d'Associacions");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el layout del menú
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        try {
            inicialitzarDades();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
                    App.opcio1(associacions, "associacions.dat");

                    break;
                case 2:
                    App.opcio2();
                    break;
                case 3:
                    App.opcio3();
                    break;
                case 7:
                    App.opcio7(associacions, "associacions.dat"); // Afegir una nova associació
                    break;
                case 8:
                    App.opcio8(associacions, "associacions.dat"); // Alta d'un membre a una associació

                    break;
                // Afegir les altres opcions
                case 18:
                    SubventanaSortir sortirDialog = new SubventanaSortir(AppGUI.this);
                    sortirDialog.setVisible(true);
                    break;
                default:
                    mostrarVentanaEmergente(new VentanaTrabajoEnProgreso(AppGUI.this));
            }
        }
    }

    /**
     * Inicialitza les dades necessàries per a l'aplicació.
     * @throws IOException Si es produeix un error al carregar o crear fitxers.
     */
    private void inicialitzarDades() throws IOException {
        String fitxerAssociacions = "associacions.dat";
        String fitxerMembres = "membres.txt";
        String fitxerAccions = "accions.txt";

        // Comprovar si els fitxers existeixen i crear-los si no és així
        java.io.File fileAssociacions = new java.io.File(fitxerAssociacions);
        if (!fileAssociacions.exists()) {
            System.out.println("El fitxer d'associacions no existeix. Creant un nou fitxer...");
            LlistaAssociacions associacionsInicials = new LlistaAssociacions(50);
            GestorPersistencia.guardarAssociacions(fitxerAssociacions, associacionsInicials);
        }

        java.io.File fileMembres = new java.io.File(fitxerMembres);
        if (!fileMembres.exists()) {
            System.out.println("El fitxer de membres no existeix. Creant un nou fitxer...");
            LlistaMembres membresInicials = new LlistaMembres("General", 100);
            GestorPersistencia.guardarMembres(fitxerMembres, membresInicials);
        }

        java.io.File fileAccions = new java.io.File(fitxerAccions);
        if (!fileAccions.exists()) {
            System.out.println("El fitxer d'accions no existeix. Creant un nou fitxer...");
            LlistaAccions accionsInicials = new LlistaAccions(50);
            GestorPersistencia.guardarAccions(fitxerAccions, accionsInicials);
        }

        // Càrrega inicial de les dades
        System.out.println("Carregant dades...");
        GestorPersistencia.carregarDades(fitxerAssociacions, fitxerMembres, fitxerAccions, associacions, membres, accions);
    }

    private void mostrarVentanaEmergente(JFrame ventanaEmergente) {
        // Oculta la finestra principal
        this.setVisible(false);
    
        // Mostra la finestra emergent
        ventanaEmergente.setVisible(true);
    
        // Torna a mostrar la finestra principal quan es tanqui la emergent
        ventanaEmergente.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                setVisible(true);
            }
        });

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
