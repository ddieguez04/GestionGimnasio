package app;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Esta clase representa a un socio en una organización, heredando de la clase
 * {@code Persona}. La representación de la clase usa los atributos
 * <code>fechaRegistro</code>, <code>fechaUltimoAcceso</code>,
 * <code>estaActivo</code>, <code>cuota</code> y <code>listaActividad</code>.
 *
 * El socio tiene un conjunto de actividades en las que puede participar, y su
 * cuota se calcula en función de la duración de dichas actividades. La clase
 * permite agregar o eliminar actividades, así como gestionar su estado activo y
 * calcular su cuota total.
 *
 * @author David Diéguez
 * @version 1.0
 */


public class Socio extends Persona{
    private LocalDate fechaRegistro;
    private LocalDate fechaUltimoAcceso;
    private boolean estaActivo;
    private double cuota;
    protected ArrayList <Actividad> listaActividad = new ArrayList();
    
    /**
     * Crea un nuevo objeto {@code Socio} con los datos proporcionados.
     *
     * Este constructor inicializa los atributos heredados de la clase
     * {@code Persona} (nombre, DNI, dirección, localidad, provincia, código
     * postal, teléfono y fecha de nacimiento), así como los atributos
     * específicos de un socio: fecha de registro, fecha del último acceso,
     * estado de actividad y cuota calculada en función de la duración de las
     * actividades.
     *
     * @param nombre El nombre del socio.
     * @param DNI El DNI del socio.
     * @param direccion La dirección del socio.
     * @param localidad La localidad del socio.
     * @param provincia La provincia del socio.
     * @param codigoPostal El código postal del socio.
     * @param telefono El teléfono del socio.
     * @param fechaNacimiento La fecha de nacimiento del socio en formato
     * "yyyy-MM-dd".
     */
    public Socio(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, String fechaNacimiento) {
        super(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaNacimiento);
        this.fechaRegistro = LocalDate.now();
        this.fechaUltimoAcceso = LocalDate.now();
        this.estaActivo = true;
        this.cuota = calcularCuota(this.getDuracionActividades());
    }
    
    /**
     * Crea un nuevo objeto {@code Socio} copiando los datos de otro socio
     * existente.
     *
     * Este constructor copia todos los atributos de un socio dado, incluyendo
     * los atributos heredados de la clase {@code Persona}, así como los
     * atributos específicos de la clase {@code Socio} como la fecha de
     * registro, la fecha del último acceso, el estado de actividad, la cuota y
     * la lista de actividades. La lista de actividades no se clona
     * profundamente, por lo que ambas instancias compartirán la misma
     * referencia.
     *
     * @param s El socio a copiar.
     */
    public Socio(Socio s){
        this(s.getNombre(), s.getDNI(), s.getDireccion(), s.getLocalidad(), s.getProvincia(), s.getCodigoPostal(), s.getTelefono(), s.getFechaNacimiento().format(java.time.format.DateTimeFormatter.ISO_DATE));
        this.fechaRegistro = s.getFechaRegistro();
        this.fechaUltimoAcceso = s.getFechaUltimoAcceso();
        this.estaActivo = s.getEstaActivo();
        this.cuota = s.getCuota();
        for(Actividad actividad : s.listaActividad){
            this.listaActividad.add(actividad);
        }
    }

    /**
     * Devuelve el valor del atributo fecha registro.
     * 
     * @return El atributo fecha registro del objeto.
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro del socio.
     *
     * @param fechaRegistro La fecha de registro que se desea asignar.
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Devuelve el valor del atributo fecha último acceso.
     * 
     * @return El atributo fecha último acceso del objeto.
     */
    public LocalDate getFechaUltimoAcceso() {
        return fechaUltimoAcceso;
    }

    /**
     * Establece la fecha de ultimo acceso del socio.
     * 
     * @param fechaUltimoAcceso La fecha de último acceso que se desea asignar.
     */
    public void setFechaUltimoAcceso(LocalDate fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }

    /**
     * Devuelve el valor del atributo esta activo.
     * 
     * @return true si el socio está activo, false si no lo está.
     */
    public boolean getEstaActivo() {
        return estaActivo;
    }

    /**
     * Establece el estado de actividad del socio.
     *
     * @param estaActivo El nuevo estado de actividad del socio (true si está
     * activo, false si no).
     */
    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
    
    /**
     * Calcula y devuelve la cuota total a pagar por el socio en función de las
     * actividades en las que está inscrito.
     *
     * La cuota total se calcula sumando las cuotas individuales de todas las
     * actividades en las que el socio participa. La cuota de cada actividad se
     * calcula en base a su duración.
     *
     * @return La cuota total a pagar por el socio.
     */
    public double getCuota() {
        double cuotaPagar = 0;
        
        for(int i = 0; i < this.listaActividad.size(); i++){
            cuotaPagar += calcularCuota(this.listaActividad.get(i).getDuracion());
        }
        
        return cuotaPagar;
    }

    /**
     * Devuelve la lista de actividades del socio.
     *
     * @return La lista de actividades del socio.
     */
    public ArrayList<Actividad> getListaActividad() {
        return listaActividad;
    }

    /**
     * Establece la lista de actividades del socio.
     *
     * Asigna una nueva lista de actividades al socio, reemplazando la lista
     * actual de actividades.
     *
     * @param listaActividad La lista de actividades a asignar al socio.
     */
    public void setListaActividad(ArrayList<Actividad> listaActividad) {
        this.listaActividad = listaActividad;
    }
    
    /**
     * Obtiene la duración total de todas las actividades del socio.
     *
     * Suma las duraciones de todas las actividades en la lista de actividades
     * del socio y devuelve el total. Si no hay actividades, el valor retornado
     * será 0.
     *
     * @return La duración total de las actividades del socio.
     */
    public int getDuracionActividades(){
        int duracion = 0;
        
        for(int i = 0; i < this.listaActividad.size(); i++){
            duracion += this.listaActividad.get(i).getDuracion();
        }
        
        return duracion;
    }
    
    /**
     * Añade una actividad a la lista de actividades del socio si se cumplen
     * ciertos criterios.
     *
     * La actividad se añade solo si la duración total de las actividades del
     * socio, después de agregar la nueva actividad, no supera los 360 minutos
     * (6 horas), y si la actividad no es premium. En caso de que la actividad
     * sea añadida, la cuota del socio se actualiza sumando el valor
     * correspondiente a la duración de la nueva actividad.
     *
     * @param a La actividad que se quiere añadir a la lista de actividades.
     * @return true si la actividad fue añadida correctamente, false si no se
     * cumplían los requisitos.
     */
    public boolean addActividad(Actividad a){
        boolean esAñadida = false;
        if(this.getDuracionActividades()+a.getDuracion() <= 360 && !a.getEsPremium()){
            listaActividad.add(a);
            this.cuota += calcularCuota(a.getDuracion());
            esAñadida = true;
        }
        
        return esAñadida;
    }
    
    /**
     * Elimina una actividad de la lista de actividades del socio y ajusta la
     * cuota del socio en función de la duración de la actividad eliminada.
     *
     * La cuota del socio se decrementa según la duración de la actividad
     * eliminada. Luego, la actividad especificada se elimina de la 
     * lista de actividades del socio.
     *
     * @param a La actividad que se desea eliminar de la lista de actividades.
     */
    public void delActividad(Actividad a){
        this.cuota += calcularCuota(-a.getDuracion());
        
        listaActividad.remove(a);
    }
    
    /**
     * Calcula el coste de la cuota para una actividad en función de su
     * duración.
     *
     * @param duracion La duración de la actividad en minutos.
     * @return El coste total de la actividad en función de su duración.
     */
    protected double calcularCuota(int duracion){
        double coste = 6.5/60;
        
        return coste*duracion;
    }
    
    @Override
    public String toString(){
        return super.toString() + " la fecha de registro es: "+fechaRegistro;
    }
}
