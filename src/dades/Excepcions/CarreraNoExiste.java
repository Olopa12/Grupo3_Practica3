package dades.Excepcions;

public class CarreraNoExiste extends Exception{
    public CarreraNoExiste(String missatge){
        System.out.println("\nLa carrera " + missatge + " no es una opcio per a aquesta associacio");
    }
}
