package aplicacio;

import java.io.IOException;
import java.util.Scanner;

import Utilitats.Data;
import dades.Accions.*;

import dades.Associacions.Associacio;
import dades.Associacions.LlistaAssociacions;
import dades.Excepcions.AccioJaExisteix;
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
    static LlistaAssociacions llistaAssociacions = new LlistaAssociacions(50);
    static LlistaMembres llistaMembres = new LlistaMembres("General", 100);
    private static LlistaAccions llistaAccions = new LlistaAccions();

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

        try {
            // Comprovar si els fitxers existeixen i crear-los si no és així
            java.io.File fileAssociacions = new java.io.File(fitxerAssociacions);
            if (!fileAssociacions.exists()) {
                System.out.println("El fitxer d'associacions no existeix. Creant un nou fitxer...");
                LlistaAssociacions associacionsInicials = new LlistaAssociacions(50);
                GestorPersistencia.guardarAssociacions(fitxerAssociacions, associacionsInicials);
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
            GestorPersistencia.carregarDades(fitxerAssociacions, fitxerMembres, fitxerAccions, llistaAssociacions, llistaMembres, llistaAccions);

            // Executar el menú principal
            menuPrincipal();

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
    public static void menuPrincipal() {
        int opcio = 0;

        do {
            try {
                mostraMenu();
                System.out.print("Selecciona una opció: ");
                opcio = Integer.parseInt(teclat.nextLine());

                // Gestiona l'opció seleccionada amb un switch
                switch (opcio) {
                    case 1:
                        opcio1(); // Mostrar les dades de la llista d'associacions
                        break;
                    case 2:
                        opcio2(); // Mostrar les dades de membres d'una associació
                        break;
                    case 3:
                        opcio3(); // Mostrar membres actius
                        break;
                    case 4:
                        opcio4(); // Mostrar la llista d'accions
                        break;
                    case 5:
                        opcio5(); // Mostrar accions d'una associació concreta
                        break;
                    case 6:
                        opcio6(); // Mostrar xerrades en una franja de dates
                        break;
                    case 7:
                        opcio7(); // Afegir una nova associació
                        break;
                    case 8:
                        opcio8(); // Alta d'un membre a una associació
                        break;
                    case 9:
                        opcio9(); // Afegir una nova xerrada
                        break;
                    case 10:
                        opcio10(); // Afegir una nova demostració
                        break;
                    case 11:
                        opcio11(); // Consultar demostracions no actives i calcular el cost total
                        break;
                    case 12:
                        opcio12(); // Calcular la persona més activa
                        break;
                    case 13:
                        opcio13(); // Consultar xerrades amb més assistents que un nombre determinat
                        break;
                    case 14:
                        opcio14(); // Valorar una xerrada
                        break;
                    case 15:
                        opcio15(); // Consultar la xerrada millor valorada
                        break;
                    case 16:
                        opcio16(); // Mostrar les xerrades d'una persona concreta
                        break;
                    case 17:
                        opcio17(); // Donar de baixa demostracions no actives
                        break;
                    case 18://S'ortir
                        sortirAplicacio();
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
    public static void opcio1() {
        System.out.println("1. Mostrar les dades de la llista d'associacions");
        // TODO: Implementar funcionalitat
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
    public static void opcio2() {
        System.out.println("2. Mostrar les dades de la llista de membres d'una associació (filtre: professors, alumnes o ambdós)");
        try {
            System.out.print("Introdueix el nom de l'associació: ");
            String nomAssociacio = teclat.nextLine();

            Associacio associacio = llistaAssociacions.buscarAssociacio(nomAssociacio);
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
    public static void opcio3() {
        System.out.println("3. Mostrar les dades de la llista de membres actius (filtre: professors, alumnes o ambdós)");
        try {
            // Seleccionar filtro
            System.out.println("Selecciona un filtre:");
            System.out.println("1. Professors");
            System.out.println("2. Alumnes");
            System.out.println("3. Ambdós");
            System.out.print("Opció: ");
            int filtre = Integer.parseInt(teclat.nextLine());
    
            // Validar el filtro
            if (filtre < 1 || filtre > 3) {
                System.out.println("Opció no vàlida. Selecciona 1, 2 o 3.");
                return;
            }
    
            // Recorrer todas las asociaciones
            boolean membresTrobats = false;
            System.out.println("\nMembres actius segons el filtre seleccionat:");
    
            for (int i = 0; i < llistaAssociacions.getNumAssociacions(); i++) {
                Associacio associacio = llistaAssociacions.copia()[i];
                Membres[] membres = associacio.getLlistaMembres();
    
                for (Membres membre : membres) {
                    if (membre == null) continue; // Saltar si el miembro es nulo
                    if (membre.getDataBaixa() != null) continue; // Saltar si el miembro está inactivo
    
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
    
            // Mensaje si no se encontraron miembros según el filtro
            if (!membresTrobats) {
                System.out.println("No s'han trobat membres actius amb aquest filtre.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Introdueix un número vàlid per al filtre.");
        } catch (Exception e) {
            System.err.println("S'ha produït un error inesperat: " + e.getMessage());
        }
    }

    public static void opcio4() { 
        System.out.println("4. Mostrar les dades de la llista d'accions (amb filtre opcional)");
        Accio[] accions = llistaAccions.getAccions();
        for (Accio accio : accions) {
            System.out.println(accio);
        }
    }

    public static void opcio5() {
        System.out.println("5. Mostrar la llista d'accions d'una associació concreta");
        // TODO: Implementar funcionalitat
    }

    public static void opcio6() {
        System.out.println("6. Mostrar les xerrades en una franja de dates");
        // TODO: Implementar funcionalitat
    }

    public static void opcio7() {//no
        System.out.println("7. Afegir una nova associació");
        // TODO: Implementar funcionalitat
    }

    public static void opcio8() {//no
        System.out.println("8. Alta d'un membre a una associació");
        // TODO: Implementar funcionalitat
    }

    public static void opcio9() {
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
            llistaAccions.afegirAccio(novaXerrada);
            System.out.println("Xerrada afegida correctament.");
            String fitxerAccions = "accions.txt";
            System.out.println("Guardant la llista d'accions en " + fitxerAccions);
            llistaAccions.guardarAccions(fitxerAccions);
            System.out.println("Llista d'accions guardada en " + fitxerAccions);
        } catch (AccioJaExisteix e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error guardant les accions: " + e.getMessage());
        }
    }

    public static void opcio10() {
        System.out.println("10. Afegir una nova demostració");
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
            llistaAccions.afegirAccio(novaDemostracio);
            System.out.println("Demostració ha sigut afegida correctament.");

            // Guardar la llista d'accions en el fitxer accions.txt
            String fitxerAccions = "accions.txt";
            System.out.println("Guardant la llista d'accions en " + fitxerAccions);
            llistaAccions.guardarAccions(fitxerAccions);
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

    public static void opcio12() {
        System.out.println("12. Calcular la persona més activa");

        // Comprovar si hi ha membres a la llista
        if (llistaMembres.numMembres() == 0) {
            System.out.println("No hi ha membres a la llista.");
            return;
        }

        Membres membreMesActiu = null; // Per guardar el membre amb més participacions
        int maxParticipacions = -1;    // Per guardar el màxim de participacions trobat

        // Recorrem la llista interna
        for (int i = 0; i < llistaMembres.numMembres(); i++) {
            Membres membreActual = llistaMembres.copia()[i]; // Utilitzem el mètode copia per accedir al membre

            // Comprovar si el membre actual és més actiu
            if (membreActual != null && membreActual.getParticipacions() > maxParticipacions) {
                membreMesActiu = membreActual; // Ac  tualitzem el membre més actiu
                maxParticipacions = membreActual.getParticipacions();
            }
        }

        // Mostrem el membre més actiu
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

    public static void opcio14() {
        System.out.println("14. Valorar una xerrada");
        // TODO: Implementar funcionalitat
    }

    public static void opcio15() {
        System.out.println("15. Consultar la xerrada millor valorada");
        Xerrada millorXerrada = null;
        int millorValoracio = 0;

        Accio[] accions = llistaAccions.getAccions();
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

    public static void opcio16() {
        System.out.println("16. Mostrar les xerrades d'una persona concreta");
        // TODO: Implementar funcionalitat
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
    public static void sortirAplicacio() {
        System.out.print("Desitja guardar els canvis abans de sortir? (y/n): ");
        String resposta = teclat.nextLine();
        if (resposta.equalsIgnoreCase("y")) {
            try {
                GestorPersistencia.guardarDades("associacions.dat", "membres.txt", "accions.txt", llistaAssociacions, llistaMembres, llistaAccions);
                System.out.println("Dades guardades correctament.");
            } catch (IOException e) {
                System.err.println("Error guardant les dades: " + e.getMessage());
            }
        }
        System.out.println("Sortint de l'aplicació.");
    }
}
