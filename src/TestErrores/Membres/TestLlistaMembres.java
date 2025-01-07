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
    /**
     * Punt d'entrada del programa de proves.
     * Crida a les funcions de test per validar el comportament de la classe LlistaMembres.
     * 
     * @param args Arguments de línia de comandes (no utilitzats).
     * @author Paolo
     */
    public static void main(String[] args) {
        testAfegirMembre();
        testGuardarICarregar();
        testOrdenacio();
        testDuplicats();
        testCopia();
    }

    /**
     * Test per verificar l'afegit de membres a la llista.
     * Es comprova que es poden afegir membres i que el nombre total s'actualitza correctament.
     * @author Paolo
     */
    static void testAfegirMembre() {
        System.out.println("=== TEST: Afegir Membres ===");
        LlistaMembres llista = new LlistaMembres("Associació X", 10);

        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);
        Professors professor = new Professors("Maria456", "maria@etse.com", new Data(15, 9, 2023), "DEIM", "D123");

        alumne.setParticipacions(5);
        professor.setParticipacions(3);

        llista.afegirMembre(alumne);
        llista.afegirMembre(professor);

        TestUtils.assertEqual(2, llista.numMembres(), "testAfegirMembre - Nombre de membres");
        System.out.println(llista);
    }

    /**
     * Test per verificar la funcionalitat de guardar i carregar membres d'un fitxer.
     * Es comprova que la informació es guarda correctament en el fitxer i es pot carregar sense errors.
     * @author Paolo
     */
    static void testGuardarICarregar() {
        System.out.println("=== TEST: Guardar i Carregar Membres ===");
        LlistaMembres llista = new LlistaMembres("Associació Y", 10);

        Alumnes alumne = new Alumnes("Anna789", "anna@etse.com", new Data(20, 11, 2022), "GESST", 3, true);
        Professors professor = new Professors("Pere111", "pere@etse.com", new Data(1, 1, 2024), "DEEEA", "D789");

        alumne.setParticipacions(10);
        alumne.setDataBaixa(new Data(15, 12, 2023));
        professor.setParticipacions(7);

        llista.afegirMembre(alumne);
        llista.afegirMembre(professor);

        String fitxer = "test_llista_membres.txt";

        try {
            // Guardar la llista al fitxer
            llista.guardarEnFitxer(fitxer);

            // Carregar la llista des del fitxer
            LlistaMembres llistaCarregada = new LlistaMembres("Associació Z", 10);
            llistaCarregada.carregarDeFitxer(fitxer);

            // Comprovar que les dades són les mateixes
            TestUtils.assertEqual(2, llistaCarregada.numMembres(), "testGuardarICarregar - Nombre de membres carregats");

            System.out.println(llistaCarregada);

            // Eliminar el fitxer després del test
            File fitxerTest = new File(fitxer);
            if (fitxerTest.exists()) {
                fitxerTest.delete();
            }
        } catch (IOException e) {
            System.err.println("Error en guardar o carregar fitxer: " + e.getMessage());
        }
    }

     /**
     * Test per verificar que la llista de membres es manté ordenada per alias.
     * Es comprova que els membres es col·loquin en l'ordre correcte.
     * @author Paolo
     */
    static void testOrdenacio() {
        System.out.println("=== TEST: Ordenació de Membres ===");
        LlistaMembres llista = new LlistaMembres("Associació Z", 10);

        Professors professor = new Professors("Pere111", "pere@etse.com", new Data(1, 1, 2024), "DEEEA", "D789");
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
     * @author Paolo
     */
    static void testDuplicats() {
        System.out.println("=== TEST: Membres Duplicats ===");
        LlistaMembres llista = new LlistaMembres("Associació Duplicats", 10);

        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);

        llista.afegirMembre(alumne);
        llista.afegirMembre(alumne); // Intentar afegir el mateix membre

        TestUtils.assertEqual(1, llista.numMembres(), "testDuplicats - Nombre de membres després de duplicat");
    }

    /**
     * Test per verificar el mètode copia de la classe LlistaMembres.
     * Es comprova que la còpia sigui una nova instància i que els membres 
     * copien les referències correctament.
     * @author Paolo
     */
    static void testCopia() {
        System.out.println("=== TEST: Còpia de Llista Membres ===");

        // Crear una llista de membres
        LlistaMembres llistaOriginal = new LlistaMembres("General", 10);

        // Afegir membres
        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);
        Professors professor = new Professors("Maria456", "maria@etse.com", new Data(15, 9, 2023), "DEIM", "D123");
        llistaOriginal.afegirMembre(alumne);
        llistaOriginal.afegirMembre(professor);

        // Obtenir la còpia
        Membres[] copia = llistaOriginal.copia();

        // Comprovar la mida
        TestUtils.assertEqual(2, copia.length, "testCopia - Mida de la còpia");

        // Comprovar que els membres són els mateixos
        TestUtils.assertEqual(alumne, copia[0], "testCopia - Primer membre");
        TestUtils.assertEqual(professor, copia[1], "testCopia - Segon membre");

        // Comprovar que no comparteixen referències amb l'array intern original
        Membres[] originalArray = llistaOriginal.copia();
        TestUtils.assertTrue(originalArray != copia, "testCopia - Arrays diferents");

        System.out.println("=== TEST: Còpia de Llista Membres completat amb èxit ===");
    }
}
