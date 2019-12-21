package grupo11.ventana;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

import grupo11.config.TitleBar;

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
        this.setTitle(TitleBar.TITULO);
        Toolkit def = Toolkit.getDefaultToolkit();
        this.setIconImage(def.getImage(getClass().getResource(TitleBar.ICONO)));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
