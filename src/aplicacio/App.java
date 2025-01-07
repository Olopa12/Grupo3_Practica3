package aplicacio;

import java.io.IOException;
import java.util.Scanner;

import Utilitats.Data;
import dades.Accions.*;

import dades.Associacions.Associacio;
import dades.Associacions.LlistaAssociacions;
import dades.Excepcions.AccioJaExisteix;
import dades.Excepcions.InstanciaNoTrobada;
import dades.Persistencia.DataManager;

import dades.Persistencia.GestorPersistencia;
import dades.Membres.*;

/**
 * Classe principal de l'aplicació que implementa un menú per gestionar associacions,
 * membres, accions, xerrades i demostracions.
 * Permet interactuar amb l'usuari a través d'una interfície de consola.
 * 
 * @author Paolo, Nermin, Sara, Alexandru
 * @version 1.0
 */
public class App {
    static Scanner teclat = new Scanner(System.in);



    /**
     * Punt d'entrada principal de l'aplicació.
     * Mostra el menú i executa les opcions seleccionades per l'usuari.
     * 
     * @param args Arguments de línia de comandes (no utilitzats).
     * @throws IOException Si es produeix un error inesperat durant l'execució.
     * @author Paolo
     */
    public static void main(String[] args) throws Exception {
        String fitxerAssociacions = "associacions.dat";
        String fitxerMembres = "membres.txt";
        String fitxerAccions = "accions.txt";
        LlistaAssociacions associacions = DataManager.getInstance().associacionsInicials;
        LlistaMembres membres = DataManager.getInstance().llistaMembres;
        LlistaAccions accions = DataManager.getInstance().llistaAccions;

        try {
            // Comprovar si els fitxers existeixen i crear-los si no és així
            java.io.File fileAssociacions = new java.io.File(fitxerAssociacions);
            if (!fileAssociacions.exists()) {
                System.out.println("El fitxer d'associacions no existeix. Creant un nou fitxer...");
                LlistaAssociacions associacionsInicials = new LlistaAssociacions(50);
                associacionsInicials.crearFitxerBinari(fitxerAssociacions);
            }

            java.io.File fileMembres = new java.io.File(fitxerMembres);
            if (!fileMembres.exists()) {
                System.out.println("El fitxer de membres no existeix. Creant un nou fitxer...");
                LlistaMembres membresInicials = new LlistaMembres("General", 100);
                GestorPersistencia.guardarMembres(fitxerMembres, membresInicials);
            }

            java.io.File fileAccions = new java.io.File(fitxerAccions);
            if (!fileAccions.exists()) {
                System.out.println("El fitxer d'accions no existeix. Creant un nou fitxer...");
                LlistaAccions accionsInicials = new LlistaAccions(50); // Constructor amb paràmetre
                GestorPersistencia.guardarAccions(fitxerAccions, accionsInicials);
            }

            // Càrrega inicial de les dades
            System.out.println("Carregant dades...");
            GestorPersistencia.carregarDades(fitxerAssociacions, fitxerMembres, fitxerAccions, associacions, membres, accions);

            // Executar el menú principal
            menuPrincipal(fitxerAssociacions, associacions, membres, accions);

        } catch (IOException e) {
            System.err.println("Error carregant o creant els fitxers inicials: " + e.getMessage());
        } finally {
            teclat.close();
        }
    }

    /**
     * Mostra el menú i executa les opcions seleccionades per l'usuari.
     * 
     * @throws Exception Si es produeix un error inesperat durant l'execució.
     * @throws NumberFormatException Si l'usuari introdueix un valor no vàlid per a una opció numèrica.
     * @author Paolo
     */
    public static void menuPrincipal(String fitxerAssociacions, LlistaAssociacions associacions, LlistaMembres membres, LlistaAccions accions) {
        int opcio = 0;

        do {
            try {
                mostraMenu();
                System.out.print("Selecciona una opció: ");
                opcio = Integer.parseInt(teclat.nextLine());

                // Gestiona l'opció seleccionada amb un switch
                switch (opcio) {
                    case 1:
                        opcio1(associacions, fitxerAssociacions); // Mostrar les dades de la llista d'associacions
                        break;
                    case 2:
                        opcio2(associacions); // Mostrar les dades de membres d'una associació
                        break;
                    case 3:
                        opcio3(associacions); // Mostrar membres actius
                        break;
                    case 4:
                        opcio4(accions); // Mostrar la llista d'accions
                        break;
                    case 5:
                        opcio5(accions); // Mostrar accions d'una associació concreta
                        break;
                    case 6:
                        opcio6(); // Mostrar xerrades en una franja de dates
                        break;
                    case 7:
                        opcio7(associacions, fitxerAssociacions); // Afegir una nova associació
                        break;
                    case 8:
                        opcio8(associacions, fitxerAssociacions); // Alta d'un membre a una associació
                        break;
                    case 9:
                        opcio9(accions); // Afegir una nova xerrada
                        break;
                    case 10:
                        opcio10(accions); // Afegir una nova demostració
                        break;
                    case 11:
                        opcio11(); // Consultar demostracions no actives i calcular el cost total
                        break;
                    case 12:
                        opcio12(membres); // Calcular la persona més activa
                        break;
                    case 13:
                        opcio13(); // Consultar xerrades amb més assistents que un nombre determinat
                        break;
                    case 14:
                        opcio14(); // Valorar una xerrada
                        break;
                    case 15:
                        opcio15(accions); // Consultar la xerrada millor valorada
                        break;
                    case 16:
                        opcio16(accions); // Mostrar les xerrades d'una persona concreta
                        break;
                    case 17:
                        opcio17(); // Donar de baixa demostracions no actives
                        break;
                    case 18://S'ortir
                        sortirAplicacio(associacions, membres, accions);
                        break;
                    default:
                        System.out.println("Opció no vàlida. Si us plau, tria una opció entre 1 i 18.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Per favor intodueix un número valgut per a l'opció del menu");
            } catch (Exception e) {
                System.err.println("S'ha produït un error inesperat: " + e.getMessage());
            }
        } while (opcio != 18);
    }

    /**
     * Mostra el menú principal de l'aplicació amb totes les opcions disponibles.
     * 
     * @author Paolo
     */
    public static void mostraMenu(){
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Mostrar les dades de la llista d'associacions");
        System.out.println("2. Mostrar les dades de la llista de membres d'una associació (filtre: professors, alumnes o ambdós)");
        System.out.println("3. Mostrar les dades de la llista de membres actius (filtre: professors, alumnes o ambdós)");
        System.out.println("4. Mostrar les dades de la llista d'accions (amb filtre opcional)");
        System.out.println("5. Mostrar la llista d'accions d'una associació concreta");
        System.out.println("6. Mostrar les xerrades en una franja de dates");
        System.out.println("7. Afegir una nova associació");
        System.out.println("8. Alta d'un membre a una associació");
        System.out.println("9. Afegir una nova xerrada");
        System.out.println("10. Afegir una nova demostració");
        System.out.println("11. Consultar demostracions no actives i calcular cost total");
        System.out.println("12. Calcular la persona més activa");
        System.out.println("13. Consultar xerrades amb més d'un cert nombre d'assistents");
        System.out.println("14. Valorar una xerrada");
        System.out.println("15. Consultar la xerrada millor valorada");
        System.out.println("16. Mostrar les xerrades d'una persona concreta");
        System.out.println("17. Donar de baixa demostracions no actives dissenyades abans d'una data");
        System.out.println("18. Sortir de l'aplicació");
    }

    // Funciones de las opciones
    /**
     * Mostra les dades de la llista de associacions amb el toString().
     * @param associacionsInicials Llista de associacions.
     * @param fitxerAssociacions Fitxer del que es llegix la informacio de les llistes ed'associacions.
     * @author Alex Radu
     */
    public static void opcio1(LlistaAssociacions associacionsInicials, String fitxerAssociacions) {
        System.out.println("1. Mostrar les dades de la llista d'associacions");
        try {
            associacionsInicials.llegirFitxerBinari(fitxerAssociacions);
            System.out.println(associacionsInicials);
        } catch (Exception e) {
            System.out.println("Error carregant el fitxer binari...");
        }
    }


    /**
     * Mostra les dades dels membres d'una associació aplicant un filtre específic (professors, alumnes o ambdós).
     * 
     * Aquest mètode permet a l'usuari introduir el nom d'una associació, seleccionar un filtre 
     * i mostrar els membres que compleixin el criteri seleccionat.
     * 
     * @throws NumberFormatException Si l'usuari introdueix un valor no numèric per al filtre.
     * @throws Exception Si es produeix un error inesperat durant l'execució.
     * @author Paolo
     */
    public static void opcio2(LlistaAssociacions associacions) {
        System.out.println("2. Mostrar les dades de la llista de membres d'una associació (filtre: professors, alumnes o ambdós)");
        try {
            System.out.print("Introdueix el nom de l'associació: ");
            String nomAssociacio = teclat.nextLine();

            Associacio associacio = associacions.buscarAssociacio(nomAssociacio);
            if (associacio == null) {
                System.out.println("No s'ha trobat cap associació amb el nom proporcionat.");
                return;
            }

            System.out.println("Selecciona un filtre:");
            System.out.println("1. Professors");
            System.out.println("2. Alumnes");
            System.out.println("3. Ambdós");
            System.out.print("Opció: ");
            int filtre = Integer.parseInt(teclat.nextLine());

            Membres[] membres = associacio.getLlistaMembres();
            boolean membresTrobats = false;

            System.out.println("\nMembres de l'associació: " + nomAssociacio);
            switch (filtre) {
                case 1: // Professors
                    for (Membres membre : membres) {
                        if (membre instanceof Professors) {
                            System.out.println(membre);
                            membresTrobats = true;
                        }
                    }
                    break;

                case 2: // Alumnes
                    for (Membres membre : membres) {
                        if (membre instanceof Alumnes) {
                            System.out.println(membre);
                            membresTrobats = true;
                        }
                    }
                    break;

                case 3: // Ambdós
                    for (Membres membre : membres) {
                        if (membre != null) {
                            System.out.println(membre);
                            membresTrobats = true;
                        }
                    }
                    break;

                default:
                    System.out.println("Opció no vàlida. Si us plau, selecciona 1, 2 o 3.");
            }

            if (!membresTrobats) {
                System.out.println("No hi ha membres amb aquest filtre a l'associació.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Has d'introduir un número vàlid per al filtre.");
        } catch (Exception e) {
            System.err.println("S'ha produït un error inesperat: " + e.getMessage());
        }
    }

    /**
     * Mostra les dades dels membres actius de totes les associacions segons un filtre determinat.
     * El filtre permet seleccionar entre professors, alumnes o ambdós tipus de membres.
     * 
     * @throws NumberFormatException Si l'usuari introdueix una entrada que no és un número vàlid.
     * @throws Exception Si es produeix un error inesperat durant l'execució.
     * @author Paolo
     */
    public static void opcio3(LlistaAssociacions associacions) {
        System.out.println("3. Mostrar les dades de la llista de membres actius (filtre: professors, alumnes o ambdós)");
        try {
            System.out.println("Selecciona un filtre:");
            System.out.println("1. Professors");
            System.out.println("2. Alumnes");
            System.out.println("3. Ambdós");
            System.out.print("Opció: ");
            int filtre = Integer.parseInt(teclat.nextLine());
    
            if (filtre < 1 || filtre > 3) {
                System.out.println("Opció no vàlida. Selecciona 1, 2 o 3.");
                return;
            }
    
            boolean membresTrobats = false;
            System.out.println("\nMembres actius segons el filtre seleccionat:");
    
            for (int i = 0; i < associacions.getNumAssociacions(); i++) {
                Associacio associacio = associacions.copia()[i];
                Membres[] membres = associacio.getLlistaMembres();
    
                for (Membres membre : membres) {
                    if (membre != null && membre.getDataBaixa() == null) {
                        switch (filtre) {
                            case 1: // Professors
                                if (membre instanceof Professors) {
                                    System.out.println(membre);
                                    membresTrobats = true;
                                }
                                break;
                            case 2: // Alumnes
                                if (membre instanceof Alumnes) {
                                    System.out.println(membre);
                                    membresTrobats = true;
                                }
                                break;
                            case 3: // Ambdós
                                System.out.println(membre);
                                membresTrobats = true;
                                break;
                        }
                    }
                }
            }
    
            if (!membresTrobats) {
                System.out.println("No s'han trobat membres actius amb aquest filtre.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Introdueix un número vàlid per al filtre.");
        } catch (Exception e) {
            System.err.println("S'ha produït un error inesperat: " + e.getMessage());
        }
    }
    
    /**
     * Opció 4: Mostrar les dades de la llista d'accions (amb filtre opcional).
     * 
     * Aquest mètode mostra totes les accions disponibles en la llista.
     * 
     * @author Sara, Nermin
     */

    public static void opcio4(LlistaAccions acciones) { 
        System.out.println("4. Mostrar les dades de la llista d'accions (amb filtre opcional)");
        Accio[] accions = acciones.getAccions();
        for (Accio accio : accions) {
            System.out.println(accio);
        }
    }
    
    /**
     * Opció 5: Buscar accions per nom de l'associació.
     * Aquest mètode permet a l'usuari introduir el nom d'una associació i mostrar les accions corresponents 
     * @author Sara, Nermin
     * 
     */
    public static void opcio5(LlistaAccions acciones) {
        System.out.print("Introdueixi el nom de l'associació que desitges trobar: ");
        Accio[] accions = acciones.getAccions();
        String nomAssociacio = teclat.nextLine();
        boolean trobat = false;
        

        System.out.println("Accions corresponents a l'associació " + nomAssociacio + ":");
        for (Accio accio : accions) {
            if (accio.getAssociacio().getNomAsociacio().equalsIgnoreCase(nomAssociacio)) {
                System.out.println(accio);
                trobat = true;
            }
        }

        if (!trobat) {
            System.out.println("No s'ha trobat cap acció per a l'associació " + nomAssociacio);
        }
    }
    
     /**
     * Opció 6: Mostrar les xerrades en una franja de dates.
     * Aquest mètode permet a l'usuari introduir una franja de dates en el format dd-mm-yyyy i mostrar les
     * xerrades que es troben dins d'aquest període.
     * @author Sara, Nermin
     */
    
    public static void opcio6() {
        System.out.println("6. Mostrar les xerrades en una franja de dates");
       System.out.print("Introdueix la data d'inici (format dd-mm-yyyy): ");
        String dataIniciStr = teclat.nextLine();
        Data dataInici = Data.parseData(dataIniciStr);

 
        System.out.print("Introdueix la data de finalització (format dd-mm-yyyy): ");
        String dataFinalStr = teclat.nextLine();
        Data dataFinal = Data.parseData(dataFinalStr);


        LlistaAccions llistaAccions = new LlistaAccions();
        String fitxerAccions = "accions.txt";
        try {
            llistaAccions.carregarAccions(fitxerAccions);
            System.out.println("Accions carregades correctament des de: " + fitxerAccions);


            Accio[] xerradesFiltrades = new Accio[100]; 
            int contador = 0;

            for (Accio accio : llistaAccions.getAccions()) {
                if (accio instanceof Xerrada) {
                    Xerrada xerrada = (Xerrada) accio;
                    Data dataXerrada = xerrada.getData();


                    if (dataXerrada.esAnterior(dataFinal) && !dataXerrada.esAnterior(dataInici)) {
                        xerradesFiltrades[contador] = xerrada;
                        contador++;
                    }
                }
            }


            if (contador > 0) {
                System.out.println("Xerrades entre " + dataInici + " i " + dataFinal + ":");
                for (int i = 0; i < contador; i++) {
                    System.out.println(xerradesFiltrades[i]); 
                                                              
                }
            } else {
                System.out.println("No s'han trobat xerrades en aquesta franja de dates.");
            }

        } catch (IOException e) {
            System.err.println("Error carregant les accions des del fitxer: " + e.getMessage());
        }
    }

    /**
     * Permet afegir una associacio a la llista de associacions pedint al usuaris la informació.
     * @param associacionsInicials Llista de associacions.
     * @author Alex Radu
     */
    public static void opcio7(LlistaAssociacions associacionsInicials, String fitxerAssociacions) {
        System.out.println("7. Afegir una nova associació");
        System.out.println("\nIntrodueix les seguents dades:");
        System.out.println("\n\tNom associacio: ");
        String nom = teclat.next();
        System.out.println("\n\tCorreu associacio: ");
        String correu = teclat.next();
        System.out.println("\n\tCarrera: ");
        String carrera = teclat.next();
        System.out.println("\nVols afegir directament president, tresorer i secretari, si si hauras d'introduir les dades dels tres membres per separat. (Si/No)");
        String resposta = teclat.next();
        if(resposta.equalsIgnoreCase("Si")){
            System.out.println("\nDades president: ");
            System.out.println("\n\tNom: ");
            String nomPresident = teclat.next();
            System.out.println("\n\tCorreu: ");
            String correuPresident = teclat.next();
            System.out.println("\n\tDia: ");            
            int diaPresident = teclat.nextInt();
            System.out.println("\n\tMes: ");
            int mesPresident = teclat.nextInt();
            System.out.println("\n\tAny: ");
            int anyPresident = teclat.nextInt();
            System.out.println("\n\tEnsenyament de l'alumne: ");            
            String ensenyamentPresident = teclat.next();
            System.out.println("\n\tNombre d'anys en l'ETSE: ");
            int antiguitatPresident = teclat.nextInt();
            System.out.println("\n\tEsta graduat? (Si/No) ");
            String graduatStringPresident = teclat.next();
            boolean graduatPresident = false;
            if(graduatStringPresident.equalsIgnoreCase("Si")){
                graduatPresident = true;
            }
            System.out.println("\n\nDades secretari: ");
            System.out.println("\n\tNom: ");
            String nomSecretari = teclat.next();
            System.out.println("\n\tCorreu: ");
            String correuSecretari = teclat.next();
            System.out.println("\n\tDia: ");            
            int diaSecretari = teclat.nextInt();
            System.out.println("\n\tMes: ");
            int mesSecretari = teclat.nextInt();
            System.out.println("\n\tAny: ");
            int anySecretari = teclat.nextInt();
            System.out.println("\n\tEnsenyament de l'alumne: ");            
            String ensenyamentSecretari = teclat.next();
            System.out.println("\n\tNombre d'anys en l'ETSE: ");
            int antiguitatSecretari = teclat.nextInt();
            System.out.println("\n\tEsta graduat? (Si/No) ");
            String graduatStringSecretari = teclat.next();
            boolean graduatSecretari = false;
            if(graduatStringSecretari.equalsIgnoreCase("Si")){
                graduatSecretari = true;
            }
            System.out.println("\n\nDades tresorer: ");
            System.out.println("\n\tNom: ");
            String nomTresorer = teclat.next();
            System.out.println("\n\tCorreu: ");
            String correuTresorer = teclat.next();
            System.out.println("\n\tDia: ");            
            int diaTresorer = teclat.nextInt();
            System.out.println("\n\tMes: ");
            int mesTresorer = teclat.nextInt();
            System.out.println("\n\tAny: ");
            int anyTresorer = teclat.nextInt();
            System.out.println("\n\tEnsenyament de l'alumne: ");            
            String ensenyamentTresorer = teclat.next();
            System.out.println("\n\tNombre d'anys en l'ETSE: ");
            int antiguitatTresorer= teclat.nextInt();
            System.out.println("\n\tEsta graduat? (Si/No) ");
            String graduatStringTresorer = teclat.next();
            boolean graduatTresorer = false;
            if(graduatStringTresorer.equalsIgnoreCase("Si")){
                graduatTresorer = true;
            }
            Alumnes president = new Alumnes(nomPresident, correuPresident, new Data(diaPresident, mesPresident, anyPresident), ensenyamentPresident, antiguitatPresident, graduatPresident);
            Alumnes secretari = new Alumnes(nomSecretari, correuSecretari, new Data(diaSecretari, mesSecretari, anySecretari), ensenyamentSecretari, antiguitatSecretari, graduatSecretari);
            Alumnes tresorer = new Alumnes(nomTresorer, correuTresorer, new Data(diaTresorer, mesTresorer, anyTresorer), ensenyamentTresorer, antiguitatTresorer, graduatTresorer);
            Associacio a = new Associacio(nom, correu, carrera, president, secretari, tresorer);
            associacionsInicials.afegirAssociacio(a);
            try {
                associacionsInicials.crearFitxerBinari(fitxerAssociacions);
            } catch (Exception e) {
                System.out.println("Error creant el fitxer binari...");
            }
        } else{
            Associacio a = new Associacio(nom, correu, carrera);
            associacionsInicials.afegirAssociacio(a);
            try {
                associacionsInicials.crearFitxerBinari(fitxerAssociacions);
            } catch (Exception e) {
                System.out.println("Error creant el fitxer binari...");
            }
        }
    }

    /**
     * Afegix un membre a una associacio concreta.
     * @param associacionsInicials Llista d'associacions.
     * @param fitxerAssociacions Fitxer al que guardem les associacions.
     * @author Alex Radu
     */
    public static void opcio8(LlistaAssociacions associacionsInicials, String fitxerAssociacions) {
        System.out.println("8. Alta d'un membre a una associació");
        System.out.println("\nNom de l'associacio a la que afegim el membre: ");
        String nomAssociacio = teclat.next();
        int i = 0;
        boolean trobat = false;
        try{
            while(i<associacionsInicials.getNumAssociacions() && !trobat){
                if(associacionsInicials.existeixAssociacio(nomAssociacio)){
                    System.out.println("Alumne (0) o professor (1)?\nIntrodueix el numero correspondent al que es vol afegir: ");
                    int qual = teclat.nextInt();
                    if(qual == 0){
                        System.out.println("\n\tNom: ");
                        String nom = teclat.next();
                        System.out.println("\n\tCorreu: ");
                        String correu = teclat.next();
                        System.out.println("\n\tDia: ");            
                        int dia = teclat.nextInt();
                        System.out.println("\n\tMes: ");
                        int mes = teclat.nextInt();
                        System.out.println("\n\tAny: ");
                        int any = teclat.nextInt();
                        System.out.println("\n\tEnsenyament de l'alumne: ");            
                        String ensenyament = teclat.next();
                        System.out.println("\n\tNombre d'anys en l'ETSE: ");
                        int antiguitat = teclat.nextInt();
                        System.out.println("\n\tEsta graduat? (Si/No) ");
                        String graduatString = teclat.next();
                        boolean graduat = false;
                        if(graduatString.equalsIgnoreCase("Si")){
                            graduat = true;
                        }
                        Alumnes a = new Alumnes(nom, correu, new Data(dia, mes, any), ensenyament, antiguitat, graduat);
                        associacionsInicials.buscarAssociacio(nomAssociacio).assignarMembresALlistaMembres(a);
                    } else{
                        System.out.println("\n\tNom: ");
                        String nom = teclat.next();
                        System.out.println("\n\tCorreu: ");
                        String correu = teclat.next();
                        System.out.println("\n\tDia: ");            
                        int dia = teclat.nextInt();
                        System.out.println("\n\tMes: ");
                        int mes = teclat.nextInt();
                        System.out.println("\n\tAny: ");
                        int any = teclat.nextInt();
                        System.out.println("\n\t Departament del profesor: ");            
                        String departament = teclat.next();
                        System.out.println("\n\tDespatx: ");
                        String despatx = teclat.next();
                        Professors p = new Professors(nom, correu, new Data(dia, mes, any), departament, despatx);
                        associacionsInicials.buscarAssociacio(nomAssociacio).assignarMembresALlistaMembres(p);
                        associacionsInicials.crearFitxerBinari(fitxerAssociacions);
                    }

                    associacionsInicials.crearFitxerBinari(fitxerAssociacions);
                    trobat = true;
                } else{
                    throw new InstanciaNoTrobada(nomAssociacio);
                }
            }
        } catch (Exception InstanciaNoTrobada) {
            opcio8(associacionsInicials, fitxerAssociacions);
        }
        
    }
     /**
     * Opció 9: Afegir una nova xerrada.
     * Aquest mètode permet a l'usuari afegir una nova xerrada a l'arxiu d'accions.
     * @author Sara, Nermin
     */
    public static void opcio9(LlistaAccions accions) {
        System.out.println("9. Afegir una nova xerrada");
       System.out.print("Introdueix el títol de la xerrada: ");
        String titol = teclat.nextLine();
        System.out.print("Introdueix el nom de l'associació: ");
        String codiAssociacio = teclat.nextLine();
        System.out.print("Introdueix el nom del responsable: ");
        String responsable = teclat.nextLine();
        System.out.print("Introdueix la data de la xerrada (format YYYY-MM-DD): ");
        String dataStr = teclat.nextLine();
        Data data = Data.parseData(dataStr);
    
        Membres responsableMembre = new Membres(responsable, "email", new Data()) {
            @Override
            public Membres copia() {
                return new Membres(this.getAlias(), this.getCorreuElectronic(), this.getDataAlta()) {
                    @Override
                    public Membres copia() {
                        return this;
                    }
                };
            }
        };
    
        Xerrada novaXerrada = new Xerrada(titol, new Associacio(codiAssociacio, "email", "GEI"),
                responsableMembre, data);
    
        System.out.print("Introdueix el nombre de valoracions: ");
        int numValoracions = Integer.parseInt(teclat.nextLine());
        for (int i = 0; i < numValoracions; i++) {
            System.out.print("Introdueix la valoració " + (i + 1) + " (0-10): ");
            int valoracio = Integer.parseInt(teclat.nextLine());
            novaXerrada.afegirValoracio(valoracio);
        }
    
        try {
            accions.afegirAccio(novaXerrada);
            System.out.println("Xerrada afegida correctament.");
            String fitxerAccions = "accions.txt";
            System.out.println("Guardant la llista d'accions en " + fitxerAccions);
            accions.guardarAccions(fitxerAccions);
            System.out.println("Llista d'accions guardada en " + fitxerAccions);
        } catch (AccioJaExisteix e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error guardant les accions: " + e.getMessage());
        }
    }
    /**
     * Opció 10: Afegir una nova demostració.
     * Aquest mètode permet a l'usuari afegir una nova demostració a l'arxiu accions.
     * Demana les dadas per teclat
     * @author Sara, Nermin
     */
    public static void opcio10(LlistaAccions accions) {
      System.out.print("Introdueix el títol de la demostració: ");
        String titol = teclat.nextLine();
        System.out.print("Introdueix el nom de l'associació: ");
        String codiAssociacio = teclat.nextLine();
        System.out.print("Introdueix el nom del responsable: ");
        String responsable = teclat.nextLine();
        System.out.print("Introdueix la data de disseny (format YYYY-MM-DD): ");
        String dataStr = teclat.nextLine();
        String dataDisseny = dataStr;
        System.out.print("És vàlida la demostració? (true/false): ");
        boolean esValida = Boolean.parseBoolean(teclat.nextLine());
        System.out.print("Introdueix el cost dels materials: ");
        double costMaterials = Double.parseDouble(teclat.nextLine());
        Membres responsableMembre = new Membres(responsable, "email", new Data()) {
            @Override
            public Membres copia() {
                return new Membres(this.getAlias(), this.getCorreuElectronic(), this.getDataAlta()) {
                    @Override
                    public Membres copia() {
                        return this;
                    }
                };
            }
        };
    
        Demostracio novaDemostracio = new Demostracio(titol, new Associacio(codiAssociacio, "email", "GEI"),
                responsableMembre, dataDisseny, esValida, costMaterials);
    
        try {
            accions.afegirAccio(novaDemostracio);
            System.out.println("Demostració ha sigut afegida correctament.");
    
            // Guardar la llista d'accions en el fitxer accions.txt
            String fitxerAccions = "accions.txt";
            System.out.println("Guardant la llista d'accions en " + fitxerAccions);
            accions.guardarAccions(fitxerAccions);
            System.out.println("Llista d'accions guardada en " + fitxerAccions);
        } catch (AccioJaExisteix e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error guardant les accions: " + e.getMessage());
        }
    }

    public static void opcio11() {
        System.out.println("11. Consultar demostracions no actives i calcular cost total");
        // TODO: Implementar funcionalitat
    }

    /**
     * Mètode per calcular i mostrar la persona més activa (amb el màxim de participacions) de la llista de membres.
     *
     * @throws NullPointerException Si llistaMembres o els seus membres són nuls.
     * @author Paolo
     */
    public static void opcio12(LlistaMembres membres) {
        System.out.println("12. Calcular la persona més activa");

        if (membres.numMembres() == 0) {
            System.out.println("No hi ha membres a la llista.");
            return;
        }

        Membres membreMesActiu = null; 
        int maxParticipacions = -1;   

        for (int i = 0; i < membres.numMembres(); i++) {
            Membres membreActual = membres.copia()[i]; 

            if (membreActual != null && membreActual.getParticipacions() > maxParticipacions) {
                membreMesActiu = membreActual; 
                maxParticipacions = membreActual.getParticipacions();
            }
        }

        if (membreMesActiu != null) {
            System.out.println("La persona més activa és:");
            System.out.println(membreMesActiu);
            System.out.println("Participacions: " + maxParticipacions);
        } else {
            System.out.println("No hi ha membres actius.");
        }
    }

    public static void opcio13() {
        System.out.println("13. Consultar xerrades amb més d'un cert nombre d'assistents");
        // TODO: Implementar funcionalitat
    }
    /**
     * Opció 14: Valorar una xerrada.
     * Aquest mètode permet a l'usuari introduir el codi d'una xerrada i afegir una valoració.
     * @author Sara, Nermin
     */
    public static void opcio14() {
        System.out.println("14. Valorar una xerrada");
        System.out.print("Introdueix el codi de la xerrada a valorar: ");
        String codi = teclat.nextLine();

        // Buscar la xerrada per codi
        Accio accio = llistaAccions.getAccioPerCodi(codi);
        if (accio instanceof Xerrada) {
            Xerrada xerrada = (Xerrada) accio;
            System.out.print("Introdueix la valoració (1-5): ");
            int valoracio = Integer.parseInt(teclat.nextLine());
            xerrada.afegirValoracio(valoracio);
            System.out.println("Valoració afegida correctament.");

            // Guardar la llista d'accions en el fitxer accions.txt
            String fitxerAccions = "accions.txt";
            try {
                System.out.println("Guardant la llista d'accions en " + fitxerAccions);
                llistaAccions.guardarAccions(fitxerAccions);
                System.out.println("Llista d'accions guardada en " + fitxerAccions);
            } catch (IOException e) {
                System.err.println("Error guardant les accions: " + e.getMessage());
            }
        } else {
            System.out.println("Xerrada no trobada o el codi no correspon a una xerrada.");
        }
    }
    
    /**
     * Opció 15: Consultar la xerrada millor valorada.
     * Aquest mètode permet a l'usuari consultar la xerrada amb la millor valoració total.
     * @author Sara, Nermin
     */
    public static void opcio15(LlistaAccions acciones) {
        System.out.println("15. Consultar la xerrada millor valorada");
       Xerrada millorXerrada = null;
        int millorValoracio = 0;

        Accio[] accions = acciones.getAccions();
        for (Accio accio : accions) {
            if (accio instanceof Xerrada) {
                Xerrada xerrada = (Xerrada) accio;
                int valoracioTotal = xerrada.valoracionsSuma();
                if (valoracioTotal > millorValoracio) {
                    millorValoracio = valoracioTotal;
                    millorXerrada = xerrada;
                }
            }
        }

        if (millorXerrada != null) {
            System.out.println("La millor xerrada valorada és: " + millorXerrada);
        } else {
            System.out.println("No hi ha xerrades valorades.");
        }
    }

   /** 
 * Opcio 16: Buscar accions per responsable.
 * Busca les accions per responsable i mostra les dades de les accions trobades.
 * @author Sara, Nermin
 */
    public static void opcio16(LlistaAccions acciones) {
        System.out.println("16. Buscar accions per responsable");
        System.out.print("Introdueix el nom del responsable: ");
        String responsable = teclat.nextLine();

        LlistaAccions llistaAccions = new LlistaAccions();
        String fitxerAccions = "accions.txt";
        try {
            llistaAccions.carregarAccions(fitxerAccions);
            System.out.println("Accions carregades correctament des de: " + fitxerAccions);

           
            LlistaAccions.mostrarAccionsPerResponsable(llistaAccions, responsable);

        } catch (IOException e) {
            System.err.println("Error carregant les accions des del fitxer: " + e.getMessage());
        }
    }
    public static void opcio17() {
        System.out.println("17. Donar de baixa demostracions no actives dissenyades abans d'una data");
        // TODO: Implementar funcionalitat
    }
    
    /**
     * Mètode per gestionar la sortida de l'aplicació.
     * Aquest mètode ofereix l'opció de guardar els canvis abans de tancar l'aplicació.
     * Si l'usuari indica que vol guardar, les dades de les associacions i membres es guarden
     * en fitxers utilitzant el gestor de persistència.
     *
     * @throws IOException Si hi ha un error durant el procés de guardat de les dades.
     * @author Paolo
    */
    public static void sortirAplicacio(LlistaAssociacions associacions, LlistaMembres membres, LlistaAccions accions) {
        System.out.print("Desitja guardar els canvis abans de sortir? (y/n): ");
        String resposta = teclat.nextLine();
        if (resposta.equalsIgnoreCase("y")) {
            try {
                GestorPersistencia.guardarDades("associacions.dat", "membres.txt", 
                "accions.txt", associacions, membres, accions);
                System.out.println("Dades guardades correctament.");
            } catch (IOException e) {
                System.err.println("Error guardant les dades: " + e.getMessage());
            }
        }
        System.out.println("Sortint de l'aplicació.");
    }
}
