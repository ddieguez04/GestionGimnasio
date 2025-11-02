package utils;

import app.Actividad;
import java.util.ArrayList;
import app.Especialidad;
import app.Monitor;
import app.Persona;

public class InicializarDatos {

    /**
     * Inicializa el sueldo de un monitor solicitando al usuario la entrada
     * desde el teclado.
     *
     * Este método solicita al usuario que ingrese un valor para el sueldo,
     * asegurándose de que el valor no sea inferior al salario mínimo
     * interprofesional (1184). Si el valor ingresado es inválido, se muestra un
     * mensaje de error y se solicita una nueva entrada.
     *
     * @return El sueldo ingresado por el usuario, validado para ser mayor o
     * igual al salario mínimo interprofesional.
     */
    public static double inicializarSueldo() {
        double salario = LeerDatosTeclado.leerDouble("Introduce el salario:", 1184, "El salario no puede ser menor que el salario minimo interprofesional");
        
        return salario;
    }
    
    /**
     * Solicita al usuario que ingrese un nombre, asegurándose de que cumpla con
     * los requisitos de longitud.
     *
     * Este método solicita al usuario que ingrese un nombre y valida que su
     * longitud esté en el rango de [10-50] caracteres. Si el nombre ingresado
     * no cumple con los requisitos, se muestra un mensaje de error y se vuelve
     * a solicitar la entrada hasta que el nombre sea válido.
     *
     * @return El nombre ingresado por el usuario, validado para tener entre 10
     * y 50 caracteres.
     */
    public static String inicializarNombre() {
        String nombre;

        do {
            nombre = LeerDatosTeclado.leerString("Introduce el nombre:");

            if (!ValidarDatos.validarNombre(nombre)) {
                System.out.println("El nombre tiene que tener entre [10-50] caracteres");
            }
        } while (!ValidarDatos.validarNombre(nombre));

        return nombre;
    }

    /**
     * Solicita al usuario que ingrese un DNI, validando su formato.
     *
     * Este método solicita al usuario que ingrese un DNI y valida que cumpla
     * con el formato correcto. Si el DNI ingresado no es válido, se muestra un
     * mensaje de error y se vuelve a solicitar la entrada hasta que el DNI sea
     * válido.
     *
     * @return El DNI ingresado por el usuario, validado para cumplir con el
     * formato adecuado.
     */
    public static String inicializarDNI() {
        String dni;

        do {
            dni = LeerDatosTeclado.leerString("Introduce el DNI:");

            if (!ValidarDatos.validarNif(dni)) {
                System.out.println("El DNI es incorrecto.");
            }
        } while (!ValidarDatos.validarNif(dni));

        return dni;
    }

    /**
     * Solicita al usuario que ingrese un texto, validando su longitud.
     *
     * Este método solicita al usuario que ingrese un texto y valida que su
     * longitud esté dentro del rango permitido de 3 a 50 caracteres. Si el
     * texto ingresado no cumple con los requisitos de longitud, se muestra un
     * mensaje de error y se vuelve a solicitar la entrada hasta que el texto
     * sea válido.
     *
     * @param mensaje El mensaje que se mostrará al usuario al solicitar la
     * entrada.
     * @return El texto ingresado por el usuario, validado para cumplir con los
     * requisitos de longitud.
     */
    public static String inicializarGeneral(String mensaje) {
        String general;

        do {
            general = LeerDatosTeclado.leerString(mensaje);

            if (!ValidarDatos.validarGeneral(general)) {
                System.out.println("El texto tiene que tener entre [3-50] caracteres");
            }
        } while (!ValidarDatos.validarGeneral(general));

        return general;
    }
    
    /**
     * Solicita al usuario que ingrese una dirección y valida su longitud.
     *
     * Este método solicita al usuario que ingrese una dirección, asegurándose
     * de que el texto ingresado tenga una longitud entre 3 y 50 caracteres. Si
     * la dirección no cumple con los requisitos, se muestra un mensaje de error
     * y se vuelve a solicitar la entrada hasta que el texto sea válido.
     *
     * @return La dirección ingresada por el usuario, validada para cumplir con
     * los requisitos de longitud.
     */
    public static String inicializarDireccion(){
        String direccion;
        
        do{
            direccion = LeerDatosTeclado.leerString("Introduce la direccion: ");
            
            if(!ValidarDatos.validarDireccion(direccion)){
                System.out.println("El texto tiene que tener entre [3-50] caracteres");
            }
        }while(!ValidarDatos.validarDireccion(direccion));
        
        return direccion;
    } 

    /**
     * Solicita al usuario que ingrese un código postal y valida que tenga 5
     * dígitos.
     *
     * Este método solicita al usuario que ingrese un código postal y asegura
     * que el texto ingresado tenga exactamente 5 dígitos. Si el código postal
     * no cumple con este requisito, se muestra un mensaje de error y se vuelve
     * a solicitar la entrada hasta que el código postal sea válido.
     *
     * @return El código postal ingresado por el usuario, validado para tener
     * exactamente 5 dígitos.
     */
    public static String inicializarCodigoPostal() {
        String codigoPostal;

        do {
            codigoPostal = LeerDatosTeclado.leerString("Introduce el código postal:");
            if (!ValidarDatos.validarCodigoPostal(codigoPostal)) {
                System.out.println("El codigo postal solo tiene 5 digitos");
            }
        } while (!ValidarDatos.validarCodigoPostal(codigoPostal));

        return codigoPostal;
    }

    /**
     * Solicita al usuario que ingrese un número de teléfono y valida que tenga
     * 9 dígitos.
     *
     * Este método solicita al usuario que ingrese un número de teléfono y
     * asegura que el texto ingresado tenga exactamente 9 dígitos. Si el número
     * de teléfono no cumple con este requisito, se muestra un mensaje de error
     * y se vuelve a solicitar la entrada hasta que el número de teléfono sea
     * válido.
     *
     * @return El número de teléfono ingresado por el usuario, validado para
     * tener exactamente 9 dígitos.
     */
    public static String inicializarTelefono() {
        String telefono;

        do {
            telefono = LeerDatosTeclado.leerString("Introduce el número de telefono:");
            if (!ValidarDatos.validarTelefono(telefono)) {
                System.out.println("El telefono solo tiene 9 digitos");
            }
        } while (!ValidarDatos.validarTelefono(telefono));

        return telefono;
    }

    /**
     * Solicita al usuario que ingrese una fecha de nacimiento en el formato
     * (yyyy-mm-dd) y valida que sea correcta.
     *
     * Este método solicita al usuario que ingrese una fecha de nacimiento y
     * valida que el formato sea correcto (yyyy-mm-dd). Si la fecha no cumple
     * con el formato adecuado o es inválida, se muestra un mensaje de error y
     * se vuelve a solicitar la entrada hasta que la fecha sea válida.
     *
     * @return La fecha de nacimiento ingresada por el usuario, validada en el
     * formato correcto (yyyy-mm-dd).
     */
    public static String inicializarFechaNacimiento() {
        String fechaNacimiento;

        do {
            fechaNacimiento = LeerDatosTeclado.leerString("Introduce la fecha de nacimiento: (yyyy-mm-dd)");
            if (!ValidarDatos.validarFecha(fechaNacimiento)) {
                System.out.println("La fecha es incorrecta");
            }
        } while (!ValidarDatos.validarFecha(fechaNacimiento));

        return fechaNacimiento;
    }

    /**
     * Solicita al usuario que ingrese el tipo de persona (socio, socio premium,
     * monitor) y valida que sea correcto.
     *
     * Este método solicita al usuario que ingrese un tipo de persona en formato
     * de texto (socio, socio premium, monitor), lo formatea y valida que el
     * tipo ingresado sea uno de los valores permitidos. Si el tipo de persona
     * no es válido, se muestra un mensaje de error y se vuelve a solicitar la
     * entrada hasta que el tipo ingresado sea válido.
     *
     * @return El tipo de persona ingresado por el usuario, validado y
     * formateado.
     */
    public static String inicializarTipoPersona() {
        String tipoPersona;

        do {
            tipoPersona = LeerDatosTeclado.leerString("Introduce el tipo de persona: (socio, socio premium, monitor)");
            tipoPersona = Utilidades.formatearString(tipoPersona);

            if (!ValidarDatos.validarTipoPersona(tipoPersona)) {
                System.out.println("El tipo de persona no es correcto");
            }
        } while (!ValidarDatos.validarTipoPersona(tipoPersona));

        return tipoPersona;
    }
    
    /**
     * Solicita al usuario que ingrese una categoría de especialidad y valida
     * que sea correcta.
     *
     * Este método solicita al usuario que ingrese una categoría de especialidad
     * en formato de texto (FITNESS, PISCINA, CICLISMO, HIIT, CORE, BAILE,
     * BODYCARE, CARDIO), la formatea y valida que sea una de las categorías
     * permitidas. Si la categoría ingresada no es válida, se muestra un mensaje
     * de error y se vuelve a solicitar la entrada hasta que la categoría sea
     * válida.
     *
     * @return La especialidad seleccionada por el usuario.
     */
    public static Especialidad inicializarEspecialidad(){
        String categoria;
        
        do {
            categoria = LeerDatosTeclado.leerString("Introduce la categoria: (FITNESS, PISCINA, CICLISMO, HIIT, CORE, BAILE, BODYCARE, CARDIO)");
            categoria = Utilidades.formatearString(categoria);
            
            if(!ValidarDatos.validarEspecialidad(categoria)){
                System.out.println("La categoria no es valida");
            }
        } while (!ValidarDatos.validarEspecialidad(categoria));
        
        return Especialidad.valueOf(categoria);
    }
    
    /**
     * Filtra una lista de personas, separando monitores de otros tipos de
     * personas.
     *
     * Este método recorre una lista de objetos {@code Persona} y filtra
     * aquellas que sean monitores o no, dependiendo del valor del parámetro
     * {@code esMonitor}. Si {@code esMonitor} es {@code true}, el método
     * devolverá una lista que solo contiene monitores. Si {@code esMonitor} es
     * {@code false}, la lista resultante contendrá solo las personas que no son
     * monitores.
     *
     * @param listaPersona La lista de personas a filtrar.
     * @param esMonitor Un valor booleano que indica si se deben filtrar los
     * monitores ({@code true}) o el resto de personas ({@code false}).
     *
     * @return Una lista de objetos {@code Persona} que contiene las personas
     * filtradas según el tipo indicado por {@code esMonitor}.
     */
    public static ArrayList filtrarPersonas(ArrayList <Persona> listaPersona, boolean esMonitor){
        ArrayList <Persona> filtrado = new ArrayList();
        
        for(Persona persona : listaPersona){
            if(!esMonitor && !(persona instanceof Monitor)){
                filtrado.add(persona);
            }else if(esMonitor && persona instanceof Monitor){
                filtrado.add(persona);
            }
        }
        
        return filtrado;
    }
    
    /**
     * Filtra una lista de personas buscando coincidencias por nombre o DNI.
     *
     * Este método recorre una lista de objetos {@code Persona} y añade a una
     * lista de coincidencias aquellas personas cuyo nombre o DNI coincidan con
     * el valor proporcionado en el parámetro {@code nombre}.
     *
     * @param lista La lista de personas a filtrar.
     * @param nombre El nombre o el DNI que se buscará en la lista de personas.
     *
     * @return Una lista de objetos {@code Persona} que contiene las personas
     * cuya información coincide con el nombre o DNI proporcionado.
     */
    public static ArrayList listaCoincidencias(ArrayList <Persona> lista, String nombre){
        ArrayList <Persona> coincidencias = new ArrayList();
        
        for(Persona persona : lista){
            String coincideNombre = Utilidades.formatearString(persona.getNombre());
            if (nombre.equals(coincideNombre) || nombre.equals(persona.getDNI())) {
                coincidencias.add(persona);
            }
        }
        
        return coincidencias;
    }
    
    /**
     * Filtra una lista de actividades buscando coincidencias por nombre.
     *
     * Este método recorre una lista de objetos {@code Actividad} y añade a una
     * lista de coincidencias aquellas actividades cuyo nombre coincida con el
     * valor proporcionado en el parámetro {@code nombre}.
     *
     * @param lista La lista de actividades a filtrar.
     * @param nombre El nombre de la actividad que se buscará en la lista.
     *
     * @return Una lista de objetos {@code Actividad} que contiene las
     * actividades cuyo nombre coincide con el proporcionado.
     */
    public static ArrayList listaCoincidenciasActividad(ArrayList<Actividad> lista, String nombre) {
        ArrayList<Actividad> coincidencias = new ArrayList();

        for (Actividad actividad : lista) {
            String coincideNombre = Utilidades.formatearString(actividad.getNombre());
            if (nombre.equals(coincideNombre)) {
                coincidencias.add(actividad);
            }
        }

        return coincidencias;
    }
    
}
