package grupo11.planillaexcel.ventana;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Joaquín León
 */
public class Calculadora implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
                TableModel modelo = (TableModel) e.getSource();
                String line = (String) modelo.getValueAt(e.getFirstRow(), e.getColumn());
                switch (line.charAt(0)) {
                        case '=':
                                System.out.println("Expression");
                }
                System.out.println(line.charAt(0));
        }
}
