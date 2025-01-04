package dades.Excepcions;

public class AccioJaExisteix extends Exception {
  
    public AccioJaExisteix(String missatge) {
        super(missatge);
        System.out.println("\nL'acció amb codi '" + missatge + "' ja existeix a la llista."); 
    }
}
