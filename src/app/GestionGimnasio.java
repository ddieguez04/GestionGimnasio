package app;

import estadisticas.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import utils.*;

public class GestionGimnasio {
    private static ArrayList <Persona> listaPersonas = new ArrayList();
    private static ArrayList <Actividad> listaActividad = new ArrayList();
    
    public static void main(String[] args) {
        prueba();
        
        int menuPrincipal;
        do{
            System.out.println("1. Gestionar usuarios (socios y monitores).");
            System.out.println("2. Gestionar actividades.");
            System.out.println("3. Consultas y estadísticas.");
            System.out.println("4. Salir.");
            menuPrincipal = LeerDatosTeclado.leerInt("Elige una de las opciones", 1, 4);
            switch (menuPrincipal) {
                case 1 -> gestionUsuarios();
                case 2 -> gestionActividades();
                case 3 -> opcionesEstadisticas();
                default -> System.out.println("Saliste de la aplicación");
            }
        }while(menuPrincipal!=4);
    }
    
    /**
     * Muestra un submenú para gestionar personas dentro del sistema,
     * permitiendo dar de alta o baja a socios y monitores, así como realizar
     * gestiones específicas sobre ellos.
     */
    private static void gestionUsuarios() {
        System.out.println("1. Altas personas(socios o monitores).");
        System.out.println("2. Baja personas.");
        System.out.println("3. Gestionar socios.");
        System.out.println("4. Gestionar monitores.");
        System.out.println("5. Inactivar socios automáticamente.");
        int submenuOpcionUno = LeerDatosTeclado.leerInt("Elige una opción: [1-5]", 1, 5);
        switch (submenuOpcionUno) {
            case 1 -> altaPersonas();
            case 2 -> bajaPersonas();
            case 3 -> gestionarSocios();
            case 4 -> gestionarMonitores();
            case 5 -> inactivarSociosAutomaticamente();
        }
    }
    
    /**
     * Muestra un submenú para la gestión de actividades en el sistema,
     * permitiendo al usuario crear una nueva actividad o eliminar una
     * existente.
     */
    private static void gestionActividades() {
        System.out.println("1. Nueva actividad.");
        System.out.println("2. Eliminar actividad.");
        int submenuOpcionDos = LeerDatosTeclado.leerInt("Elige una opción: [1-2]", 1, 2);
        if (submenuOpcionDos == 1) {
            nuevaActividad();
        } else {
            eliminarActividad();
        }
    }

    /**
     * Muestra un submenú con opciones de consulta y estadísticas sobre
     * personas, actividades y monitores registrados en el sistema.
     */
    private static void opcionesEstadisticas() {
        System.out.println("1. Listar personas existentes.");
        System.out.println("2. Listar las n mejores actividades.");
        System.out.println("3. Listar las n mejores actividades por categoría.");
        System.out.println("4. Listar las n mejores actividades por cantidad de kcal.");
        System.out.println("5. Listar los n mejores monitores.");
        int submenuOpcionTres = LeerDatosTeclado.leerInt("Elige una opción: [1-5]", 1, 5);
        switch (submenuOpcionTres) {
            case 1 -> listaPersonas();
            case 2 -> listaMejoresActividades();
            case 3 -> listaActividadesCategoria();
            case 4 -> listaActividadesKcal();
            case 5 -> listaMejoresMonitores();
        }
    }

    /**
     * Da de alta a una nueva persona en el sistema, ya sea socio, socio
     * premium o monitor.
     * 
     * <p>Primero se determina el tipo de persona a registrar y se filtra la lista
     * de personas según corresponda. Luego, se solicita y valida el DNI para
     * evitar duplicados. A continuación, se recopilan los datos personales
     * comunes (nombre, dirección, etc.).</p>
     * 
     * <p>Dependiendo del tipo de persona seleccionado, se instancia un objeto de
     * tipo {@code Socio}, {@code SocioPremium} o {@code Monitor}, y se añade a
     * la lista global {@code listaPersonas}.</p>
     * 
     * En el caso de los monitores, también se solicita y registra el sueldo.
     */
    private static void altaPersonas(){
        Persona p;
        ArrayList lista;
        
        String tipoPersona = InicializarDatos.inicializarTipoPersona();
        
        String nombre = InicializarDatos.inicializarNombre();
        
        if(tipoPersona.equals("SOCIO") || tipoPersona.equals("SOCIOPREMIUM")){
            lista = InicializarDatos.filtrarPersonas(listaPersonas, false);
        }else{
            lista = InicializarDatos.filtrarPersonas(listaPersonas, true);
        }
        
        String DNI;
        do{
            DNI = InicializarDatos.inicializarDNI();
            if(ValidarDatos.comprobarExistenciaNif(lista, DNI) != -1){
                System.out.println("El DNI ya lo tiene otra persona.");
            }
        }while(ValidarDatos.comprobarExistenciaNif(lista, DNI) != -1);
        
        String direccion = InicializarDatos.inicializarDireccion();
        String localidad = InicializarDatos.inicializarGeneral("Introduce la localidad: ");
        String provincia = InicializarDatos.inicializarGeneral("Introduce la provincia: ");
        String codigoPostal = InicializarDatos.inicializarCodigoPostal();
        String telefono = InicializarDatos.inicializarTelefono();
        String fechaNacimiento = InicializarDatos.inicializarFechaNacimiento();
        
        
        if(tipoPersona.equals("SOCIO")){
            p = new Socio(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaNacimiento);
            listaPersonas.add(p);
        }else if(tipoPersona.equals("MONITOR")){
            double sueldo = InicializarDatos.inicializarSueldo();
            p = new Monitor(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaNacimiento, sueldo);
            listaPersonas.add(p);
        }else{
            p = new SocioPremium(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaNacimiento);
            listaPersonas.add(p);
        }
    }
    
    /**
     * Da de baja a una persona registrada en el sistema a partir de su DNI.
     *
     * <p>Primero se verifica si hay personas registradas. Si la lista está vacía,
     * se notifica al usuario. En caso contrario, se solicita al usuario que
     * introduzca un DNI válido y se busca la persona correspondiente en
     * {@code listaPersonas}.</p>
     *
     * Si se encuentra una coincidencia, se procede a confirmar su baja mediante
     * el método confirmarBaja(int posicion). Si el DNI no existe, se
     * informa al usuario y se vuelve a solicitar.
     */
    private static void bajaPersonas() {
        if(listaPersonas.isEmpty()){
            System.out.println("No hay personas.\n");
        }else{
            int posicion = PosicionDatos.encontrarPersona(listaPersonas, false, "Introduce el DNI de la persona: ");
            
            if (posicion != -1) {
                confirmarBaja(posicion);
            } else {
                System.out.println("No existe el NIF");
            }
        }
    }
    
    /**
     * Confirma y ejecuta la baja de una persona de la lista de personas
     * registradas.
     *
     * Se solicita confirmación al usuario antes de eliminar a la persona
     * identificada por su posición en la lista {@code listaPersonas}. Si el
     * usuario confirma, se elimina a la persona y se notifica el éxito de la
     * operación. En caso contrario, se cancela la baja y se informa al usuario.
     *
     * @param posicion Posición de la persona en la lista {@code listaPersonas}
     * que se desea eliminar.
     */
    private static void confirmarBaja(int posicion) {
        String nombre = listaPersonas.get(posicion).getNombre();

        boolean decision = LeerDatosTeclado.leerConfirmacion("¿Seguro que quieres dar de baja a: " + nombre + "? (SI/NO)");

        if (decision) {
            System.out.println("Diste de baja a: " + nombre);
            listaPersonas.remove(posicion);
        } else {
            System.out.println("No diste de baja a: " + nombre);
        }
    }
    
    /**
     * Gestiona las acciones disponibles para un socio del sistema, puediendo
     * mostrar su lista de actividades, añadir una actividad 
     * de {@code listaActividades}, eliminarse de una actividad
     * y valorar actividades.
     *
     * Primero se filtran los socios de la lista general de personas. Si existen
     * socios, se solicita al usuario que introduzca el nombre o DNI para
     * identificar al socio. Si se encuentra, se marca como activo, se actualiza
     * su fecha de último acceso y se muestra un submenú.
     */
    private static void gestionarSocios(){
        ArrayList <Persona> listaSocios = InicializarDatos.filtrarPersonas(listaPersonas, false);

        if(!listaSocios.isEmpty()){
            int posicion = PosicionDatos.encontrarPersona(listaSocios, false, "Introduce el nombre o el DNI del socio: ");

            if (posicion != -1) {
                System.out.println();
                System.out.println("Bienvenido al perfil de " + listaSocios.get(posicion).getNombre() + ".");
                
                boolean esPremium = listaSocios.get(posicion) instanceof SocioPremium;
                Socio socio = (Socio) listaSocios.get(posicion);
                socio.setFechaUltimoAcceso(LocalDate.now());
                socio.setEstaActivo(true);
                
                int opcionMenuSocios;
                do {
                    opcionMenuSocios = menuSocios(esPremium);

                    switch (opcionMenuSocios) {
                        case 1 -> actividadesSocio(socio);
                        case 2 -> añadirActividad(socio);
                        case 3 -> eliminarActividad(socio);
                        case 4 -> valorarActividad(socio);
                        case 5 -> convertirPremium(socio);
                        default -> System.out.println("Saliste al menu principal.");
                    }
                } while (opcionMenuSocios != 6 && opcionMenuSocios != 5);
            } else {
                System.out.println("No se encontro al socio.");
            }
        }else{
            System.out.println("No hay socios.");
        }
    }
    
    /**
     * Muestra el menú de gestión de actividades para un socio y solicita al
     * usuario que elija una opción.
     *
     * El menú varía en función de si el socio es premium o no. Si no es
     * premium, se incluye la opción para convertir al socio en premium. En
     * ambos casos, se da la opción de salir del menú.
     * @param esPremium Indica si el socio es de tipo {@code SocioPremium}
     * @return El número de opción seleccionada por el usuario.
     */
    private static int menuSocios(boolean esPremium) {
        int opcionGestionarSocios;
        System.out.println("1. Mostrar lista de actividades.");
        System.out.println("2. Añadir actividad.");
        System.out.println("3. Eliminar actividad.");
        System.out.println("4. Valorar actividad.");
        if (!esPremium) {
            System.out.println("5. Convertir en premium.");
            System.out.println("6. Salir");
            
            opcionGestionarSocios = LeerDatosTeclado.leerInt("Elija una opción: [1-6]", 1, 6);
        } else {
            System.out.println("5. Salir");
            
            opcionGestionarSocios = LeerDatosTeclado.leerInt("Elija una opción: [1-5]", 1, 5);
            
            if (opcionGestionarSocios == 5) {
                opcionGestionarSocios = 6;
            }
        }
        return opcionGestionarSocios;
    }
    
    /**
     * Muestra la lista de actividades de un socio, incluyendo
     * información sobre cada una de ellas.
     *
     * Si el socio tiene actividades registradas, se muestra el nombre de cada
     * actividad, las calorías quemadas durante la actividad, su categoría, y la
     * duración total de todas las actividades realizadas por el socio. Si el
     * socio no tiene actividades registradas, se informa al usuario de que no
     * tiene actividades.
     *
     * @param socio El objeto {@code Socio} del cual se mostrarán las
     * actividades.
     */
    private static void actividadesSocio(Socio socio){
        if(!socio.listaActividad.isEmpty()){
            for (int i = 0; i < socio.listaActividad.size(); i++) {
                System.out.println("La actividad " + (i + 1) + " es:");
                System.out.println(socio.listaActividad.get(i).getNombre());
                System.out.println("Se queman: "+socio.listaActividad.get(i).getCalorias()+" kcal");
                System.out.println("Es de la categoria: "+socio.listaActividad.get(i).getCategoria()+"\n");
            }
            System.out.println("La duración total de la lista es de " + socio.getDuracionActividades() + " minutos.\n");
        }else{
            System.out.println("No tienes actividades. \n");
        }
    }
    
    /**
     * Permite a un socio añadir una actividad de la lista de actividades
     * disponibles.
     *
     * Si hay actividades disponibles, el socio puede seleccionar una de ellas.
     * Si la actividad seleccionada se añade correctamente a la lista de
     * actividades del socio, se informa al usuario del éxito de la operación.
     * Si la actividad no se puede añadir, se notifica al usuario. Si no hay
     * actividades disponibles en la lista, se informa al usuario de que no hay
     * actividades.
     *
     * @param socio El objeto {@code Socio} al que se le añadirá la actividad
     * seleccionada.
     */
    private static void añadirActividad(Socio socio){
        if(listaActividad.isEmpty()){
            System.out.println("No hay actividades.\n");
        }else{
            int numeroActividades = listaActividad.size();

            mostrarActividades(listaActividad);

            int actividadElegida = (LeerDatosTeclado.leerInt("Introduce una de las actividades de la lista: (1-" + numeroActividades + ")", 1, numeroActividades) - 1);

            boolean añadida = socio.addActividad(listaActividad.get(actividadElegida));

            if (añadida) {
                System.out.println("La actividad se ha añadido");
                System.out.println(listaActividad.get(actividadElegida)+"\n");
            } else {
                System.out.println("La actividad no se ha añadido\n");
            }
        }
    }
    
    /**
     * Muestra la lista de actividades disponibles, indicando si son premium o
     * no.
     *
     * Para cada actividad en la lista proporcionada, se muestra su nombre y se
     * indica si es una actividad premium o no. Las actividades premium se
     * marcan con un mensaje específico.
     *
     * @param lista La lista de actividades que se van a mostrar.
     */
    private static void mostrarActividades(ArrayList <Actividad> lista) {
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).getEsPremium()){
                System.out.println((i+1) + ". " + lista.get(i).getNombre()+", es premium");
            }else{
                System.out.println((i+1) + ". " + lista.get(i).getNombre()+", no es premium");
            }
        }
    }
    
    /**
     * Permite a un socio eliminar una actividad de su lista de actividades.
     *
     * Si el socio tiene actividades registradas, se muestra la lista de
     * actividades y el socio puede elegir una actividad para eliminarla. Si no
     * tiene actividades, se le informa que no tiene ninguna actividad.
     *
     * @param socio El objeto {@code Socio} del cual se eliminará la actividad
     * seleccionada.
     */
    private static void eliminarActividad(Socio socio){
        if(!socio.listaActividad.isEmpty()){
            int numeroActividades = socio.listaActividad.size();
            mostrarActividades(socio.listaActividad);
            
            int actividadElegida = (LeerDatosTeclado.leerInt("Introduce una de las actividades de la lista: (1-" + numeroActividades + ")", 1, numeroActividades)-1);
            
            socio.delActividad(socio.listaActividad.get(actividadElegida));
        }else{
            System.out.println("No tienes ninguna actividad.\n");
        }
    }
    
    /**
     * Permite a un socio valorar una de las actividades de su lista.
     *
     * Si el socio tiene actividades registradas, se muestra la lista de
     * actividades y el socio puede elegir una actividad para valorarla. Luego,
     * el socio puede proporcionar una calificación entre 1 y 10 para la
     * actividad seleccionada. Si la valoración se registra correctamente, se
     * informa al socio del éxito de la operación. Si no puede registrarse el
     * voto, se informa del error. Si el socio no tiene actividades, se le
     * informa que no tiene ninguna actividad.
     *
     * @param socio El objeto {@code Socio} que realizará la valoración de la
     * actividad seleccionada.
     */
    private static void valorarActividad(Socio socio){
        if(!socio.listaActividad.isEmpty()){
            int numeroActividades = socio.listaActividad.size();
            
            mostrarActividades(socio.listaActividad);
            
            int actividadElegida = (LeerDatosTeclado.leerInt("Introduce una de las actividades que quieres valorar: (1-" + numeroActividades + ")", 1, numeroActividades)-1);
            
            int voto = LeerDatosTeclado.leerInt("Introduce la nota que le quieres poner: (1-10)", 1, 10);
            boolean votado = socio.listaActividad.get(actividadElegida).votar(voto);
            
            if(votado){
                System.out.println("El voto se ha registrado\n");
            }else{
                System.out.println("El voto no se pudo registrar\n");
            }
        }else{
            System.out.println("No tienes ninguna actividad\n");
        }
    }
    
    /**
     * Convierte un socio en un socio premium.
     *
     * Este método convierte a un socio en un socio premium, creando un
     * nuevo objeto {@code SocioPremium} basado en el socio actual. Luego, se
     * reemplaza el objeto {@code Socio} original en la lista de personas por el
     * nuevo objeto {@code SocioPremium}, otorgando los beneficios
     * correspondientes a la categoría premium.
     *
     * @param socio El objeto {@code Socio} que será convertido a
     * {@code SocioPremium}.
     */
    private static void convertirPremium(Socio socio){
        SocioPremium socioPremium = new SocioPremium(socio);
        
        System.out.println("Ahora eres socio premium\n");
        
        listaPersonas.remove(socio);
        listaPersonas.add(socioPremium);
    }
    
    /**
     * Gestiona la interacción con los monitores registrados en el sistema.
     *
     * Este método muestra la lista de monitores registrados y permite la
     * búsqueda de un monitor por su nombre. Si el monitor existe, se le da la
     * bienvenida y se le permite acceder a un menú de opciones específicas para
     * monitores. Si no se encuentra al monitor o si no hay monitores
     * registrados, se informa al usuario correspondiente.
     */
    private static void gestionarMonitores(){
        ArrayList<Persona> listaMonitores = InicializarDatos.filtrarPersonas(listaPersonas, true);
        
        if(listaMonitores.isEmpty()){
            System.out.println("No hay monitores");
        }else{
            int posicion = PosicionDatos.encontrarPersona(listaMonitores, true, "Introduce el nombre del monitor: ");

            if (posicion != -1) {
                System.out.println();
                System.out.println("Bienvenido al perfil de " + listaMonitores.get(posicion).getNombre() + ".");

                Monitor monitor = (Monitor) listaMonitores.get(posicion);

                menuMonitor(monitor);
            } else {
                System.out.println("No se encontro al monitor.\n");
            }
        }
    }
    
    /**
     * Muestra un menú para gestionar las opciones disponibles para un monitor.
     *
     * Este método presenta un menú de opciones donde se puede actualizar el
     * sueldo del monitor, modificar la lista de especialidades, realizar una
     * valoración o salir al menú principal. El proceso se repite hasta que el
     * usuario elige salir.
     *
     * @param monitor El objeto {@code Monitor} que será gestionado a través de
     * las opciones del menú.
     */
    private static void menuMonitor(Monitor monitor) {
        int opcionGestionarMonitor;
        
        do {
            System.out.println("1. Actualizar sueldo.");
            System.out.println("2. Actualizar lista de especialidades.");
            System.out.println("3. Realizar valoración.");
            System.out.println("4. Salir");
            opcionGestionarMonitor = LeerDatosTeclado.leerInt("Elija una opción: [1-4]", 1, 4);
            
            switch (opcionGestionarMonitor) {
                case 1 -> { actualizarSueldo(monitor); }
                case 2 -> { actualizarEspecialidades(monitor); }
                case 3 -> { realizarValoracion(monitor); }
                default -> { System.out.println("Saliste al menu principal.\n"); }
            }
        } while (opcionGestionarMonitor != 4);
    }
    
    /**
     * Actualiza el sueldo de un monitor.
     *
     * Este método solicita al usuario que introduzca un nuevo sueldo para el
     * monitor, y luego actualiza el sueldo del monitor con el valor
     * ingresado.
     *
     * @param monitor El objeto {@code Monitor} cuyo sueldo será actualizado.
     */
    private static void actualizarSueldo(Monitor monitor){
        double sueldo = InicializarDatos.inicializarSueldo();
        
        monitor.setSueldo(sueldo);
    }
    
    /**
     * Muestra un menú para que un monitor pueda gestionar sus especialidades.
     * Permite añadir o eliminar especialidades de la lista del monitor.
     *
     * Este método proporciona un menú interactivo que permite al monitor añadir
     * o eliminar especialidades. Primero muestra las opciones disponibles y
     * luego ejecuta la acción correspondiente según la opción seleccionada.
     *
     * @param monitor El objeto {@code Monitor} cuyo listado de especialidades
     * se va a modificar.
     */
    private static void actualizarEspecialidades(Monitor monitor){
        int seleccion;
        
        do {
            System.out.println("1. Añadir especialidad");
            System.out.println("2. Eliminar especialidad");
            System.out.println("3. Salir");
            seleccion = LeerDatosTeclado.leerInt("Elige una opción: [1-3]", 1, 3);
            
            Especialidad [] arrayEspecialidad = monitor.getEspecialidad();
            
            int elementosArray = contarEspecialidades(arrayEspecialidad);
            
            Utilidades.ordenarArray(arrayEspecialidad);
            
            switch (seleccion) {
                case 1 -> { añadirEspecialidad(elementosArray, monitor, arrayEspecialidad); }
                case 2 -> { eliminarEspecialidad(elementosArray, arrayEspecialidad); }
                default -> { System.out.println("Saliste al menu de especialidad.\n"); }
            }
        } while(seleccion != 3);
    }

    /**
     * Cuenta el número de elementos no nulos en un array de especialidades.
     *
     * Este método recorre el array de especialidades y cuenta cuántos elementos
     * no son {@code null}. Se utiliza para determinar cuántas especialidades
     * válidas hay en el array.
     *
     * @param arrayEspecialidad El array de {@code Especialidad} que se va a
     * evaluar.
     * @return El número de elementos no nulos en el array de especialidades.
     */
    private static int contarEspecialidades(Especialidad[] arrayEspecialidad) {
        int elementosArray = 0;
        for (Especialidad elemento : arrayEspecialidad) {
            if (elemento != null) {
                elementosArray++;
            }
        }
        return elementosArray;
    }

    /**
     * Permite a un monitor añadir una nueva especialidad a su lista de
     * especialidades.
     *
     * Este método añade una especialidad al array de especialidades del monitor
     * si el monitor no tiene más de 3 especialidades. Si el monitor ya tiene
     * esa especialidad, no se añade. Si la lista de especialidades está llena,
     * el método informa al usuario de que no puede añadir más
     * especialidades.
     *
     * @param elementosArray El número de especialidades actualmente registradas
     * en el monitor.
     * @param monitor El objeto {@code Monitor} al cual se le va a añadir la
     * especialidad.
     * @param arrayEspecialidad El array de {@code Especialidad} donde se
     * almacenan las especialidades del monitor.
     */
    private static void añadirEspecialidad(int elementosArray, Monitor monitor, Especialidad[] arrayEspecialidad) {
        if(elementosArray < 3){
            Especialidad nombre = InicializarDatos.inicializarEspecialidad();
            
            if(!ValidarDatos.comprobarExistenciaEspecialidad(monitor, nombre)){
                arrayEspecialidad[elementosArray] = nombre;
            }else{
                System.out.println("Ya tiene esta especialidad.\n");
            }
        }else{
            System.out.println("No pueden añadir más de 3 especialidades.\n");
        }
    }

    /**
     * Permite eliminar una especialidad de la lista de especialidades de un
     * monitor.
     *
     * Si el monitor tiene especialidades, se muestra una lista numerada de las
     * especialidades existentes y el monitor puede seleccionar cuál eliminar.
     * Si no tiene especialidades, se informa al usuario.
     *
     * @param elementosArray El número de especialidades actualmente registradas
     * en el monitor.
     * @param arrayEspecialidad El array de {@code Especialidad} donde se
     * almacenan las especialidades del monitor.
     */
    private static void eliminarEspecialidad(int elementosArray, Especialidad[] arrayEspecialidad) {
        if(elementosArray >= 1){
            for (int i = 0; i < arrayEspecialidad.length; i++) {
                if (arrayEspecialidad[i] != null) {
                    System.out.println((i + 1) + ". " + arrayEspecialidad[i]);
                }
            }
            
            int especialidadEliminar = (LeerDatosTeclado.leerInt("¿Qué especialidad quieres eliminar? [1-"+elementosArray+"]", 1, elementosArray)-1);
            arrayEspecialidad[especialidadEliminar] = null;
        }else{
            System.out.println("No hay especialidades.\n");
        }
    }
    
    /**
     * Permite realizar una valoración de un monitor.
     *
     * La persona realiza una valoración sobre su experiencia con el monitor al
     * responder si le gusta o no. El resultado se guarda en el monitor
     * utilizando el método {@code meGusta}.
     *
     * @param monitor El objeto {@code Monitor} que será valorado.
     */
    private static void realizarValoracion(Monitor monitor){
        boolean decision = LeerDatosTeclado.leerConfirmacion("¿Te gusta el monitor "+monitor.getNombre()+"? (SI/NO)");
        monitor.meGusta(decision);
    }
    
    /**
     * Inactiva automáticamente a los socios que no han accedido en el último
     * mes.
     *
     * Este método recorre la lista de socios y verifica si su última fecha de
     * acceso es anterior a un mes respecto a la fecha actual. Si un socio no ha
     * accedido en al menos un mes, se inactiva automáticamente estableciendo su
     * estado de actividad a {@code false}.
     */
    private static void inactivarSociosAutomaticamente(){
        ArrayList <Persona> listaSocios = InicializarDatos.filtrarPersonas(listaPersonas, false);
        for(int i = 0; i < listaSocios.size(); i++){
            Socio socio = (Socio) listaSocios.get(i);

            if(socio.getEstaActivo()){
                int mesInactivo = (int) ChronoUnit.MONTHS.between(socio.getFechaUltimoAcceso(), LocalDate.now());

                if (mesInactivo >= 1) {
                    socio.setEstaActivo(false);
                }
            }
        }
    }
    
    /**
     * Crea una nueva actividad y la agrega a la lista de actividades si no
     * existe previamente.
     *
     * Este método solicita al usuario la información necesaria para crear una
     * nueva actividad, como el nombre, duración, calorías, categoría y si es
     * premium. Luego verifica si la actividad ya existe en la lista. Si no
     * existe, la actividad es añadida a la lista, de lo contrario, se informa
     * al usuario que la actividad no se pudo crear.
     */
    private static void nuevaActividad(){
        String nombre = InicializarDatos.inicializarGeneral("Introduce el nombre de la actividad: ");
        int duracion = LeerDatosTeclado.leerInt("Introduce la duración de la actividad: ", 2, 120, "La duración debe de ser mayor a 1 minuto y no exceder las 2 horas");
        int calorias = LeerDatosTeclado.leerInt("Introduce el número de calorías: ", 0, "No puedes introducir un número negativo");
        Especialidad categoria = InicializarDatos.inicializarEspecialidad();
        boolean decision = LeerDatosTeclado.leerConfirmacion("¿Es premium? (SI/NO)");
        
        Actividad actividad = new Actividad(nombre, duracion, calorias, categoria, decision);
        
        int existe = ValidarDatos.comprobarExistenciaActividad(listaActividad, actividad);
        
        if(existe == -1){
            listaActividad.add(actividad);
            System.out.println("Se creo\n");
        }else{
            System.out.println("No se creo\n");
        }
    }
    
    /**
     * Elimina una actividad de la lista de actividades si no está asignada a
     * ningún socio.
     *
     * Este método solicita al usuario el nombre de la actividad que desea
     * eliminar. Luego verifica si la actividad existe en la lista y, en caso
     * afirmativo, revisa si algún socio tiene dicha actividad registrada. Si
     * algún socio tiene la actividad, no se puede eliminar y se informa al
     * usuario. Si la actividad no está asignada a ningún socio, se elimina de
     * la lista de actividades.
     */
    private static void eliminarActividad(){
        if(listaActividad.isEmpty()){
            System.out.println("No hay actividades.\n");
        }else{
            StringBuilder sociosConActividad = new StringBuilder();
            boolean esEliminado = true;
            String nombre = InicializarDatos.inicializarGeneral("Introduce el nombre de la actividad: ");
            nombre = Utilidades.formatearString(nombre);

            int existe = ValidarDatos.comprobarExistenciaActividad(listaActividad, nombre);
            
            if (existe != -1) {
                for (int i = 0; i < listaPersonas.size(); i++) {
                    if (listaPersonas.get(i) instanceof Socio) {
                        Socio s = (Socio) listaPersonas.get(i);
                        for (int j = 0; j < s.listaActividad.size(); j++) {
                            Actividad actividad = s.listaActividad.get(j);

                            if (listaActividad.get(existe).equals(actividad)) {
                                esEliminado = false;
                                sociosConActividad.append(s.getNombre() + ". ");
                            }
                        }
                    }
                }

                if (!esEliminado) {
                    System.out.println("No se puede eliminar porque los siguientes usuarios tienen la actividad "+listaActividad.get(existe).getNombre()+":");
                    System.out.println(sociosConActividad + "\n");
                } else {
                    listaActividad.remove(existe);
                    System.out.println("Se ha eliminado la actividad\n");
                }
            } else {
                System.out.println("No existe ninguna actividad con ese nombre\n");
            }
        }
    }

    /**
     * Muestra una lista de personas según el tipo seleccionado por el usuario.
     *
     * Este método permite al usuario elegir entre mostrar todas las personas,
     * solo monitores, solo socios o solo socios premium. Si no hay personas
     * registradas, se informa al usuario. La opción seleccionada se utiliza
     * para filtrar y mostrar los datos correspondientes.
     */
    private static void listaPersonas(){
        if(listaPersonas.isEmpty()){
            System.out.println("No hay personas.\n");
        }else{
            System.out.println("1. Mostrar todas las personas existentes.");
            System.out.println("2. Mostrar solo monitores.");
            System.out.println("3. Mostrar solo socios.");
            System.out.println("4. Mostrar solo socios premium.");

            int seleccion = LeerDatosTeclado.leerInt("Introduce una de las opciones: (1-4)", 1 , 4);

            switch (seleccion) {
                case 1 -> { mostrarPersonasFiltradas(true, true, true); }
                case 2 -> { mostrarPersonasFiltradas(true, false, false); }
                case 3 -> { mostrarPersonasFiltradas(false, true, false); }
                case 4 -> { mostrarPersonasFiltradas(false, false, true); }
            }
        }
    }
    
    /**
     * Muestra la lista de actividades ordenadas por valoración.
     *
     * Si no hay actividades registradas, se informa al usuario. En caso
     * contrario, se ordenan de mayor a menor según su valoración y se muestran
     * por pantalla.
     */
    private static void listaMejoresActividades(){
        if(listaActividad.isEmpty()){
            System.out.println("No existe ninguna actividad.\n");
        }else{
            Collections.sort(listaActividad, new MejorActividadPorValoracion());
            System.out.println("Actividades ordenadas por valoracion: \n");

            for (Actividad actividad : listaActividad) {
                System.out.println(actividad + "\n");
            }
        }
    }
    
    /**
     * Muestra las actividades de una categoría específica, ordenadas por
     * valoración.
     *
     * Si no hay actividades registradas, informa al usuario. Si existen,
     * solicita una categoría al usuario, ordena las actividades por valoración
     * y muestra solo aquellas que coinciden con la categoría seleccionada. Si
     * no se encuentra ninguna actividad con esa categoría, lo indica al
     * usuario.
     */
    private static void listaActividadesCategoria(){
        if(listaActividad.isEmpty()){
            System.out.println("No se encontro ninguna actividad.\n");
        }else{
            boolean encontrado = false;
            Collections.sort(listaActividad, new MejorActividadPorValoracion());
            Especialidad categoria = InicializarDatos.inicializarEspecialidad();

            System.out.println("Actividades ordenadas por " + categoria + ": \n");

            for (Actividad actividad : listaActividad) {
                if (actividad.getCategoria().equals(categoria)) {
                    System.out.println(actividad + "\n");
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontro ninguna actividad con esa categoria.\n");
            }
        }
    }
    
    /**
     * Muestra las actividades ordenadas por la cantidad de calorías quemadas.
     *
     * Si no hay actividades registradas, informa al usuario. En caso contrario,
     * ordena la lista de actividades por la cantidad de kilocalorías y las
     * muestra por pantalla.
     */
    private static void listaActividadesKcal(){
        if(listaActividad.isEmpty()){
            System.out.println("No se encontro ninguna actividad.\n");
        }else{
            Collections.sort(listaActividad, new MejoresActividadesPorCantidadKcal());

            System.out.println("Actividades ordenadas por cantidad de kcal: \n");

            for (Actividad actividad : listaActividad) {
                System.out.println(actividad + "\n");
            }
        }
    }
    
    /**
     * Muestra la lista de monitores ordenados por su valoración.
     *
     * Si no hay monitores registrados, informa al usuario. En caso contrario,
     * se ordenan por su valoración de mayor a menor y se muestran por pantalla.
     */
    private static void listaMejoresMonitores(){
        ArrayList <Monitor> listaMonitores = InicializarDatos.filtrarPersonas(listaPersonas, true);
        
        if(listaMonitores.isEmpty()){
            System.out.println("No se encontro ningun monitor.\n");
        }else{
            Collections.sort(listaMonitores, new MejoresMonitoresPorValoracion());

            System.out.println("Monitores ordenados por valoracion: \n");

            for (Monitor monitor : listaMonitores) {
                System.out.println(monitor + "\n");
            }
        }
    }
    
    /**
     * Muestra por pantalla las personas filtradas según su tipo.
     *
     * Este método recorre la lista de personas y muestra aquellas que coincidan
     * con los tipos especificados mediante los parámetros:
     * {@code mostrarMonitores}, {@code mostrarSocios} y
     * {@code mostrarSociosPremium}. Si no se encuentra ninguna persona que
     * cumpla con los filtros, se notifica al usuario.
     *
     * @param mostrarMonitores si se deben mostrar los monitores
     * @param mostrarSocios si se deben mostrar los socios no premium
     * @param mostrarSociosPremium si se deben mostrar los socios premium
     */
    private static void mostrarPersonasFiltradas(boolean mostrarMonitores, boolean mostrarSocios, boolean mostrarSociosPremium){
        boolean encontrado = false;
        
        for(Persona persona : listaPersonas){
            boolean esMonitor = persona instanceof Monitor;
            boolean esSocio = persona instanceof Socio && !(persona instanceof SocioPremium);
            boolean esSocioPremium = persona instanceof SocioPremium;
            
            if ((mostrarMonitores && esMonitor) || (mostrarSocios && esSocio) || (mostrarSociosPremium && esSocioPremium)) {
                System.out.println(persona + "\n");
                encontrado = true;
            }
        }
        
        if(!encontrado){
            System.out.println("No hay personas con ese filtro\n");
        }
    }
    
    private static void prueba(){
        //Creamos socios
        Socio s1 = new Socio("Manolo Sanchez", "12345678Z", "Calle Cervantes", "Madrid", "La Rosaleda", "11111", "123456789", "2004-4-12");
        Socio s2 = new Socio("Manolo Gutierrez", "05696354J", "Calle Manoli", "Mallorca", "Mallorca", "11634", "123623934", "2005-4-12");
        Socio s3 = new Socio("Federico Garcia Lorca", "87654321X", "Calle Ganivel", "Granada", "Fuente Vaqueros", "12345", "669123445", "1998-6-5");
        
        //Creamos socios premium
        SocioPremium sp1 = new SocioPremium("Laura Martinez Ruiz", "88455593P", "Avenida del Sol", "Sevilla", "Sevilla", "41001", "678901234", "1995-12-15");
        SocioPremium sp2 = new SocioPremium("Carlos Gomez Perez", "39331921N", "Calle Mayor 22", "Madrid", "Madrid", "28010", "612345678", "1988-07-30");
        SocioPremium sp3 = new SocioPremium("Ana Lopez Garcia", "81815144G", "Carrer del Mar, 88", "Barcelona", "Barcelona", "08028", "634567890", "1992-03-22");
        
        //Creamos monitores
        Monitor m1 = new Monitor("Albert Einstein", "11111111H", "Calle Munsterplatz", "Alemania", "Ulm", "56434", "314159265", "1979-3-14", 3000.14);
        Monitor m2 = new Monitor("Robert De Niro", "57305590D", "Calle Broadway", "Nueva York", "Manhattan", "10000", "739846215", "1943-8-17", 1810);
        Monitor m3 = new Monitor("Robert Lewandowski", "98765432M", "Calle Marszalkowska", "Polonia", "Varsovia", "93455", "347124765", "1988-8-21", 1237);
        
        //No se puede validar si una Persona es igual que una de la lista, por lo que se podrían crear dos monitores iguales (de esta forma, en la aplicacion no se puede).
        Monitor m4 = new Monitor("Federico Garcia Lorca", "87654321X", "Calle Ganivel", "Granada", "Fuente Vaqueros", "12345", "669123445", "1998-6-5", 1200);
        Monitor m5 = new Monitor("Federico Garcia Lorca", "87654321X", "Calle Ganivel", "Granada", "Fuente Vaqueros", "12345", "669123445", "1998-6-5", 1200);
        
        //Creamos actividades
        Actividad a1 = new Actividad("Cardio extremo", 30, 60, Especialidad.FITNESS, true);
        Actividad a2 = new Actividad("Fuerza total", 45, 55, Especialidad.FITNESS, false);

        Actividad a3 = new Actividad("Aqua Zumba", 40, 30, Especialidad.PISCINA, true);
        Actividad a4 = new Actividad("Natación libre", 60, 25, Especialidad.PISCINA, false);

        Actividad a5 = new Actividad("Spinning pro", 50, 45, Especialidad.CICLISMO, true);
        Actividad a6 = new Actividad("Ruta indoor", 35, 40, Especialidad.CICLISMO, false);

        Actividad a7 = new Actividad("HIIT intenso", 25, 35, Especialidad.HIIT, true);
        Actividad a8 = new Actividad("HIIT básico", 30, 30, Especialidad.HIIT, false);

        Actividad a9 = new Actividad("Core avanzado", 40, 45, Especialidad.CORE, true);
        Actividad a10 = new Actividad("Entrenamiento abdominal", 30, 50, Especialidad.CORE, false);

        Actividad a11 = new Actividad("Zumba dance", 25, 50, Especialidad.BAILE, true);
        Actividad a12 = new Actividad("Bailes latinos", 45, 60, Especialidad.BAILE, false);

        Actividad a13 = new Actividad("Stretch y Relax", 60, 30, Especialidad.BODYCARE, true);
        Actividad a14 = new Actividad("Pilates suave", 45, 40, Especialidad.BODYCARE, false);

        Actividad a15 = new Actividad("Cardio funcional", 35, 55, Especialidad.CARDIO, true);
        
        //No se puede validar si una Actividad es igual que una de la lista, por lo que se podrían crear dos actividades iguales (de esta forma, en la aplicacion no se puede).
        Actividad a16 = new Actividad("Maratón en cinta", 50, 50, Especialidad.CARDIO, false);
        Actividad a17 = new Actividad("Maratón en cinta", 50, 50, Especialidad.CARDIO, false);
        
        //Añadimos las personas a la lista personas
        listaPersonas.add(s1);
        listaPersonas.add(s2);
        listaPersonas.add(s3);
        listaPersonas.add(sp1);
        listaPersonas.add(sp2);
        listaPersonas.add(sp3);
        listaPersonas.add(m1);
        listaPersonas.add(m2);
        listaPersonas.add(m3);
        listaPersonas.add(m4);
        listaPersonas.add(m5);
        
        //Añadimos las actividades a la lista actividades
        listaActividad.add(a1);
        listaActividad.add(a2);
        listaActividad.add(a3);
        listaActividad.add(a4);
        listaActividad.add(a5);
        listaActividad.add(a6);
        listaActividad.add(a7);
        listaActividad.add(a8);
        listaActividad.add(a9);
        listaActividad.add(a10);
        listaActividad.add(a11);
        listaActividad.add(a12);
        listaActividad.add(a13);
        listaActividad.add(a14);
        listaActividad.add(a15);
        listaActividad.add(a16);
        listaActividad.add(a17);
        
        //Añadimos actividades a socios
        s1.addActividad(a2);
        s1.addActividad(a4);
        s1.addActividad(a6);

        s2.addActividad(a8);
        s2.addActividad(a10);
        s2.addActividad(a12);

        s3.addActividad(a14);
        s3.addActividad(a16);

        //Añadimos actividades a socios premium
        sp1.addActividad(a1);
        sp1.addActividad(a2);
        sp1.addActividad(a3);
        sp1.addActividad(a4);
        sp1.addActividad(a5);
        sp1.addActividad(a6);

        sp2.addActividad(a7);
        sp2.addActividad(a8);
        sp2.addActividad(a9);
        sp2.addActividad(a10);
        sp2.addActividad(a11);
        sp2.addActividad(a12);

        sp3.addActividad(a13);
        sp3.addActividad(a14);
        sp3.addActividad(a15);

        sp3.addActividad(a16);
        sp3.addActividad(a17);

        sp1.setFechaRegistro(LocalDate.of(2024, 8, 1));
        sp1.setFechaUltimoAcceso(LocalDate.of(2025, 3, 7));
        
        s1.setFechaRegistro(LocalDate.of(2024, 8, 1));
        s1.setFechaUltimoAcceso(LocalDate.of(2025, 3, 7));
        //Valoramos monitores
        m1.meGusta(true);
        m1.meGusta(true);
        m1.meGusta(false);
        
        m2.meGusta(true);
       
        m3.meGusta(false);
        m3.meGusta(false);
        m3.meGusta(true);
        m3.meGusta(true);
        
        //Valoramos actividades
        a1.votar(5);
        a1.votar(6);

        a2.votar(8);
        a2.votar(7);
        a2.votar(9);

        a3.votar(6);

        a4.votar(7);
        a4.votar(6);

        a5.votar(9);
        a5.votar(8);
        a5.votar(10);

        a6.votar(4);
        a6.votar(5);

        a7.votar(10);
        a7.votar(9);

        a8.votar(6);

        a9.votar(7);
        a9.votar(8);
        a9.votar(6);

        a10.votar(5);

        a11.votar(8);
        a11.votar(9);

        a12.votar(7);
        a12.votar(7);

        a13.votar(9);
        a13.votar(8);
        a13.votar(10);

        a14.votar(6);

        a15.votar(8);
        a15.votar(9);
        a15.votar(7);

        a16.votar(7);
        a16.votar(6);
    }
}
