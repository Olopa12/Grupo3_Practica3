package dades.Accions;

/**
 * Subclasse Xerrada.
 */
public class Xerrada extends Accio {
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
