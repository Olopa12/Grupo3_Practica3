package dades.Membres;

import Utilitats.Data;

/**
 * Classe Professsors que representa un membre del tipus professor.
 * Inclou informació específica com el departament i el número de despatx.
 * 
 * Hereta de la classe abstracta Membres.
 * 
 * @author Paolo
 * @version 1.0
 */
public class Professsors extends Membres{
    private String departament; // Departament al qual pertany el professor (DEIM o DEEEA)
    private String numDespatx;  // Número de despatx del professor

    /**
     * Constructor que inicialitza un objecte de tipus Professsors amb els valors
     * especificats.
     * 
     * @param alias           - Alias del membre.
     * @param correuElectronic - Correu electrònic institucional del membre.
     * @param dataAlta        - Data d'alta del membre.
     * @param departament     - Departament al qual pertany el professor.
     * @param numDespatx      - Número de despatx del professor.
     * @throws IllegalArgumentException Si el departament no és vàlid o si el número de despatx és nul o buit.
     */
    public Professsors(String alias, String correuElectronic, Data dataAlta, 
    String departament, String numDespatx) {
        super(alias, correuElectronic, dataAlta);
        try {
            if (!departament.equals("DEIM") && !departament.equals("DEEEA")) {
                throw new IllegalArgumentException("Departament no vàlid. Només s'accepten DEIM o DEEEA.");
            }
            if (numDespatx == null || numDespatx.isEmpty()) {
                throw new IllegalArgumentException("El número de despatx no pot ser buit o nul.");
            }
            this.departament = departament;
            this.numDespatx = numDespatx;
        } catch (Exception e) {
            System.err.println("Error inicialitzant el professor: " + e.getMessage());
            throw new IllegalArgumentException("Dades inicials no vàlides.");
        }
    }

    /**
     * Setter per modificar el departament del professor.
     * 
     * @param departament - El nou valor del departament.
     * @throws IllegalArgumentException Si el departament no és vàlid.
     */
    public void setDepartament(String departament) {
        try {
            if (!departament.equals("DEIM") && !departament.equals("DEEEA")) {
                throw new IllegalArgumentException("Departament no vàlid. Només s'accepten DEIM o DEEEA.");
            }
            this.departament = departament;
        } catch (Exception e) {
            System.err.println("Error assignant el departament: " + e.getMessage());
        }
    }

    /**
     * Setter per modificar el número de despatx del professor.
     * 
     * @param numDespatx - El nou valor del número de despatx.
     * @throws IllegalArgumentException Si el número de despatx és nul o buit.
     */
    public void setNumDespatx(String numDespatx) {
        try {
            if (numDespatx == null || numDespatx.isEmpty()) {
                throw new IllegalArgumentException("El número de despatx no pot ser buit o nul.");
            }
            this.numDespatx = numDespatx;
        } catch (Exception e) {
            System.err.println("Error assignant el número de despatx: " + e.getMessage());
        }
    }

    /**
     * Getter per obtenir el departament del professor.
     * 
     * @return El valor del departament.
     */
    public String getDepartament() {
        return departament;
    }

    /**
     * Getter per obtenir el número de despatx del professor.
     * 
     * @return El valor del número de despatx.
     */
    public String getNumDespatx() {
        return numDespatx;
    }

    /**
     * Retorna una representació textual de l'objecte Professsors.
     * 
     * @return Una cadena de text amb els detalls del professor.
     * @throws RuntimeException Si es produeix un error durant la generació del text.
     */
    @Override
    public String toString() {
        try {
            return (super.toString() +" Professsors departament=" + departament + 
            ", numDespatx=" + numDespatx);
        } catch (Exception e) {
            System.err.println("Error generant la representació textual: " + e.getMessage());
            return "Error al generar el text.";
        }
    }

    /**
     * Crea una còpia de l'objecte Professsors actual.
     * 
     * @return Una nova instància de l'objecte Professsors amb els mateixos valors.
     * @throws RuntimeException Si es produeix un error durant la còpia.
     */
    @Override
    public Professsors copia() {
        try {
            return new Professsors(
                this.getAlias(),
                this.getCorreuElectronic(),
                this.getDataAlta(),
                this.departament,
                this.numDespatx
            );
        } catch (Exception e) {
            System.err.println("Error copiant l'objecte Professsors: " + e.getMessage());
            return null;
        }
    }
}
