package grupo11.planillaexcel.ventana;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Joaquín León Ulloa
 */
public class HolderTabla {
    private static TableModel modelo = null;
    public static TableModel getModelo() { return modelo; }
    public static void setModelo(TableModel m) { modelo = m; }    
}
