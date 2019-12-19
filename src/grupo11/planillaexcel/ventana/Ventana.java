package grupo11.planillaexcel.ventana;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Joaquín León Ulloa
 */
public class Ventana extends JFrame {
    public Ventana() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new Planilla());
        this.pack();
        this.setTitle("Grupo 11 - Planilla Excel");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
