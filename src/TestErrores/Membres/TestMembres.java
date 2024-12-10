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
    public static void main(String[] args) {
        testGetters();
        testSetters();
        testToString();
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
        TestUtils.assertEqual("Membre", membre.getRol(), "testGetters - rol");
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

        TestUtils.assertEqual("Anna456", membre.getAlias(), "testSetters - alias");
        TestUtils.assertEqual("anna@etse.com", membre.getCorreuElectronic(), "testSetters - correuElectronic");
        TestUtils.assertEqual(new Data(1, 9, 2023), membre.getDataAlta(), "testSetters - dataAlta");
        TestUtils.assertEqual(new Data(15, 5, 2024), membre.getDataBaixa(), "testSetters - dataBaixa");
        TestUtils.assertEqual("Membre", membre.getRol(), "testSetters - rol");
    }

    /**
     * Test per verificar el mètode toString de la classe Membres.
     * Es comprova que la representació textual sigui correcta.
     */
    static void testToString() {
        Membres membre = new TestMembre("Joan123", "joan@etse.com", new Data(10, 1, 2024));
        membre.setDataBaixa(new Data(1, 6, 2024));

        String expected = "Membre => alias=Joan123, correuElectronic=joan@etse.com, dataAlta=10-01-2024, dataBaixa=1-06-2024, rol=Membre";
        TestUtils.assertEqual(expected, membre.toString(), "testToString - format correcte");
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
            return new TestMembre(this.getAlias(), this.getCorreuElectronic(), this.getDataAlta());
        }
    }
}
