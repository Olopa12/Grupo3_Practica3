package TestErrores.accions;

import dades.Accions.Demostracio;


public class TestDemostracio {
    public static void main(String[] args) {
        comprovaConstructor();
        comprovaValiditat();
    }

    public static void comprovaConstructor() {
        Associacio associacio1 = new Associacio("Associacion1", "assoc@gmail.com", "GEB");
        Membres responsable = new Membres("A", "A@gmail.com");

        Demostracio demo = new Demostracio("EX A", associacio1, responsable, "2025-01-1", true, 300.0);
        System.out.println(demo);
    }

    public static void comprovaValiditat() {
        Associacio associacio2 = new Associacio("EX B", "B@infogei.com", "GEI");
        Membres responsable2 = new Membres("EX C", "C@gmail.com");

        Demostracio demo2 = new Demostracio("EX D", associacio2, responsable2, "2025-01-1", false, 400.0);
        System.out.println("OK? " + demo2.isValida());
    }
}


