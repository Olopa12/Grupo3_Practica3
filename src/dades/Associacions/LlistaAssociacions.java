package dades.Associacions;
import dades.Associacions.Associacio;

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
            if(a.nomAssociacio.equalsIgnoreCase(llista[i].nomAssociacio) && a.correuAssociacio.equalsIgnoreCase(llista[i].correuAssociacio)){
                trobat = true;
            }

            llista[i] = a;
        }
    }
}
