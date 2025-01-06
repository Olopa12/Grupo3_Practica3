package dades.Excepcions;

public class InstanciaNoTrobada extends Exception{
    public InstanciaNoTrobada(String missatge){
        System.out.println("No s'ha trobat cap associacio amb el nom: " + missatge + "\nIntenta-ho una altra vegada amb el nom d'una associacio que existeix :)");
    }
}
