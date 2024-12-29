package dades.Associacions;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import dades.Associacions.Associacio;
import dades.Membres.Membres;

public class LlistaAssociacions {
    private Associacio[] llista;
    private int numAssociacions;
    
    public LlistaAssociacions(int mida){
        llista = new Associacio[mida];
        numAssociacions = 0;
    }

    public void afegirAssociacio(Associacio a){
        int i = 0;
        boolean trobat = false;
        while(i < numAssociacions && !trobat){
            // Comprueba si ya existe esta associacio en la llista.
            if(a.getNomAsociacio().equalsIgnoreCase(llista[i].getNomAsociacio()) && a.getCorreuAsociacio().equalsIgnoreCase(llista[i].getCorreuAsociacio())){
                trobat = true;
            } else{
                // Si no esta la añade
                llista[i] = a;
            }
        }
    }

    private void llegirBufferedReader(String nomFitxer) throws IOException {
        BufferedReader fitxer = new BufferedReader(new FileReader(nomFitxer));

        String nomAssociacio, correuAssociacio, carrera, president, secretari, tresorer;
        String linia;
        Scanner trossos;

        linia = fitxer.readLine();
        while (linia != null) {
            trossos = new Scanner(linia);
            trossos.useDelimiter(";");
            trossos.useLocale(Locale.ENGLISH);
            nomAssociacio = trossos.next();
            correuAssociacio = trossos.next();
            carrera = trossos.next();
            president = trossos.next();
            secretari = trossos.next();
            tresorer = trossos.next();

            Associacio l = new Associacio(nomAssociacio, correuAssociacio, carrera, president, secretari, tresorer);
            llista[numAssociacions] = l;
            
            System.out.println("BufferedReader " + nomAssociacio + ", " + correuAssociacio + ", " + carrera + ", " + president + ", " + secretari + ", " + tresorer);
            
            numAssociacions++;

            linia = fitxer.readLine();
        }

        fitxer.close();
    }

    private void escriureBufferReader(String nomFitxer) throws IOException {
        BufferedWriter fSort = new BufferedWriter((new FileWriter("LlistaAssociacions.txt", false)));

        for(int i = 0; i < numAssociacions; i++){
            fSort.write(llista[i].getNomAsociacio() + ";" + llista[i].getCorreuAsociacio() + ";" + llista[i].getCarrera() + ";" + llista[i].getPresident() + ";" + llista[i].getSecretari() + ";" + llista[i].getTresorer() + "/n");
        }

        fSort.close();

    }
}
