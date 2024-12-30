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

    public Demostracio(String titol, Associacio associacio, Membres responsable, String dataDisseny, boolean esValida, double costMaterials) {
        super(titol, associacio, responsable);
        this.dataDisseny = dataDisseny;
        this.esValida = esValida;
        this.vegadesOferida = 0;
        this.costMaterials = costMaterials;
    }

    public void incrementarVegadesOferida() {
        vegadesOferida++;
    }

    public String getDataDisseny() {
        return dataDisseny;
    }

    public boolean isEsValida() {
        return esValida;
    }

    public int getVegadesOferida() {
        return vegadesOferida;
    }

    public double getCostMaterials() {
        return costMaterials;
    }

    @Override
    public String toString() {
        return super.toString() + ", Data Disseny=" + dataDisseny + ", És Vàlida=" + esValida + ", Vegades Oferida=" + vegadesOferida + ", Cost Materials=" + costMaterials;
    }
}
