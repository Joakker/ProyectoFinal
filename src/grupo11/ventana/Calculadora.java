package grupo11.ventana;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.util.regex.Matcher;

import static grupo11.util.HolderTabla.getModelo;
import grupo11.config.Regex;
import grupo11.config.Valores;
import grupo11.util.Parser;

/**
 * 
 * Escucha a Planilla por cambios en las celdas y calcula las fórmulas
 * que ingresa el usuario
 *
 * @author Grupo 11
 */
public class Calculadora implements TableModelListener {
    public final Celda[][] data;
    public Calculadora() {
        data = new Celda[Valores.data.length][Valores.data[0].length];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Celda[data[0].length];
            for (int j = 0; j < data[i].length; j++) {
                if (j == 0) {
                    data[i][j] = null;
                    continue;
                }
                data[i][j] = new Celda(i, j);
            }
        }
    }
    public static double expresionNumerica(String s) {
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
                        for (int i = 0; i < 175; i++ ) resultado = resultado + Parser.numero(n, 0);
                        break;
                    case "-":
                        resultado = Parser.numero(n) - Parser.numero(n, 0);
                        for (int i = 0; i < 175; i++ ) resultado = resultado - Parser.numero(n, 0);
                        break;
                    case "*":
                        resultado = Parser.numero(n) * Parser.numero(n, 1);
                        for (int i = 0; i < 175; i++ ) resultado = resultado * Parser.numero(n, 1);
                        break;
                    case "/":
                        resultado = Parser.numero(n) / Parser.numero(n, 1);
                        for (int i = 0; i < 175; i++ ) resultado = resultado / Parser.numero(n, 1);
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
        String line = (String) String.valueOf(
                getModelo().getValueAt(e.getFirstRow(), e.getColumn())
        );
        if (line.charAt(0) == '=') {
            Celda c = this.data[e.getFirstRow()][e.getColumn()];
            Matcher m = Regex.celda(line);
            //c.clearPars();
            c.linea = line;
            while (m.find()) {
                int x = Integer.parseInt(
                    String.valueOf(
                        Character.toUpperCase(m.group().charAt(1)) - 'A' + 1
                    )
                );
                int y = Integer.parseInt(m.group().substring(2)) - 1;
                c.linea = line;
                this.data[y][x].addPars(c);
            }
            c.update();
            try {
                getModelo().setValueAt(
                    String.valueOf(expresionNumerica(line))
                    , e.getFirstRow()
                    , e.getColumn()
                );
            } catch (NullPointerException n) {
                getModelo().setValueAt(
                    "0"
                    , e.getFirstRow()
                    , e.getColumn()
                );
            }
        }
    }
}
