package utils;

import java.time.LocalDate;
import java.util.ArrayList;
import app.Actividad;
import app.Especialidad;
import app.Monitor;
import app.Persona;

public class ValidarDatos {
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
    
    /**
     * Verifica si un DNI proporcionado es válido si el formato del DNI 
     * coincide con el patrón.
     *
     * @param dni El DNI a verificar.
     * @return {@code true} si el DNI es válido según el patrón definido,
     * {@code false} en caso contrario.
     */
    public static boolean esDNI(String dni){
        return dni.matches(PatronesUtiles.PATRON_NIF);
    }
    
    /**
     * Valida si el String tiene entre [3-50] caracteres y solo caracteres.
     *
     * @param nombre El String a validar.
     * @return True si el nombre es valido y false en caso contrario.
     */
    public static boolean validarNombre(String nombre) {
        return nombre.matches(PatronesUtiles.PATRON_NOMBRE);
    }

    /**
     * Valida si el String tiene entre [3-50] caracteres y solo caracteres.
     *
     * @param general El String a ValidarDatos.
     * @return True si el nombre es valido y false en caso contrario.
     */
    public static boolean validarGeneral(String general) {
        return general.matches(PatronesUtiles.PATRON_GENERAL);
    }

    /**
     * Valida si un NIF cumple con el patrón y si el digito de control es el
     * correcto.
     *
     * @param nif El NIF a ValidarDatos.
     * @return True si el NIF cumple con el patrón y tiene el digito de control
     * correcto y false si alguna condición no se cumple.
     */
    public static boolean validarNif(String nif) {
        return nif.matches(PatronesUtiles.PATRON_NIF) && averiguarLetraNif(nif).equals(ultimoCaracter(nif));
    }

    /**
     * Valida si un double es positivo.
     *
     * @param numero El número a ValidarDatos.
     * @return True si es positivo y false si no es valido.
     */
    public static boolean esPositivo(double numero) {
        return numero >= 0;
    }
    
    public static boolean validarDireccion(String direccion) {
        return direccion.matches(PatronesUtiles.PATRON_DIRECCION);
    }

    /**
     * Valida si un String tiene solo 5 números.
     *
     * @param codigoPostal El String a validar.
     * @return True si el String tiene solamente 5 números, en otro caso false.
     */
    public static boolean validarCodigoPostal(String codigoPostal) {
        return codigoPostal.matches(PatronesUtiles.PATRON_CODIGO_POSTAL);
    }

    /**
     * Valida si un String tiene solo 9 números.
     *
     * @param telefono El String a validar.
     * @return True si el String tiene solamente 9 números, en otro caso false.
     */
    public static boolean validarTelefono(String telefono) {
        return telefono.matches(PatronesUtiles.PATRON_TELEFONO);
    }

    /**
     * Valida si un String tiene el formato correcto de la fecha.
     *
     * @param fecha El String a validar.
     * @return True si el formato es correcto y false si no es correcto.
     */
    public static boolean validarFormatoFecha(String fecha) {
        return fecha.matches(PatronesUtiles.PATRON_FECHA);
    }

    public static boolean validarFecha(String fecha) {
        boolean esValido = false;

        if (validarFormatoFecha(fecha)) {
            LocalDate hoy = LocalDate.now();
            
            int year = Utilidades.extraerYearDesdeFecha(fecha);
            int month = Utilidades.extraerMonthDesdeFecha(fecha);
            int day = Utilidades.extraerDayDesdeFecha(fecha);
            
            if (esFechaValida(year, month, day)) {
                LocalDate fechaIngresada = LocalDate.of(year, month, day);
                if (!fechaIngresada.isAfter(hoy) && Utilidades.calcularEdad(fechaIngresada) <= 99) {
                    esValido = true;
                }
            }
        }

        return esValido;
    }
    
    public static boolean validarSueldo(double sueldo){
        return sueldo>=1184;
    }
    
    public static boolean validarDuracion(int duracion){
        return duracion>1 && duracion<=120;
    }
    
    public static boolean validarTipoPersona(String tipoPersona){
        boolean esValido = false;
        
        tipoPersona = Utilidades.formatearString(tipoPersona);
        
        if(tipoPersona.equals("SOCIO") || tipoPersona.equals("SOCIOPREMIUM") || tipoPersona.equals("MONITOR")){
            esValido = true;
        }
        
        return esValido;
    }
    
    public static boolean validarEspecialidad(String categoria){
        boolean esValido = false;
        categoria = Utilidades.formatearString(categoria);
        
        for(Especialidad e : Especialidad.values()){
            if(e.name().equals(categoria)){
                esValido = true;
            }
        }
        
        return esValido;
    }
    
    /**
     * Valida que el NIF que le pasamos, este dentro de la lista de 
     * tarjetas.
     * 
     * @param listaPersonas La lista de tarjetas donde comprobaremos
     * si el NIF esta o no.
     * @param nif El NIF que queremos saber su posición.
     * @return Devuelve -1 si el NIF no esta en la lista de tarjetas o
     * la posición donde se encuentra el NIF si esta en la lista
     * de tarjetas.
     */
    public static int comprobarExistenciaNif(ArrayList <Persona> listaPersonas, String nif){
        int contador = -1;
        
        for(int i=0; i<listaPersonas.size(); i++){
            if(listaPersonas.get(i).getDNI().equals(nif)){
                contador = i;
            }
        }
        
        return contador;
    }
    
    public static int comprobarExistenciaNif(ArrayList <Persona> listaPersonas, String nif, boolean esMonitor){
        int contador = -1;
        
        for(int i=0; i<listaPersonas.size(); i++){
            if(esMonitor && listaPersonas.get(i).getDNI().equals(nif) && listaPersonas.get(i) instanceof Monitor){
                contador = i;
            }else if(!esMonitor && listaPersonas.get(i).getDNI().equals(nif) && !(listaPersonas.get(i) instanceof Monitor)){
                contador = i;
            }
        }
        
        return contador;
    }
    
    /**
     * Saca la letra del NIF.
     *
     * @param nif NIF del que queremos sacar la letra.
     * @return String con la letra del NIF.
     * @throws IllegalArgumentException Si el NIF no tienen el formato correcto.
     */
    private static String ultimoCaracter(String nif) {
        formatoDNI(nif);

        String ultimoCaracter = nif.substring(nif.length() - 1, nif.length());

        return ultimoCaracter;
    }

    /**
     * Averigua la letra del DNI que queramos saber.
     *
     * @param nif DNI del que queremos saber la letra.
     * @return String con la letra del DNI.
     */
    private static String averiguarLetraNif(String nif) {
        formatoDNI(nif);

        int numeroDni, posicionLetra;
        String letraValidacion;

        numeroDni = Integer.parseInt(nif.substring(0, nif.length() - 1));
        posicionLetra = numeroDni % 23;
        letraValidacion = LETRAS_DNI.substring(posicionLetra, posicionLetra + 1);

        return letraValidacion;
    }

    private static void formatoDNI(String nif) throws IllegalArgumentException {
        if (!nif.matches(PatronesUtiles.PATRON_NIF)) {
            throw new IllegalArgumentException("ERROR: El NIF no cumple con el patrón adecuado");
        }
    }
    
    private static boolean esFechaValida(int year, int month, int day) {
        boolean esValido = false;
        
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                esValido = (day >= 1 && day <= 31);
            }
            case 4, 6, 9, 11 -> {
                esValido = (day >= 1 && day <= 30);
            }
            case 2 -> {
                if (esBisiesto(year)) {
                    esValido = (day >= 1 && day <= 29);
                } else {
                    esValido = (day >= 1 && day <= 28);
                }
            }
        }
        return esValido;
    }
    
    private static boolean esBisiesto(int year){
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
    
    public static int comprobarExistenciaActividad(ArrayList <Actividad> listaActividad, String nombre){
        int contador = PosicionDatos.mostrarCoincidenciasActividad(listaActividad, nombre);
        
        return contador;
    }
    
    public static int comprobarExistenciaActividad(ArrayList <Actividad> listaActividad, Actividad actividad){
        int contador = -1;
        
        for(int i = 0; i < listaActividad.size(); i++){
            if(actividad.equals(listaActividad.get(i))){
                contador = i;
            }
        }
        
        return contador;
    }
    
    public static int comprobarExistenciaNombre(ArrayList <Persona> listaPersonas, String nombre){
        int contador = -1;
        
        for(int i = 0; i < listaPersonas.size(); i++){
            String nombrePersona = listaPersonas.get(i).getNombre();
            nombrePersona = Utilidades.formatearString(nombrePersona);
            nombre = Utilidades.formatearString(nombre);
            
            if(nombre.equals(nombrePersona)) {
                contador = i;
            }
        }
        
        return contador;
    }
    
    public static boolean comprobarExistenciaEspecialidad(Monitor monitor, Especialidad especialidad){
        boolean existe = false;
        Especialidad [] arrayEspecialidad = monitor.getEspecialidad();
        
        for(int i = 0; i < arrayEspecialidad.length; i++){
            if(especialidad.equals(arrayEspecialidad[i])){
                existe = true;
            }
        }
        
        return existe;
    }
}
