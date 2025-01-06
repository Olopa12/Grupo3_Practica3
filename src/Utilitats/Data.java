package Utilitats;

import java.io.Serializable;

/**
 * Classe per guardar dates.
 * 
 * @author Professores de programació.
 *
 */

public class Data implements Serializable{
	private int dia;
	private int mes;
	private int any;

	/**
	 * Constructor per defecte, sense paràmetres inicialitza a una data de
	 * referència
	 */
	public Data() {
		this.dia = 1;
		this.mes = 1;
		this.any = 2000;
	}

	/**
	 * Constructor que rep la data per paràmetre
	 * Ha de validar que la data és correcta. Si rep una data incorrecta inicialitza la instància
	 * amb la data de referència.
	 * @param dia
	 * @param mes
	 * @param any
	 */
	public Data(int dia, int mes, int any) {
		if (esDataCorrecta(dia, mes, any)) { // ens asegurem que és una data valida
			this.dia = dia;
			this.mes = mes;
			this.any = any;
		} else { // posem la data de referència com a senyal d'error
			this.dia = 1;
			this.mes = 1;
			this.any = 2000;
		}
	}

	/**
	 * Getter
	 * @return dia de la data
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * Getter
	 * @return mes de la data
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * Getter
	 * @return any de la data
	 */
	public int getAny() {
		return any;
	}

	/**
	 * Setter conjunt per a poder validar la correctesa de la data rebuda.
	 * Només es fa la modificació de la data si que es rep per paràmetre és correcte.
	 * @param dia
	 * @param mes
	 * @param any
	 */
	public void setData(int dia, int mes, int any) {
		if (esDataCorrecta(dia, mes, any)) { // ens asegurem que hi ha una data vàlida
			this.dia = dia;
			this.mes = mes;
			this.any = any;
		}
	}

	 /**
     * Mètode que comprova si aquesta data és anterior a una altra.
     * 
     * @param altra - Una altra instància de la classe Data per comparar.
     * @return `true` si aquesta data és anterior, `false` altrament.
     */
    public boolean esAnterior(Data altra) {
        if (this.any < altra.any) {
            return true;
        } else if (this.any == altra.any) {
            if (this.mes < altra.mes) {
                return true;
            } else if (this.mes == altra.mes) {
                return this.dia < altra.dia;
            }
        }
        return false;
    }

	/**
	 * Mètode que comprova si la data actual és la mateixa a la que es rep per paràmetre
	 * @param data - valor de data a comparar amb l'actual
	 * @return si són iguals
	 */
	public boolean esIgual(Data data) {
		// diferents formes d'accedir als atributs del paràmetre
		if (this.dia == data.dia && this.mes == data.mes && this.any == data.getAny()) {
			return true;
		}
		return false;
	}

	/**
	 * Mètode que calcula sobre la instància actual el valor del dia següent
	 */
	public void diaSeguent() {
		// el this és opcional en aquest cas
		this.dia++;
		if (this.dia > diesMes(this.mes, this.any)) {
			this.dia = 1;
			this.mes++;
			if (this.mes > 12) {
				this.mes = 1;
				this.any++;
			}
		}
	}

	/**
	 * Mètode per comparar si dos objectes Data són iguals.
	 * Es basa en els valors dels atributs dia, mes i any.
	 * 
	 * @param obj - L'objecte a comparar amb aquesta instància de Data.
	 * @return True si els dos objectes tenen els mateixos valors per a dia, mes i any.
	 */
	public boolean equals(Object obj) {
		if (this == obj) return true; // Compara referencias
		if (obj == null || getClass() != obj.getClass()) return false; // Verifica el tipo
	
		Data altraData = (Data) obj; // Realiza el casting
		return this.dia == altraData.dia && this.mes == altraData.mes && this.any == altraData.any;
	}


	/**
	 * Mètode que transforma el contingut d'un objecte en una cadena de caracters per ser
	 * mostrat per pantalla
	 */
	public String toString() {
		return String.format("%d-%02d-%04d", dia, mes, any);
	}

	// Mètodes de classe (STATIC).
	// no s'apliquen sobre el contingut d'una instància de data sinó sobre valors
	// que es reben per paràmetre.
	// són mètodes auxiliars

	private static boolean esDataCorrecta(int dia, int mes, int any) {
		boolean hoEs=true;
		if (dia < 1 || dia > 31) { // dia incorrecte
			hoEs= false;
		}
		if (mes < 1 || mes > 12) { // mes incorrecte
			hoEs= false;
		}
		if (dia > diesMes(mes, any)) { // dia del mes incorrecte
			hoEs= false;
		}
		return hoEs;
	}

	private static boolean esAnyTraspas(int any) { // ens estalviem crear una instancia de data
		if ((any % 4 == 0) && ((any % 100 != 0) || (any % 400 == 0))) {
			return true;
		} else {
			return false;
		}
	}

	private static int diesMes(int mes, int any) { // per saber quants dies te un mes d'un any
		int diesMes;
		if (mes == 2) {
			if (esAnyTraspas(any)) {
				diesMes = 29;
			} else {
				diesMes = 28;
			}
		} else {
			if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
				diesMes = 30;
			} else {
				diesMes = 31;
			}
		}
		return diesMes;
	}

	/**
	 * Mètode estàtic per convertir una cadena de text en un objecte Data.
	 * Si la cadena no segueix el format correcte o hi ha algun error,
	 * retorna una instància de Data amb valors predeterminats.
	 * 
	 * @param dataStr - Cadena de text amb la data en format "dd-mm-yyyy".
	 * @return Un objecte Data corresponent a la cadena o una Data per defecte si hi ha errors.
	 */
	public static Data parseData(String dataStr) {
		try {
			String[] parts = dataStr.split("-");
			if (parts.length != 3) {
				throw new IllegalArgumentException("El formato de la fecha es incorrecto. Use dd-mm-yyyy.");
			}
			int dia = Integer.parseInt(parts[0]);
			int mes = Integer.parseInt(parts[1]);
			int any = Integer.parseInt(parts[2]);
			return new Data(dia, mes, any);
		} catch (Exception e) {
			System.err.println("Error al parsear la fecha: " + dataStr + " - " + e.getMessage());
			return new Data(); // Retorna una fecha por defecto si hay error.
		}
	}
}

