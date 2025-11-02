package app;

import java.util.ArrayList;
import java.util.Objects;
import utils.ValidarDatos;

/**
 * Esta clase representa una actividad que puede ser realizada por los socios de
 * una organización. La representación de la clase usa los atributos
 * <code>nombre</code>, <code>duracion</code>, <code>calorias</code>,
 * <code>categoria</code>, <code>esPremium</code> y <code>votos</code>.
 *
 * La actividad tiene un nombre, duración, calorías quemadas, categoría, y un
 * estado de si es premium. Además, permite realizar votaciones y calcular la
 * valoración media de la actividad.
 *
 * <p>Los métodos de la clase permiten manipular estos atributos de forma
 * controlada, validando que se respeten las condiciones mínimas para su
 * correcto funcionamiento.</p>
 *
 * @author David Diéguez
 * @version 1.0
 */


public class Actividad implements Comparable<Actividad>, Valorable{
    private String nombre;
    private int duracion;
    private int calorias;
    private Especialidad categoria;
    private boolean esPremium;
    private ArrayList <Integer> votos;
    
    /**
     * Crea una nueva actividad con los parámetros especificados.
     *
     * Valida el nombre, la duración y las calorías antes de asignarlos. Si
     * alguno de los parámetros no es válido, lanza una excepción
     * {@code IllegalArgumentException} con un mensaje descriptivo del error.
     *
     * @param nombre el nombre de la actividad (entre 3 y 50 caracteres)
     * @param duracion la duración de la actividad en minutos (entre 2 y 120)
     * @param calorias la cantidad de calorías que quema la actividad (debe ser
     * positiva)
     * @param categoria la categoría de la actividad (especialidad)
     * @param esPremium indica si la actividad es exclusiva para socios premium
     * @throws IllegalArgumentException si alguno de los parámetros no cumple
     * con las validaciones
     */
    public Actividad(String nombre, int duracion, int calorias, Especialidad categoria, boolean esPremium){
        if(!ValidarDatos.validarGeneral(nombre)){
            throw new IllegalArgumentException("El nombre tiene que tener entre [3-50] caracteres");
        }
        
        if(!ValidarDatos.validarDuracion(duracion)){
            throw new IllegalArgumentException("La duración debe de ser mayor a 1 minuto y no exceder las 2 horas");
        }
        
        if(!ValidarDatos.esPositivo(calorias)){
            throw new IllegalArgumentException("Las calorias tienen que ser positivas");
        }
        
        this.nombre = nombre;
        this.duracion = duracion;
        this.calorias = calorias;
        this.categoria = categoria;
        this.esPremium = esPremium;
        this.votos = new ArrayList();
    }
    
    /**
     * Crea una nueva actividad copiando los datos de otra actividad existente.
     *
     * Copia todos los atributos de la actividad original, incluyendo la lista
     * de votos.
     *
     * @param a la actividad a copiar
     */
    public Actividad(Actividad a){
        this(a.nombre, a.duracion, a.calorias, a.categoria, a.esPremium);
        this.votos = new ArrayList<>();
        for(Integer voto : a.votos){
            this.votos.add(voto);
        }
    }

    /**
     * Devuelve el nombre de la actividad.
     *
     * @return El atributo nombre del objeto.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el valor del atributo nombre. El valor recibido por parámetro
     * debe ser un string con un tamaño válido. Si el valor no cumple con las
     * condiciones establecidas, el atributo no se modificará.
     *
     * @param nombre El nuevo nombre de la actividad, debe cumplir con las
     * condiciones de validación.
     */
    public void setNombre(String nombre) {
        if(ValidarDatos.validarGeneral(nombre)){
            this.nombre = nombre;
        }
    }
    
    /**
     * Devuelve el valor del atributo duracion.
     *
     * @return El atributo duracion del objeto.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece el valor del atributo duracion. El valor recibido por parámetro
     * debe ser mayor a 1 minuto y no exceder las 2 horas. Si el valor no cumple
     * con estos requisitos, el atributo no se modificará.
     *
     * @param duracion El valor de la duración en minutos.
     */    
    public void setDuracion(int duracion) {
        if(ValidarDatos.validarDuracion(duracion)){
            this.duracion = duracion;
        }
    }
    
    /**
     * Devuelve el valor del atributo calorías.
     * 
     * @return El atributo calorias del objeto.
     */
    public int getCalorias() {
        return calorias;
    }

    /**
     * Establece el valor del atributo calorias. El valor recibido por parámetro
     * debe ser positivo. Si el valor no cumple con este requisito, el atributo
     * no se modificará.
     *
     * @param calorias El valor de las calorías, debe ser positivo.
     */
    public void setCalorias(int calorias) {
        if(ValidarDatos.esPositivo(calorias)){
            this.calorias = calorias;
        }
    }

    /**
     * Devuelve el valor del atributo categoria.
     * 
     * @return El atributo categoria del objeto.
     */
    public Especialidad getCategoria() {
        return categoria;
    }
    
    /**
     * Establece el valor del atributo categoría.
     *
     * @param categoria El valor de la categoría de la actividad.
     */
    public void setCategoria(Especialidad categoria) {
        this.categoria = categoria;
    }

    /**
     * Devuelve el valor del atributo esPremium.
     * 
     * @return El atributo esPremium del objeto.
     */
    public boolean getEsPremium() {
        return esPremium;
    }

    /**
     * Establece si la actividad es premium.
     *
     * @param esPremium {@code true} si la actividad es premium, {@code false}
     * en caso contrario.
     */
    public void setEsPremium(boolean esPremium) {
        this.esPremium = esPremium;
    }
    
    /**
     * Añade un voto a la lista de votos si el valor es válido.
     *
     * El voto debe estar en el rango de 0 a 10 (inclusive). Si es válido, se
     * agrega a la lista de votos y se devuelve {@code true}. En caso contrario,
     * no se añade y se devuelve {@code false}.
     *
     * @param voto el valor del voto a añadir, entre 0 y 10.
     * @return {@code true} si el voto fue añadido correctamente, {@code false}
     * si no es válido.
     */
    public boolean votar(int voto){
        boolean esValido = false;
        
        if(voto>=0 && voto<=10){
            votos.add(voto);
            esValido = true;
        }
        
        return esValido;
    }
    
    /**
     * Calcula y devuelve la valoración media de la actividad.
     *
     * Este método suma todos los votos registrados en la lista de votos y
     * calcula la media aritmética. El resultado se redondea al entero más
     * cercano y se devuelve como valoración.
     *
     * @return la valoración media redondeada como un entero.
     */
    @Override
    public int calcularValoracion(){
        double contador = 0;
        
        for(int i = 0; i < votos.size(); i++){
            contador += votos.get(i);
        }
        
        double valoracion = contador/votos.size();
        
        return (int) Math.round(valoracion);
    }
    
    @Override
    public String toString(){
        if(esPremium){
            return "El nombre de la actividad es: " + nombre + ", dura " + duracion + " minutos, se queman " + calorias + " kcal, forma parte de la categoria " + categoria + " y es premium";
        }else{
            return "El nombre de la actividad es: " + nombre + ", dura " + duracion + " minutos, se queman " + calorias + " kcal, forma parte de la categoria " + categoria + " y no es premium";
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actividad other = (Actividad) obj;
        if (this.duracion != other.duracion) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return this.categoria == other.categoria;
    }
    
    @Override
    public int compareTo(Actividad a){
        String actividad1 = this.nombre;
        String actividad2 = a.nombre;
        
        int comparar = actividad1.compareTo(actividad2);
        if(comparar == 0){
            comparar = Integer.compare(this.duracion, a.duracion);
        }
        
        return comparar;
    }
}
