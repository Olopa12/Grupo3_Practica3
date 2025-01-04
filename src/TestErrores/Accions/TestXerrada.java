package TestErrores.Accions;

import dades.Accions.Xerrada;
import dades.Associacions.Associacio;
import dades.Membres.Membres;
import Utilitats.Data;

/**
 * En aquesta classe testejem la classe Xerrada. 
 * Testejem si es pot crear una xerrada amb els seus atributs i si es pot afegir membres impartidors, valoracions i assistents.
 * 
 * @author Nermin Tribak, Sara Tribak
 * @version 1.0
 */

public class TestXerrada {
    public static void main(String[] args) {
        try {
            
            /**
             * Testejem si es pot crear una xerrada amb els seus atributs.
             */
            System.out.println("Testejem si es pot crear una xerrada amb els seus atributs.");
            Associacio associacio1 = new Associacio("EX B", "B@infogei.com", "GEI");
            Membres membre1 = new Membres("EX A", "1@gmail.com", new Data(2023, 12, 30)) {
                @Override
                public Membres copia() {
                    return this;
                }
            };
            Membres membre2 = new Membres("EX C", "2@gmail.com", new Data(2023, 12, 30)) {
                @Override
                public Membres copia() {
                    return this;
                }
            };
   
            Data dataXerrada = new Data(2024, 11, 15);
            Xerrada xerrada1 = new Xerrada("Xerrada", associacio1, membre2, dataXerrada);
            
            /**
             * Testejem si es pot afegir membres impartidors a la xerrada
             * i si es pot afegir assistents i valoracions
             */
            xerrada1.afegirMembreImpartidor(membre1);
            xerrada1.afegirMembreImpartidor(membre2);
            xerrada1.afegirAssistents(50);
            xerrada1.afegirValoracio(8);
            xerrada1.afegirValoracio(9);
            xerrada1.afegirValoracio(10);
            
            /**
             * Testejem si es pot imprimir la xerrada amb els seus atributs
             */
            System.out.println(xerrada1);
        } catch (Exception e) {
            System.err.println("Error en el test de Xerrada: " + e.getMessage());
        }
    }

   
}
