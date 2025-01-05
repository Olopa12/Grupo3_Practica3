package dades.Membres;

import Utilitats.Data;

/**
 * Classe Alumnes que representa un membre del tipus alumne.
 * Inclou informació específica com l'ensenyament, l'antiguitat
 * i si l'alumne està graduat.
 * 
 * Hereta de la classe abstracta Membres.
 * 
 * @author Paolo
 * @version 1.0
 */
public class Alumnes extends Membres{
    private String ensenyament; // Sigles de l'ensenyament (ex. GEI)
    private int antiguitat; // Anys a l'ETSE
    private boolean graduat; // Indica si l'alumne està graduat

    /**
     * Constructor que inicialitza un objecte de tipus Alumnes amb els valors
     * especificats.
     * 
     * @param alias           - Alias del membre.
     * @param correuElectronic - Correu electrònic institucional del membre.
     * @param dataAlta        - Data d'alta del membre.
     * @param ensenyament     - Ensenyament de l'alumne.
     * @param antiguitat      - Nombre d'anys que l'alumne porta a l'ETSE.
     * @param graduat         - Indica si l'alumne està graduat.
     * @throws IllegalArgumentException Si l'ensenyament és nul o buit, o si l'antiguitat és negativa.
     */
    public Alumnes(String alias, String correuElectronic, Data dataAlta, 
    String ensenyament, int antiguitat, boolean graduat) {
        super(alias, correuElectronic, dataAlta);
        try {
            if (ensenyament == null || ensenyament.isEmpty()) {
                throw new IllegalArgumentException("L'ensenyament no pot ser buit o nul.");
            }
            if (antiguitat < 0) {
                throw new IllegalArgumentException("L'antiguitat no pot ser negativa.");
            }
            this.ensenyament = ensenyament;
            this.antiguitat = antiguitat;
            this.graduat = graduat;
        } catch (Exception e) {
            System.err.println("Error inicialitzant un alumne: " + e.getMessage());
            throw new IllegalArgumentException("Dades inicials no vàlides.");
        }
    }

    /**
     * Getter per obtenir l'ensenyament de l'alumne.
     * 
     * @return El valor de l'ensenyament.
     */
    public String getEnsenyament() {
        return ensenyament;
    }

    /**
     * Getter per obtenir l'antiguitat de l'alumne.
     * 
     * @return El nombre d'anys que l'alumne porta a l'ETSE.
     */
    public int getAntiguitat() {
        return antiguitat;
    }

    /**
     * Getter per comprovar si l'alumne està graduat.
     * 
     * @return Cert si l'alumne està graduat, fals en cas contrari.
     */
    public boolean isGraduat() {
        return graduat;
    }

    /**
     * Setter per modificar l'ensenyament de l'alumne.
     * 
     * @param ensenyament - El nou valor de l'ensenyament.
     * @throws IllegalArgumentException Si l'ensenyament és nul o buit.
     */
    public void setEnsenyament(String ensenyament) {
        try {
            if (ensenyament == null || ensenyament.isEmpty()) {
                throw new IllegalArgumentException("L'ensenyament no pot ser buit o nul.");
            }
            this.ensenyament = ensenyament;
        } catch (Exception e) {
            System.err.println("Error assignant l'ensenyament: " + e.getMessage());
        }
    }

    /**
     * Setter per modificar l'antiguitat de l'alumne.
     * 
     * @param antiguitat - El nou valor d'antiguitat.
     * @throws IllegalArgumentException Si l'antiguitat és negativa.
     */
    public void setAntiguitat(int antiguitat) {
        try {
            if (antiguitat < 0) {
                throw new IllegalArgumentException("L'antiguitat no pot ser negativa.");
            }
            this.antiguitat = antiguitat;
        } catch (Exception e) {
            System.err.println("Error assignant l'antiguitat: " + e.getMessage());
        }
    }

    /**
     * Setter per modificar l'estat de graduació de l'alumne.
     * 
     * @param graduat - El nou estat de graduació (cert o fals).
     */
    public void setGraduat(boolean graduat) {
        try {
            this.graduat = graduat;
        } catch (Exception e) {
            System.err.println("Error assignant l'estat de graduació: " + e.getMessage());
        }
    }

    /**
     * Retorna una representació textual de l'objecte Alumnes.
     * 
     * @return Una cadena de text amb els detalls de l'alumne.ç
     * @throws RuntimeException Si es produeix un error en generar la representació.
     */
    @Override
    public String toString() {
        try {
            return (super.toString() + " Alumnes => ensenyament=" + ensenyament + 
            ", antiguitat=" + antiguitat + ", graduat=" + graduat);
        } catch (Exception e) {
            System.err.println("Error generant la representació textual: " + e.getMessage());
            return "Error al generar el text.";
        }
    }
   
    /**
     * Crea una còpia de l'objecte Alumnes actual.
     * 
     * @return Una nova instància de l'objecte Alumnes amb els mateixos valors.
     * @throws RuntimeException Si es produeix un error durant la còpia.
     */
    @Override
    public Alumnes copia() {
        try {
            Alumnes copia = new Alumnes(
                this.getAlias(), 
                this.getCorreuElectronic(),
                this.getDataAlta(),
                this.ensenyament,
                this.antiguitat, 
                this.graduat
            );
        
            if (this.getDataBaixa() != null) {
                copia.setDataBaixa(this.getDataBaixa());
            }
        
            copia.setParticipacions(this.getParticipacions());
        
            return copia;
        } catch (Exception e) {
            System.err.println("Error copiant l'objecte Alumnes: " + e.getMessage());
            return null;
        }
    }
}
