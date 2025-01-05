package aplicacio;

import java.io.IOException;
import java.util.Scanner;

import Utilitats.Data;
import dades.Associacions.*;
import dades.Persistencia.GestorPersistencia;
import dades.Membres.Alumnes;
import dades.Membres.LlistaMembres;
import dades.Membres.Membres;

/**
 * Classe principal de l'aplicació que implementa un menú per gestionar associacions,
 * membres, accions, xerrades i demostracions.
 * Permet interactuar amb l'usuari a través d'una interfície de consola.
 * 
 * @author Paolo, Nermin, Sara, Alexandru
 * @version 1.0
 */
public class App {
    static Scanner teclat = new Scanner(System.in);
    static LlistaAssociacions llistaAssociacions = new LlistaAssociacions(50);
    static LlistaMembres llistaMembres = new LlistaMembres("General", 100);

    /**
     * Punt d'entrada principal de l'aplicació.
     * Mostra el menú i executa les opcions seleccionades per l'usuari.
     * 
     * @param args Arguments de línia de comandes (no utilitzats).
     * @throws IOException Si es produeix un error inesperat durant l'execució.
     * @author Paolo
     */
    public static void main(String[] args) throws Exception {
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
            GestorPersistencia.carregarDades(fitxerAssociacions, fitxerMembres, llistaAssociacions, llistaMembres);

            // Executar el menú principal
            menuPrincipal(fitxerAssociacions);

        } catch (IOException e) {
            System.err.println("Error carregant o creant els fitxers inicials: " + e.getMessage());
        } finally {
            teclat.close();
        }
    }

    /**
     * Mostra el menú i executa les opcions seleccionades per l'usuari.
     * 
     * @throws Exception Si es produeix un error inesperat durant l'execució.
     * @throws NumberFormatException Si l'usuari introdueix un valor no vàlid per a una opció numèrica.
     * @author Paolo
     */
    public static void menuPrincipal(String associacionsInicials) {
        int opcio = 0;

        associacionsInicials.llegirFitxerBinari(associacionsInicials);

        do {
            try {
                mostraMenu();
                System.out.print("Selecciona una opció: ");
                opcio = Integer.parseInt(teclat.nextLine());

                // Gestiona l'opció seleccionada amb un switch
                switch (opcio) {
                    case 1:
                        opcio1(); // Mostrar les dades de la llista d'associacions
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
                        opcio7(associacionsInicials); // Afegir una nova associació
                        break;
                    case 8:
                        opcio8(); // Alta d'un membre a una associació
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
    public static void opcio1(LlistaAssociacions associacionsInicials, String fitxerAssociacions) {
        System.out.println("1. Mostrar les dades de la llista d'associacions");
        associacionsInicials.llegirFitxerBinari(fitxerAssociacions);
        llistaAssociacions.toString();
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

    public static void opcio7(LlistaAssociacions associacionsInicials) {
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
            Alumnes president = new Alumnes(nomPresident, correuPresident, new Data(diaPresident, mesPresident, anyPresident));
            Alumnes secretari = new Alumnes(nomSecretari, correuSecretari, new Data(diaSecretari, mesSecretari, anySecretari));
            Alumnes tresorer = new Alumnes(nomPresident, correuTresorer, new Data(diaTresorer, mesTresorer, anyTresorer));
            Associacio a = new Associacio(nom, correu, carrera, president, secretari, tresorer);
            associacionsInicials.afegirAssociacio(a);
        } else{
            Associacio a = new Associacio(nom, correu, carrera);
            associacionsInicials.afegirAssociacio(a);
        }
    }

    public static void opcio8(LlistaAssociacions associacionsInicials) {
        System.out.println("8. Alta d'un membre a una associació");
        try {
            associacionsInicials.
        } catch (Exception e) {
            // TODO: handle exception
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
                GestorPersistencia.guardarDades("associacions.dat", "membres.txt", llistaAssociacions, llistaMembres);
                System.out.println("Dades guardades correctament.");
            } catch (IOException e) {
                System.err.println("Error guardant les dades: " + e.getMessage());
            }
        }
        System.out.println("Sortint de l'aplicació.");
    }
}
