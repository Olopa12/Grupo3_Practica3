package dades.Accions;

import dades.Associacions.*;
import dades.Membres.*;
import Utilitats.Data;

/**
 * La classe Xerrada és una subclasse de la classe Accio.
 * En aquesta classe guardarem la data en què s’ha fet la xerrada, les dades dels membres que l’han impartit (podem suposar un màxim prefixat, inicialment a 3).
 * També guardarem quants assistents ha tingut la xerrada i les valoracions que aquests han fet (les valoracions estaran en l’escala [0-10]). 
 *
 * @author Nermin Tribak, Sara Tribak
 * @version 1.0
 */

public class Xerrada extends Accio {
    private Data data; 
    private Membres[] membresImpartidors; 
    private int numAssistents; 
    private int[] valoracions; 
    private int valoracionsCount; 
    private static final int MAX_MEMBRES = 3; 
    private static final int MAX_VALORACIONS = 100; 

    /**
     * Constructor per a la classe Xerrada.
     * 
     * @param titol El títol de la xerrada.
     * @param associacio L'associació que organitza la xerrada.
     * @param responsable El membre responsable de la xerrada.
     * @param data La data en què es farà la xerrada.
     */
    public Xerrada(String titol, Associacio associacio, Membres responsable, Data data) {
        super(titol, associacio, responsable);
        this.data = data;
        this.membresImpartidors = new Membres[MAX_MEMBRES];
        this.numAssistents = 0;
        this.valoracions = new int[MAX_VALORACIONS]; 
        this.valoracionsCount = 0;
    }

    /**
     * Afegeix un membre impartidor a la xerrada.
     * 
     * @param membre El membre a afegir.
     * @return true si el membre s'ha afegit correctament, false en cas contrari.
     */
    public boolean afegirMembreImpartidor(Membres membre) {
        if (membre == null) {
            return false; 
        }
        for (int i = 0; i < membresImpartidors.length; i++) {
            if (membresImpartidors[i] == null) {
                membresImpartidors[i] = membre;
                return true; 
            }
        }
        return false; 
    }

    /**
     * Afegeix assistents a la xerrada.
     * 
     * @param nombre El nombre d'assistents a afegir.
     */
    public void afegirAssistents(int nombre) {
        if (nombre >= 0) {
            numAssistents += nombre; 
        }
    }

    /**
     * Afegeix una valoració a la xerrada.
     * 
     * @param valoracio La valoració a afegir (ha de ser entre 0 i 10).
     */
    public void afegirValoracio(int valoracio) {
        if (valoracio >= 0 && valoracio <= 10 && valoracionsCount < valoracions.length) {
            valoracions[valoracionsCount++] = valoracio; 
        }
    }

    /**
     * Calcula la valoració mitjana de la xerrada.
     * 
     * @return La valoració mitjana de la xerrada.
     */
    public double getValoracioMitjana() {
        if (valoracionsCount == 0) {
            return 0; 
        }
        int suma = 0;
        for (int i = 0; i < valoracionsCount; i++) {
            suma += valoracions[i];
        }
        return (double) suma / valoracionsCount;
    }

    public int valoracionsSuma() {
        int suma = 0;
        for (int i = 0; i < valoracionsCount; i++) {
            suma += valoracions[i];
        }
        return suma;
    }

    /**
     * Obté la data de la xerrada.
     * 
     * @return La data de la xerrada.
     */
    public Data getData() {
        return data;
    }

    /**
     * Obté els membres impartidors de la xerrada.
     * 
     * @return Un array amb els membres impartidors de la xerrada.
     */
    public Membres[] getMembresImpartidors() {
        return membresImpartidors;
    }

    /**
     * Obté el nombre d'assistents a la xerrada.
     * 
     * @return El nombre d'assistents a la xerrada.
     */
    public int getNumAssistents() {
        return numAssistents;
    }

    /**
     * Obté les valoracions fetes a la xerrada.
     * 
     * @return Un array amb les valoracions fetes a la xerrada.
     */
    public int[] getValoracions() {
        int[] valoracionsCopies = new int[valoracionsCount];
        System.arraycopy(valoracions, 0, valoracionsCopies, 0, valoracionsCount);
        return valoracionsCopies;
    }

    /**
     * Retorna una representació en forma de cadena de la xerrada.
     * 
     * @return Una cadena que representa la xerrada.
     */
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
