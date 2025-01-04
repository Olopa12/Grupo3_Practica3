package TestErrores.Accions;

import dades.Accions.Accio;
import dades.Accions.*;
import dades.Membres.*;
import dades.Associacions.Associacio;
import dades.Excepcions.AccioJaExisteix;
import Utilitats.Data;
import java.io.File;
import java.io.IOException;

/**
 * Classe de prova per a la classe LlistaAccions.
 * En aquesta classe es creen diferents accions i es proven les funcions de la
 * classe LlistaAccions.
 * 
 * @author Nermin Tribak, Sara Tribak
 * @version 1.0
 */

public class TestLlistaAccions {
    public static void main(String[] args) {
        testAfegirAccio();
        testEliminarAccio();
        testGuardarICarregar();
        testObtenirAccioPerCodi();
        testAfegirXerradaIDemostracio();
    }

    /**
     * Prova per afegir accions a la llista.
     * En la prova es crea una accio i s'afegeix a la llista.
     */
    static void testAfegirAccio() {
        System.out.println("=== TEST: Afegir Accions ===");
        LlistaAccions llista = new LlistaAccions();
        try {
            Accio accio1 = new TestAccio("Titol1", new Associacio("Nom1", "Email1", "GEI"),
                    new Membres("Alias1", "Email1", new Data()) {
                        @Override
                        public Membres copia() {
                            return this;
                        }
                    });
            Accio accio2 = new TestAccio("Titol2", new Associacio("Nom2", "Email2", "GEI"),
                    new Membres("Alias2", "Email2", new Data()) {
                        @Override
                        public Membres copia() {
                            return this;
                        }
                    });
            llista.afegirAccio(accio1);
            llista.afegirAccio(accio2);
        } catch (AccioJaExisteix e) {
            System.err.println("Excepció trobada: " + e.getMessage());
        }
        assert llista.getAccions().length == 2 : "testAfegirAccio - Nombre d'accions";
        System.out.println(llista);
    }

    /**
     * Prova per eliminar accions de la llista.
     * En la prova es crea una accio i s'elimina.
     */
    static void testEliminarAccio() {
        System.out.println("=== TEST: Eliminar Accions ===");
        LlistaAccions llista = new LlistaAccions();
        Accio accio1 = null;
        try {
            accio1 = new TestAccio("Titol1", new Associacio("Nom1", "Email1", "GEI"),
                    new Membres("Alias1", "Email1", new Data()) {
                        @Override
                        public Membres copia() {
                            return this;
                        }
                    });
            llista.afegirAccio(accio1);
        } catch (AccioJaExisteix e) {
            System.err.println("Excepció trobada: " + e.getMessage());
        }
        boolean eliminat = llista.eliminarAccio(accio1.getCodi());
        assert eliminat : "testEliminarAccio - Accio eliminada";
        assert llista.getAccions().length == 0 : "testEliminarAccio - Nombre d'accions després d'eliminar";
        System.out.println(llista);
    }
    

    /**
     * Prova per guardar i carregar accions des d'un arxiu, després s'esborra l'arxiu.
     */
    static void testGuardarICarregar() {
        System.out.println("=== TEST: Guardar i Carregar Accions ===");
        LlistaAccions llista = new LlistaAccions();

        Accio accio1 = new TestAccio("Titol1", new Associacio("Nom1", "Email1", "GEI"),
                new Membres("Alias1", "Email1", new Data()) {
                    @Override
                    public Membres copia() {
                        return this;
                    }
                });

        Accio xerrada = new Xerrada("Xerrada1", new Associacio("NomXerrada", "EmailXerrada", "GEI"),
                new Membres("AliasXerrada", "EmailXerrada", new Data()) {
                    @Override
                    public Membres copia() {
                        return this;
                    }
                }, new Data(1, 1, 2025));

        Accio demostracio = new Demostracio("Demostracio1", new Associacio("NomDemostracio", "EmailDemostracio", "GEI"),
                new Membres("AliasDemostracio", "EmailDemostracio", new Data()) {
                    @Override
                    public Membres copia() {
                        return this;
                    }
                }, "2025-01-01", true, 100.0);

        try {
            llista.afegirAccio(accio1);
            llista.afegirAccio(xerrada);
            llista.afegirAccio(demostracio);
        } catch (AccioJaExisteix e) {
            System.err.println("Excepció trobada: " + e.getMessage());
        }

        String fitxer = "test_llista_accions.txt";

        try {
            llista.guardarAccions(fitxer);
            LlistaAccions llistaCarregada = new LlistaAccions();
            llistaCarregada.carregarAccions(fitxer);

            assert llistaCarregada.getAccions().length == 3 : "testGuardarICarregar - Nombre d'accions carregades";
            System.out.println("Accions carregades des del fitxer:");
            System.out.println(llistaCarregada);

            File file = new File(fitxer);
            if (file.delete()) {
                System.out.println("El fitxer s'ha eliminat correctament.");
            } else {
                System.err.println("No s'ha pogut eliminar el fitxer.");
            }

        } catch (IOException e) {
            System.err.println("Error en guardar o carregar fitxer: " + e.getMessage());
        }
    }

    /**
     * Prova per obtenir una acció pel seu codi.
     * En aquesta prova es crea una accion i s'afegeix a la llista.
     * Utilitzant el seu codi es busca l'acció a la llista.
     */
    static void testObtenirAccioPerCodi() {
        System.out.println("=== TEST: Obtenir Accio Per Codi ===");
        LlistaAccions llista = new LlistaAccions();
        Accio accio1 = new TestAccio("Titol1", new Associacio("Nom1", "Email1", "GEI"),
                new Membres("Alias1", "Email1", new Data()) {
                    @Override
                    public Membres copia() {
                        return this;
                    }
                });
        Accio accio2 = new TestAccio("Titol2", new Associacio("Nom2", "Email2", "GEI"),
                new Membres("Alias2", "Email2", new Data()) {
                    @Override
                    public Membres copia() {
                        return this;
                    }
                });
        try {
            llista.afegirAccio(accio1);
            llista.afegirAccio(accio2);
        } catch (AccioJaExisteix e) {
            System.err.println("Excepció trobada: " + e.getMessage());
        }
        Accio accioObtinguda = llista.getAccioPerCodi(accio1.getCodi());
        assert accioObtinguda != null : "testObtenirAccioPerCodi - Accio obtinguda no ha de ser null";
        System.out.println(accioObtinguda);
    }

    /**
     * Prova per afegir una Xerrada i una Demostracio a la llista.
     */
    static void testAfegirXerradaIDemostracio() {
        System.out.println("=== TEST: Afegir Xerrada i Demostracio ===");
        LlistaAccions llista = new LlistaAccions();
        Accio xerrada = new Xerrada("Xerrada1", new Associacio("NomXerrada", "EmailXerrada", "GEI"),
                new Membres("AliasXerrada", "EmailXerrada", new Data()) {
                    @Override
                    public Membres copia() {
                        return this;
                    }
                }, new Data(1, 1, 2025));
        Accio demostracio = new Demostracio("Demostracio1", new Associacio("NomDemostracio", "EmailDemostracio", "GEI"),
                new Membres("AliasDemostracio", "EmailDemostracio", new Data()) {
                    @Override
                    public Membres copia() {
                        return this;
                    }
                }, "2025-01-01", true, 100.0);
        try {
            llista.afegirAccio(xerrada);
            llista.afegirAccio(demostracio);
        } catch (AccioJaExisteix e) {
            System.err.println("Excepció trobada: " + e.getMessage());
        }
        assert llista.getAccions().length == 2 : "testAfegirXerradaIDemostracio - Nombre d'accions";
        System.out.println(llista);
    }
}

/**
 * Classe de prova per a la classe Accio.
 */
class TestAccio extends Accio {
    public TestAccio(String titol, Associacio associacio, Membres responsable) {
        super(titol, associacio, responsable);
    }

    @Override
    public String getCodi() {
        return super.getCodi();
    }
}
