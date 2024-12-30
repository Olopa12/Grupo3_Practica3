package dades.Accions;

import dades.Associacions.*;
import dades.Membres.*;

/**
 * La classe compta amb els atributs d'una accion, que són:
 * - Codi
 * - Titol
 * - Associacio
 * - Responsable
 * El codi es genera automàticament a partir de les tres primeres lletres del nom de l'associació, seguides d'un número (començant per 100).
 * 
 * @author Sara Tribak, Nermin Tribak
 * @version 1.0
 */

public abstract class Accio {
    private static int codiCounter = 100;
    private String codi;
    private String titol;
    private Associacio associacio;
    private Membres responsable;

    public Accio(String titol, Associacio associacio, Membres responsable) {
        this.titol = titol;
        this.associacio = associacio;
        this.responsable = responsable;
        this.codi = generarCodi(associacio);
    }

    private String generarCodi(Associacio associacio) {
        return associacio.getNomAsociacio().substring(0, 3).toUpperCase() + (codiCounter++);
    }

    public String getCodi() {
        return codi;
    }

    public String getTitol() {
        return titol;
    }

    public Associacio getAssociacio() {
        return associacio;
    }

    public Membres getResponsable() {
        return responsable;
    }

    @Override
    public String toString() {
        return "Acció [Codi=" + codi + ", Títol=" + titol + ", Associació=" + associacio.getNomAsociacio() + ", Responsable=" + responsable.getAlias() + "]";
    }
}


// TODO 1 revisar los test, y corregir errores del código 
