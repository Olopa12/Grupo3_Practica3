package TestErrores.Associacions;

import Utilitats.Data;
import dades.Associacions.Associacio;
import dades.Membres.Alumnes;
import dades.Membres.Membres;

/**
 * Classe TestAssociacio que representa els testes fer per comprovar
 * que els metodes de Associacio.java funcioniguen correctament.
 * 
 * @author Alex Radu
 * @version 1.0
 */
public class TestAssociacio{
    public static void main(String[] args){
        comprovaConstructorV1();
        comprovaConstructorV2();
        validacioGettersSetters();
        validacioAssignarMembresALlistaMembres();
        validarAssignarPresident();
        validarAssignarSecretari();
        validarAssignarTresorer();
    }

    private static void comprovaConstructorV1(){
        System.out.println("Primera comprobacio: ");

        System.out.println("\n\nValidem la primera versio del constructor sense president, secretari i tresorer.\n");
        Associacio associacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI");
        System.out.println(associacioPaelles.toString());

        System.out.println("\nSegona comprobacio: ");

        Associacio associacioEstudiantsPromig = new Associacio("EstudiantsPromigURV", "estudiantspromigurv@urv.cat", "nom arbitrari");
        System.out.println(associacioEstudiantsPromig.toString());
    }

    private static void comprovaConstructorV2(){
        System.out.println("\n\nValidem la segona versio del constructor amb president, secretari i tresorer.\n");
        Membres membre = new TestMembre("Joan123", "joan@etse.com", new Data(10, 1, 2024));
        Associacio associacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI", membre.copia(), membre.copia(), membre.copia());
        System.out.println(associacioPaelles.toString());
    }

    private static void validacioGettersSetters() {
        System.out.println("\n\nValidem getters i setters\n");
        Membres membre1 = new TestMembre("Joan123", "joan@etse.com", new Data(10, 1, 2024));
        Membres membre2 = new TestMembre("Marc321", "correu@exemple.com", new Data(1, 5, 2022));
        Associacio associacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI", membre1.copia(), membre1.copia(), membre1.copia());
        System.out.println("\nGetters: El contingut de la associacio asociacioPaelles es: " + associacioPaelles.getNomAsociacio() + "\n\t" + associacioPaelles.getCorreuAsociacio() + "\n\t" + associacioPaelles.getCarrera() + "\n\t" + associacioPaelles.getPresident() + "\n\t" + associacioPaelles.getSecretari() + "\n\t" + associacioPaelles.getTresorer());
        associacioPaelles.setNomAssociacio("nomAuxiliar123");
        associacioPaelles.setCorreuAssociacio("correuAuxiliar@gmail.com");
        associacioPaelles.setCarrera("BioGEI");
        associacioPaelles.assignarPresident(membre2.copia());
        associacioPaelles.assignarSecretari(membre2.copia());
        associacioPaelles.assignarTresorer(membre2.copia());
        System.out.println("\nSetters: El contingut de la nova associacio asociacioPaelles es " + associacioPaelles.getNomAsociacio() + "\n\t" + associacioPaelles.getCorreuAsociacio() + "\n\t" + associacioPaelles.getCarrera() + "\n\t" + associacioPaelles.getPresident() + "\n\t" + associacioPaelles.getSecretari() + "\n\t" + associacioPaelles.getTresorer());
    }

    private static void validacioAssignarMembresALlistaMembres(){
        System.out.println("\n\nValidem assignar un membre a la llista de membres de Associacio que servira per comprovar si els presidents, secretaris i tresorers son alumnes i no profesors.\n");
        Alumnes membre1 = new TestMembre("Joan123", "joan@etse.com", new Data(10, 1, 2024));
        Membres membre2 = new TestMembre("Marc321", "correu@exemple.com", new Data(1, 5, 2022));
        Associacio associacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI", membre1.copia(), membre1.copia(), membre1.copia());
        String aux = "";
        Membres[] llista = associacioPaelles.getLlistaMembres();
        for(int i = 0; i < associacioPaelles.getNumMembres(); i++){
            aux += "\n" + llista[i].copia();
        }
        System.out.println(aux);
        associacioPaelles.assignarMembresALlistaMembres(membre2);
        aux = "";
        llista = associacioPaelles.getLlistaMembres();
        for(int i = 0; i < associacioPaelles.getNumMembres(); i++){
            aux += "\n" + llista[i].copia();
        }
        System.out.println(aux);
    }

    private static void validarAssignarPresident(){
        System.out.println("\n\nValidem la assignacio de president\n");
        Associacio associacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI");
        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);
        associacioPaelles.assignarMembresALlistaMembres(alumne);
        associacioPaelles.assignarPresident(alumne);
        System.out.println(associacioPaelles.toString());
    }

    private static void validarAssignarSecretari(){
        System.out.println("\n\nValidem la assignacio de secretari\n");
        Associacio associacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI");
        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);
        associacioPaelles.assignarMembresALlistaMembres(alumne);
        associacioPaelles.assignarSecretari(alumne);
        System.out.println(associacioPaelles.toString());
    }

    private static void validarAssignarTresorer(){
        System.out.println("\n\nValidem la assignacio de tresorer\n");
        Associacio associacioPaelles = new Associacio("PaellersURV", "paellersurv@urv.cat", "GEI");
        Alumnes alumne = new Alumnes("Joan123", "joan@etse.com", new Data(10, 1, 2024), "GEI", 2, false);
        associacioPaelles.assignarMembresALlistaMembres(alumne);
        associacioPaelles.assignarTresorer(alumne);
        System.out.println(associacioPaelles.toString());
    }

    /**
     * Classe concreta per provar la classe abstracta Membres.
     * Es proporciona una implementació mínima per al mètode abstracte `copia`.
     * 
     * Aixo es necessari per a que funcioni el codig degut a que necessita parts del codi d'un altre integrant
     * del grup i per decisions de desenvolupament tinc aquest metode. Copiat de la seua part per a ser funcional.
     * 
     * @author Paolo
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
