package grupo11.config;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Joaquín León Ulloa
 */
public abstract class Regex {
    private static final String NUMERO = "-?\\d+(\\.\\d+)?((e|E)-?\\d+)?";
    private static final String OPERADOR = "(\\+|-|\\*|/|\\^)";
    private static final String CELDA = "\\$[A-G][1-2]?[0-9]";
    private static final String FUNCION = "(sqr|rnd)";
    private static final String PARENTESIS = "\\(.*\\)";
        
    public static Matcher numero(String s) { return Pattern.compile(NUMERO).matcher(s); }
    public static Matcher operador(String s) { return Pattern.compile(OPERADOR).matcher(s); }
    public static Matcher celda(String s) { return Pattern.compile(CELDA).matcher(s); }
    public static Matcher funcion(String s) { return Pattern.compile(FUNCION).matcher(s); }
    public static Matcher parentesis(String s) { return Pattern.compile(PARENTESIS).matcher(s); }
}
