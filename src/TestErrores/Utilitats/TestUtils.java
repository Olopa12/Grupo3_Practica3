package TestErrores.Utilitats;

/**
 * Classe TestUtils que proporciona mètodes auxiliars per a realitzar asercions
 * en tests.
 * 
 * @author Paolo
 * @version 1.0
 */
public class TestUtils {
    /**
     * Comprova si dos objectes són iguals i mostra un missatge segons el resultat.
     * 
     * @param expected - Valor esperat.
     * @param actual   - Valor actual.
     * @param testName - Nom del test.
     * @author Paolo
     */
    public static void assertEqual(Object expected, Object actual, String testName) {
        if (expected == null && actual == null) {
            System.out.println("Pasó " + testName);
        } else if (expected != null && expected.equals(actual)) {
            System.out.println("Pasó " + testName);
        } else {
            System.out.println("Fallo en " + testName + ": esperado " + expected + ", pero fue " + actual);
        }
    }

    /**
     * Comprova si una condició és certa i mostra un missatge segons el resultat.
     * 
     * @param condition - Condició que s'ha de verificar.
     * @param testName  - Nom del test.
     * @author Paolo
     */
    public static void assertTrue(boolean condition, String testName) {
        if (!condition) {
            System.out.println("Fallo en " + testName);
        } else {
            System.out.println("Pasó " + testName);
        }
    }

    /**
     * Comprova si una condició és falsa i mostra un missatge segons el resultat.
     * 
     * @param condition - Condició que s'ha de verificar.
     * @param testName  - Nom del test.
     * @author Paolo
     */
    public static void assertFalse(boolean condition, String testName) {
        assertTrue(!condition, testName);
    }
}
