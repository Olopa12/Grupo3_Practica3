package TestErrores.Accions;

import dades.Accions.Demostracio;
import dades.Associacions.Associacio;
import dades.Membres.Membres;
import Utilitats.Data;

/**
 * Aquí es comprova que el constructor de la classe Demostracio funciona correctament.
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

    // Método para comprobar el constructor de Demostracio
    public static void comprovaConstructor() {
        try {
            // Creació d'una instancia de Associacio i una instancia anónima de Membres
            Associacio associacio1 = new Associacio("Associacion1", "assoc@gmail.com", "GEB");
            
          
            Membres responsable = new Membres("A", "A@gmail.com", new Data(2023, 12, 30)) {
                @Override
                public Membres copia() {
                    return this;
                }
            };

            // Crear una instancia de Demostracio
            Demostracio demo = new Demostracio("EX A", associacio1, responsable, "2025-01-01", true, 300.0);
            System.out.println(demo);
        } catch (Exception e) {
            System.err.println("Error en comprovaConstructor: " + e.getMessage());
        }
    }

    // Mètode per comprovar la validesa de Demostracio
    public static void comprovaValiditat() {
        try {
            // Creació d'una instancia de Associacio i una instancia anónima de Membres
            Associacio associacio2 = new Associacio("EX B", "B@infogei.com", "GEI");
            
           
            Membres responsable2 = new Membres("EX C", "C@gmail.com", new Data(2023, 12, 30)) {
                @Override
                public Membres copia() {
                    return this;
                }
            };

            // Creació d'una instancia de Demostracio
            Demostracio demo2 = new Demostracio("EX D", associacio2, responsable2, "2025-01-01", true, 400.0);
            System.out.println("OK? " + demo2.isEsValida());
        } catch (Exception e) {
            System.err.println("Error en comprovaValiditat: " + e.getMessage());
        }
    }
}
