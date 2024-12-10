package dades.Accions;

/**
 * Classe base de acció.
 */
public abstract class Accio {
    private static int codiIni = 100;

    private String codi; // Codi identificador de l'acció
    private String titol; // Títol de l'acció
    private String[] associacionsOrganitzadores; // Associacions que organitzen l'acció
    private String responsable; // Responsable de l'acció


    public Accio(String titol, String[] associacions, String responsable) {
        if (associacions != null && associacions.length > 0) {
            this.titol = titol;
            this.associacionsOrganitzadores = associacions.clone();
            this.responsable = responsable;
            this.codi = generarCodi(associacions[0]);
        } 
    }

    /**
     * Generació codi per a l'acció.
     *
     * @param nomAssociacio Nom de l'associació principal.
     * @return 
     */
    private String generarCodi(String nomAssociacio) {
        String curt = nomAssociacio.substring(0, Math.min(3, nomAssociacio.length())).toUpperCase();
        return curt + codiIni++;
    } // Emplem Math.min per agafar només 3 caràcters

   
    // Els getters

    public String getCodi() {
        return codi;
    }

    public String getTitol() {
        return titol;
    }

    public String[] getAssociacionsOrganitzadores() {
        return associacionsOrganitzadores.clone();
    }

    public String getResponsable() {
        return responsable;
    }
   
}
