package app;

/**
 * Esta clase representa a un socio premium en una organización, heredando de la
 * clase {@code Socio}. La representación de la clase usa los atributos
 * heredados de la clase {@code Socio}, como {@code fechaRegistro},
 * {@code fechaUltimoAcceso}, {@code estaActivo}, {@code cuota} y
 * {@code listaActividad}.
 *
 * El socio premium tiene las mismas características que un socio, pero
 * con algunas diferencias en la forma en que las actividades se gestionan. La
 * principal diferencia es que el socio premium no tiene restricción en cuanto a
 * la duración de las actividades que puede realizar, y la cuota se recalcula en
 * función de la actividad añadida.
 *
 * @author David Diéguez
 * @version 1.0
 */

public class SocioPremium extends Socio{
    /**
     * Crea un nuevo socio premium a partir de los datos proporcionados.
     *
     * Este constructor inicializa un objeto de tipo `SocioPremium` utilizando
     * los mismos parámetros que el constructor de la clase base `Socio`.
     *
     * @param nombre El nombre del socio premium.
     * @param DNI El DNI del socio premium.
     * @param direccion La dirección del socio premium.
     * @param localidad La localidad del socio premium.
     * @param provincia La provincia del socio premium.
     * @param codigoPostal El código postal del socio premium.
     * @param telefono El teléfono del socio premium.
     * @param fechaNacimiento La fecha de nacimiento del socio premium en
     * formato `yyyy-mm-dd`.
     */
    public SocioPremium(String nombre, String DNI, String direccion, String localidad, String provincia, String codigoPostal, String telefono, String fechaNacimiento){
        super(nombre, DNI, direccion, localidad, provincia, codigoPostal, telefono, fechaNacimiento);
    }
    
    /**
     * Crea un nuevo objeto {@code SocioPremium} copiando los datos de un socio
     * existente.
     *
     * Este constructor crea una nueva instancia de {@code SocioPremium}
     * utilizando los valores del {@code Socio} pasado como parámetro. Se copian
     * todos los atributos heredados de la clase {@code Socio} como la fecha de
     * registro, la fecha del último acceso, el estado de actividad, la cuota y
     * la lista de actividades.
     *
     * @param s El socio a copiar.
     */
    public SocioPremium(Socio s){
        super(s);
        s.getFechaRegistro();
        s.getFechaUltimoAcceso();
        s.getEstaActivo();
        s.getCuota();
        for(Actividad actividad : s.listaActividad){
            this.listaActividad.add(actividad);
        }
    }
    
    /**
     * Añade una actividad a la lista de actividades del socio.
     *
     * Este método agrega una actividad a la lista de actividades del socio y
     * recalcula la cuota en función de la duración de la actividad añadida.
     *
     * @param a La actividad que se va a añadir a la lista de actividades del
     * socio.
     * @return true si la actividad se ha añadido correctamente a la lista, de
     * lo contrario, false si no se pudo añadir.
     */
    @Override
    public boolean addActividad(Actividad a){
        boolean esAñadida = true;
        this.listaActividad.add(a);
        calcularCuota(a.getDuracion());
        return esAñadida;
    }
}
