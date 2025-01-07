package dades.Accions;

import dades.Excepcions.AccioJaExisteix;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import Utilitats.Data;
import dades.Associacions.Associacio;
import dades.Membres.Membres;

/**
 * Classe que representa una llista d'accions.
 * Aquesta classe gestiona una llista d'accions i les operacions relacionades.
 * Les operacions inclouen afegir, eliminar, obtenir i guardar les dades.
 * 
 * @author Nermin Tribak, Sara Tribak
 * @version 1.0
 */

public class LlistaAccions {
    private Accio[] accions;
    private int numAccions;

    /**
     * Constructor de la classe LlistaAccions.
     * Inicialitza l'array d'accions amb una mida especificada.
     * 
     * @param capacitat La mida inicial de l'array.
     */
    public LlistaAccions(int capacitat) {
        this.accions = new Accio[capacitat];
        this.numAccions = 0;
    }

    /**
     * Constructor de la classe LlistaAccions.
     * Inicialitza l'array d'accions amb una mida de 100.
     */
    public LlistaAccions() {
        this(100);
    }

    public void afegirAccio(Accio accio) throws AccioJaExisteix {
        if (accio != null) {
            for (int i = 0; i < numAccions; i++) {
                if (accions[i].getCodi().equals(accio.getCodi())) {
                    throw new AccioJaExisteix(accio.getCodi());
                }
            }
            if (numAccions < accions.length) {
                accions[numAccions++] = accio;
            }
        }
    }
    
    /**
     * Busca les accions que tenen un responsable amb el nom especificat.
     *
     * @param alias L'alias del responsable a buscar.
     * @return Un array amb les accions que tenen el responsable. 
     */
    
    public Accio[] buscarAccionsPerResponsable(String alias) {
        Accio[] accionsEncontradas = new Accio[numAccions];
        int count = 0;
        for (int i = 0; i < numAccions; i++) {
            if (accions[i].getResponsable().getAlias().equals(alias)) {
                accionsEncontradas[count++] = accions[i];
            }
        }
        Accio[] result = new Accio[count];
        System.arraycopy(accionsEncontradas, 0, result, 0, count);
        return result;
    }
    
    /**
     * Mostra les accions que tenen un responsable amb el nom especificat.
     * 
     * @param llistaAccions
     * @param responsable
     */
    public static void mostrarAccionsPerResponsable(LlistaAccions llistaAccions, String responsable) {
        Accio[] accionsPerResponsable = llistaAccions.buscarAccionsPerResponsable(responsable);
    
        if (accionsPerResponsable.length > 0) {
            System.out.println("Accions per al responsable " + responsable + ":");
            for (Accio accio : accionsPerResponsable) {
                System.out.println(accio);
            }
        } else {
            System.out.println("No s'han trobat accions per al responsable: " + responsable);
        }
    }

    /**
     * Elimina una acció de la llista pel seu codi.
     * 
     * @param codi El codi de l'acció a eliminar.
     * @return true si l'acció va ser eliminada, false en cas contrari.
     */
    public boolean eliminarAccio(String codi) {
        for (int i = 0; i < numAccions; i++) {
            if (accions[i].getCodi().equals(codi)) {
                accions[i] = accions[--numAccions];
                accions[numAccions] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Obté totes les accions de la llista.
     * 
     * @return Un array amb totes les accions.
     */
    public Accio[] getAccions() {
        Accio[] result = new Accio[numAccions];
        System.arraycopy(accions, 0, result, 0, numAccions);
        return result;
    }

    /**
     * Obté una acció pel seu codi.
     * 
     * @param codi El codi de l'acció.
     * @return L'acció corresponent al codi, null si no es troba.
     */
    public Accio getAccioPerCodi(String codi) {
        for (Accio accio : accions) {
            if (accio.getCodi().equals(codi)) {
                return accio;
            }
        }
        return null;
    }

    /**
     * Guarda les accions en un fitxer.
     * 
     * @param fitxer El nom del fitxer.
     * @throws IOException Si ocorre un error d'entrada o sortida.
     */
    public void guardarAccions(String fitxer) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxer))) {
            for (int i = 0; i < numAccions; i++) {
                writer.write(accioToString(accions[i]));
                writer.newLine();
            }
        }
    }

    /**
     * Carrega les accions des d'un fitxer.
     * 
     * @param fitxer El nom del fitxer.
     * @throws IOException Si es produeix un error d'entrada o sortida.
     */
    public void carregarAccions(String fitxer) throws IOException {
        numAccions = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                Accio accio = stringToAccio(linia);
                if (accio != null) {
                    accions[numAccions++] = accio;
                }
            }
        }
    }

    /**
     * Converteix una acció a una cadena de text.
     * 
     * @param accio L'acció a convertir.
     * @return La representació en cadena de l'acció.
     */
    public String accioToString(Accio accio) {
        if (accio instanceof Demostracio) {
            Demostracio demo = (Demostracio) accio;
            return String.join(";",
                    demo.getCodi(),
                    demo.getTitol(),
                    demo.getAssociacio().getNomAsociacio(),
                    demo.getResponsable().getAlias(),
                    demo.getDataDisseny(),
                    String.valueOf(demo.isEsValida()),
                    String.valueOf(demo.getVegadesOferida()),
                    String.valueOf(demo.getCostMaterials()));
        } else if (accio instanceof Xerrada) {
            Xerrada xerrada = (Xerrada) accio;
            return String.join(";",
                    xerrada.getCodi(),
                    xerrada.getTitol(),
                    xerrada.getAssociacio().getNomAsociacio(),
                    xerrada.getResponsable().getAlias(),
                    xerrada.getData().toString(),
                    membresToString(xerrada.getMembresImpartidors()),
                    String.valueOf(xerrada.getNumAssistents()),
                    valoracionsToString(xerrada.getValoracions()));
        } else {
            return String.join(";",
                    accio.getCodi(),
                    accio.getTitol(),
                    accio.getAssociacio().getNomAsociacio(),
                    accio.getResponsable().getAlias());
        }
    }

    /**
     * Converteix una cadena de text a una acció.
     * 
     * @param linia La cadena de text a convertir.
     * @return L'acció corresponent a la cadena de text.
     */
    public Accio stringToAccio(String linia) {
        String[] parts = linia.split(";");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Línia de dades incompleta: " + linia);
        }
        String codi = parts[0];
        String titol = parts[1];
        String nomAssociacio = parts[2];
        String responsable = parts[3];
        String titulacio = "GEI";
        Associacio associacio = new Associacio(nomAssociacio, "email_placeholder", titulacio);
        Membres membre = new Membres(responsable, "email_placeholder", new Data()) {
            @Override
            public Membres copia() {
                return this;
            }
        };

        if (parts.length == 4) {
            return new Accio(titol, associacio, membre) {
                @Override
                public String getCodi() {
                    return codi;
                }
            };
        } else if (parts.length >= 8) {
            Data data = Data.parseData(parts[4]);
            Membres[] membresImpartidors = stringToMembres(parts[5]);
            int numAssistents = Integer.parseInt(parts[6]);
            int[] valoracions = stringToValoracions(parts[7]);
            Xerrada xerrada = new Xerrada(titol, associacio, membre, data);
            for (Membres membreImpartidor : membresImpartidors) {
                xerrada.afegirMembreImpartidor(membreImpartidor);
            }
            xerrada.afegirAssistents(numAssistents);
            for (int valoracio : valoracions) {
                xerrada.afegirValoracio(valoracio);
            }
            return xerrada;
        } else if (parts.length >= 8) {
            String dataDisseny = parts[4];
            boolean esValida = Boolean.parseBoolean(parts[5]);
            int vegadesOferida = Integer.parseInt(parts[6]);
            double costMaterials = Double.parseDouble(parts[7]);
            Demostracio demo = new Demostracio(titol, associacio, membre, dataDisseny, esValida, costMaterials);
            for (int i = 0; i < vegadesOferida; i++) {
                demo.incrementarVegadesOferida();
            }
            return demo;
        }
        return null;
    }

    /**
     * Converteix un array de membres a una cadena de text.
     * 
     * @param membres L'array de membres a convertir.
     * @return La representació en cadena dels membres.
     */
    private String membresToString(Membres[] membres) {
        StringBuilder sb = new StringBuilder();
        for (Membres membre : membres) {
            if (membre != null) {
                sb.append(membre.getAlias()).append(",");
            }
        }
        return sb.toString();
    }

    /**
     * Converteix una cadena de text a un array de membres.
     * 
     * @param str La cadena de text a convertir.
     * @return L'array de membres corresponent a la cadena de text.
     */
    private Membres[] stringToMembres(String str) {
        String[] parts = str.split(",");
        Membres[] membres = new Membres[parts.length];
        for (int i = 0; i < parts.length; i++) {
            membres[i] = new Membres(parts[i], "", new Data()) {
                @Override
                public Membres copia() {
                    return this;
                }
            };
        }
        return membres;
    }

    /**
     * Converteix un array de valoracions a una cadena de text.
     * 
     * @param valoracions L'array de valoracions a convertir.
     * @return La representació en cadena de les valoracions.
     */
    private String valoracionsToString(int[] valoracions) {
        StringBuilder sb = new StringBuilder();
        for (int valoracio : valoracions) {
            sb.append(valoracio).append(",");
        }
        return sb.toString();
    }

    /**
     * Converteix una cadena de text a un array de valoracions.
     * 
     * @param str La cadena de text a convertir.
     * @return L'array de valoracions corresponent a la cadena de text.
     */
    private int[] stringToValoracions(String str) {
        String[] parts = str.split(",");
        int[] valoracions = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            try {
                valoracions[i] = (int) Math.round(Double.parseDouble(parts[i]));
            } catch (NumberFormatException e) {
                System.err.println("Error convertint valoració: " + parts[i]);
                valoracions[i] = 0;
            }
        }
        return valoracions;
    }

    /**
     * Retorna una representació en cadena de la llista d'accions.
     * 
     * @return La representació en cadena de la llista d'accions.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numAccions; i++) {
            sb.append(accions[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
