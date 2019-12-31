package grupo11.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Joaquín León Ulloa
 */
public abstract class Regex {
    private static final String NUMERO = "-?\\d+(\\.\\d+)?(e-?\\d+)?";
    private static final String OPERADOR = "(\\+|-|\\*|/|\\^)";
    private static final String CELDA = "\\$[A-G][1-2]?[0-9]";
    private static final String FUNCION = "((sin|cos|tan|abs|sqr)\\(-?\\d+(\\.\\d+)?(e-?\\d+)?\\)|(rnd\\(\\)))";
    private static final String CONSTANTE = "(PI|E)";
    private static final String PARENTESIS = "\\(-?\\d+(\\.\\d+)?(e-?\\d+)?\\)";
        
    public static Matcher numero(String s)     { return Pattern.compile(NUMERO).matcher(s);     }
    public static Matcher operador(String s)   { return Pattern.compile(OPERADOR).matcher(s);   }
    public static Matcher celda(String s)      { return Pattern.compile(CELDA).matcher(s);      }
    public static Matcher funcion(String s)    { return Pattern.compile(FUNCION).matcher(s);    }
    public static Matcher parentesis(String s) { return Pattern.compile(PARENTESIS).matcher(s); }
    public static Matcher constante(String s)  { return Pattern.compile(CONSTANTE).matcher(s);  }
}
