package dades.Persistencia;

import dades.Accions.LlistaAccions;
import dades.Associacions.LlistaAssociacions;
import dades.Membres.LlistaMembres;

public class DataManager {
    private static DataManager instance;
    public LlistaAssociacions associacionsInicials = new LlistaAssociacions(50);
    public LlistaMembres llistaMembres = new LlistaMembres("General", 100);
    public LlistaAccions llistaAccions = new LlistaAccions();

    private DataManager() {}

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public LlistaAssociacions getAssociacionsInicials() {
        return associacionsInicials;
    }
}
