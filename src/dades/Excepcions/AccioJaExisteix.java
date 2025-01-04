package dades.Excepcions;

/**
 * Excepción que se lanza cuando una acción ya existe en la lista.
 */
public class AccioJaExisteix extends Exception {
    /**
     * Constructor de la excepción.
     * @param missatge El mensaje que describe la excepción.
     */
    public AccioJaExisteix(String missatge) {
        super(missatge);
        System.out.println("\nL'acció amb codi '" + missatge + "' ja existeix a la llista."); // Pasa el mensaje al constructor de Exception
    }
}
