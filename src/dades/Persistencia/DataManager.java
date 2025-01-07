package dades.Persistencia;

import dades.Accions.LlistaAccions;
import dades.Associacions.LlistaAssociacions;
import dades.Membres.LlistaMembres;

/**
 * Classe DataManager que implementa el patró Singleton per gestionar les dades de l'aplicació.
 * Conté les llistes principals d'associacions, membres i accions, i garanteix que només existeixi una instància compartida.
 * 
 * @author Paolo
 */
public class DataManager {
    private static DataManager instance;
    public LlistaAssociacions associacionsInicials = new LlistaAssociacions(50);
    public LlistaMembres llistaMembres = new LlistaMembres("General", 100);
    public LlistaAccions llistaAccions = new LlistaAccions();

    /**
     * Constructor privat per evitar la creació d'instàncies externes.
     * Garanteix que només es pot accedir a la instància a través del mètode getInstance().
     * 
     * @author Paolo
     */
    private DataManager() {}

    /**
     * Mètode estàtic que retorna la instància única de DataManager.
     * Si no existeix, la crea. Garanteix que només hi ha una instància (patró Singleton).
     *
     * @return La instància única de DataManager.
     * @author Paolo
     */
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    /**
     * Retorna la llista inicial d'associacions.
     *
     * @return LlistaAssociacions objecte que conté les associacions.
     * @author Paolo
     */
    public LlistaAssociacions getAssociacionsInicials() {
        return associacionsInicials;
    }
}
