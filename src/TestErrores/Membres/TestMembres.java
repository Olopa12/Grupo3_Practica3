package TestErrores.Membres;

import TestErrores.Utilitats.TestUtils;
import Utilitats.Data;
import dades.Membres.Membres;

/**
 * Classe de test per verificar el comportament de la classe abstracta Membres.
 * Es comproven els getters, setters i el mètode toString utilitzant una classe concreta per proves.
 * 
 * @author Paolo
 * @version 1.0
 */
public class TestMembres {
    /**
     * Punt d'entrada del programa de proves.
     * Crida a les funcions de test per validar el comportament de la classe Membres.
     * 
     * @param args Arguments de línia de comandes (no utilitzats).
     */
    public static void main(String[] args) {
        testGetters();
        testSetters();
        testToString();
        testCopia();
    }

    /**
     * Test per verificar els getters de la classe Membres.
     * Es comprova que cada getter retorni el valor correcte.
     */
    static void testGetters() {
        Membres membre = new TestMembre("Joan123", "joan@etse.com", new Data(10, 1, 2024));

        TestUtils.assertEqual("Joan123", membre.getAlias(), "testGetters - alias");
        TestUtils.assertEqual("joan@etse.com", membre.getCorreuElectronic(), "testGetters - correuElectronic");
        TestUtils.assertEqual(new Data(10, 1, 2024), membre.getDataAlta(), "testGetters - dataAlta");
        TestUtils.assertEqual(null, membre.getDataBaixa(), "testGetters - dataBaixa inicial");
        TestUtils.assertEqual(0, membre.getParticipacions(), "testGetters - participacions");
    }

    /**
     * Test per verificar els setters de la classe Membres.
     * Es comprova que els valors es modifiquen correctament.
     */
    static void testSetters() {
        Membres membre = new TestMembre("Joan123", "joan@etse.com", new Data(10, 1, 2024));

        membre.setAlias("Anna456");
        membre.setCorreuElectronic("anna@etse.com");
        membre.setDataAlta(new Data(1, 9, 2023));
        membre.setDataBaixa(new Data(15, 5, 2024));
        membre.setParticipacions(3);

        TestUtils.assertEqual("Anna456", membre.getAlias(), "testSetters - alias");
        TestUtils.assertEqual("anna@etse.com", membre.getCorreuElectronic(), "testSetters - correuElectronic");
        TestUtils.assertEqual(new Data(1, 9, 2023), membre.getDataAlta(), "testSetters - dataAlta");
        TestUtils.assertEqual(new Data(15, 5, 2024), membre.getDataBaixa(), "testSetters - dataBaixa");
        TestUtils.assertEqual(3, membre.getParticipacions(), "testSetters - participacions");
    }

    /**
     * Test per verificar el mètode toString de la classe Membres.
     * Es comprova que la representació textual sigui correcta.
     */
    static void testToString() {
        Membres membre = new TestMembre("Joan123", "joan@etse.com", new Data(10, 1, 2024));
        membre.setDataBaixa(new Data(1, 6, 2024));
        membre.setParticipacions(5);

        String expected = "Membre => alias=Joan123, correuElectronic=joan@etse.com, dataAlta=10-01-2024, dataBaixa=1-06-2024, participacions=5";
        TestUtils.assertEqual(expected, membre.toString(), "testToString - format correcte");
    }

    /**
     * Test per verificar el mètode copia de la classe Membres.
     * Es comprova que la còpia sigui una nova instància amb els mateixos valors.
     */
    static void testCopia() {
        Membres membreOriginal = new TestMembre("Joan123", "joan@etse.com", new Data(10, 1, 2024));
        membreOriginal.setDataBaixa(new Data(15, 6, 2024));
        membreOriginal.setParticipacions(7);

        Membres membreCopia = membreOriginal.copia();

        // Verificar que els valors són iguals
        TestUtils.assertEqual(membreOriginal.getAlias(), membreCopia.getAlias(), "testCopia - alias");
        TestUtils.assertEqual(membreOriginal.getCorreuElectronic(), membreCopia.getCorreuElectronic(), "testCopia - correuElectronic");
        TestUtils.assertEqual(membreOriginal.getDataAlta(), membreCopia.getDataAlta(), "testCopia - dataAlta");
        TestUtils.assertEqual(membreOriginal.getDataBaixa(), membreCopia.getDataBaixa(), "testCopia - dataBaixa");
        TestUtils.assertEqual(membreOriginal.getParticipacions(), membreCopia.getParticipacions(), "testCopia - participacions");

        // Verificar que són instàncies diferents
        if (membreOriginal == membreCopia) {
            System.err.println("testCopia - Error: La còpia apunta a la mateixa instància.");
        } else {
            System.out.println("testCopia - Passat: La còpia és una instància diferent.");
        }
    }

    /**
     * Classe concreta per provar la classe abstracta Membres.
     * Es proporciona una implementació mínima per al mètode abstracte `copia`.
     */
    static class TestMembre extends Membres {
        public TestMembre(String alias, String correuElectronic, Data dataAlta) {
            super(alias, correuElectronic, dataAlta);
        }

        @Override
        public Membres copia() {
            Membres copia = new TestMembre(this.getAlias(), this.getCorreuElectronic(), this.getDataAlta());
            copia.setDataBaixa(this.getDataBaixa());
            copia.setParticipacions(this.getParticipacions());
            return copia;
        }
    }
}
