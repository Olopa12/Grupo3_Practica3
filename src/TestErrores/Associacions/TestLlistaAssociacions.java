package TestErrores.Associacions;

import java.io.IOException;

import Utilitats.Data;
import dades.Associacions.Associacio;
import dades.Associacions.LlistaAssociacions;
import dades.Membres.Alumnes;

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
        comprovaBuscarAssociacio();
        comprovaExisteixAssociacio();
        comprovarFitxerBinari();
    }
    private static void validarAfegirAssociacio(){
        System.out.println("\n\nValidem assignar una associacio a la llista d'associacions.\n");

        LlistaAssociacions llistaAs = new LlistaAssociacions(10);

        System.out.println("Abans d'afegir res:");

        System.out.println(llistaAs.toString());

        System.out.println("null\n");

        System.out.println("Afegim una associacio:\n");

        Alumnes membre1 = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "Informatica", 3, true);
        Alumnes membre2 = new Alumnes("Marc321", "correu@exemple.com", new Data(1, 5, 2022), "Informatica", 3, true);
        Associacio asociacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI", membre1.copia(), membre1.copia(), membre1.copia());
        llistaAs.afegirAssociacio(asociacioPaelles);
        System.out.println(llistaAs.toString());

        System.out.println("\nAfegim segona associacio:\n");
        Associacio asociacioPescaders = new Associacio("PescadersURV", "pescadersurv@urv.cat", "BioGEI", membre2.copia(), membre2.copia(), membre2.copia());
        llistaAs.afegirAssociacio(asociacioPescaders);
        System.out.println(llistaAs.toString());
    }

    private static void comprovaBuscarAssociacio(){
        System.out.println("\n\nValidem buscar si esta una associacio a la llista d'associacions.\n");

        LlistaAssociacions llistaAs = new LlistaAssociacions(10);
        Alumnes membre1 = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "Informatica", 3, true);
        Alumnes membre2 = new Alumnes("Marc321", "correu@exemple.com", new Data(1, 5, 2022), "Informatica", 3, true);
        Associacio asociacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI", membre1.copia(), membre1.copia(), membre1.copia());
        llistaAs.afegirAssociacio(asociacioPaelles);
        Associacio asociacioPescaders = new Associacio("PescadersURV", "pescadersurv@urv.cat", "BioGEI", membre2.copia(), membre2.copia(), membre2.copia());
        llistaAs.afegirAssociacio(asociacioPescaders);
        System.out.println("\nCas 1:");
        Associacio a = llistaAs.buscarAssociacio("PescadersURV");
        if(a == null){
            System.out.println("\nAssociacio no esta");
        } else{
            System.out.println(a);
        }

        System.out.println("\nCas 2:");
        a = llistaAs.buscarAssociacio("noExististeix");
        if(a == null){
            System.out.println("\nAssociacio no esta");
        } else{
            System.out.println(a);
        }
    }

    private static void comprovaExisteixAssociacio(){
        System.out.println("\n\nValidem comprovar si existeix una associacio a la llista d'associacions.\n");

        LlistaAssociacions llistaAs = new LlistaAssociacions(10);
        Alumnes membre1 = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "Informatica", 3, true);
        Alumnes membre2 = new Alumnes("Marc321", "correu@exemple.com", new Data(1, 5, 2022), "Informatica", 3, true);
        Associacio asociacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI", membre1.copia(), membre1.copia(), membre1.copia());
        llistaAs.afegirAssociacio(asociacioPaelles);
        Associacio asociacioPescaders = new Associacio("PescadersURV", "pescadersurv@urv.cat", "BioGEI", membre2.copia(), membre2.copia(), membre2.copia());
        llistaAs.afegirAssociacio(asociacioPescaders);
        System.out.println("\nCas 1:");
        boolean existeix = llistaAs.existeixAssociacio("PescadersURV");
        if(existeix){
            System.out.println("\nExisteix l'associacio.");
        } else{
            System.out.println("\nNo existeix l'associacio.");
        }

        System.out.println("\nCas 2:");
        existeix = llistaAs.existeixAssociacio("noExisteix");
        if(existeix){
            System.out.println("\nExisteix l'associacio.");
        } else{
            System.out.println("\nNo existeix l'associacio.");
        }
    }

    private static void comprovarFitxerBinari(){
        System.out.println("\n\nValidem crear i llegir un fitxer binari amb l'informacio de la LlistaAssociacions.\n");

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
