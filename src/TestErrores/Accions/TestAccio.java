package TestErrores.Accions;

import dades.Accions.Accio.*;

public class TestAccio {
    public static void main(String[] args) {
        Associacio associacio1 = new Associacio("EX B", "B@infogei.com", "GEI");
        Membres membre1 = new Membres("B", "1@gmail.com");

        // Probando con Demostracio (hereda de Accio)
        Demostracio demo = new Demostracio("Demostració1", associacio1, membre1, "2024-01-01", true, 300.0);
        System.out.println("Codi: " + demo.getCodi());
        System.out.println("Títol: " + demo.getTitol());
        System.out.println("Associació: " + demo.getAssociacio().getNomAsociacio());
        System.out.println("Responsable: " + demo.getResponsable().getAlias());

        // Probando con Xerrada (hereda de Accio)
        Membres[] impartidors = { membre1 };
        Xerrada xerrada = new Xerrada("Xerrada", associacio1, membre1, "2024-02-1", impartidors, 20);
        System.out.println("Codi: " + xerrada.getCodi());
        System.out.println("Títol: " + xerrada.getTitol());
        System.out.println("Associació: " + xerrada.getAssociacio().getNomAsociacio().toString());
        System.out.println("Responsable: " + xerrada.getResponsable().getAlias());
    }
}
