package grupo11.util;

import java.util.regex.Matcher;
import grupo11.config.Regex;

/**
 * Clase abstracta que hace el trabajo de analizar la entrada de texto
 * del usuario. Solamente se invoca si el texto comienza con un "="
 *
 * @author Joaquín León Ulloa
 * @see grupo11.ventana.Calculadora
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
    private static String parseFuncion(String nombre, String arg) {
        if ("rnd".equals(nombre)) return String.valueOf(Math.random());
        arg = arg.replaceFirst("\\(", " ").replaceFirst("\\)", " ").trim();
        double a = Double.parseDouble(arg);
        switch (nombre) {
            case "sin": return String.valueOf(Math.sin(a));
            case "cos": return String.valueOf(Math.cos(a));
            case "tan": System.out.println(Math.tan(a)); return String.valueOf(Math.tan(a));
            case "abs": return String.valueOf(Math.abs(a));
            case "sqr": return a > 0D ? String.valueOf(Math.sqrt(a)): "0";
            default: return "0";
        }
    }
    
    public static double numero(Matcher m) { return Double.parseDouble(m.group()); }
    public static double numero(Matcher m, double fallback) {
        return m.find()? Double.parseDouble(m.group()): fallback;
    }
    public static String celda(String s) {
        Matcher c = Regex.celda(s);
        while (c.find()) s = s.replace(c.group(), Parser.parseCelda(c.group()));
        return s;
    }
    public static String funcion(String s) {
        Matcher f = Regex.funcion(s);
        Matcher p = Regex.parentesis(s);
        while (f.find()){
            String a = p.find()? p.group(): "()";
            s = s.replace(f.group(), parseFuncion(f.group(), a)).replace(a, "");
        }
        return s;
    }
    
    public static String constante(String s) {
        Matcher k = Regex.constante(s);
        while (k.find()) {
            switch (k.group()) {
                case "PI": s = s.replace(k.group(), String.valueOf(Math.PI)); break;
                case "E": s = s.replace(k.group(), String.valueOf(Math.E)); break;
            }
        }
        return s;
    }
    
}
