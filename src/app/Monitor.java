package app;

import utils.ValidarDatos;

/**
 * Esta clase representa un monitor en una organización, heredando de la clase
 * {@code Persona}. La representación de la clase usa los atributos
 * <code>especialidad</code>, <code>sueldo</code>, <code>votosPositivos</code>,
 * <code>votosNegativos</code>, <code>contador</code> y
 * <code>votosTotales</code>.
 *
 * El monitor tiene un conjunto de especialidades, un sueldo, y cuenta con un
 * sistema de votos positivos y negativos que permiten valorar al monitor. Los
 * métodos de la clase permiten manipular estos atributos y calcular una
 * valoración basada en los votos.
 *
 * @author David Diéguez
 * @version 1.0
 */


public class Monitor extends Persona implements Valorable{
    private Especialidad [] especialidad;
    private double sueldo;
    private int votosPositivos;
    private int votosNegativos;
    private int contador = 0;
    private static int votosTotales = 0;
    
    /**
     * Crea un nuevo objeto Monitor con los datos proporcionados.
     *
     * Este constructor inicializa los atributos del monitor, incluyendo la
     * validación del sueldo, un array de especialidades, y establece los 
     * votos positivos y negativos a cero.
     *
     * @param nombre El nombre del monitor.
     * @param DNI El DNI del monitor, que debe ser válido según las reglas
     * definidas.
     * @param direccion La dirección del monitor, que debe cumplir con los
     * criterios de validez.
     * @param localidad La localidad del monitor.
     * @param provincia La provincia del monitor.
     * @param codigoPostal El código postal del monitor, que debe tener 5
     * dígitos.
     * @param telefono El teléfono del monitor, que debe ser un número válido
     * con 9 dígitos.
     * @param fechaNacimiento La fecha de nacimiento del monitor, en formato
     * "yyyy-mm-dd", que debe ser válida.
     * @param sueldo El sueldo del monitor, que no puede ser menor que el
     * salario mínimo interprofesional.
     * 
     * @throws IllegalArgumentException Si el sueldo es menor que el salario
     * mínimo interprofesional.
     */
    public Monitor(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, String fechaNacimiento, double sueldo) {
        super(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaNacimiento);
        
        if(!ValidarDatos.validarSueldo(sueldo)){
            throw new IllegalArgumentException("El sueldo no puede ser menor que el salario mínimo interprofesional");
        }
        
        this.especialidad = new Especialidad[3];
        this.sueldo = sueldo;
        this.votosNegativos = 0;
        this.votosPositivos = 0;
    }
    
    /**
     * Crea un nuevo objeto {@code Monitor} copiando los datos de otro monitor
     * existente.
     *
     * Este constructor copia todos los atributos de un monitor dado, incluyendo
     * los atributos heredados de la clase {@code Persona}, así como los
     * atributos específicos de la clase {@code Monitor} como la especialidad,
     * el sueldo, los votos negativos, los votos positivos y el contador. Los
     * valores de especialidad, sueldo, votos y contador se copian directamente.
     *
     * @param m El monitor a copiar.
     */
    public Monitor(Monitor m){
        this(m.getNombre(), m.getDNI(), m.getDireccion(), m.getLocalidad(), m.getLocalidad(), m.getCodigoPostal(), m.getTelefono(), m.getFechaNacimiento().format(java.time.format.DateTimeFormatter.ISO_DATE), m.getSueldo());
        this.especialidad = m.especialidad;
        this.sueldo = m.sueldo;
        this.votosNegativos = m.votosNegativos;
        this.votosPositivos = m.votosPositivos;
        this.contador = m.contador;
    }

    /**
     * Establece una especialidad para el monitor, asignándola a la primera
     * posición disponible en el array de especialidades.
     *
     * Si el array de especialidades tiene espacio (es decir, hay menos de 3
     * especialidades asignadas), se agrega la nueva especialidad en la posición
     * correspondiente. Si el array ya contiene 3 especialidades, no se
     * realizará ninguna acción.
     *
     * @param e La especialidad a asignar al monitor.
     */
    public void setEspecialidad(Especialidad e) {
        if(contador<3){
            this.especialidad[contador] = e;
        }
    }
    
    /**
     * Devuelve el array de Especialidad.
     *
     * @return El array especidalidad del objeto.
     */
    public Especialidad [] getEspecialidad(){
        return this.especialidad;
    }
    
    /**
     * Establece el valor del atributo sueldo del monitor.
     *
     * El sueldo debe ser un valor mayor o igual al salario mínimo
     * interprofesional. Si el valor proporcionado es válido, se establece como
     * el sueldo del monitor. Si el sueldo no cumple con los requisitos, no se
     * modificará el atributo.
     *
     * @param sueldo El sueldo del monitor.
     */
    public void setSueldo(double sueldo){
        if(ValidarDatos.validarSueldo(sueldo)){
            this.sueldo = sueldo;
        }
    }
    
    /**
     * Devuelve el atributo sueldo.
     * 
     * @return El atributo sueldo del objeto.
     */
    public double getSueldo(){
        return this.sueldo;
    }
    
    /**
     * Registra un voto positivo o negativo para el monitor.
     *
     * Si el parámetro {@code like} es {@code true}, se incrementa el contador
     * de votos positivos. Si es {@code false}, se incrementa el contador de
     * votos negativos. En ambos casos, el contador total de votos también se
     * incrementa.
     *
     * @param like {@code true} si el voto es positivo, {@code false} si es
     * negativo.
     */
    public void meGusta(boolean like){
        if(like){
            votosPositivos++;
        }else{
            votosNegativos++;
        }
        votosTotales++;
    }
    
    /**
     * Calcula la valoración del monitor en función de los votos positivos y
     * totales.
     *
     * La valoración se calcula como el porcentaje de votos positivos sobre el
     * total de votos, multiplicado por 10 para obtener un valor entre 0 y 10.
     * El resultado se redondea al entero más cercano.
     *
     * @return La valoración del monitor, un número entero entre 0 y 10.
     */
    @Override
    public int calcularValoracion(){
        double valoracion = votosPositivos / votosTotales;
        
        valoracion *= 10;
        
        return (int) Math.round(valoracion);
    }
    
    @Override
    public String toString(){
        return super.toString() + " su sueldo es: "+sueldo+", con "+votosNegativos+" votos negativos y "+votosPositivos+" votos positivos.";
    }
}
