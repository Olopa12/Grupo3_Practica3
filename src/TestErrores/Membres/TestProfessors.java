package TestErrores.Membres;

import TestErrores.Utilitats.TestUtils;
import Utilitats.Data;
import dades.Membres.Professors;

/**
 * Classe de test per verificar el comportament de la classe Professors.
 * Es comproven els getters, setters, el mètode de còpia i el mètode toString.
 * 
 * @author Paolo
 * @version 1.0
 */
public class TestProfessors {
    /**
     * Punt d'entrada del programa de proves.
     * Crida a les funcions de test per validar el comportament de la classe Professors.
     * 
     * @param args Arguments de línia de comandes (no utilitzats).
     * @author Paolo
     */
    public static void main(String[] args) {
        testGetters();
        testSetters();
        testCopia();
        testToString();
    }

    /**
     * Test per verificar els getters de la classe Professors.
     * Es comprova que cada getter retorni el valor correcte.
     * @author Paolo
     */
    static void testGetters() {
        Professors professor = new Professors("Maria456", "maria@etse.com", new Data(10, 1, 2024), "DEIM", "D123");

        TestUtils.assertEqual("Maria456", professor.getAlias(), "testGetters - alias");
        TestUtils.assertEqual("maria@etse.com", professor.getCorreuElectronic(), "testGetters - correuElectronic");
        TestUtils.assertEqual(new Data(10, 1, 2024), professor.getDataAlta(), "testGetters - dataAlta");
        TestUtils.assertEqual(null, professor.getDataBaixa(), "testGetters - dataBaixa inicial");
        TestUtils.assertEqual("DEIM", professor.getDepartament(), "testGetters - departament");
        TestUtils.assertEqual("D123", professor.getNumDespatx(), "testGetters - numDespatx");
    }

    /**
     * Test per verificar els setters de la classe Professors.
     * Es comprova que els valors es modifiquen correctament.
     * @author Paolo
     */
    static void testSetters() {
        Professors professor = new Professors("Maria456", "maria@etse.com", new Data(10, 1, 2024), "DEIM", "D123");

        professor.setDepartament("DEEEA");
        professor.setNumDespatx("D456");

        TestUtils.assertEqual("DEEEA", professor.getDepartament(), "testSetters - departament");
        TestUtils.assertEqual("D456", professor.getNumDespatx(), "testSetters - numDespatx");
    }

    /**
     * Test per verificar el mètode copia de la classe Professors.
     * Es comprova que la còpia sigui una nova instància amb els mateixos valors.
     * @author Paolo
     */
    static void testCopia() {
        Professors professor = new Professors("Maria456", "maria@etse.com", new Data(10, 1, 2024), "DEIM", "D123");
        professor.setDataBaixa(new Data(15, 6, 2025)); // Assignar data de baixa
        professor.setParticipacions(5); // Assignar participacions
    
        Professors copia = professor.copia();
        
        TestUtils.assertEqual(professor.getAlias(), copia.getAlias(), "testCopia - alias igual");
        TestUtils.assertEqual(professor.getCorreuElectronic(), copia.getCorreuElectronic(), "testCopia - correu igual");
        TestUtils.assertEqual(professor.getDataAlta(), copia.getDataAlta(), "testCopia - dataAlta igual");
        TestUtils.assertEqual(professor.getDataBaixa(), copia.getDataBaixa(), "testCopia - dataBaixa igual");
        TestUtils.assertEqual(professor.getParticipacions(), copia.getParticipacions(), "testCopia - participacions iguals");
        TestUtils.assertEqual(professor.getDepartament(), copia.getDepartament(), "testCopia - departament igual");
        TestUtils.assertEqual(professor.getNumDespatx(), copia.getNumDespatx(), "testCopia - numDespatx igual");
    
    }

    /**
     * Test per verificar el mètode toString de la classe Professors.
     * Es comprova que la representació textual sigui correcta.
     * @author Paolo
     */
    static void testToString() {
        Professors professor = new Professors("Maria456", "maria@etse.com", new Data(10, 1, 2024), "DEIM", "D123");
        professor.setParticipacions(6);

        String expected = "Membre => alias=Maria456, correuElectronic=maria@etse.com, dataAlta=10-01-2024, dataBaixa=Actiu, participacions=6 Professors departament=DEIM, numDespatx=D123";
        TestUtils.assertEqual(expected, professor.toString(), "testToString - format correcte");
    }
}
