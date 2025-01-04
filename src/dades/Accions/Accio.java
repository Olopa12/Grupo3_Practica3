package dades.Accions;

import dades.Associacions.*;
import dades.Membres.*;

/**
 * La classe compta amb els atributs d'una acció, que són:
 * - Codi
 * - Títol
 * - Associació
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

    /**
     * Constructor per a la classe Accio.
     * 
     * @param titol El títol de l'acció.
     * @param associacio L'associació que organitza l'acció.
     * @param responsable El membre responsable de l'acció.
     */
    public Accio(String titol, Associacio associacio, Membres responsable) {
        this.titol = titol;
        this.associacio = associacio;
        this.responsable = responsable;
        this.codi = generarCodi(associacio);
    }

    /**
     * Genera un codi únic per a l'acció a partir del nom de l'associació.
     * 
     * @param associacio L'associació que organitza l'acció.
     * @return El codi generat per a l'acció.
     */
    private String generarCodi(Associacio associacio) {
        return associacio.getNomAsociacio().substring(0, 3).toUpperCase() + (codiCounter++);
    }

    /**
     * Obté el codi de l'acció.
     * 
     * @return El codi de l'acció.
     */
    public String getCodi() {
        return codi;
    }

    /**
     * Obté el títol de l'acció.
     * 
     * @return El títol de l'acció.
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Obté l'associació que organitza l'acció.
     * 
     * @return L'associació que organitza l'acció.
     */
    public Associacio getAssociacio() {
        return associacio;
    }

    /**
     * Obté el membre responsable de l'acció.
     * 
     * @return El membre responsable de l'acció.
     */
    public Membres getResponsable() {
        return responsable;
    }

    /**
     * Retorna una representació en forma de cadena de l'acció.
     * 
     * @return Una cadena que representa l'acció.
     */
    @Override
    public String toString() {
        return "Acció [Codi=" + codi + ", Títol=" + titol + ", Associació=" + associacio.getNomAsociacio() + ", Responsable=" + responsable.getAlias() + "]";
    }
}
