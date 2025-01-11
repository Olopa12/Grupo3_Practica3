package dades.Associacions;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe LlistaAssociacions que representa una llista de associacions.
 * Permet afegir-ne noves associacions i crear un fitxer serialitzat on
 * guardarem la llista i d'on la podrem llegir de volta amb una trucada al metode.
 * 
 * @author Alex Radu
 * @version 1.0
 */
public class LlistaAssociacions {
    private Associacio[] llista;
    private int numAssociacions;
    
    /**
     * Constructor de la clase LlistaAssociacions.
     * @param mida Mida de la taula de associacions.
     * @author Alex Radu
     */
    public LlistaAssociacions(int mida){
        llista = new Associacio[mida];
        numAssociacions = 0;
    }

    @Override
    public String toString() {
        String aux = "";
        for(int i = 0; i < numAssociacions; i++){
            aux += "\n" + llista[i].toString();
        }
        return aux;
    }

    public int getNumAssociacions(){
        return numAssociacions;
    }

    public Associacio[] copia(){
        return llista;
    }

    /**
     * Busca la associacio amb el nom introduit en la llista d'associacions i la retorna.
     * @param nomAssociacio Nom de la associacio en questio que es busca.
     * @return Associacio encontrada, o null si no existeix.
     * @author Alex Radu
     */
    public Associacio buscarAssociacio(String nomAssociacio){
        int i = 0;
        boolean trobat = false;
        Associacio a = null;
        while(i < numAssociacions && !trobat){
            if(llista[i].getNomAsociacio().equalsIgnoreCase(nomAssociacio)){
                a = llista[i];
                trobat = true;
            }
            i++;
        }
        return a;
    }

    /**
     * Comprova si existeix una associacio concreta passant el nom de l'associacio com a parametre.
     * @param nomAssociacio Nom de la associacio en questio.
     * @return True o False de si la associacio esta en la llista de associacions.
     * @author Alex Radu
     */
    public boolean existeixAssociacio(String nomAssociacio){
        int i = 0;
        boolean trobat = false;
        while(i < numAssociacions && !trobat){
            if(llista[i].getNomAsociacio().equalsIgnoreCase(nomAssociacio)){
                trobat = true;
            }
            i++;
        }
        return trobat;
    }

    
    public Associacio associacioEnPossicioDeLlista(int i){
        return llista[i];
    }

    /**
     * Metode que afegix una associacio a la llista de associacions.
     * @param a Associacio que s'afegix.
     * @throws ArrayIndexOutOfBoundsException Si no hi ha espai suficient a la llista per afegir la nova associacio.
     * @author Alex Radu
     */
    public void afegirAssociacio(Associacio a){
        try{
            int i = 0;
            boolean trobat = false;
            while(i < numAssociacions && !trobat){
                // Comprueba si ya existe esta associacio en la llista.
                if(llista[i] != null && a.getNomAsociacio().equalsIgnoreCase(llista[i].getNomAsociacio()) && a.getCorreuAsociacio().equalsIgnoreCase(llista[i].getCorreuAsociacio())){
                    trobat = true;
                }
                i++;
            }
            if(!trobat){
                llista[numAssociacions] = a;
                numAssociacions++;
            }
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("\nNo hay espacio suficiente en la lista");
        }
        
    }

    /**
     * Metode que llegeix el fitxer binari i el deserialitza i almaçena l'informacio.
     * @param nomFitxer Nom del fitxer que estem llegint.
     * @throws IOException Si es produeix un error en llegir o escriure al fitxer.
     * @throws ClassNotFoundException Si no es pot trobar la classe per deserialitzar l'objecte.
     * @author Alex Radu
     */
    public void llegirFitxerBinari(String nomFitxer) throws IOException {
        ObjectInputStream fitxer = null;
        try {
            fitxer = new ObjectInputStream(new FileInputStream(nomFitxer));
        } catch (FileNotFoundException e) {
            System.out.println("\nArchivo " + nomFitxer + " no encontrado");
        }

        boolean totLlegit = false;

        while (!totLlegit) {
            try {
                Associacio as = (Associacio) fitxer.readObject();
                afegirAssociacio(as);
            } catch (EOFException e) {
                totLlegit=true;
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            } 
        }
        fitxer.close();
    }



    /**
     * Crea un fitxer binari que enmagatzema les associacions mediant serialitzacio.
     * @param fitxerOut El nom del fitxer binari creat.
     * @throws IOException Salta si es produeix un error durant la creació del fitxer / l'escriptura d'aquest.
     * @author Alex Radu
     */
    public void crearFitxerBinari(String fitxerOut) throws IOException {
        ObjectOutputStream fSort = new ObjectOutputStream(new FileOutputStream((fitxerOut)));
        for(int i = 0; i < numAssociacions; i++){
            fSort.writeObject(llista[i]);
        }
        fSort.close();
    }
}