package grupo11.config;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 
 * Define las regex que Parser utiliza para traducir el texto que el usuario
 * entra en alguna de las celdas de la planilla.
 *
 * @see grupo11.util.Parser
 * @author Joaquín León Ulloa
 */
public abstract class Regex {
    private static final String NUMERO      = "-?\\d+(\\.\\d+)?((e|E)-?\\d+)?";
    private static final String OPERADOR    = "(\\+|-|\\*|/|\\^)";
    private static final String CELDA       = "\\$[A-G][1-2]?[0-9]";
    private static final String FUNCION     = "(sqr|rnd|sin|cos|tan|abs)";
    private static final String PARENTESIS  = "\\((-?\\d+(\\.\\d+)?((e|E)-?\\d+)?)?\\)";
    private static final String CONSTANTE   = "(PI|E)";
        
    /**
     * 
     * @param s La línea de texto en la cual se buscarán números
     * @return Un objeto Matcher que detecta números en s
     */
    public static Matcher numero(String s) { return Pattern.compile(NUMERO).matcher(s); }
    /**
     * 
     * @param s La línea de texto en la cual se buscarán operadores aritméticos
     * @return Un objeto Matcher que detecta operadores en s
     */
    public static Matcher operador(String s) { return Pattern.compile(OPERADOR).matcher(s); }
    /**
     * 
     * @param s La línea de texto en la cual se buscarán coordenadas de celdas, de la forma $[A-G][1-25]
     * @return Un objeto Matcher que detecta coordenadas en s
     */
    public static Matcher celda(String s) { return Pattern.compile(CELDA).matcher(s); }
    /**
     * 
     * @param s La línea de texto en la cual se buscarán funciones
     * @return Un objeto Matcher que detecta nombres de funciones en s
     */
    public static Matcher funcion(String s) { return Pattern.compile(FUNCION).matcher(s); }
    /**
     * 
     * @param s La línea de texto en la cual se buscarán objetos entre paréntesis
     * @return Un objeto Matcher que detecta grupos entre paréntesis en s
     */
    public static Matcher parentesis(String s) { return Pattern.compile(PARENTESIS).matcher(s); }

    /**
     * 
     * @param s La línea de texto en la cual se buscarán los nombres de constantes
     * @return Un objeto Matcher que detecta constantes en s
     */
    public static Matcher constante(String s) { return Pattern.compile(CONSTANTE).matcher(s); }
}
