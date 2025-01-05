package dades.Associacions;

import dades.Membres.*;

import java.io.Serializable;

import dades.Excepcions.*;

/**
 * Classe Associacio que representa una associacio basica amb nom, correu i carrera.
 * Inclou informació una llista de membres que s'utilitza per fer comprobacions en 
 * les assignacions del president, secretari o tresorer.
 * 
 * @author Alex Radu
 * @version 1.0
 */
public class Associacio implements Serializable{
    private final int MAXMEMBRES = 100;
    private String nomAssociacio = null;
    private String correuAssociacio = null;

    private Membres[] llistaM;
    private int nMembres;

    private static String[] carreras = {"GEB", "GEI", "GESST", "BioGEI", "DG GEB-GESST", "extern-ETSE"};
    private String carrera = null;

    
    private Membres president;
    private Membres secretari;
    private Membres tresorer;

    /**
     * Primer constructor sense president, secretari i tresorer.
     * @param nom Nom de la associacio.
     * @param correu Correu de la associacio.
     * @param carrera Carrera a la que pertanya la associacio.
     * @throws IllegalArgumentException Si algun dels parametres 'nom', 'correu' o 'carrera' es nul.
     * @throws CarreraNoExiste Si la carrera proporcionada no es troba dins de les carreres disponibles.
     * @author Alex Radu
     */
    public Associacio (String nom, String correu, String carrera){
        if (nom == null) {
            throw new IllegalArgumentException("El nombre de la asociación no puede estar vacío.");
        }
        if (correu == null) {
            throw new IllegalArgumentException("El correo de la asociación no puede estar vacío.");
        }
        if (carrera == null) {
            throw new IllegalArgumentException("Debe haber un nombre para la carrera, no puede estar vacio.");
        }
        nomAssociacio = nom;
        correuAssociacio = correu;
        nMembres = 0;
        president = null;
        secretari = null;
        tresorer = null;
        llistaM = new Membres[MAXMEMBRES];
        int i = 0;
        boolean trobat = false;
        try{
            while(i < carreras.length && !trobat){
                    if(carrera.equalsIgnoreCase(carreras[i])){
                        this.carrera = carrera;
                        trobat = true;
                    }
                    i++;
                }
                if(!trobat){
                    throw new CarreraNoExiste(carrera);
                }
        }
        catch(CarreraNoExiste e){
                String aux = "\nPosibles carreres: ";
                    for(int j = 0; j < carreras.length; j++){
                        if(j == carreras.length-1){
                            aux += carreras[j] + ".";
                        }else{
                            aux += carreras[j] + ", ";
                        }
                    }
                    this.carrera = "¡Carrera Inexistente!" + aux;
        }
        
    }
    /**
     * Segon constructor amb president, secretari i tresorer.
     * @param nom Nom de la associacio.
     * @param correu Correu de la associacio.
     * @param carrera Carrera a la que pertanya la associacio.
     * @param president Membre que fa com a president de la associacio.
     * @param secretari Membre que fa com a secretari de la associacio.
     * @param tresorer Membre que fa com a tresorer de la associacio.
     * @throws IllegalArgumentException Si algun dels parametres 'nom', 'correu' o 'carrera' es nul.
     * @throws CarreraNoExiste Si la carrera proporcionada no es troba dins de les carreres disponibles.
     * @author Alex Radu
     */
    public Associacio (String nom, String correu, String carrera, Membres president, Membres secretari, Membres tresorer){
        if (nom == null) {
            throw new IllegalArgumentException("El nombre de la asociación no puede estar vacío.");
        }
        if (correu == null) {
            throw new IllegalArgumentException("El correo de la asociación no puede estar vacío.");
        }
        if (carrera == null) {
            throw new IllegalArgumentException("Debe haber un nombre para la carrera, no puede estar vacio.");
        }
        nomAssociacio = nom;
        correuAssociacio = correu;
        nMembres = 0;
        this.president = president;
        this.secretari = secretari;
        this.tresorer = tresorer;
        llistaM = new Membres[MAXMEMBRES];
        if(president == secretari && president != tresorer){
            llistaM[0] = president.copia();
            llistaM[1] = tresorer.copia();
            nMembres = 2;
        } else if(president != secretari && president == tresorer){
            llistaM[0] = president.copia();
            llistaM[1] = secretari.copia();
            nMembres = 2;
        } else if(president == secretari && president == tresorer){
            llistaM[0] = president.copia();
            nMembres = 1;
        } else if(president != secretari && president != tresorer){
            llistaM[0] = president.copia();
            llistaM[1] = secretari.copia();
            llistaM[2] = tresorer.copia();
            nMembres = 3;
        }
        int i = 0;
        boolean trobat = false;
        try{
            while(i < carreras.length && !trobat){
                    if(carrera.equalsIgnoreCase(carreras[i])){
                        this.carrera = carrera;
                        trobat = true;
                    }
                    i++;
                }
                if(!trobat){
                    throw new CarreraNoExiste(carrera);
                }
        }
        catch(CarreraNoExiste e){
                String aux = "\n\tPosibles carreres: ";
                    for(int j = 0; j < carreras.length; j++){
                        if(j == carreras.length-1){
                            aux += carreras[j] + ".";
                        }else{
                            aux += carreras[j] + ", ";
                        }
                    }
                    this.carrera = "¡Carrera Inexistente!" + aux;
        }
    }

    @Override
    public String toString() {
        return "\nAsociacio: \n\tNom: " + nomAssociacio + "\n\tCorreu: " + correuAssociacio + "\n\tCarrera: " + carrera + "\n\tPresident: " + president + "\n\tSecretari: " + secretari + "\n\tTresorer: " + tresorer;
    }

    /** 
     * Serie de getters i setters autodescriptius.
     * @Author Alex Radu.
     */
    public String getNomAsociacio() {
        return nomAssociacio;
    }

    public String getCorreuAsociacio() {
        return correuAssociacio;
    }

    public String getCarrera() {
        return carrera;
    }

    public Membres getPresident(){
        return president;
    }

    public Membres getSecretari(){
        return secretari;
    }

    public Membres getTresorer(){
        return tresorer;
    }

    public Membres[] getLlistaMembres(){
        return llistaM;
    }

    public int getNumMembres(){
        return nMembres;
    }

    public static String[] getCarreras(){
        return carreras;
    }

    /**
     * @throws IllegalArgumentException Si el parametre 'nom' es buit.
     */
    public void setNomAssociacio(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nombre de la asociación no puede estar vacío.");
        }
        this.nomAssociacio = nom;
    }

    /**
     * @throws IllegalArgumentException Si el parametre 'correu' es buit.
     */
    public void setCorreuAssociacio(String correu) {
        if (correu == null) {
            throw new IllegalArgumentException("El correo de la asociación no puede estar vacío.");
        }
        this.correuAssociacio = correu;
    }

    /**
     * Estableix les sigles de la carrera a la que pertanya l'associacio.
     * Si el nom de la carrera proporcionat no es valid es llança una
     * excepcio de tipus IllegalArgumentException i es nombra una llista
     * de les carreres que si que son valides carreres valides.
     *
     * @param carrera Sigles de la carrera a la que pertanya l'associacio.
     * @throws IllegalArgumentException Si el parametre 'carrera' no coincideix amb una carrera valida.
     * En cas que no sigui valida, l'excepcio inclou una llista de les carreres possibles que si son valides.
     * @author Alex Radu
     */
    public void setCarrera(String carrera) {
        if (carrera == null) {
            throw new IllegalArgumentException("Debe haber un nombre para la carrera, no puede estar vacío.");
        }

        String posiblesCarreras = "";
        boolean carreraValida = false;
        int i = 0;
        while(i < carreras.length && !carreraValida) {
            if (carrera.equalsIgnoreCase(carreras[i])) {
                carreraValida = true;
                if(i == carreras.length-1){
                    posiblesCarreras += carreras[i] + ".";
                }else{
                    posiblesCarreras += carreras[i] + ", ";
                }
            }
            i++;
        }
        
        if (!carreraValida) {
            throw new IllegalArgumentException("\nCarrera no valida. Las posibles carreras son: " + posiblesCarreras);
        }

        this.carrera = carrera;
    }

    /**
     * Metode que asigna un membre a la llista de membres que servira per a les comprobacions dels rols.
     * @param membre Membre a afegir.
     * @throws ArrayIndexOutOfBoundsException Si la llista de membres esta plena i no es pot afegir mes membres.
     * @throws MiembroYaExiste Si el membre ja existeix a la llista.
     * @author Alex Radu.
     */
    public void assignarMembresALlistaMembres(Membres membre){

        if (nMembres >= MAXMEMBRES) {
            throw new ArrayIndexOutOfBoundsException("La llista de membres esta plena, no es posible agregar mes membres");
        }

        boolean miembroYaExiste = false;
        int i = 0;
        while(i < nMembres && miembroYaExiste == false){
            if(llistaM[i].getAlias().equalsIgnoreCase(membre.getAlias())){
                miembroYaExiste = true;
            }
            else{
                i++;
            }
        }

        try{
            if(!miembroYaExiste){
                llistaM[nMembres] = membre;
            }
            else{
                throw new MiembroYaExiste(membre.getAlias());
            }
        } catch(MiembroYaExiste e){
            System.out.println("\nIntrodueix un nom que de un membre que no estigue fins ara...");
        }

        nMembres++;
    }
    
    /**
     * Metode que rep un membre i si es membre d'aquesta associacio i un alumne el fa president de la associacio.
     * @param membre Membre a fer president de la associacio.
     * @throws IllegalStateException Si la llista de membres no ha estat inicialitzada i esta buida.
     * @author Alex Radu
     */
    public void assignarPresident(Membres membre){
        
        if (llistaM == null) {  
            throw new IllegalStateException("Las listas de miembros no han sido inicializadas y estan vacias."); 
        }

        boolean trobat = false;
        int i = 0;
        while(i < nMembres && trobat == false){
            if(llistaM[i].getAlias().equalsIgnoreCase(membre.getAlias()) && llistaM[i] instanceof Alumnes){
                trobat = true;
                president = membre;
            }
            else{
                i++;
            }
        }
    }
    
    /**
     * Metode que rep un membre i si es membre d'aquesta associacio i un alumne el fa secretari de la associacio.
     * @param membre Membre a fer secretari de la associacio.
     * @throws IllegalStateException Si la llista de membres no ha estat inicialitzada i esta buida.
     * @author Alex Radu
     */
    public void assignarSecretari(Membres membre){
        
        if (llistaM == null) {     
            throw new IllegalStateException("Las listas de miembros no han sido inicializadas y estan vacias."); 
        }

        boolean trobat = false;
        int i = 0;
        while(i < nMembres && trobat == false){
            if(llistaM[i].getAlias().equalsIgnoreCase(membre.getAlias()) && llistaM[i] instanceof Alumnes){
                trobat = true;
                secretari = membre;
            }
            else{
                i++;
            }
        }
    }

    /**
     * Metode que rep un membre i si es membre d'aquesta associacio i un alumne el fa tresorer de la associacio.
     * @param membre Membre a fer tresorer de la associacio.
     * @throws IllegalStateException Si la llista de membres no ha estat inicialitzada i esta buida.
     * @author Alex Radu
     */
    public void assignarTresorer(Membres membre){

        if (llistaM == null) {     
            throw new IllegalStateException("Las listas de miembros no han sido inicializadas y estan vacias."); 
        }

        boolean trobat = false;
        int i = 0;
        while(i < nMembres && trobat == false){
            if(llistaM[i].getAlias().equalsIgnoreCase(membre.getAlias()) && llistaM[i] instanceof Alumnes){
                trobat = true;
                tresorer = membre;
            }
            else{
                i++;
            }
        }
    }

    public Associacio copia() {
        return new Associacio(this.getNomAsociacio(), this.getCorreuAsociacio(), this.getCarrera(), this.getPresident(), this.getSecretari(), this.getTresorer());
    }

}

