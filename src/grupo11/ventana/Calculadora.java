package grupo11.ventana;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Joaquín León Ulloa
 */
public class Calculadora implements TableModelListener {
    
    private final TableModel modelo;
    private final Pattern numero = Pattern.compile("-?\\d+(\\.\\d+)?((e|E)-?\\d+)?");
    private final Pattern operador = Pattern.compile("(\\+|-|\\*|/)");
    private Matcher matchNumero ;
    private Matcher matchOperador;
    
    public Calculadora() { this.modelo = HolderTabla.getModelo(); }
    
    private double parseNumero() {return Double.parseDouble(matchNumero.group());}
    private double parseNumero(double fallback) { return matchNumero.find()? Double.parseDouble(matchNumero.group()): fallback;}
    
    private double expresionNumerica(String s) {
        matchNumero = numero.matcher(s);
        matchOperador = operador.matcher(s);
        double resultado = 0;
        while (matchNumero.find()) {
            if (matchOperador.find(matchNumero.end())) {
                switch (matchOperador.group()) {
                    case "+":
                        resultado = this.parseNumero() + this.parseNumero(0);
                        break;
                    case "-":
                        resultado = this.parseNumero() - this.parseNumero(0);
                        break;
                    case "*":
                        resultado = this.parseNumero() * this.parseNumero(1);
                        break;
                    case "/":
                        resultado = this.parseNumero() / this.parseNumero(1);
                }
            } else {
                resultado = Double.parseDouble(matchNumero.group());
            }
        }
        return resultado;
    }
        
    @Override
    public void tableChanged(TableModelEvent e) {
        String line = (String) modelo.getValueAt(e.getFirstRow(), e.getColumn());
        switch (line.charAt(0)) {
            case '=':
                modelo.setValueAt(
                    String.valueOf(
                        this.expresionNumerica(line))
                        ,e.getFirstRow()
                        , e.getColumn()
                );
                break;
            default:
        }
    }

}
