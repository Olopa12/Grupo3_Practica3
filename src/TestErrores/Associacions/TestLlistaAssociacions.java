package TestErrores.Associacions;

import java.io.IOException;

import TestErrores.Associacions.TestAssociacio.TestMembre;
import Utilitats.Data;
import dades.Associacions.Associacio;
import dades.Associacions.LlistaAssociacions;
import dades.Membres.Membres;

/**
 * Classe TestLlistaAssociacio que representa els testes fer per comprovar que
 * els metodes de LlistaAssociacio.java funcioniguen correctament.
 * 
 * @author Alex Radu
 * @version 1.0
 */
public class TestLlistaAssociacions {
    public static void main(String[] args){
        validarAfegirAssociacio();
        comprovarFitxerBinari();
    }
    private static void validarAfegirAssociacio(){
        System.out.println("\n\nValidem assignar una associacio a la llista d'associacions.\n");

        LlistaAssociacions llistaAs = new LlistaAssociacions(10);

        System.out.println("Abans d'afegir res:");

        System.out.println(llistaAs.toString());

        System.out.println("null\n");

        System.out.println("Afegim una associacio:\n");

        Membres membre1 = new TestMembre("Joan123", "joan@etse.com", new Data(10, 1, 2024));
        Membres membre2 = new TestMembre("Marc321", "correu@exemple.com", new Data(1, 5, 2022));
        Associacio asociacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI", membre1.copia(), membre1.copia(), membre1.copia());
        llistaAs.afegirAssociacio(asociacioPaelles);
        System.out.println(llistaAs.toString());

        System.out.println("\nAfegim segona associacio:\n");
        Associacio asociacioPescaders = new Associacio("PescadersURV", "pescadersurv@urv.cat", "BioGEI", membre2.copia(), membre2.copia(), membre2.copia());
        llistaAs.afegirAssociacio(asociacioPescaders);
        System.out.println(llistaAs.toString());
    }

    private static void comprovarFitxerBinari(){
        LlistaAssociacions llistaAs = new LlistaAssociacions(10);
        
        Associacio paellersAssociacio = new Associacio("PaellersURV", "paellersAssociacio@urv.cat", "GEI");
        Associacio pescadorsAssociacio = new Associacio("PescadorsURV", "pescadorsAssociacio@urv.cat", "BioGEI");
        
        llistaAs.afegirAssociacio(paellersAssociacio);
        llistaAs.afegirAssociacio(pescadorsAssociacio);

        System.out.println("\n\nLlista abans de guardar:");
        System.out.println(llistaAs.toString());

        try {
            llistaAs.crearFitxerBinari("associacions.dat");
        } catch (IOException e) {
            System.err.println("Error al crear el fitxer binari: " + e.getMessage());
        }

        LlistaAssociacions novaLlista = new LlistaAssociacions(10);

        try {
            // Llegir les associacions des del fitxer binari
            novaLlista.llegirFitxerBinari("associacions.dat");
        } catch (IOException e) {
            System.err.println("Error al llegir el fitxer binari: " + e.getMessage());
        }

        System.out.println("\nLlista després de llegir des del fitxer:");
        System.out.println(novaLlista.toString());
    }
}
