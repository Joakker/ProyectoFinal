package grupo11.ventana;

import grupo11.util.HolderTabla;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Joaquín León Ulloa
 */
public class Planilla extends JPanel {
    public final JTable tabla;
    public Planilla() {
        super();
        this.tabla = new JTable();
        tabla.setModel(HolderTabla.getModelo());
        tabla.getTableHeader().setReorderingAllowed(false);
        JScrollPane contenedor = new JScrollPane(this.tabla);
        tabla.setSize(new Dimension(640, 480));
        tabla.setFillsViewportHeight(true);
        this.add(contenedor);
    }

}
