package TestErrores.Membres;

import dades.Membres.*;
import java.io.File;
import java.io.IOException;

import TestErrores.Utilitats.TestUtils;
import Utilitats.Data;

/**
 * Classe de test per verificar el comportament de LlistaMembres.
 * Es comproven funcionalitats com afegir membres, guardar i carregar en fitxer,
 * ordenar i evitar duplicats.
 * 
 * @author Paolo
 * @version 1.0
 */
public class TestLlistaMembres {
    public static void main(String[] args) {
        testAfegirMembre();
        testGuardarICarregar();
        testOrdenacio();
        testDuplicats();
    }

    /**
     * Test per verificar l'afegit de membres a la llista.
     * Es comprova que es poden afegir membres i que el nombre total s'actualitza correctament.
     */
    static void testAfegirMembre() {
        System.out.println("=== TEST: Afegir Membres ===");
        LlistaMembres llista = new LlistaMembres("Associació X", 10);

        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);
        Professsors professor = new Professsors("Maria456", "maria@etse.com", new Data(15, 9, 2023), "DEIM", "D123");

        llista.afegirMembre(alumne);
        llista.afegirMembre(professor);

        TestUtils.assertEqual(2, llista.numMembres(), "testAfegirMembre - Nombre de membres");
        System.out.println(llista);
    }

    /**
     * Test per verificar la funcionalitat de guardar i carregar membres d'un fitxer.
     * Es comprova que la informació es guarda correctament en el fitxer i es pot carregar sense errors.
     */
    static void testGuardarICarregar() {
        System.out.println("=== TEST: Guardar i Carregar Membres ===");
        LlistaMembres llista = new LlistaMembres("Associació Y", 10);
        Alumnes alumne = new Alumnes("Anna789", "anna@etse.com", new Data(20, 11, 2022), "GESST", 3, true);
        Professsors professor = new Professsors("Pere111", "pere@etse.com", new Data(1, 1, 2024), "DEEEA", "D789");

        llista.afegirMembre(alumne);
        llista.afegirMembre(professor);

        String fitxer = "test_llista_membres.txt";

        try {
            llista.guardarEnFitxer(fitxer);

            LlistaMembres llistaCarregada = new LlistaMembres("Associació Z", 10);
            llistaCarregada.carregarDeFitxer(fitxer);

            TestUtils.assertEqual(2, llistaCarregada.numMembres(), "testGuardarICarregar - Nombre de membres carregats");
            System.out.println(llistaCarregada);

            // Eliminar el fitxer després del test
            new File(fitxer).delete();
        } catch (IOException e) {
            System.err.println("Error en guardar o carregar fitxer: " + e.getMessage());
        }
    }

     /**
     * Test per verificar que la llista de membres es manté ordenada per alias.
     * Es comprova que els membres es col·loquin en l'ordre correcte.
     */
    static void testOrdenacio() {
        System.out.println("=== TEST: Ordenació de Membres ===");
        LlistaMembres llista = new LlistaMembres("Associació Z", 10);

        Professsors professor = new Professsors("Pere111", "pere@etse.com", new Data(1, 1, 2024), "DEEEA", "D789");
        Alumnes alumne1 = new Alumnes("Anna789", "anna@etse.com", new Data(20, 11, 2022), "GESST", 3, true);
        Alumnes alumne2 = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);

        llista.afegirMembre(professor);
        llista.afegirMembre(alumne1);
        llista.afegirMembre(alumne2);

        System.out.println("Llista ordenada:");
        System.out.println(llista);
    }

    /**
     * Test per verificar que no es poden afegir membres duplicats a la llista.
     * Es comprova que el nombre de membres no augmenta quan s'intenta afegir un duplicat.
     */
    static void testDuplicats() {
        System.out.println("=== TEST: Membres Duplicats ===");
        LlistaMembres llista = new LlistaMembres("Associació Duplicats", 10);

        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);

        llista.afegirMembre(alumne);
        llista.afegirMembre(alumne); // Intentar afegir el mateix membre

        TestUtils.assertEqual(1, llista.numMembres(), "testDuplicats - Nombre de membres després de duplicat");
    }
}
