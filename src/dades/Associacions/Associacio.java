package dades.Associacions;

import dades.Membres.*;

// Hecho por Alex Radu

// TODO Explicaciones de los metodos correctas

public class Associacio {
    private final int MAXMEMBRES = 100;
    private String nomAsociacio = null;
    private String correuAsociacio = null;

    private Membres[] llistaM;
    private Membres[] llistaA;
    private int nMembresM;
    private int nMembresA;

    private String[] carreras = {"GEB", "GEI", "GESST", "BioGEI", "DG GEB-GESST", "extern-ETSE"};
    private String carrera = null;

    
    private String president;
    private String secretari;
    private String tresorer;

    public Associacio (String nom, String correu, String carrera){
        nomAsociacio = nom;
        correuAsociacio = correu;
        nMembresM = 0;
        president = null;
        secretari = null;
        tresorer = null;
        llistaM = new Membres[MAXMEMBRES];
        llistaA = new Membres[MAXMEMBRES];
        int i = 0;
        boolean trobat = false;
        while(i < carreras.length && !trobat){
            if(carrera.equalsIgnoreCase(carreras[i])){
                this.carrera = carrera;
                trobat = true;
            }
            i++;
        }
        if(!trobat){
            String aux = ", posibles carreres: ";
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
        return "\nAsociacio: \n\tNom: " + nomAsociacio + "\n\tCorreu: " + correuAsociacio + "\n\tCarrera: " + carrera;
    }

    public String getNomAsociacio() {
        return nomAsociacio;
    }

    public String getCorreuAsociacio() {
        return correuAsociacio;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getPresident(){
        return president;
    }

    public String getSecretari(){
        return secretari;
    }

    public String getTresorer(){
        return tresorer;
    }

    // Llista membres generica
    public void assignarMembresALlistaM(Membres membre){
        boolean miembroYaExiste = false;
        int i = 0;
        while(i < llistaA.length && miembroYaExiste == false){
            if(llistaM[i].getAlias().equalsIgnoreCase(membre.getAlias())){
                miembroYaExiste = true;
            }
            else{
                i++;
            }
        }
        if(!miembroYaExiste){
            llistaM[nMembresM] = membre;
        }
        nMembresM++;
    }

    // Llista membres alumne
    public void assignarMembresALlistaA(Membres membre){
        boolean miembroYaExiste = false;
        int i = 0;
        while(i < llistaA.length && miembroYaExiste == false){
            if(llistaA[i].getAlias().equalsIgnoreCase(membre.getAlias()) && membre instanceof Alumnes){
                miembroYaExiste = true;
            }
            else{
                i++;
            }
        }
        if(!miembroYaExiste && membre instanceof Alumnes){
            llistaM[nMembresA] = membre;
        }
        nMembresA++;
    }
    
    /*TODO Añadir que el presidente solo pueda añadirse si es alumno*/

    public void assignarPresident(String nom){
        boolean trobat = false;
        int i = 0;
        while(i < llistaA.length && trobat == false){
            if(llistaA[i].getAlias().equalsIgnoreCase(nom)){
                trobat = true;
                president = nom;
            }
            else{
                i++;
            }
        }
    }
    
    /*TODO Añadir que el secretari solo pueda añadirse si es alumno*/

    public void assignarSecretari(String nom){
        boolean trobat = false;
        int i = 0;
        while(i < llistaA.length && trobat == false){
            if(llistaA[i].getAlias().equalsIgnoreCase(nom)){
                trobat = true;
                secretari = nom;
            }
            else{
                i++;
            }
        }
    }

    /*TODO Añadir que el tresorer solo pueda añadirse si es alumno*/

    public void assignarTresorer(String nom){
        boolean trobat = false;
        int i = 0;
        while(i < llistaA.length && trobat == false){
            if(llistaA[i].getAlias().equalsIgnoreCase(nom)){
                trobat = true;
                tresorer = nom;
            }
            else{
                i++;
            }
        }
    }
}

