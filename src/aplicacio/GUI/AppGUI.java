package aplicacio.GUI;

import javax.swing.*;

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
    /**
     * Constructor principal de la classe AppGUI.
     * Configura la interfície gràfica, inicialitza les dades i crea els botons.
     * 
     * @author Paolo
     */
    public AppGUI() {
        setTitle("Gestió d'Associacions");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        try {
            inicialitzarDades();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);

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

        for (int i = 0; i < botons.length; i++) {
            JButton button = new JButton(botons[i]);
            button.addActionListener(new ButtonClickListener(i + 1)); // Opcions comencen en 1
            panel.add(button);
        }

        add(panel);
    }

    /**
     * Classe per gestionar els clics dels botons.
     * 
     * @author Paolo
     */
    private class ButtonClickListener implements ActionListener {
        private final int opcio;

        /**
         * Constructor que assigna el número d'opció al listener.
         * @param opcio Número de l'opció seleccionada.
         * @author Paolo
         */
        public ButtonClickListener(int opcio) {
            this.opcio = opcio;
        }

        /**
         * Mètode que es crida quan es prem un botó.
         * Executa l'acció corresponent segons el número de l'opció.
         * @author Paolo
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (opcio) {
                case 2:
                    // Mostra la GUI per a l'opció 2
                    mostrarVentanaEmergente(new Opcio2GUI(associacions, AppGUI.this));
                    break;
                case 3:
                    // Mostra la GUI per a l'opció 3
                    mostrarVentanaEmergente(new Opcio3GUI(AppGUI.this));
                    break;
                case 18:
                    // Mostra la finestra de confirmació per sortir
                    SubventanaSortir sortirDialog = new SubventanaSortir(AppGUI.this, associacions, membres, accions);
                    sortirDialog.setVisible(true);
                    break;
                default:
                    // Per a opcions no implementades, mostra un missatge de "treball en progrés"
                    mostrarVentanaEmergente(new VentanaTrabajoEnProgreso(AppGUI.this));
            }
        }
    }

    /**
     * Inicialitza les dades necessàries per a l'aplicació.
     * @throws IOException Si es produeix un error al carregar o crear fitxers.
     * 
     * @author Paolo
     */
    private void inicialitzarDades() throws IOException {
        String fitxerAssociacions = "associacions.dat";
        String fitxerMembres = "membres.txt";
        String fitxerAccions = "accions.txt";

        // Comprovar o crear el fitxer d'associacions
        java.io.File fileAssociacions = new java.io.File(fitxerAssociacions);
        if (!fileAssociacions.exists()) {
            System.out.println("El fitxer d'associacions no existeix. Creant un nou fitxer...");
            LlistaAssociacions associacionsInicials = new LlistaAssociacions(50);
            GestorPersistencia.guardarAssociacions(fitxerAssociacions, associacionsInicials);
        }

        // Comprovar o crear el fitxer de membres
        java.io.File fileMembres = new java.io.File(fitxerMembres);
        if (!fileMembres.exists()) {
            System.out.println("El fitxer de membres no existeix. Creant un nou fitxer...");
            LlistaMembres membresInicials = new LlistaMembres("General", 100);
            GestorPersistencia.guardarMembres(fitxerMembres, membresInicials);
        }

        // Comprovar o crear el fitxer d'accions
        java.io.File fileAccions = new java.io.File(fitxerAccions);
        if (!fileAccions.exists()) {
            System.out.println("El fitxer d'accions no existeix. Creant un nou fitxer...");
            LlistaAccions accionsInicials = new LlistaAccions(50);
            GestorPersistencia.guardarAccions(fitxerAccions, accionsInicials);
        }

        // Carregar dades des dels fitxers
        System.out.println("Carregant dades...");
        GestorPersistencia.carregarDades(fitxerAssociacions, fitxerMembres, fitxerAccions, associacions, membres, accions);
    }

    /**
     * Mostra una finestra emergent i oculta la finestra principal mentre aquesta està oberta.
     * @param ventanaEmergente Finestra emergent que es vol mostrar.
     * 
     * @author Paolo
     */
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
     * Punt d'entrada principal per a la GUI.
     * Inicialitza i mostra la finestra principal de l'aplicació.
     * 
     * @author Paolo
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppGUI gui = new AppGUI();
            gui.setVisible(true);
        });
    }
}
