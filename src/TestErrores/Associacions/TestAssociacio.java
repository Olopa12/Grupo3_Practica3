package TestErrores.Associacions;

import dades.Associacions.Associacio;

// Hecho por Alex Radu

// TODO Actualizar los tests con el nuevo codigo añadido en Asociacions.java

public class TestAssociacio{
    public static void main(String[] args){
        comprovaConstructor();
    }

    public static void comprovaConstructor(){
        Associacio asociacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI");
        System.out.println(asociacioPaelles.toString());

        Associacio asociacioEstudiantsPromig = new Associacio("EstudiantsPromigURV", "estudiantspromigurv@urv.cat", "nom arbitrari");
        System.out.println(asociacioEstudiantsPromig.toString());
    }
}
