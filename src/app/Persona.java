package app;

import java.time.LocalDate;
import java.util.Objects;
import utils.Utilidades;
import utils.ValidarDatos;

/**
 * Esta clase representa a una persona con atributos comunes como nombre, DNI, dirección,
 * localidad, provincia, código postal, teléfono y fecha de nacimiento. Esta clase sirve 
 * como base para otros tipos de personas (como Socios, Socios premium o Monitores) y
 * proporciona validaciones y métodos para obtener y modificar estos atributos,
 * así como calcular la edad.
 * 
 * La clase implementa la interfaz {@code Comparable<Persona>}, lo que permite ordenar objetos
 * de tipo Persona en función de su edad y nombre.
 * 
 * @author David Diéguez
 * @version 1.0
 */


public abstract class Persona implements Comparable<Persona>{
    private String nombre;
    private String DNI;
    private String direccion;
    private String localidad;
    private String provincia;
    private String codigoPostal;
    private String telefono;
    private LocalDate fechaNacimiento;
      
    /**
     * Construye un nuevo objeto Persona con los datos proporcionados.
     *
     * Todos los campos son validados antes de ser asignados. Si alguno de los
     * valores no cumple con los requisitos establecidos, se lanza una excepción
     * indicando el motivo.
     *
     * @param nombre Nombre completo de la persona, entre 10 y 50 caracteres.
     * @param DNI DNI en formato válido.
     * @param direccion Dirección de la persona, entre 3 y 50 caracteres.
     * @param localidad Localidad de residencia, entre 3 y 50 caracteres.
     * @param provincia Provincia de residencia, entre 3 y 50 caracteres.
     * @param codigoPostal Código postal válido de 5 dígitos.
     * @param telefono Número de teléfono válido de 9 dígitos.
     * @param fechaNacimiento Fecha de nacimiento en formato "yyyy-mm-dd".
     *
     * @throws IllegalArgumentException si alguno de los valores es inválido.
     */
    public Persona(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, String fechaNacimiento) {
        if(!ValidarDatos.validarNombre(nombre)){
            throw new IllegalArgumentException("El nombre tiene que tener entre [10-50] caracteres");
        }
        
        if(!ValidarDatos.validarNif(DNI)){
            throw new IllegalArgumentException("El DNI "+DNI+" es incorrecto");
        }
        
        if(!ValidarDatos.validarDireccion(direccion)){
            throw new IllegalArgumentException("La dirección tiene que tener entre [3-50] caracteres");
        }
        
        if(!ValidarDatos.validarGeneral(localidad)){
            throw new IllegalArgumentException("La localidad tiene que tener entre [3-50] caracteres");
        }
        
        if(!ValidarDatos.validarGeneral(provincia)){
            throw new IllegalArgumentException("La provincia tiene que tener entre [3-50] caracteres");
        }
        
        if(!ValidarDatos.validarCodigoPostal(codigoPostal)){
            throw new IllegalArgumentException("El codigo postal solo tiene 5 digitos");
        }
        
        if(!ValidarDatos.validarTelefono(telefono)){
            throw new IllegalArgumentException("El telefono solo tiene 9 digitos");
        }
        
        if(!ValidarDatos.validarFecha(fechaNacimiento)){
            throw new IllegalArgumentException("La fecha no tiene el formato adecuado (yyyy-mm-dd), no es correcta o tienes más de 99 años");
        }
        
        int year = Utilidades.extraerYearDesdeFecha(fechaNacimiento);
        int month = Utilidades.extraerMonthDesdeFecha(fechaNacimiento);
        int day = Utilidades.extraerDayDesdeFecha(fechaNacimiento);
        
        this.nombre = nombre;
        this.DNI = DNI;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.fechaNacimiento = LocalDate.of(year, month, day);
    }
    
    /**
     * Devuelve el valor del atributo nombre.
     * 
     * @return El atributo nombre del objeto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el valor del atributo nombre. El valor recibido por parámetro
     * debe ser un string con entre 10 y 50 caracteres. Si el valor no tiene el
     * tamaño permitido o contiene caracteres no válidos, el atributo no se
     * modificará.
     *
     * @param nombre El valor del nombre en el rango [10, 50].
     */
    public void setNombre(String nombre) {
        if(ValidarDatos.validarNombre(nombre)){
            this.nombre = nombre;
        }
    }

    /**
     * Devuelve el valor del atributo DNI.
     * 
     * @return El atributo DNI del objeto.
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Establece el valor del atributo DNI. El valor recibido debe cumplir con
     * el formato válido de un NIF español. Si no es válido, el atributo no se
     * modificará.
     *
     * @param DNI El DNI que se desea establecer.
     */
    public void setDNI(String DNI) {
        if (ValidarDatos.validarNif(DNI)) {
            this.DNI = DNI;
        }
    }

    /**
     * Devuelve el valor del atributo dirección.
     * 
     * @return El atributo dirección del objeto.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece el valor del atributo dirección. El valor debe tener entre 3 y
     * 50 caracteres. Si no cumple con estas condiciones, el atributo no se
     * modificará.
     *
     * @param direccion La dirección a establecer.
     */
    public void setDireccion(String direccion) {
        if(ValidarDatos.validarDireccion(direccion)){
            this.direccion = direccion;
        }
    }

    /**
     * Devuelve el valor del atributo localidad.
     * 
     * @return El atributo localidad del objeto.
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Establece el valor del atributo localidad. El valor debe tener entre 3 y
     * 50 caracteres. Si no cumple con estas condiciones, el atributo no se
     * modificará.
     *
     * @param localidad La localidad a establecer.
     */
    public void setLocalidad(String localidad) {
        if(ValidarDatos.validarGeneral(localidad)){
            this.localidad = localidad;
        }
    }

    /**
     * Devuelve el valor del atributo provincia.
     * 
     * @return El atributo provincia del objeto.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Establece el valor del atributo provincia. El valor debe tener entre 3 y
     * 50 caracteres. Si no cumple con estas condiciones, el atributo no se
     * modificará.
     *
     * @param provincia La provincia a establecer.
     */
    public void setProvincia(String provincia) {
        if(ValidarDatos.validarGeneral(provincia)){
            this.provincia = provincia;
        }
    }

    /**
     * Devuelve el valor del atributo codigo postal.
     *
     * @return El atributo codigo postal del objeto.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Establece el valor del atributo código postal. El valor debe tener
     * exactamente 5 dígitos. Si no cumple con esta condición, el atributo no se
     * modificará.
     *
     * @param codigoPostal El código postal a establecer.
     */
    public void setCodigoPostal(String codigoPostal) {
        if(ValidarDatos.validarCodigoPostal(codigoPostal)){
            this.codigoPostal = codigoPostal;
        }
    }

    /**
     * Devuelve el valor del atributo telefono.
     * 
     * @return El atributo telefono del objeto.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el valor del atributo teléfono. El valor debe tener exactamente
     * 9 dígitos. Si no cumple con esta condición, el atributo no se modificará.
     *
     * @param telefono El número de teléfono a establecer.
     */
    public void setTelefono(String telefono) {
        if(ValidarDatos.validarTelefono(telefono)){
            this.telefono = telefono;
        }
    }

    /**
     * Devuelve el valor del atributo fecha nacimiento.
     * 
     * @return El atributo fecha nacimiento del objeto.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece el valor del atributo fecha de nacimiento. El valor debe estar
     * en formato "yyyy-MM-dd" y ser una fecha válida. Si la fecha no es válida
     * o la persona tiene más de 99 años, el atributo no se modificará.
     *
     * @param fechaNacimiento La fecha de nacimiento en formato "yyyy-MM-dd".
     */    
    public void setFechaNacimiento(String fechaNacimiento) {
        if(ValidarDatos.validarFecha(fechaNacimiento)){
            int year = Utilidades.extraerYearDesdeFecha(fechaNacimiento);
            int month = Utilidades.extraerMonthDesdeFecha(fechaNacimiento);
            int day = Utilidades.extraerDayDesdeFecha(fechaNacimiento);

            this.fechaNacimiento = LocalDate.of(year, month, day);
        }
    }
    
    /**
     * Devuelve la edad de la persona calculada a partir de su fecha de
     * nacimiento. La edad se calcula restando la fecha actual a la fecha de
     * nacimiento y ajustando en función de si la fecha de nacimiento ya ocurrió
     * este año.
     *
     * @return La edad de la persona en años.
     */
    public int getEdad(){
        return Utilidades.calcularEdad(this.fechaNacimiento);
    }
    
    @Override
    public String toString(){
        return nombre + " con DNI: " + DNI + " su dirección es: " + direccion + " de la provincia "
                + provincia + " con CP " + codigoPostal + " su telefono es: " + telefono + " nació el día: " + fechaNacimiento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.DNI);
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
        final Persona other = (Persona) obj;
        return Objects.equals(this.DNI, other.DNI);
    }
    
    @Override
    public int compareTo(Persona p) {
        String persona1 = this.getEdad()+this.nombre;
        String persona2 = p.getEdad()+p.nombre;
        
        return persona1.compareTo(persona2);
    }
}
