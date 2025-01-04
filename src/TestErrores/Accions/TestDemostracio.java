package TestErrores.Accions;

import dades.Accions.Demostracio;
import dades.Associacions.Associacio;
import dades.Membres.Membres;
import Utilitats.Data;

/**
 * Aquí es comprova que el constructor de la classe Demostracio funciona
 * correctament.
 * 
 * @author Nermin Tribak, Sara Tribak
 * @version 1.0
 */

public class TestDemostracio {
    public static void main(String[] args) {
        try {
            comprovaConstructor();
            comprovaValiditat();
        } catch (Exception e) {
            System.err.println("Error en el test: " + e.getMessage());
        }
    }

    /**
     * Mètode per comprovar el constructor de Demostració.
     * Aquest mètode crea una instancia de Demostracio i imprimeix el resultat.
     * La instancia de Demostracio es crea amb un nom, una associació, un
     * responsable, una data, un estat i un preu.
     */
    public static void comprovaConstructor() {
        try {

            Associacio associacio1 = new Associacio("Associacion1", "assoc@gmail.com", "GEB");
            Membres responsable = new Membres("A", "A@gmail.com", new Data(2023, 12, 30)) {
                @Override
                public Membres copia() {
                    return this;
                }
            };

            Demostracio demo = new Demostracio("EX A", associacio1, responsable, "2025-01-01", true, 300.0);
            System.out.println(demo);
        } catch (Exception e) {
            System.err.println("Error en comprovaConstructor: " + e.getMessage());
        }
    }

    /**
     * Mètode per comprovar la validesa de Demostracio.
     */
    public static void comprovaValiditat() {
        try {
            Associacio associacio2 = new Associacio("EX B", "B@infogei.com", "GEI");
            Membres responsable2 = new Membres("EX C", "C@gmail.com", new Data(2023, 12, 30)) {
                @Override
                public Membres copia() {
                    return this;
                }
            };

            /**
             * Test per comprovar que si el cost dels materials es menor a 0, surt fals.
             */
            System.out.println("Test comprova validitat");
            Demostracio demo2 = new Demostracio("EX D", associacio2, responsable2, "2025-01-01", true, -400.0);
            System.out.println("És valida: " + demo2.isEsValida());

            /**
             * Test per comprovar que si el cost dels materials és major o igual a 0, surt cert.
             */
            Demostracio demo3 = new Demostracio("EX E", associacio2, responsable2, "2025-01-01", true, 400.0);
            System.out.println("És valida: " + demo3.isEsValida());

        } catch (Exception e) {
            System.err.println("Error en comprovaValiditat: " + e.getMessage());
        }
    }
}
