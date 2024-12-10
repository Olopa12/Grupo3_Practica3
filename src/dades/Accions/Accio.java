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

/**
 * Subclasse demostració.
 */
class Demonstracio extends Accio {
    private String dataDisseny; // Data en què es va dissenyar la demostració
    private boolean activa; // Si la demostració encara és vàlida
    private int vegadesOfertes; // Nombre de vegades que s'ha ofert
    private double costMaterials; // Cost per crear els materials de la demostració

    public Demonstracio(String titol, String[] associacions, String responsable, String dataDisseny,
                        boolean activa, double costMaterials) {
        super(titol, associacions, responsable);
        this.dataDisseny = dataDisseny;
        this.activa = activa;
        this.vegadesOfertes = 0;
        this.costMaterials = costMaterials;
    }

    public String getDataDisseny() {
        return dataDisseny;
    }

    public boolean isActiva() {
        return activa;
    }

    public int getVegadesOfertes() {
        return vegadesOfertes;
    }

    public double getCostMaterials() {
        return costMaterials;
    }

    public void incrementarVegadesOfertes() {
        vegadesOfertes++;
    }

}

/**
 * Subclasse xerrada.
 */
class Xerrada extends Accio {
    private String dataXerrada; // Data en què es farà la xerrada
    private String[] membresImpartidors; // Membres que imparteixen la xerrada
    private int assistents; // Nombre d'assistents
    private int[] valoracions; // Valoracions fetes pels assistents
    private int numMembres;
    private int numValoracions;

    public Xerrada(String titol, String[] associacions, String responsable, String dataXerrada) {
        super(titol, associacions, responsable);
        this.dataXerrada = dataXerrada;
        this.membresImpartidors = new String[3]; // Fins a 3 membres
        this.assistents = 0;
        this.valoracions = new int[100]; // Fins a 100 valoracions
        this.numMembres = 0;
        this.numValoracions = 0;
    }

    public String getDataXerrada() {
        return dataXerrada;
    }

    public String[] getMembresImpartidors() {
        String[] impartidors = new String[numMembres];
        System.arraycopy(membresImpartidors, 0, impartidors, 0, numMembres);
        return impartidors;
    }

    public int getAssistents() {
        return assistents;
    }

    public int[] getValoracions() {
        int[] numV = new int[numValoracions];
        System.arraycopy(valoracions, 0, numV, 0, numValoracions);
        return numV;
    }

    public void afegirMembreImpartidor(String membre) {
        if (numMembres < 3) {
            membresImpartidors[numMembres++] = membre;
        }
    }

    public void afegirAssistents(int nombre) {
        if (nombre >= 0) {
            assistents += nombre;
        }
    }

    public void afegirValoracio(int valoracio) {
        if (valoracio >= 0 && valoracio <= 10 && numValoracions < valoracions.length) {
            valoracions[numValoracions++] = valoracio;
        }
    }

    

}
