package dades.Accions;

/**
 * Subclasse Demonstració.
 */
public class Demostracio extends Accio{
    private String dataDisseny; // Data en què es va dissenyar la demostració
    private boolean activa; // Si la demostració encara és vàlida
    private int vegadesOfertes; // Nombre de vegades que s'ha ofert
    private double costMaterials; // Cost per crear els materials de la demostració

    public Demostracio(String titol, String[] associacions, String responsable, String dataDisseny,
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
