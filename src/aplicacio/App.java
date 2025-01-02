package aplicacio;

import java.util.Scanner;

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
    /**
     * Punt d'entrada principal de l'aplicació.
     * Mostra el menú i executa les opcions seleccionades per l'usuari.
     * 
     * @param args Arguments de línia de comandes (no utilitzats).
     * @throws Exception Si es produeix un error inesperat durant l'execució.
     * @author Paolo
     */
    public static void main(String[] args) throws Exception {
        int opcio = 0;

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
                        opcio7(); // Afegir una nova associació
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
                        System.out.println("Sortint de l'aplicació. Fins aviat!");
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
    public static void opcio1() {
        System.out.println("1. Mostrar les dades de la llista d'associacions");
        // TODO: Implementar funcionalitat
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

    public static void opcio7() {
        System.out.println("7. Afegir una nova associació");
        // TODO: Implementar funcionalitat
    }

    public static void opcio8() {
        System.out.println("8. Alta d'un membre a una associació");
        // TODO: Implementar funcionalitat
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
    
}
