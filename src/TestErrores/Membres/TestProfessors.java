package TestErrores.Membres;

import TestErrores.Utilitats.TestUtils;
import Utilitats.Data;
import dades.Membres.Professsors;

/**
 * Classe de test per verificar el comportament de la classe Professsors.
 * Es comproven els getters, setters, el mètode de còpia i el mètode toString.
 * 
 * @author Paolo
 * @version 1.0
 */
public class TestProfessors {
    public static void main(String[] args) {
        testGetters();
        testSetters();
        testCopia();
        testToString();
    }

    /**
     * Test per verificar els getters de la classe Professsors.
     * Es comprova que cada getter retorni el valor correcte.
     */
    static void testGetters() {
        Professsors professor = new Professsors("Maria456", "maria@etse.com", new Data(10, 1, 2024), "DEIM", "D123");

        TestUtils.assertEqual("Maria456", professor.getAlias(), "testGetters - alias");
        TestUtils.assertEqual("maria@etse.com", professor.getCorreuElectronic(), "testGetters - correuElectronic");
        TestUtils.assertEqual(new Data(10, 1, 2024), professor.getDataAlta(), "testGetters - dataAlta");
        TestUtils.assertEqual(null, professor.getDataBaixa(), "testGetters - dataBaixa inicial");
        TestUtils.assertEqual("DEIM", professor.getDepartament(), "testGetters - departament");
        TestUtils.assertEqual("D123", professor.getNumDespatx(), "testGetters - numDespatx");
    }

    /**
     * Test per verificar els setters de la classe Professsors.
     * Es comprova que els valors es modifiquen correctament.
     */
    static void testSetters() {
        Professsors professor = new Professsors("Maria456", "maria@etse.com", new Data(10, 1, 2024), "DEIM", "D123");

        professor.setDepartament("DEEEA");
        professor.setNumDespatx("D456");

        TestUtils.assertEqual("DEEEA", professor.getDepartament(), "testSetters - departament");
        TestUtils.assertEqual("D456", professor.getNumDespatx(), "testSetters - numDespatx");
    }

    /**
     * Test per verificar el mètode copia de la classe Professsors.
     * Es comprova que la còpia sigui una nova instància amb els mateixos valors.
     */
    static void testCopia() {
        Professsors professor = new Professsors("Maria456", "maria@etse.com", new Data(10, 1, 2024), "DEIM", "D123");
        Professsors copia = professor.copia();

        TestUtils.assertTrue(professor != copia, "testCopia - referencia distinta");
        TestUtils.assertEqual(professor.toString(), copia.toString(), "testCopia - contenido igual");
    }

    /**
     * Test per verificar el mètode toString de la classe Professsors.
     * Es comprova que la representació textual sigui correcta.
     */
    static void testToString() {
        Professsors professor = new Professsors("Maria456", "maria@etse.com", new Data(10, 1, 2024), "DEIM", "D123");

        String expected = "Membre => alias=Maria456, correuElectronic=maria@etse.com, dataAlta=10-01-2024, dataBaixa=Actiu Professsors departament=DEIM, numDespatx=D123";
        TestUtils.assertEqual(expected, professor.toString(), "testToString - format correcte");
    }
}
