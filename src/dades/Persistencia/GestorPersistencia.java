package dades.Persistencia;

import java.io.IOException;
import dades.Membres.LlistaMembres;
import dades.Associacions.LlistaAssociacions;

/**
 * Classe GestorPersistencia.
 * Aquesta classe gestiona la lectura i escriptura de fitxers per garantir la persistència de dades.
 * Proporciona mètodes estàtics per carregar i guardar associacions i membres, tant en fitxers binaris com de text.
 * 
 * @author Paolo
 * @version 1.0
 */
public class GestorPersistencia {
    /**
     * Llegeix i carrega les associacions des d'un fitxer binari.
     * 
     * @param fitxerAssociacions Nom del fitxer binari amb les associacions.
     * @param llistaAssociacions Objecte LlistaAssociacions on carregar les dades.
     * @throws IOException Si hi ha un error en la lectura del fitxer.
     */
    public static void carregarAssociacions(String fitxerAssociacions, LlistaAssociacions llistaAssociacions) throws IOException {
        try {
            llistaAssociacions.llegirFitxerBinari(fitxerAssociacions);
            System.out.println("Associacions carregades correctament.");
        } catch (IOException e) {
            System.err.println("Error carregant associacions: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Guarda les associacions en un fitxer binari.
     * 
     * @param fitxerAssociacions Nom del fitxer binari on guardar les associacions.
     * @param llistaAssociacions Objecte LlistaAssociacions que conté les dades.
     * @throws IOException Si hi ha un error en l'escriptura del fitxer.
     */
    public static void guardarAssociacions(String fitxerAssociacions, LlistaAssociacions llistaAssociacions) throws IOException {
        try {
            llistaAssociacions.crearFitxerBinari(fitxerAssociacions);
            System.out.println("Associacions guardades correctament.");
        } catch (IOException e) {
            System.err.println("Error guardant associacions: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Carrega els membres d'una associació des d'un fitxer de text.
     * @param fitxerMembres Nom del fitxer de text amb els membres.
     * @param llistaMembres Objecte LlistaMembres on carregar les dades.
     * @throws IOException Si hi ha un error en la lectura del fitxer.
     */
    public static void carregarMembres(String fitxerMembres, LlistaMembres llistaMembres) {
        try {
            llistaMembres.carregarDeFitxer(fitxerMembres);
            System.out.println("Membres carregats correctament des de: " + fitxerMembres);
        } catch (IOException e) {
            System.err.println("Error carregant membres: " + e.getMessage());
        }
    }

    /**
     * Guarda els membres d'una associació en un fitxer de text.
     * @param fitxerMembres Nom del fitxer de text on guardar els membres.
     * @param llistaMembres Objecte LlistaMembres que conté les dades.
     * @throws IOException Si hi ha un error en l'escriptura del fitxer.
     */
    public static void guardarMembres(String fitxerMembres, LlistaMembres llistaMembres) {
        try {
            llistaMembres.guardarEnFitxer(fitxerMembres);
            System.out.println("Membres guardats correctament en: " + fitxerMembres);
        } catch (IOException e) {
            System.err.println("Error guardant membres: " + e.getMessage());
        }
    }
}
