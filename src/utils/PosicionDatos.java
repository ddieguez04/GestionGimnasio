package utils;

import app.Actividad;
import app.Monitor;
import java.util.ArrayList;
import app.Persona;

public class PosicionDatos {
    /**
     * Busca la posición de una persona en la lista a partir de un nombre o DNI.
     *
     * Este método solicita al usuario un dato de búsqueda (nombre o DNI) a
     * través de un mensaje personalizado. Si el valor introducido es un DNI
     * válido y no se está buscando un monitor, se buscará la posición por DNI.
     * Si es un nombre válido, se buscará por nombre. Devuelve la posición de la
     * persona encontrada en la lista o -1 si no se encuentra.
     *
     * @param lista Lista de personas donde se realizará la búsqueda.
     * @param esMonitor Indica si se está buscando un monitor (true) o un socio
     * (false).
     * @param mensaje El mensaje mostrado al usuario para solicitar el dato de
     * búsqueda.
     * @return La posición de la persona en la lista, o -1 si no se encuentra.
     */
    public static int encontrarPersona(ArrayList <Persona> lista, boolean esMonitor, String mensaje){
        int posicion = -1;
        String persona = LeerDatosTeclado.leerString(mensaje);
        if(ValidarDatos.esDNI(persona) && !esMonitor){
            posicion = obtenerPosicionPorDNI(lista, persona);
        }else if(ValidarDatos.validarNombre(persona)){
            posicion = obtenerPosicionPorNombre(lista, persona);
        }
        
        return posicion;
    }
    
    /**
     * Busca la posición de una persona en la lista a partir de su DNI.
     *
     * Este método valida el DNI proporcionado. Si el DNI no es válido, solicita
     * un nuevo DNI al usuario. Luego, busca la persona en la lista utilizando
     * el DNI validado. Si se encuentra una coincidencia, se devuelve la
     * posición de la persona en la lista.
     *
     * @param lista Lista de personas donde se realizará la búsqueda.
     * @param dni El DNI de la persona a buscar.
     * @return La posición de la persona en la lista, o -1 si no se encuentra.
     */
    private static int obtenerPosicionPorDNI(ArrayList <Persona> lista, String dni){
        if(!ValidarDatos.validarNif(dni)){
            dni = InicializarDatos.inicializarDNI();
        }
        
        return mostrarCoincidencias(lista, dni);
    }
    
    /**
     * Busca la posición de una persona en la lista a partir de su nombre.
     *
     * Este método formatea el nombre proporcionado para asegurarse de que
     * coincide con el formato adecuado. Luego, busca la persona en la lista
     * utilizando el nombre formateado. Si se encuentra una coincidencia, se
     * devuelve la posición de la persona en la lista.
     *
     * @param lista Lista de personas donde se realizará la búsqueda.
     * @param nombre El nombre de la persona a buscar.
     * @return La posición de la persona en la lista, o -1 si no se encuentra.
     */
    private static int obtenerPosicionPorNombre(ArrayList <Persona> lista, String nombre){
        nombre = Utilidades.formatearString(nombre);
        
        return mostrarCoincidencias(lista, nombre);
    }

    /**
     * Muestra las coincidencias de personas en la lista basadas en un nombre y
     * permite seleccionar una de ellas.
     *
     * Este método busca todas las personas que coincidan con el nombre
     * proporcionado, muestra las opciones disponibles si hay más de una
     * coincidencia, y permite al usuario seleccionar una persona. Luego, valida
     * si la persona seleccionada es un monitor y obtiene la posición de esa
     * persona en la lista.
     *
     * @param lista Lista de personas en la que se buscarán las coincidencias.
     * @param nombre El nombre de la persona a buscar.
     * @return La posición de la persona seleccionada en la lista, o -1 si no se
     * encuentra.
     */
    private static int mostrarCoincidencias(ArrayList<Persona> lista, String nombre) {
        int seleccion, posicion = -1;
        boolean esMonitor = false;
        ArrayList <Persona> coincidencias = InicializarDatos.listaCoincidencias(lista, nombre);
        
        if(!coincidencias.isEmpty()){
            seleccion = 0;
            
            if(coincidencias.size() > 1){
                mostrarOpcionesSocios(coincidencias);
                
                seleccion = (LeerDatosTeclado.leerInt("Introduce el usuario que quieras seleccionar: ", 1, coincidencias.size())-1);
            }
            
            if(coincidencias.get(seleccion) instanceof Monitor){
                esMonitor = true;
            }
            
            String dniPersona = coincidencias.get(seleccion).getDNI();
            
            posicion = ValidarDatos.comprobarExistenciaNif(lista, dniPersona, esMonitor);
        }
        
        return posicion;
    }
    
    /**
     * Muestra las coincidencias de actividades en la lista basadas en un nombre
     * y permite seleccionar una de ellas.
     *
     * Este método busca todas las actividades que coincidan con el nombre
     * proporcionado, muestra las opciones disponibles si hay más de una
     * coincidencia, y permite al usuario seleccionar una actividad. Luego,
     * valida si la actividad seleccionada existe en la lista y obtiene su
     * posición.
     *
     * @param lista Lista de actividades en la que se buscarán las
     * coincidencias.
     * @param nombre El nombre de la actividad a buscar.
     * @return La posición de la actividad seleccionada en la lista, o -1 si no
     * se encuentra.
     */
    public static int mostrarCoincidenciasActividad(ArrayList<Actividad> lista, String nombre) {
        int seleccion, posicion = -1;
        
        ArrayList<Actividad> coincidencias = InicializarDatos.listaCoincidenciasActividad(lista, nombre);

        if (!coincidencias.isEmpty()) {
            seleccion = 0;

            if (coincidencias.size() > 1) {
                mostrarOpcionesActividad(coincidencias);

                seleccion = (LeerDatosTeclado.leerInt("Introduce el usuario que quieras seleccionar: ", 1, coincidencias.size()) - 1);
            }
            Actividad actividad = coincidencias.get(seleccion);

            posicion = ValidarDatos.comprobarExistenciaActividad(lista, actividad);
        }

        return posicion;
    }

    /**
     * Muestra las opciones disponibles de socios o monitores en una lista de
     * coincidencias.
     *
     * Este método recorre la lista de coincidencias, imprime el nombre, DNI y
     * el tipo de cada persona (socios o monitores) y muestra las opciones
     * numeradas para que el usuario pueda seleccionar la persona deseada.
     *
     * @param coincidencias Lista de personas (socios o monitores) que coinciden
     * con la búsqueda.
     */
    private static void mostrarOpcionesSocios(ArrayList <Persona> coincidencias){
        for (int i = 0; i < coincidencias.size(); i++) {
            if(coincidencias.get(i) instanceof Monitor){
                System.out.println((i + 1) + ". " + coincidencias.get(i).getNombre() + " con DNI: " + coincidencias.get(i).getDNI() + ", es monitor.");
            }else{
                System.out.println((i + 1) + ". " + coincidencias.get(i).getNombre() + " con DNI: " + coincidencias.get(i).getDNI() + ", es socio.");
            }
        }
    }
    
    /**
     * Muestra las opciones disponibles de actividades en una lista de
     * coincidencias.
     *
     * Este método recorre la lista de actividades coincidentes, imprimiendo el
     * nombre y detalles de cada actividad, y muestra las opciones numeradas
     * para que el usuario pueda seleccionar la actividad deseada.
     *
     * @param coincidencias Lista de actividades que coinciden con la búsqueda.
     */
    private static void mostrarOpcionesActividad(ArrayList<Actividad> coincidencias) {
        for (int i = 0; i < coincidencias.size(); i++) {
            System.out.println((i + 1) + ". " + coincidencias.get(i));
        }
    }
}
