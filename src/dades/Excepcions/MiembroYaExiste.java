package dades.Excepcions;

public class MiembroYaExiste extends Exception {
    public MiembroYaExiste(String missatge){
        System.out.println("\nEl miembro con alias '" + missatge + "' ya existe en la lista."); // Pasa el mensaje al constructor de Exception
    }
}
