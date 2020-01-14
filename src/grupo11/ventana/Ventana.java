package grupo11.ventana;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

import grupo11.config.TitleBar;

/**
 *
 * Representa la ventana que ve el usuario, al igual que
 * todas sus propiedades
 * 
 * @author Grupo 11
 */
public class Ventana extends JFrame {
    /**
     * Constructor de la clase. Las propiedades por defecto son:
     * <dl>
     *  <dt>Operación al cierre</dt>
     *      <dd>JFrame.EXIT_ON_CLOSE</dd>
     *  <dt>Layout</dt>
     *      <dd>BorderLayout</dd>
     *  <dt>Icono de Ventana</dt>
     *      <dd>/grupo11/config/icono.png</dd>
     *  <dt>Posición en la pantalla</dt>
     *      <dd>Centro</dd>
     *  <dt>Redimensionable</dt>
     *      <dd>No</dd>
     * </dl>
     */
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
