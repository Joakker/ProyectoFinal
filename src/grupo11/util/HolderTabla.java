package grupo11.util;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import grupo11.config.Valores;
import grupo11.ventana.Calculadora;

/**
 * 
 * Holder para el TableModel de la planilla
 *
 * @author Joaquín León Ulloa
 * @see grupo11.ventana.Calculadora
 */
public  abstract class HolderTabla {
    private static TableModel modelo = null;

    /**
     * Crea/Retorna un DefaultTableModel que reporta a una instancia de calculadora
     * @return El modelo de la planilla. Un objeto singleton
     */
    public static TableModel getModelo() {
        if (modelo != null) return modelo;
        else {
            modelo = new DefaultTableModel(Valores.data, Valores.HEADERS) {
                @Override
                public String getColumnName(int index) { return Valores.HEADERS[index]; }
                @Override
                public int getColumnCount() { return Valores.HEADERS.length; }
                @Override
                public int getRowCount() { return Valores.data.length; }
                @Override
                public boolean isCellEditable(int row, int column) { return column != 0; }
            };
            modelo.addTableModelListener(new Calculadora());
            return modelo;
        }
    }
}