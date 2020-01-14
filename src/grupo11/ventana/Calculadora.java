package grupo11.ventana;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.util.regex.Matcher;

import static grupo11.util.HolderTabla.getModelo;
import static grupo11.config.Valores.data;
import grupo11.config.Regex;
import grupo11.util.Parser;

/**
 * 
 * Escucha a Planilla por cambios en las celdas y calcula las fórmulas
 * que ingresa el usuario
 *
 * @author Grupo 11
 */
public class Calculadora implements TableModelListener {
    private final Celda[][] data = {
        {null, new Celda(0, 1), new Celda(0, 2), new Celda(0, 3), new Celda(0, 4), new Celda(0, 5), new Celda(0, 6), new Celda(0, 7)},
        {null, new Celda(1, 1), new Celda(1, 2), new Celda(1, 3), new Celda(1, 4), new Celda(1, 5), new Celda(1, 6), new Celda(1, 7)},
        {null, new Celda(2, 1), new Celda(2, 2), new Celda(2, 3), new Celda(2, 4), new Celda(2, 5), new Celda(2, 6), new Celda(2, 7)},
        {null, new Celda(3, 1), new Celda(3, 2), new Celda(3, 3), new Celda(3, 4), new Celda(3, 5), new Celda(3, 6), new Celda(3, 7)},
        {null, new Celda(4, 1), new Celda(4, 2), new Celda(4, 3), new Celda(4, 4), new Celda(4, 5), new Celda(4, 6), new Celda(4, 7)},
        {null, new Celda(5, 1), new Celda(5, 2), new Celda(5, 3), new Celda(5, 4), new Celda(5, 5), new Celda(5, 6), new Celda(5, 7)},
        {null, new Celda(6, 1), new Celda(6, 2), new Celda(6, 3), new Celda(6, 4), new Celda(6, 5), new Celda(6, 6), new Celda(6, 7)},
        {null, new Celda(7, 1), new Celda(7, 2), new Celda(7, 3), new Celda(7, 4), new Celda(7, 5), new Celda(7, 6), new Celda(7, 7)},
        {null, new Celda(8, 1), new Celda(8, 2), new Celda(8, 3), new Celda(8, 4), new Celda(8, 5), new Celda(8, 6), new Celda(8, 7)},
        {null, new Celda(9, 1), new Celda(9, 2), new Celda(9, 3), new Celda(9, 4), new Celda(9, 5), new Celda(9, 6), new Celda(9, 7)},
        {null, new Celda(10, 1), new Celda(10, 2), new Celda(10, 3), new Celda(10, 4), new Celda(10, 5), new Celda(1, 6), new Celda(10, 7)},
        {null, new Celda(11, 1), new Celda(11, 2), new Celda(11, 3), new Celda(11, 4), new Celda(11, 5), new Celda(11, 6), new Celda(11, 7)},
        {null, new Celda(12, 1), new Celda(12, 2), new Celda(12, 3), new Celda(12, 4), new Celda(12, 5), new Celda(12, 6), new Celda(12, 7)},
        {null, new Celda(13, 1), new Celda(13, 2), new Celda(13, 3), new Celda(13, 4), new Celda(13, 5), new Celda(13, 6), new Celda(13, 7)},
        {null, new Celda(14, 1), new Celda(14, 2), new Celda(14, 3), new Celda(14, 4), new Celda(14, 5), new Celda(14, 6), new Celda(14, 7)},
        {null, new Celda(15, 1), new Celda(15, 2), new Celda(15, 3), new Celda(15, 4), new Celda(15, 5), new Celda(15, 6), new Celda(15, 7)},
        {null, new Celda(16, 1), new Celda(16, 2), new Celda(16, 3), new Celda(16, 4), new Celda(16, 5), new Celda(16, 6), new Celda(16, 7)},
        {null, new Celda(17, 1), new Celda(17, 2), new Celda(17, 3), new Celda(17, 4), new Celda(17, 5), new Celda(17, 6), new Celda(17, 7)},
        {null, new Celda(18, 1), new Celda(18, 2), new Celda(18, 3), new Celda(18, 4), new Celda(18, 5), new Celda(18, 6), new Celda(18, 7)},
        {null, new Celda(19, 1), new Celda(19, 2), new Celda(19, 3), new Celda(19, 4), new Celda(19, 5), new Celda(19, 6), new Celda(19, 7)},
        {null, new Celda(20, 1), new Celda(20, 2), new Celda(20, 3), new Celda(20, 4), new Celda(20, 5), new Celda(20, 6), new Celda(20, 7)},
        {null, new Celda(21, 1), new Celda(21, 2), new Celda(21, 3), new Celda(21, 4), new Celda(21, 5), new Celda(21, 6), new Celda(21, 7)},
        {null, new Celda(22, 1), new Celda(22, 2), new Celda(22, 3), new Celda(22, 4), new Celda(22, 5), new Celda(22, 6), new Celda(22, 7)},
        {null, new Celda(23, 1), new Celda(23, 2), new Celda(23, 3), new Celda(23, 4), new Celda(23, 5), new Celda(23, 6), new Celda(23, 7)},
        {null, new Celda(24, 1), new Celda(24, 2), new Celda(24, 3), new Celda(24, 4), new Celda(24, 5), new Celda(24, 6), new Celda(24, 7)},
    };
    public Calculadora() {}
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
            while (m.find()) {
                int x = Integer.parseInt(
                    String.valueOf(
                        Character.toUpperCase(m.group().charAt(1)) - 'A' + 1
                    )
                );
                int y = Integer.parseInt(m.group().substring(2)) - 1;
                c.linea = line;
                System.out.println(String.format("%d %d", x, y));
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
