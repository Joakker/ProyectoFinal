package grupo11.util;

import grupo11.config.Regex;
import grupo11.ventana.HolderTabla;
import java.util.regex.Matcher;

/**
 *
 * @author Joaquín León Ulloa
 */
public abstract class Parser {
    private static String parseCelda(String s) {
        int col = (s.charAt(1) - 'A') + 1;
        int fil = Integer.parseInt(s.substring(2)) - 1;
        String resultado = null;
        Object reemplazo = HolderTabla.getModelo().getValueAt(fil, col);
        if (reemplazo instanceof Integer || reemplazo instanceof Double) resultado = String.valueOf(reemplazo);
        else if (reemplazo instanceof String) resultado = (String) reemplazo;
        return resultado == null? "0": resultado;
    }
    
    public static double numero(Matcher m) { return numero(m, 0); }
    public static double numero(Matcher m, double fallback) { return Double.parseDouble(m.group());}
    public static String valorCelda(String s) {
        Matcher c = Regex.celda(s);
        while (c.find()) s = s.replace(c.group(), Parser.parseCelda(c.group()));
        return s;
    }
    
}
