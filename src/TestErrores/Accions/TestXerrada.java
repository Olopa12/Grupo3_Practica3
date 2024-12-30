package TestErrores.accions;

import dades.Accions.Xerrada.*;

public class TestXerrada {
    public static void main(String[] args) {
        Associacio associacio1 = new Associacio("EX B", "B@infogei.com", "GEI");
        Membres membre1 = new Membres("EX A", "1@gmail.com");
        Membres membre2 = new Membres("EX C", "2@gmail.com");

        // Test Xerrada
        Membres[] impartidors = { membre1, membre2 };
        Xerrada xerrada1 = new Xerrada("Xerrada", associacio1, membre2, "2024-11-15", impartidors, 50);
        xerrada1.afegirValoracio(8);
        xerrada1.afegirValoracio(9);
        xerrada1.afegirValoracio(10);
        System.out.println(xerrada1);
    }
}
