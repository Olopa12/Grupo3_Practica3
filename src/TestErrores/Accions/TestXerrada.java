package TestErrores.Accions;

import dades.Accions.Xerrada;
import dades.Associacions.Associacio;
import dades.Membres.Membres;
import Utilitats.Data;

/**
 * En aquesta classe testejem la classe Xerrada. 
 * Testejem si es pot crear una xerrada amb els seus atributs i si es pot afegir membres impartidors, valoracions i assistents.
 * 
 * @author Nermin Tribak y Sara Tribak
 * @version 1.0
 */

public class TestXerrada {
    public static void main(String[] args) {
        try {
            // Crear una instància d'Associacio
            Associacio associacio1 = new Associacio("EX B", "B@infogei.com", "GEI");
            
            // Crear instàncies anònimes de Membres
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

            // Crear una instància de Xerrada
            Xerrada xerrada1 = new Xerrada("Xerrada", associacio1, membre2, "2024-11-15");
            
            // Afegir membres impartidors
            xerrada1.afegirMembreImpartidor(membre1);
            xerrada1.afegirMembreImpartidor(membre2);
            
            // Afegir assistents i valoracions
            xerrada1.afegirAssistents(50);
            xerrada1.afegirValoracio(8);
            xerrada1.afegirValoracio(9);
            xerrada1.afegirValoracio(10);
            
            // Imprimir la informació de la xerrada
            System.out.println(xerrada1);
        } catch (Exception e) {
            System.err.println("Error en el test de Xerrada: " + e.getMessage());
        }
    }

    // TODO: Treballar més en les excepcions en la clase xerrada
}



