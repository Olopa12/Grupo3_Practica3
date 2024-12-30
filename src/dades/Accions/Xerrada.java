package dades.Accions;

import dades.Associacions.*;
import dades.Membres.*;

/**
 * La classe Xerrada és una subclasse de la classe Accio.
 * En aquesta classe guardarem la data en què s’ha fet la xerrada, les dades dels membres que l’han impartit (podem suposar un màxim prefixat, inicialment a 3).
 * També guardarem quants assistents ha tingut la xerrada i les valoracions que aquests han fet (les valoracions estaran en l’escala [0-10]). 
 *
 * @author Nermin Tribak, Sara Tribak
 * @version 1.0
 */

public class Xerrada extends Accio {
    private String data; // La data en què es farà la xerrada
    private Membres[] membresImpartidors; // Membres impartidors de la xerrada
    private int numAssistents; // Nombre d'assistents
    private int[] valoracions; //  Valoració dels assistents
    private int valoracionsCount; // Valoracions fins a la data
    private static final int MAX_MEMBRES = 3; //Nombre màxim de membres que puguin impartir xerrada
    private static final int MAX_VALORACIONS = 100; // Màxim de valoracions 

    public Xerrada(String titol, Associacio associacio, Membres responsable, String data) {
        super(titol, associacio, responsable);
        this.data = data;
        this.membresImpartidors = new Membres[MAX_MEMBRES];
        this.numAssistents = 0;
        this.valoracions = new int[MAX_VALORACIONS]; // Estableixem un màxim de 100 valoracions
        this.valoracionsCount = 0;
    }

    // Mètode per afegir un membre impartidor
    public boolean afegirMembreImpartidor(Membres membre) {
        if (membre == null) {
            return false; // Si el membre és null, no s'afegeix
        }
        for (int i = 0; i < membresImpartidors.length; i++) {
            if (membresImpartidors[i] == null) {
                membresImpartidors[i] = membre;
                return true; // Membre afegit correctament
            }
        }
        return false; // Si ja hi ha 3 membres, no s'afegeix més
    }

    // Mètode per afegir assistents
    public void afegirAssistents(int nombre) {
        if (nombre >= 0) {
            numAssistents += nombre; // Afegim el nombre d'assistents
        }
    }

    // Mètode per afegir valoracions
    public void afegirValoracio(int valoracio) {
        if (valoracio >= 0 && valoracio <= 10 && valoracionsCount < valoracions.length) {
            valoracions[valoracionsCount++] = valoracio; // Afegim la valoració a l'array
        }
    }

    // Mètode per calcular la valoració mitjana
    public double getValoracioMitjana() {
        if (valoracionsCount == 0) {
            return 0; // Si no hi ha valoracions, retornem 0
        }
        int suma = 0;
        for (int i = 0; i < valoracionsCount; i++) {
            suma += valoracions[i];
        }
        return (double) suma / valoracionsCount;
    }

    // Getter per obtenir la data de la xerrada
    public String getData() {
        return data;
    }

    // Getter per obtenir els membres impartidors
    public Membres[] getMembresImpartidors() {
        return membresImpartidors;
    }

    // Getter per obtenir el nombre d'assistents
    public int getNumAssistents() {
        return numAssistents;
    }

    // Getter per obtenir les valoracions fetes
    public int[] getValoracions() {
        int[] valoracionsCopies = new int[valoracionsCount];
        System.arraycopy(valoracions, 0, valoracionsCopies, 0, valoracionsCount);
        return valoracionsCopies;
    }

    // Mètode toString per representar la xerrada
    @Override
    public String toString() {
        String impartidors = "";
        for (Membres membre : membresImpartidors) {
            if (membre != null) {
                impartidors += membre.getAlias() + " "; 
            }
        }
        return "Data=" + data +
               ", Membres Impartidors=" + impartidors.trim() +
               ", Assistents=" + numAssistents +
               ", Valoració Mitjana=" + getValoracioMitjana();
    }
    
}
