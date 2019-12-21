package grupo11.ventana;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static grupo11.config.Tabla.*;

/**
 *
 * @author Joaquín León Ulloa
 */
public class HolderTabla {
    private static TableModel modelo = null;
    public static TableModel getModelo() {
        if (modelo != null) return modelo;
        else {
            modelo = new DefaultTableModel(data, HEADERS) {
                @Override
                public String getColumnName(int index) {
                    return HEADERS[index];
                }
                @Override
                public int getColumnCount() {
                    return HEADERS.length;
                }
                @Override
                public int getRowCount() {
                    return data.length;
                }
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column != 0;
                }
            };
            modelo.addTableModelListener(new Calculadora());
            return modelo;
        }
    }
}
