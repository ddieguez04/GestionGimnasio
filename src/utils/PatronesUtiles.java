package utils;

import java.util.regex.Pattern;

public class PatronesUtiles {
    public static final String PATRON_GENERAL = "[a-zA-Z áéíóÁÉÍÓ]{3,50}";
    public static final String PATRON_DIRECCION = "[a-zA-Z, \\d]{3,50}";
    public static final String PATRON_NOMBRE = "[a-zA-Z áéíóÁÉÍÓ]{10,50}";
    public static final String PATRON_NIF = "\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]";
    public static final String PATRON_CODIGO_POSTAL = "[0-9]{5}";
    public static final String PATRON_TELEFONO = "[0-9]{9}";
    public static final String PATRON_NUMERO = "[0-9]";
    public static final String PATRON_NUMEROS = "\\d+";
    public static final String PATRON_CARACTER = "[A-Z]";
    public static final String PATRON_FECHA = "^(\\d{4})-(\\d{1,2})-(\\d{1,2})$";
    public static final Pattern TRABAJAR_FECHAS = Pattern.compile(PATRON_FECHA);
}
