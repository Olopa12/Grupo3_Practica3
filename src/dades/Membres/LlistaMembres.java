package dades.Membres;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import Utilitats.Data;


/**
 * Classe LlistaMembres que representa una llista de membres.
 * Permet gestionar una col·lecció de membres, afegir-ne, comprovar si ja existeixen
 * i obtenir informació sobre la llista.
 * 
 * @author Paolo
 * @version 1.0
 */
public class LlistaMembres {
    private String nomLlista; // Nom de la llista
    private int nMembres;     // Nombre actual de membres a la llista
    private Membres[] llista; // Llista per emmagatzemar els membres

    /**
     * Constructor que inicialitza una llista amb un nom i una dimensió màxima.
     * 
     * @param nom      - Nom de la llista.
     * @param dimensio - Nombre màxim de membres que pot contenir la llista.
     * 
     * @author Paolo
     */
    public LlistaMembres (String nom, int dimensio){
        this.nomLlista = "Llista de "+ nom;
        nMembres = 0;
        this.llista = new Membres[dimensio];
    }

    /**
     * Ordena la llista de membres per alias.
     * 
     * @throws RuntimeException Si es produeix un error durant l'ordenació.
     * 
     * @author Paolo
     */
    private void ordenarLlista() {
        try {
            Arrays.sort(llista, 0, nMembres, (m1, m2) -> m1.getAlias().compareTo(m2.getAlias()));
        } catch (Exception e) {
            System.err.println("Error ordenant la llista: " + e.getMessage());
        }
    }

    /**
     * Afegeix un membre a la llista si no hi és ja i hi ha espai disponible.
     * Es fa una còpia del membre abans d'afegir-lo.
     * 
     * @param m - Membre que es vol afegir a la llista.
     * @throws IllegalStateException Si la llista està plena.
     * 
     * @author Paolo
     */
    public void afegirMembre(Membres m) {
        try {
            if (esta(m)) {
                System.out.println("Aquest membre ja està a la llista.");
            } else if (nMembres < llista.length) {
                llista[nMembres] = m.copia();
                nMembres++;
                ordenarLlista();
            } else {
                System.out.println("No es poden afegir més membres. Llista plena.");
            }
        } catch (Exception e) {
            System.err.println("Error afegint un membre: " + e.getMessage());
        }
    }

    /**
     * Obté el nombre actual de membres a la llista.
     * 
     * @return El nombre de membres actuals.
     * 
     * @author Paolo
     */
    public int numMembres(){
        return nMembres;
    }

    /**
     * Comprova si un membre ja existeix a la llista.
     * La comprovació es basa en l'alias del membre.
     * 
     * @param m - Membre que es vol comprovar.
     * @return Cert si el membre ja està a la llista, fals en cas contrari.
     * @throws RuntimeException Si es produeix un error durant la comprovació.
     * 
     * @author Paolo
     */
    public boolean esta(Membres m) {
        try {
            for (int i = 0; i < nMembres; i++) {
                if (llista[i].getAlias().equals(m.getAlias())) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Error comprovant si un membre existeix: " + e.getMessage());
        }
        return false;
    }

    /**
     * Getter per obtenir el nom de la llista.
     * 
     * @return El nom de la llista.
     * 
     * @author Paolo
     */
    public String getNomLlista() {
        return nomLlista;
    }

    /**
     * Setter per modificar el nom de la llista.
     * 
     * @param nomLlista - El nou nom de la llista.
     * 
     * @author Paolo
     */
    public void setNomLlista(String nomLlista) {
        this.nomLlista = nomLlista;
    }

    /**
     * Guarda la llista de membres en un fitxer de text.
     * 
     * @param fitxer - Nom del fitxer on es guardarà la informació.
     * @throws IOException si hi ha un problema amb l'escriptura del fitxer.
     * 
     * @author Paolo
     */
    public void guardarEnFitxer(String fitxer) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxer))) {
            for (int i = 0; i < nMembres; i++) {
                Membres membre = llista[i];
                try {
                    if (membre instanceof Alumnes) {
                        Alumnes alumne = (Alumnes) membre;
                        bw.write(String.format(
                            "Alumne;%s;%s;%s;%s;%d;%b;%d;%s",
                            alumne.getAlias(),
                            alumne.getCorreuElectronic(),
                            alumne.getDataAlta(),
                            alumne.getEnsenyament(),
                            alumne.getAntiguitat(),
                            alumne.isGraduat(),
                            alumne.getParticipacions(),
                            alumne.getDataBaixa() != null ? alumne.getDataBaixa() : "Actiu"
                        ));
                    } else if (membre instanceof Professors) {
                        Professors professor = (Professors) membre;
                        bw.write(String.format(
                            "Professor;%s;%s;%s;%s;%s;%d;%s",
                            professor.getAlias(),
                            professor.getCorreuElectronic(),
                            professor.getDataAlta(),
                            professor.getDepartament(),
                            professor.getNumDespatx(),
                            professor.getParticipacions(),
                            professor.getDataBaixa() != null ? professor.getDataBaixa() : "Actiu"
                        ));
                    }
                    bw.newLine();
                } catch (Exception e) {
                    System.err.println("Error escrivint un membre al fitxer: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error guardant en fitxer: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Carrega la llista de membres des d'un fitxer de text.
     * 
     * @param fitxer - Nom del fitxer d'on es carregarà la informació.
     * @throws IOException si hi ha un problema amb la lectura del fitxer.
     * 
     * @author Paolo
     */
    public void carregarDeFitxer(String fitxer) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                try {
                    String[] atributs = linia.split(";");
                    if (atributs.length == 0 || atributs[0].isEmpty()) {
                        System.err.println("Línia buida o malformada: " + linia);
                        continue;
                    }
                    Membres membre = null;
                    switch (atributs[0].toLowerCase()) {
                        case "alumne":
                            membre = carregarAlumne(atributs);
                            break;
                        case "professor":
                            membre = carregarProfessor(atributs);
                            break;
                        default:
                            System.err.println("Tipus de membre desconegut: " + atributs[0]);
                            break;
                    }
                    if (membre != null) {
                        afegirMembre(membre);
                    } else {
                        System.err.println("Error carregant membre de línia: " + linia);
                    }
                } catch (Exception e) {
                    System.err.println("Error processant la línia: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error llegint el fitxer: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Carrega un alumne des d'una línia del fitxer.
     * 
     * @param atributs - Atributs separats de la línia del fitxer.
     * @return Una instància d'Alumnes o null si hi ha un error.
     * @throws IllegalArgumentException Si els atributs no són suficients o hi ha errors en el format.
     * 
     * @author Paolo
     */
    private Membres carregarAlumne(String[] atributs) {
        try {
            if (atributs.length != 9) {
                throw new IllegalArgumentException("Atributs insuficients per carregar un alumne.");
            }
            String alias = atributs[1];
            String correuElectronic = atributs[2];
            Data dataAlta = Data.parseData(atributs[3]);
            String ensenyament = atributs[4];
            int antiguitat = Integer.parseInt(atributs[5]);
            boolean graduat = Boolean.parseBoolean(atributs[6]);
            int participacions = Integer.parseInt(atributs[7]);
            Data dataBaixa = atributs[8].equals("Actiu") ? null : Data.parseData(atributs[8]);

            Alumnes alumne = new Alumnes(alias, correuElectronic, dataAlta, ensenyament, antiguitat, graduat);
            alumne.setParticipacions(participacions);
            alumne.setDataBaixa(dataBaixa);

            return alumne;
        } catch (Exception e) {
            System.err.println("Error carregant Alumne: " + Arrays.toString(atributs) + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Carrega un professor des d'una línia del fitxer.
     * 
     * @param atributs - Atributs separats de la línia del fitxer.
     * @return Una instància de Professsors o null si hi ha un error.
     * @throws IllegalArgumentException Si els atributs no són suficients o hi ha errors en el format.
     * 
     * @author Paolo
     */
    private Membres carregarProfessor(String[] atributs) {
        try {
            if (atributs.length != 8) {
                throw new IllegalArgumentException("Atributs insuficients per carregar un professor.");
            }
            String alias = atributs[1];
            String correuElectronic = atributs[2];
            Data dataAlta = Data.parseData(atributs[3]);
            String departament = atributs[4];
            String numDespatx = atributs[5];
            int participacions = Integer.parseInt(atributs[6]);
            Data dataBaixa = atributs[7].equals("Actiu") ? null : Data.parseData(atributs[7]);

            Professors professor = new Professors(alias, correuElectronic, dataAlta, departament, numDespatx);
            professor.setParticipacions(participacions);
            professor.setDataBaixa(dataBaixa);

            return professor;
        } catch (Exception e) {
            System.err.println("Error carregant Professor: " + Arrays.toString(atributs) + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Retorna una còpia de l'array intern de membres.
     * Inclou només els membres actualment presents a la llista (exclou els espais buits).
     * 
     * @return Una còpia de l'array de membres actual.
     * 
     * @author Paolo
     */
    public Membres[] copia() {
        Membres[] copia = new Membres[nMembres];
        for (int i = 0; i < nMembres; i++) {
            copia[i] = llista[i]; // Copia les referències dels membres
        }
        return copia;
    }

    /**
     * Retorna una representació textual de la llista amb tots els membres.
     * 
     * @return Una cadena de text amb els detalls de tots els membres.
     * 
     * @author Paolo
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < nMembres; i++) {
            result = result + llista[i] + "\n";
        }
        return result.toString();
    }
    
}
