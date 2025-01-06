package aplicacio;

import java.io.IOException;
import java.util.Scanner;

import Utilitats.Data;
import dades.Associacions.*;
import dades.Excepcions.InstanciaNoTrobada;
import dades.Persistencia.GestorPersistencia;
import dades.Membres.Alumnes;
import dades.Membres.LlistaMembres;
import dades.Membres.Membres;
import dades.Membres.Professsors;
import javax.swing.*;
import java.awt.*;

/**
 * Classe principal de l'aplicació que implementa un menú per gestionar associacions,
 * membres, accions, xerrades i demostracions.
 * Permet interactuar amb l'usuari a través d'una interfície de consola.
 * 
 * @author Paolo, Nermin, Sara, Alexandru
 * @version 1.0
 */
public class App extends JFrame{
    private static final long serialVersionUID=1L;
    private JButton[] botons; // Array per guardar els botons.
    static Scanner teclat = new Scanner(System.in);
    static LlistaAssociacions associacionsInicials = new LlistaAssociacions(50);
    static LlistaMembres llistaMembres = new LlistaMembres("General", 100);

    /**
     * Punt d'entrada principal de l'aplicació.
     * Paolo: Mostra el menú i executa les opcions seleccionades per l'usuari.
     * Alex: Apartat de l'interfaç gràfica.
     * 
     * @param args Arguments de línia de comandes (no utilitzats).
     * @throws IOException Si es produeix un error inesperat durant l'execució.
     * @author Paolo i Alexandru
     */
    public static void main(String[] args) throws Exception {
        App finestra = new App();
        finestra.setTitle("PRAC3");
        finestra.setSize(800, 600); // Assegura la mida
        finestra.setDefaultCloseOperation(EXIT_ON_CLOSE);
        finestra.setVisible(true);
        finestra.iniBotonsAccions();

        
        String fitxerAssociacions = "associacions.dat";
        String fitxerMembres = "membres.txt";

        try {
            // Comprovar si els fitxers existeixen i crear-los si no és així
            java.io.File fileAssociacions = new java.io.File(fitxerAssociacions);
            if (!fileAssociacions.exists()) {
                System.out.println("El fitxer d'associacions no existeix. Creant un nou fitxer...");
                LlistaAssociacions associacionsInicials = new LlistaAssociacions(50);
                associacionsInicials.crearFitxerBinari(fitxerAssociacions);
            }

            java.io.File fileMembres = new java.io.File(fitxerMembres);
            if (!fileMembres.exists()) {
                System.out.println("El fitxer de membres no existeix. Creant un nou fitxer...");
                LlistaMembres membresInicials = new LlistaMembres("General", 100);
                GestorPersistencia.guardarMembres(fitxerMembres, membresInicials);
            }

            // Càrrega inicial de les dades
            System.out.println("Carregant dades...");
            GestorPersistencia.carregarDades(fitxerAssociacions, fitxerMembres, associacionsInicials, llistaMembres);
            finestra.funcionalitatBotons(associacionsInicials, fitxerAssociacions);
            // Executar el menú principal
            menuPrincipal(fitxerAssociacions);

        } catch (IOException e) {
            System.err.println("Error carregant o creant els fitxers inicials: " + e.getMessage());
        } finally {
            teclat.close();
        }
    }
    
    /**
     * Crea un array amb 18 botos, un per a cada opcio.
     * @author Alex Radu
     */
    public void iniBotonsAccions() { 
        int nfil = 3, ncol = 6;
        botons = new JButton[nfil * ncol];
        this.setLayout(new GridLayout(nfil, ncol, 10, 10));

        for (int i = 0; i < nfil * ncol; i++) {
            botons[i] = new JButton("Opció " + (i + 1));
            this.add(botons[i]);
        }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Mostra el menú i executa les opcions seleccionades per l'usuari.
     * 
     * @throws Exception Si es produeix un error inesperat durant l'execució.
     * @throws NumberFormatException Si l'usuari introdueix un valor no vàlid per a una opció numèrica.
     * @author Paolo
     */
    public static void menuPrincipal(String fitxerAssociacions) {
        int opcio = 0;

        try {
            associacionsInicials.llegirFitxerBinari(fitxerAssociacions);
        } catch (Exception e) {
            System.out.println("Error carregant el fitxer binari...");
        }
        do {
            try {
                mostraMenu();
                System.out.print("Selecciona una opció: ");
                opcio = Integer.parseInt(teclat.nextLine());

                // Gestiona l'opció seleccionada amb un switch
                switch (opcio) {
                    case 1:
                        opcio1(associacionsInicials, fitxerAssociacions); // Mostrar les dades de la llista d'associacions
                        break;
                    case 2:
                        opcio2(); // Mostrar les dades de membres d'una associació
                        break;
                    case 3:
                        opcio3(); // Mostrar membres actius
                        break;
                    case 4:
                        opcio4(); // Mostrar la llista d'accions
                        break;
                    case 5:
                        opcio5(); // Mostrar accions d'una associació concreta
                        break;
                    case 6:
                        opcio6(); // Mostrar xerrades en una franja de dates
                        break;
                    case 7:
                        opcio7(associacionsInicials, fitxerAssociacions); // Afegir una nova associació
                        break;
                    case 8:
                        opcio8(associacionsInicials, fitxerAssociacions); // Alta d'un membre a una associació
                        break;
                    case 9:
                        opcio9(); // Afegir una nova xerrada
                        break;
                    case 10:
                        opcio10(); // Afegir una nova demostració
                        break;
                    case 11:
                        opcio11(); // Consultar demostracions no actives i calcular el cost total
                        break;
                    case 12:
                        opcio12(); // Calcular la persona més activa
                        break;
                    case 13:
                        opcio13(); // Consultar xerrades amb més assistents que un nombre determinat
                        break;
                    case 14:
                        opcio14(); // Valorar una xerrada
                        break;
                    case 15:
                        opcio15(); // Consultar la xerrada millor valorada
                        break;
                    case 16:
                        opcio16(); // Mostrar les xerrades d'una persona concreta
                        break;
                    case 17:
                        opcio17(); // Donar de baixa demostracions no actives
                        break;
                    case 18://S'ortir
                        sortirAplicacio();
                        break;
                    default:
                        System.out.println("Opció no vàlida. Si us plau, tria una opció entre 1 i 18.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Per favor intodueix un número valgut per a l'opció del menu");
            } catch (Exception e) {
                System.err.println("S'ha produït un error inesperat: " + e.getMessage());
            }
        } while (opcio != 18);
    }

    /**
     * Mostra el menú principal de l'aplicació amb totes les opcions disponibles.
     * 
     * @author Paolo
     */
    public static void mostraMenu(){
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Mostrar les dades de la llista d'associacions");
        System.out.println("2. Mostrar les dades de la llista de membres d'una associació (filtre: professors, alumnes o ambdós)");
        System.out.println("3. Mostrar les dades de la llista de membres actius (filtre: professors, alumnes o ambdós)");
        System.out.println("4. Mostrar les dades de la llista d'accions (amb filtre opcional)");
        System.out.println("5. Mostrar la llista d'accions d'una associació concreta");
        System.out.println("6. Mostrar les xerrades en una franja de dates");
        System.out.println("7. Afegir una nova associació");
        System.out.println("8. Alta d'un membre a una associació");
        System.out.println("9. Afegir una nova xerrada");
        System.out.println("10. Afegir una nova demostració");
        System.out.println("11. Consultar demostracions no actives i calcular cost total");
        System.out.println("12. Calcular la persona més activa");
        System.out.println("13. Consultar xerrades amb més d'un cert nombre d'assistents");
        System.out.println("14. Valorar una xerrada");
        System.out.println("15. Consultar la xerrada millor valorada");
        System.out.println("16. Mostrar les xerrades d'una persona concreta");
        System.out.println("17. Donar de baixa demostracions no actives dissenyades abans d'una data");
        System.out.println("18. Sortir de l'aplicació");
    }

    // Funciones de las opciones
    /**
     * Mostra les dades de la llista de associacions amb el toString().
     * @param associacionsInicials Llista de associacions.
     * @param fitxerAssociacions Fitxer del que es llegix la informacio de les llistes ed'associacions.
     * @author Alex Radu
     */
    public static void opcio1(LlistaAssociacions associacionsInicials, String fitxerAssociacions) {
        System.out.println("1. Mostrar les dades de la llista d'associacions");
        try {
            associacionsInicials.llegirFitxerBinari(fitxerAssociacions);
            System.out.println(associacionsInicials);
        } catch (Exception e) {
            System.out.println("Error carregant el fitxer binari...");
        }
    }

    public static void opcio2() {
        System.out.println("2. Mostrar les dades de la llista de membres d'una associació (filtre: professors, alumnes o ambdós)");
        // TODO: Implementar funcionalitat
    }   

    public static void opcio3() {
        System.out.println("3. Mostrar les dades de la llista de membres actius (filtre: professors, alumnes o ambdós)");
        // TODO: Implementar funcionalitat
    }

    public static void opcio4() {
        System.out.println("4. Mostrar les dades de la llista d'accions (amb filtre opcional)");
        // TODO: Implementar funcionalitat
    }

    public static void opcio5() {
        System.out.println("5. Mostrar la llista d'accions d'una associació concreta");
        // TODO: Implementar funcionalitat
    }

    public static void opcio6() {
        System.out.println("6. Mostrar les xerrades en una franja de dates");
        // TODO: Implementar funcionalitat
    }
    /**
     * Permet afegir una associacio a la llista de associacions pedint al usuaris la informació.
     * @param associacionsInicials Llista de associacions.
     * @author Alex Radu
     */
    public static void opcio7(LlistaAssociacions associacionsInicials, String fitxerAssociacions) {
        System.out.println("7. Afegir una nova associació");
        System.out.println("\nIntrodueix les seguents dades:");
        System.out.println("\n\tNom associacio: ");
        String nom = teclat.next();
        System.out.println("\n\tCorreu associacio: ");
        String correu = teclat.next();
        System.out.println("\n\tCarrera: ");
        String carrera = teclat.next();
        System.out.println("\nVols afegir directament president, tresorer i secretari, si si hauras d'introduir les dades dels tres membres per separat. (Si/No)");
        String resposta = teclat.next();
        if(resposta.equalsIgnoreCase("Si")){
            System.out.println("\nDades president: ");
            System.out.println("\n\tNom: ");
            String nomPresident = teclat.next();
            System.out.println("\n\tCorreu: ");
            String correuPresident = teclat.next();
            System.out.println("\n\tDia: ");            
            int diaPresident = teclat.nextInt();
            System.out.println("\n\tMes: ");
            int mesPresident = teclat.nextInt();
            System.out.println("\n\tAny: ");
            int anyPresident = teclat.nextInt();
            System.out.println("\n\tEnsenyament de l'alumne: ");            
            String ensenyamentPresident = teclat.next();
            System.out.println("\n\tNombre d'anys en l'ETSE: ");
            int antiguitatPresident = teclat.nextInt();
            System.out.println("\n\tEsta graduat? (Si/No) ");
            String graduatStringPresident = teclat.next();
            boolean graduatPresident = false;
            if(graduatStringPresident.equalsIgnoreCase("Si")){
                graduatPresident = true;
            }
            System.out.println("\n\nDades secretari: ");
            System.out.println("\n\tNom: ");
            String nomSecretari = teclat.next();
            System.out.println("\n\tCorreu: ");
            String correuSecretari = teclat.next();
            System.out.println("\n\tDia: ");            
            int diaSecretari = teclat.nextInt();
            System.out.println("\n\tMes: ");
            int mesSecretari = teclat.nextInt();
            System.out.println("\n\tAny: ");
            int anySecretari = teclat.nextInt();
            System.out.println("\n\tEnsenyament de l'alumne: ");            
            String ensenyamentSecretari = teclat.next();
            System.out.println("\n\tNombre d'anys en l'ETSE: ");
            int antiguitatSecretari = teclat.nextInt();
            System.out.println("\n\tEsta graduat? (Si/No) ");
            String graduatStringSecretari = teclat.next();
            boolean graduatSecretari = false;
            if(graduatStringSecretari.equalsIgnoreCase("Si")){
                graduatSecretari = true;
            }
            System.out.println("\n\nDades tresorer: ");
            System.out.println("\n\tNom: ");
            String nomTresorer = teclat.next();
            System.out.println("\n\tCorreu: ");
            String correuTresorer = teclat.next();
            System.out.println("\n\tDia: ");            
            int diaTresorer = teclat.nextInt();
            System.out.println("\n\tMes: ");
            int mesTresorer = teclat.nextInt();
            System.out.println("\n\tAny: ");
            int anyTresorer = teclat.nextInt();
            System.out.println("\n\tEnsenyament de l'alumne: ");            
            String ensenyamentTresorer = teclat.next();
            System.out.println("\n\tNombre d'anys en l'ETSE: ");
            int antiguitatTresorer= teclat.nextInt();
            System.out.println("\n\tEsta graduat? (Si/No) ");
            String graduatStringTresorer = teclat.next();
            boolean graduatTresorer = false;
            if(graduatStringTresorer.equalsIgnoreCase("Si")){
                graduatTresorer = true;
            }
            Alumnes president = new Alumnes(nomPresident, correuPresident, new Data(diaPresident, mesPresident, anyPresident), ensenyamentPresident, antiguitatPresident, graduatPresident);
            Alumnes secretari = new Alumnes(nomSecretari, correuSecretari, new Data(diaSecretari, mesSecretari, anySecretari), ensenyamentSecretari, antiguitatSecretari, graduatSecretari);
            Alumnes tresorer = new Alumnes(nomTresorer, correuTresorer, new Data(diaTresorer, mesTresorer, anyTresorer), ensenyamentTresorer, antiguitatTresorer, graduatTresorer);
            Associacio a = new Associacio(nom, correu, carrera, president, secretari, tresorer);
            associacionsInicials.afegirAssociacio(a);
            try {
                associacionsInicials.crearFitxerBinari(fitxerAssociacions);
            } catch (Exception e) {
                System.out.println("Error creant el fitxer binari...");
            }
        } else{
            Associacio a = new Associacio(nom, correu, carrera);
            associacionsInicials.afegirAssociacio(a);
            try {
                associacionsInicials.crearFitxerBinari(fitxerAssociacions);
            } catch (Exception e) {
                System.out.println("Error creant el fitxer binari...");
            }
        }
    }

    /**
     * Afegix un membre a una associacio concreta.
     * @param associacionsInicials Llista d'associacions.
     * @param fitxerAssociacions Fitxer al que guardem les associacions.
     * @author Alex Radu
     */
    public static void opcio8(LlistaAssociacions associacionsInicials, String fitxerAssociacions) {
        System.out.println("8. Alta d'un membre a una associació");
        System.out.println("\nNom de l'associacio a la que afegim el membre: ");
        String nomAssociacio = teclat.next();
        int i = 0;
        boolean trobat = false;
        try{
            while(i<associacionsInicials.getNumAssociacions() && !trobat){
                if(associacionsInicials.existeixAssociacio(nomAssociacio)){
                    System.out.println("Alumne (0) o professor (1)?\nIntrodueix el numero correspondent al que es vol afegir: ");
                    int qual = teclat.nextInt();
                    if(qual == 0){
                        System.out.println("\n\tNom: ");
                        String nom = teclat.next();
                        System.out.println("\n\tCorreu: ");
                        String correu = teclat.next();
                        System.out.println("\n\tDia: ");            
                        int dia = teclat.nextInt();
                        System.out.println("\n\tMes: ");
                        int mes = teclat.nextInt();
                        System.out.println("\n\tAny: ");
                        int any = teclat.nextInt();
                        System.out.println("\n\tEnsenyament de l'alumne: ");            
                        String ensenyament = teclat.next();
                        System.out.println("\n\tNombre d'anys en l'ETSE: ");
                        int antiguitat = teclat.nextInt();
                        System.out.println("\n\tEsta graduat? (Si/No) ");
                        String graduatString = teclat.next();
                        boolean graduat = false;
                        if(graduatString.equalsIgnoreCase("Si")){
                            graduat = true;
                        }
                        Alumnes a = new Alumnes(nom, correu, new Data(dia, mes, any), ensenyament, antiguitat, graduat);
                        associacionsInicials.buscarAssociacio(nomAssociacio).assignarMembresALlistaMembres(a);
                    } else{
                        System.out.println("\n\tNom: ");
                        String nom = teclat.next();
                        System.out.println("\n\tCorreu: ");
                        String correu = teclat.next();
                        System.out.println("\n\tDia: ");            
                        int dia = teclat.nextInt();
                        System.out.println("\n\tMes: ");
                        int mes = teclat.nextInt();
                        System.out.println("\n\tAny: ");
                        int any = teclat.nextInt();
                        System.out.println("\n\t Departament del profesor: ");            
                        String departament = teclat.next();
                        System.out.println("\n\tDespatx: ");
                        String despatx = teclat.next();
                        Professsors p = new Professsors(nom, correu, new Data(dia, mes, any), departament, despatx);
                        associacionsInicials.buscarAssociacio(nomAssociacio).assignarMembresALlistaMembres(p);
                        associacionsInicials.crearFitxerBinari(fitxerAssociacions);
                    }

                    associacionsInicials.crearFitxerBinari(fitxerAssociacions);
                    trobat = true;
                } else{
                    throw new InstanciaNoTrobada(nomAssociacio);
                }
            }
        } catch (Exception InstanciaNoTrobada) {
            opcio8(associacionsInicials, fitxerAssociacions);
        }
        
    }

    public static void opcio9() {
        System.out.println("9. Afegir una nova xerrada");
        // TODO: Implementar funcionalitat
    }

    public static void opcio10() {
        System.out.println("10. Afegir una nova demostració");
        // TODO: Implementar funcionalitat
    }

    public static void opcio11() {
        System.out.println("11. Consultar demostracions no actives i calcular cost total");
        // TODO: Implementar funcionalitat
    }

    public static void opcio12() {
        System.out.println("12. Calcular la persona més activa");
        // TODO: Implementar funcionalitat
    }

    public static void opcio13() {
        System.out.println("13. Consultar xerrades amb més d'un cert nombre d'assistents");
        // TODO: Implementar funcionalitat
    }

    public static void opcio14() {
        System.out.println("14. Valorar una xerrada");
        // TODO: Implementar funcionalitat
    }

    public static void opcio15() {
        System.out.println("15. Consultar la xerrada millor valorada");
        // TODO: Implementar funcionalitat
    }

    public static void opcio16() {
        System.out.println("16. Mostrar les xerrades d'una persona concreta");
        // TODO: Implementar funcionalitat
    }

    public static void opcio17() {
        System.out.println("17. Donar de baixa demostracions no actives dissenyades abans d'una data");
        // TODO: Implementar funcionalitat
    }

    /**
     * Agrega les funcionalitats a cada boto i el actionListener que comprova si s'ha fet click en el boto en questio.
     * @param associacionsInicials Llista d'associacions.
     * @param fitxerAssociacions Nom del fitxer binari on es guarda i llegixen les associacions.
     * @author Alex Radu
     */
    public void funcionalitatBotons(LlistaAssociacions associacionsInicials, String fitxerAssociacions){
            for (int i = 0; i < botons.length; i++) {
                int opcio = i + 1;
                botons[i].addActionListener(e -> {
                    System.out.println("Boto " + opcio + " clickat");
                    if (opcio == 1) {
                        opcio1(associacionsInicials, "associacions.dat");
                    } else if(opcio == 2){
                        opcio2();
                    } else if(opcio == 3){
                        opcio3();
                    } else if(opcio == 4){
                        opcio4();
                    } else if(opcio == 5){
                        opcio5();
                    } else if(opcio == 6){
                        opcio6();
                    } else if(opcio == 7){
                        opcio7(associacionsInicials, fitxerAssociacions);
                    } else if(opcio == 8){
                        opcio8(associacionsInicials, fitxerAssociacions);
                    } else if(opcio == 9){
                        opcio9();
                    } else if(opcio == 10){
                        opcio10();
                    } else if(opcio == 11){
                        opcio11();
                    } else if(opcio == 12){
                        opcio12();
                    } else if(opcio == 13){
                        opcio13();
                    } else if(opcio == 14){
                        opcio14();
                    } else if(opcio == 15){
                        opcio15();
                    } else if(opcio == 16){
                        opcio16();
                    } else if(opcio == 17){
                        opcio17();
                    } else{
                        sortirAplicacio();
                    }
                });
            }
    }
    
    /**
     * Mètode per gestionar la sortida de l'aplicació.
     * Aquest mètode ofereix l'opció de guardar els canvis abans de tancar l'aplicació.
     * Si l'usuari indica que vol guardar, les dades de les associacions i membres es guarden
     * en fitxers utilitzant el gestor de persistència.
     *
     * @throws IOException Si hi ha un error durant el procés de guardat de les dades.
     * @author Paolo
    */
    public static void sortirAplicacio() {
        System.out.print("Desitja guardar els canvis abans de sortir? (y/n): ");
        String resposta = teclat.nextLine();
        if (resposta.equalsIgnoreCase("y")) {
            
            try {
                GestorPersistencia.guardarDades("associacions.dat", "membres.txt", associacionsInicials, llistaMembres);
                System.out.println("Dades guardades correctament.");
            } catch (IOException e) {
                System.err.println("Error guardant les dades: " + e.getMessage());
            }
        }
        System.out.println("Sortint de l'aplicació.");
    }
}
