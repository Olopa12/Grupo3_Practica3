package dades.Accions;

import dades.Associacions.*;
import dades.Membres.*;

/**
 * La classe Demostracio és una subclasse de Accio i representa una demostració d'una acció.
 * i fa el següent:
 * - Guarda la data en què es va dissenyar i si encara és una acció que es considera vàlida i per tant es continua oferint.
 * - Comptabilitza quantes vegades s’ha ofert aquesta demostració, guarda aquesta informació i registra el cost associat a la creació dels materials per a la demostració.
 *
 * @author Sara Tribak, Nermin Tribak
 * @version 1.0
 */

public class Demostracio extends Accio {
    private String dataDisseny;
    private boolean esValida;
    private int vegadesOferida;
    private double costMaterials;

    /**
     * Constructor per a la classe Demostracio.
     * 
     * @param titol             Títol de la demostració.
     * @param associacio        L'associació que organitza la demostració.
     * @param responsable       Membre responsable de la demostració.
     * @param dataDisseny       Data en què es va dissenyar la demostració.
     * @param esValida          Indica si la demostració encara és vàlida.
     * @param costMaterials     Cost dels materials per a la demostració.
     */
    public Demostracio(String titol, Associacio associacio, Membres responsable, String dataDisseny, boolean esValida, double costMaterials) {
        super(titol, associacio, responsable);
        this.dataDisseny = dataDisseny;
        this.esValida = esValida;
        this.vegadesOferida = 0;
        this.costMaterials = costMaterials;
    }

    /**
     * Incrementa el nombre de vegades que s'ha ofert la demostració.
     */
    public void incrementarVegadesOferida() {
        vegadesOferida++;
    }

    /**
     * Obté la data en què es va dissenyar la demostració.
     * 
     * @return La data de disseny de la demostració.
     */
    public String getDataDisseny() {
        return dataDisseny;
    }

    /**
     * Indica si la demostració encara és vàlida.
     * 
     * @return true si la demostració és vàlida, false en cas contrari.
     */
    public boolean isEsValida() { 
        return this.costMaterials >= 0;
     }

    /**
     * Obté el nombre de vegades que s'ha ofert la demostració.
     * 
     * @return El nombre de vegades que s'ha ofert la demostració.
     */
    public int getVegadesOferida() {
        return vegadesOferida;
    }

    /**
     * Obté el cost dels materials per a la demostració.
     * 
     * @return El cost dels materials per a la demostració.
     */
    public double getCostMaterials() {
        return costMaterials;
    }

    /**
     * Retorna una representació en forma de cadena de la demostració.
     * 
     * @return Una cadena que representa la demostració.
     */
    @Override
    public String toString() {
        return super.toString() + ", Data Disseny=" + dataDisseny + ", És Vàlida=" + esValida + ", Vegades Oferida=" + vegadesOferida + ", Cost Materials=" + costMaterials;
    }
}
