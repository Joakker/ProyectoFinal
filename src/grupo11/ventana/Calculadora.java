package grupo11.ventana;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.util.regex.Matcher;

import static grupo11.ventana.HolderTabla.getModelo;
import grupo11.config.Regex;

/**
 *
 * @author Joaquín León Ulloa
 */
public class Calculadora implements TableModelListener {
    
    private double crearNum(Matcher m) {return Double.parseDouble(m.group());}
    private double crearNum(Matcher m, double fallback) { return m.find()? Double.parseDouble(m.group()): fallback;}
    
    private String parseCeldas(String s) {
        int col = (s.charAt(1) - 'A') + 1;
        int fil = Integer.parseInt(s.substring(2)) - 1;
        String resultado = null;
        Object reemplazo = HolderTabla.getModelo().getValueAt(fil, col);
        if (reemplazo instanceof Integer || reemplazo instanceof Double) resultado = String.valueOf(reemplazo);
        else if (reemplazo instanceof String) resultado = (String) reemplazo;
        return resultado == null? "0": resultado;
    }
    
    private String parseFunciones(String nombre, String args) {
        if (nombre.equals("rnd")) return String.valueOf(Math.random());
        args = args.replace('(', ' ').replace(')', ' ').strip();
        return "";
    }
    
    private String getCeldas(String s) {
        Matcher c = Regex.celda(s);
        while (c.find()) s = s.replace(c.group(), this.parseCeldas(c.group()));
        return s;
    }
    
    private String getFunciones(String s) {
        Matcher f = Regex.funcion(s);
        Matcher p = Regex.parentesis(s);
        while (f.find() && p.find()) s = s.replace(f.group(), this.parseFunciones(f.group(),p.group()));
        return s;
    }
    
    private double expresionNumerica(String s) {
        s = this.getCeldas(s);
        s = this.getFunciones(s);
        Matcher n = Regex.numero(s);
        Matcher o = Regex.operador(s);
        double resultado = 0;
        while (n.find()) {
            if (o.find(n.end())) {
                switch (o.group()) {
                    case "+":
                        resultado = this.crearNum(n) + this.crearNum(n, 0);
                        break;
                    case "-":
                        resultado = this.crearNum(n) - this.crearNum(n, 0);
                        break;
                    case "*":
                        resultado = this.crearNum(n) * this.crearNum(n, 1);
                        break;
                    case "/":
                        resultado = this.crearNum(n) / this.crearNum(n, 1);
                        break;
                    case "^":
                        resultado = Math.pow(this.crearNum(n), this.crearNum(n, 0));
                        break;
                }
            } else {
                resultado = Double.parseDouble(n.group());
            }
        }
        return resultado;
    }
        
    @Override
    public void tableChanged(TableModelEvent e) {
        String line = (String) getModelo().getValueAt(e.getFirstRow(), e.getColumn());
        if (line.charAt(0) == '=') getModelo().setValueAt(
                String.valueOf(this.expresionNumerica(line))
                , e.getFirstRow()
                , e.getColumn()
        );
    }

}
