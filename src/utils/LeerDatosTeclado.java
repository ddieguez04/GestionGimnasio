package utils;

import java.util.Scanner;

public class LeerDatosTeclado {
    /**
     * Solicita al usuario que introduzca un número por teclado.
     *
     * Este método muestra un mensaje al usuario y espera la entrada de un
     * número válido. Si el usuario introduce un valor no numérico, se muestra
     * un mensaje de error y se vuelve a solicitar la entrada hasta que se
     * introduzca un número válido.
     *
     * @param mensaje El mensaje que se mostrará al usuario solicitando el
     * valor.
     * @return El valor introducido por el usuario.
     */
    public static double leerDouble(String mensaje){
        Scanner teclado = new Scanner(System.in);
        
        System.out.println(mensaje);
        while (!teclado.hasNextDouble()) {
            teclado.nextLine();
            System.out.println("No se ha introducido un double. Vuelve a introducir el valor ");
        }
        double valor = teclado.nextDouble();
        return valor;
    }
    
    public static double leerDouble(String mensaje, double minimo, String error) {
        double valor;

        do {
            valor = leerInt(mensaje);
            
            if (valor < minimo) {
                System.out.println(error);
            }

        } while (valor < minimo);

        return valor;
    }
    
    /**
     * Solicita al usuario que introduzca un número de tipo {@code int}.
     *
     * Este método muestra un mensaje al usuario y espera la introducción de un
     * número entero. Si la entrada no es un número válido de tipo {@code int},
     * se mostrará un mensaje de error y se volverá a solicitar la entrada hasta
     * que sea correcta.
     *
     * @param mensaje El mensaje mostrado al usuario solicitando la entrada.
     * @return El valor {@code int} introducido por el usuario.
     */
    public static int leerInt(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println(mensaje);
        while (!teclado.hasNextInt()) {
            teclado.nextLine();
            System.out.println("No se ha introducido un int. Vuelve a introducir el valor ");
        }
        int valor = teclado.nextInt();
        return valor;
    }
    
    /**
     * Solicita al usuario que introduzca un número de tipo {@code int} dentro
     * de un rango específico.
     *
     * Este método muestra un mensaje al usuario y espera la introducción de un
     * número entero. Si la entrada no es válida o no está dentro del rango
     * especificado, se vuelve a solicitar hasta que se introduzca un valor
     * correcto. Se utiliza un mensaje de error genérico si el valor está fuera
     * del rango.
     *
     * @param mensaje El mensaje mostrado al usuario solicitando la entrada.
     * @param minimo El valor mínimo aceptado (inclusive).
     * @param maximo El valor máximo aceptado (inclusive).
     * @return El valor {@code int} introducido por el usuario dentro del rango
     * especificado.
     */
    public static int leerInt(String mensaje, int minimo, int maximo) {
        return leerInt(mensaje, minimo, maximo, "El valor no tiene el rango adecuado.");
    }
    
    /**
     * Solicita al usuario que introduzca un número de tipo {@code int} dentro
     * de un rango específico, mostrando un mensaje de error personalizado si el
     * valor no está dentro del rango.
     *
     * Este método muestra un mensaje al usuario solicitando la entrada de un
     * número entero. Si el valor introducido no está dentro del rango
     * especificado por los parámetros {@code minimo} y {@code maximo}, se
     * muestra el mensaje de error proporcionado y se vuelve a solicitar el
     * valor hasta que sea válido.
     *
     * @param mensaje El mensaje mostrado al usuario para solicitar la entrada.
     * @param minimo El valor mínimo aceptado (inclusive).
     * @param maximo El valor máximo aceptado (inclusive).
     * @param error El mensaje de error personalizado que se mostrará si el
     * valor está fuera del rango.
     * @return El valor {@code int} introducido por el usuario dentro del rango
     * especificado.
     */
    public static int leerInt(String mensaje, int minimo, int maximo, String error) {
        int valor;

        do {
            valor = leerInt(mensaje);
            
            if (valor < minimo || valor > maximo) {
                System.out.println(error);
            }

        } while (valor < minimo || valor  > maximo);

        return valor;
    }
    
    /**
     * Solicita al usuario que introduzca un número de tipo {@code int} mayor o
     * igual que un valor mínimo, mostrando un mensaje de error personalizado si
     * el valor introducido es menor que el mínimo.
     *
     * Este método muestra un mensaje al usuario solicitando un número entero.
     * Si el número introducido es menor que el valor especificado por
     * {@code minimo}, se muestra el mensaje de error proporcionado y se vuelve
     * a solicitar la entrada hasta que se cumpla la condición.
     *
     * @param mensaje El mensaje mostrado al usuario para solicitar la entrada.
     * @param minimo El valor mínimo aceptado (inclusive).
     * @param error El mensaje de error que se mostrará si el valor introducido
     * es menor que el mínimo.
     * @return El valor {@code int} introducido por el usuario que cumple con la
     * condición mínima.
     */
    public static int leerInt(String mensaje, int minimo, String error) {
        int valor;

        do {
            valor = leerInt(mensaje);
            
            if (valor < minimo) {
                System.out.println(error);
            }

        } while (valor < minimo);

        return valor;
    }
    
    /**
     * Solicita al usuario que introduzca una cadena de texto mostrando un
     * mensaje personalizado.
     *
     * Este método imprime un mensaje por consola para solicitar al usuario una
     * entrada de texto y luego lee la línea introducida utilizando un
     * {@code Scanner}.
     *
     * @param mensaje El mensaje mostrado al usuario para solicitar la entrada.
     * @return La cadena de texto introducida por el usuario.
     */
    public static String leerString(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        
        String n;
        
        System.out.println(mensaje);
        n = teclado.nextLine();
        
        return n;
    }
    
    /**
     * Solicita al usuario una confirmación de tipo sí/no mostrando un mensaje
     * personalizado.
     *
     * Este método imprime un mensaje por consola y espera que el usuario
     * introduzca una respuesta afirmativa o negativa (específicamente "si" o
     * "no", sin importar mayúsculas/minúsculas). Repite la solicitud hasta que
     * la entrada sea válida.
     *
     * @param mensaje El mensaje mostrado al usuario para solicitar la
     * confirmación.
     * @return {@code true} si el usuario responde "si", {@code false} si
     * responde "no".
     */
    public static boolean leerConfirmacion(String mensaje){
        boolean comprobar = false;
        String respuesta;
        
        do {
            respuesta = leerString(mensaje);
            respuesta = Utilidades.formatearString(respuesta);
            
            if (respuesta.equals("SI")) {
                comprobar = true;
            } else if (respuesta.equals("NO")) {
                comprobar = false;
            }
            
            if(!(respuesta.equals("SI") || respuesta.equals("NO"))){
                System.out.println("No has introducido si o no");
            }
            
        } while (!(respuesta.equals("SI") || respuesta.equals("NO")));
        
        return comprobar;
    }
}
