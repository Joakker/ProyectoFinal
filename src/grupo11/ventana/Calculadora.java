package grupo11.ventana;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.util.regex.Matcher;

import static grupo11.util.HolderTabla.getModelo;
import grupo11.util.Regex;
import grupo11.util.Parser;

/**
 *
 * @author Joaquín León Ulloa
 */
public class Calculadora implements TableModelListener {    
    private double expresionNumerica(String s) {
        s = Parser.celda(s);
        s = Parser.constante(s);
        s = Parser.funcion(s);
        Matcher n = Regex.numero(s);
        Matcher o = Regex.operador(s);
        double resultado = 0;
        while (n.find()) {
            if (o.find(n.end())) {
                switch (o.group()) {
                    case "+":
                        resultado = Parser.numero(n) + Parser.numero(n, 0);
                        break;
                    case "-":
                        resultado = Parser.numero(n) - Parser.numero(n, 0);
                        break;
                    case "*":
                        resultado = Parser.numero(n) * Parser.numero(n, 1);
                        break;
                    case "/":
                        resultado = Parser.numero(n) / Parser.numero(n, 1);
                        break;
                    case "^":
                        resultado = Math.pow(Parser.numero(n), Parser.numero(n, 0));
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
