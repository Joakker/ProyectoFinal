package grupo11.ventana;

import grupo11.util.HolderTabla;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 
 * Representa la planilla excel. Contiene a JScrollPane y JTable. También
 * fija el modelo de JTable
 *
 * @author Joaquín León Ulloa
 * @see grupo11.util.HolderTabla
 * @see grupo11.ventana.Calculadora
 */
public class Planilla extends JPanel {
    private final JTable tabla;
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
