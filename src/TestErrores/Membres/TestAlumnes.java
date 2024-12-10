package TestErrores.Membres;

import TestErrores.Utilitats.TestUtils;
import Utilitats.Data;
import dades.Membres.Alumnes;

/**
 * Classe de test per verificar el comportament de la classe Alumnes.
 * Es comproven els getters, setters, el mètode de còpia, i el mètode toString.
 * També s'inclouen asercions per verificar els resultats esperats.
 * 
 * @author Paolo
 * @version 1.0
 */
public class TestAlumnes {
    public static void main(String[] args) {
        testGetters();
        testSetters();
        testCopia();
        testToString();
    }

    /**
     * Test per verificar els getters de la classe Alumnes.
     * Es comprova que cada getter retorni el valor correcte.
     */
    static void testGetters() {
        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);

        TestUtils.assertEqual("Joan123", alumne.getAlias(), "testGetters - alias");
        TestUtils.assertEqual("joan@etse.com", alumne.getCorreuElectronic(), "testGetters - correuElectronic");
        TestUtils.assertEqual(new Data(10, 1, 2024), alumne.getDataAlta(), "testGetters - dataAlta");
        TestUtils.assertEqual(null, alumne.getDataBaixa(), "testGetters - dataBaixa inicial");
        TestUtils.assertEqual("GEI", alumne.getEnsenyament(), "testGetters - ensenyament");
        TestUtils.assertEqual(2, alumne.getAntiguitat(), "testGetters - antiguitat");
        TestUtils.assertFalse(alumne.isGraduat(), "testGetters - graduat");
    }

    /**
     * Test per verificar els setters de la classe Alumnes.
     * Es comprova que els valors es modifiquen correctament.
     */
    static void testSetters() {
        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);

        alumne.setEnsenyament("GESST");
        alumne.setAntiguitat(3);
        alumne.setGraduat(true);

        TestUtils.assertEqual("GESST", alumne.getEnsenyament(), "testSetters - ensenyament");
        TestUtils.assertEqual(3, alumne.getAntiguitat(), "testSetters - antiguitat");
        TestUtils.assertTrue(alumne.isGraduat(), "testSetters - graduat");
    }

    /**
     * Test per verificar el mètode copia.
     * Es comprova que la còpia sigui una nova instància amb els mateixos valors.
     */
    static void testCopia() {
        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);
        Alumnes copia = alumne.copia();

        TestUtils.assertTrue(alumne != copia, "testCopia - referencia distinta");
        TestUtils.assertEqual(alumne.toString(), copia.toString(), "testCopia - contenido igual");
    }

    /**
     * Test per verificar el mètode toString.
     * Es comprova que la representació textual sigui correcta.
     */
    static void testToString() {
        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);

        String expected = "Membre => alias=Joan123, correuElectronic=joan@etse.com, dataAlta=10-01-2024, dataBaixa=Actiu, rol=Membre Alumnes => ensenyament=GEI, antiguitat=2, graduat=false";
        TestUtils.assertEqual(expected, alumne.toString(), "testToString - formato correcto");
    }
}
