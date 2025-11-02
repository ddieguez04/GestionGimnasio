package utils;

import app.Especialidad;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;

public class Utilidades {
    /**
     * Ordena un array de especialidades, moviendo los valores nulos al final
     * del array.
     *
     * Este método recorre el array de especialidades y mueve cualquier valor
     * nulo hacia el final del array, intercambiando elementos adyacentes cuando
     * es necesario.
     *
     * @param array El array de especialidades a ordenar.
     * @return El array de especialidades ordenado, con los valores nulos al
     * final.
     */
    public static Especialidad[] ordenarArray(Especialidad[] array) {
        Especialidad aux;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == null && array[i + 1] != null) {
                aux = array[i];
                array[i] = array[i + 1];
                array[i + 1] = aux;
            }
        }
        return array;
    }

    /**
     * Formatea una cadena de texto, convirtiéndola a mayúsculas y eliminando
     * los espacios en blanco.
     *
     * @param s La cadena de texto que se desea formatear.
     * @return La cadena formateada, en mayúsculas y sin espacios.
     */    
    public static String formatearString(String s) {
        String formateado = s.toUpperCase().replaceAll("\\s", "");
        return formateado;
    }

    /**
     * Extrae el día de una fecha en formato específico.
     *
     * Este método toma una fecha en formato de cadena y utiliza una expresión
     * regular para extraer el valor del día de la misma. La fecha debe
     * coincidir con el patrón esperado para ser procesada correctamente.
     *
     * @param fecha La fecha de la que se desea extraer el día, en formato de
     * cadena (por ejemplo: "yyyy-MM-dd").
     * @return El valor del día extraído de la fecha. Si no se puede extraer el
     * día, se devuelve 0.
     */
    public static int extraerDayDesdeFecha(String fecha) {
        int day = 0;
        Matcher matcher = PatronesUtiles.TRABAJAR_FECHAS.matcher(fecha);
        if (matcher.find()) {
            day = Integer.parseInt(matcher.group(3));
        }
        return day;
    }

    /**
     * Extrae el mes de una fecha en formato específico.
     *
     * Este método toma una fecha en formato de cadena y utiliza una expresión
     * regular para extraer el valor del mes de la misma. La fecha debe
     * coincidir con el patrón esperado para ser procesada correctamente.
     *
     * @param fecha La fecha de la que se desea extraer el mes, en formato de
     * cadena (por ejemplo: "yyyy-MM-dd").
     * @return El valor del mes extraído de la fecha. Si no se puede extraer el
     * mes, se devuelve 0.
     */
    public static int extraerMonthDesdeFecha(String fecha) {
        int month = 0;
        Matcher matcher = PatronesUtiles.TRABAJAR_FECHAS.matcher(fecha);
        if (matcher.find()) {
            month = Integer.parseInt(matcher.group(2));
        }
        return month;
    }

    /**
     * Extrae el año de una fecha en formato específico.
     *
     * Este método toma una fecha en formato de cadena y utiliza una expresión
     * regular para extraer el valor del año de la misma. La fecha debe
     * coincidir con el patrón esperado para ser procesada correctamente.
     *
     * @param fecha La fecha de la que se desea extraer el año, en formato de
     * cadena (por ejemplo: "yyyy-MM-dd").
     * @return El valor del año extraído de la fecha. Si no se puede extraer el
     * año, se devuelve 0.
     */
    public static int extraerYearDesdeFecha(String fecha) {
        int year = 0;
        Matcher matcher = PatronesUtiles.TRABAJAR_FECHAS.matcher(fecha);
        if (matcher.find()) {
            year = Integer.parseInt(matcher.group(1));
        }
        return year;
    }
    
    /**
     * Calcula la edad de una persona basada en su fecha de nacimiento.
     *
     * Este método toma una fecha de nacimiento como entrada y calcula la edad
     * en años completos, utilizando la fecha actual como referencia. La edad se
     * calcula restando los años entre la fecha de nacimiento y la fecha actual.
     *
     * @param fechaNacimiento La fecha de nacimiento de la persona, representada
     * como un objeto {@link LocalDate}.
     * @return La edad de la persona en años completos.
     */
    public static int calcularEdad(LocalDate fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        int edad = (int) ChronoUnit.YEARS.between(fechaNacimiento, hoy);
        return edad;
    }
}
