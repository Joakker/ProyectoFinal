package grupo11.planillaexcel.ventana;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Joaquín León Ulloa
 */
public class Planilla extends JPanel {
    public JTable tabla;
    private static final String[] headersColumna = {
        "", "A", "B", "C", "D", "E", "F", "G"
    };
    private static Object[][] dataTabla = {
        {1, null, null, null, null, null, null, null},
        {2, null, null, null, null, null, null, null},
        {3, null, null, null, null, null, null, null},
        {4, null, null, null, null, null, null, null},
        {5, null, null, null, null, null, null, null},
        {6, null, null, null, null, null, null, null},
        {7, null, null, null, null, null, null, null},
        {8, null, null, null, null, null, null, null},
        {9, null, null, null, null, null, null, null},
        {10,null, null, null, null, null, null, null},
        {11,null, null, null, null, null, null, null},
        {12,null, null, null, null, null, null, null},
        {13,null, null, null, null, null, null, null},
        {14,null, null, null, null, null, null, null},
        {15,null, null, null, null, null, null, null},
        {16,null, null, null, null, null, null, null},
    };
    public Planilla() {
        super();
        this.tabla = new JTable(dataTabla, headersColumna);
        tabla.setPreferredSize(new Dimension(640, dataTabla.length*16));
        TableModel m = tabla.getModel();
        HolderTabla.setModelo(m);
        m.addTableModelListener(new Calculadora());
        JScrollPane headers = new JScrollPane(this.tabla);
        headers.setPreferredSize(new Dimension(640, (dataTabla.length + 1)*16 + 10));
        this.add(headers);
    }

}
