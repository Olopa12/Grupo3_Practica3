package dades.Membres;

import Utilitats.Data;

/**
 * Classe abstracta Membres.
 * Representa un membre genèric d'una associació amb els atributs bàsics
 * com alias, correu electrònic, data d'alta i data de baixa.
 * 
 * Aquesta classe defineix mètodes comuns i obliga a les subclasses
 * a implementar el mètode abstracte `copia`.
 * 
 * @author Paolo
 * @version 1.0
 */
public abstract class Membres {
    private String alias; // Identificador únic del membre
    private String correuElectronic; // Correu electrònic del membre
    private Data dataAlta; // Data d'alta del membre a l'associació
    private Data dataBaixa; // Data de baixa del membre
    private int participacions; // Nombre de participacions del membre

    /**
     * Constructor que inicialitza un membre amb alias, correu electrònic i data d'alta.
     * La data de baixa s'inicialitza a null.
     * 
     * @param alias            - Identificador únic del membre.
     * @param correuElectronic - Correu electrònic del membre.
     * @param dataAlta         - Data en què el membre s'ha donat d'alta.
     * @throws IllegalArgumentException Si qualsevol dels paràmetres és invàlid.
     */
    public Membres(String alias, String correuElectronic, Data dataAlta) {
        try {
            this.alias = alias;
            this.correuElectronic = correuElectronic;
            this.dataAlta = dataAlta;
            dataBaixa = null;
            this.participacions = 0;
        } catch (Exception e) {
            System.err.println("Error inicialitzant el membre: " + e.getMessage());
            throw new IllegalArgumentException("Dades inicials no vàlides");
        } 
    }

    /**
     * Setter per modificar l'alias del membre.
     * Assigna el valor rebut per paràmetre.
     * 
     * @param alias - Nou valor per a l'alias del membre.
     * @throws IllegalArgumentException Si l'alias és buit o nul.
     */
    public void setAlias(String alias) {
        try {
            if (alias == null || alias.isEmpty()) {
                throw new IllegalArgumentException("L'alias no pot ser buit o nul.");
            }
            this.alias = alias;
        } catch (Exception e) {
            System.err.println("Error assignant l'alias: " + e.getMessage());
        }
    }

    /**
     * Setter per modificar el correu electrònic del membre.
     * Assigna el valor rebut per paràmetre.
     * 
     * @param correuElectronic - Nou valor per al correu electrònic del membre.
     * @throws IllegalArgumentException Si el correu electrònic és buit o nul.
     */
    public void setCorreuElectronic(String correuElectronic) {
        try {
            if (correuElectronic == null || correuElectronic.isEmpty()) {
                throw new IllegalArgumentException("El correu electrònic no pot ser buit o nul.");
            }
            this.correuElectronic = correuElectronic;
        } catch (Exception e) {
            System.err.println("Error assignant el correu electrònic: " + e.getMessage());
        }
    }

    /**
     * Setter per modificar la data d'alta del membre.
     * Assigna el valor rebut per paràmetre.
     * 
     * @param dataAlta - Nou valor per a la data d'alta del membre.
     * @throws IllegalArgumentException Si la data d'alta és nul·la.
     */
    public void setDataAlta(Data dataAlta) {
        try {
            if (dataAlta == null) {
                throw new IllegalArgumentException("La data d'alta no pot ser nul·la.");
            }
            this.dataAlta = dataAlta;
        } catch (Exception e) {
            System.err.println("Error assignant la data d'alta: " + e.getMessage());
        }
    }

    /**
     * Setter per modificar el nombre de participacions del membre.
     * Si el valor és nul o buit, es manté el rol actual.
     * 
     * @param participacions - Nou valor per al rol del membre.
     * @throws IllegalArgumentException Si el rol és buit o nul.
     */
    public void setParticipacions(int participacions) {
        try {
            if (participacions < 0) {
                throw new IllegalArgumentException("El nombre de participacions no pot ser negatiu.");
            }
            this.participacions = participacions;
        } catch (Exception e) {
            System.err.println("Error assignant el nombre de participacions: " + e.getMessage());
        }
    }

    /**
     * Setter per modificar la data de baixa del membre.
     * Assigna el valor rebut per paràmetre.
     * 
     * @param dataBaixa - Nou valor per a la data de baixa del membre.
     * @throws IllegalArgumentException Si la data de baixa és anterior a la data d'alta.
     */
    public void setDataBaixa(Data dataBaixa) {
        try {
            if (dataBaixa != null && dataBaixa.esAnterior(dataAlta)) {
                throw new IllegalArgumentException("La data de baixa no pot ser anterior a la data d'alta.");
            }
            this.dataBaixa = dataBaixa;
        } catch (Exception e) {
            System.err.println("Error assignant la data de baixa: " + e.getMessage());
        }
    }

    /**
     * Getter per obtenir l'alias del membre.
     * 
     * @return El valor de l'alias del membre.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Getter per obtenir el correu electrònic del membre.
     * 
     * @return El valor del correu electrònic del membre.
     */
    public String getCorreuElectronic() {
        return correuElectronic;
    }

    /**
     * Getter per obtenir la data d'alta del membre.
     * 
     * @return El valor de la data d'alta.
     */
    public Data getDataAlta() {
        return dataAlta;
    }

    /**
     * Getter per obtenir la data de baixa del membre.
     * 
     * @return El valor de la data de baixa o null si el membre està actiu.
     */
    public Data getDataBaixa() {
        return dataBaixa;
    }

    /**
     * Getter per obtenir el nombre de participacions del membre.
     * 
     * @return El valor del nombre de participacions.
     */
    public int getParticipacions() {
        return participacions;
    }

    /**
     * Retorna una representació textual del membre.
     * 
     * @return Una cadena de text amb els detalls del membre.
     * @throws RuntimeException Si es produeix un error durant la generació del text.
     */
    @Override
    public String toString() {
        try {
            return "Membre => alias=" + alias + ", correuElectronic=" + 
            correuElectronic + ", dataAlta=" + dataAlta + 
            ", dataBaixa=" + (dataBaixa != null ? dataBaixa : "Actiu") + 
            ", participacions=" + participacions;
        } catch (Exception e) {
            System.err.println("Error generant la representació textual: " + 
            e.getMessage());
            return "Error al generar el text.";
        }
    }   

    /**
     * Mètode abstracte que obliga les subclasses a implementar una còpia
     * de l'objecte actual.
     * 
     * @return Una nova instància del membre amb els mateixos valors.
     */
    public abstract Membres copia();

}
