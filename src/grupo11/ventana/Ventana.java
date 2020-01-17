package grupo11.ventana;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import grupo11.config.TitleBar;
import static grupo11.util.HolderTabla.getCeldas;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * Representa la ventana que ve el usuario, al igual que
 * todas sus propiedades
 * 
 * @author Grupo 11
 */
public class Ventana extends JFrame {
    private final JMenuBar barra;
    private final Planilla planilla;
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
        this.barra = new JMenuBar();
        barra.setBackground(new Color(225, 225, 225));
        this.setJMenuBar(this.barra);
            JMenu archivo = new JMenu("Archivo");
                JMenuItem guardar =  new JMenuItem("Guardar");
                archivo.add(guardar);
                guardar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String file = JOptionPane.showInputDialog(rootPane,
                                "Escriba nombre del archivo",
                                "Guardar como",
                                JOptionPane.PLAIN_MESSAGE
                        );
                        try {
                            FileWriter writer = new FileWriter(file);
                            for (Celda[] k: getCeldas()) {
                                String linea = "";
                                for (Celda c: k) {
                                    if (c != null) { 
                                        linea += String.format("%s, ", c.linea);
                                    }
                                }
                                linea = linea.substring(0, linea.lastIndexOf(", "));
                                writer.write(String.format("%s\n",linea));
                                linea = "";
                            }
                            writer.close();
                        } catch (IOException o) {
                            JOptionPane.showMessageDialog(rootPane,
                                    "No se pudo escribir el archivo",
                                    "Error", JOptionPane.ERROR_MESSAGE
                            );
                        } catch (NullPointerException o) {
                            return;
                        }
                    }
                });
                JMenuItem salir = new JMenuItem("Salir");
                archivo.add(salir);
                salir.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                barra.add(archivo);
            JMenu ayuda = new JMenu("Ayuda");
                JMenuItem acercaDe = new JMenuItem("Acerca de");
                acercaDe.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(
                                rootPane,
                                String.format("%s\n\n%s\n\n%s\n\n%s",
                                        "Proyecto Final Grupo 11",
                                        "Integrantes:\nJoaquín León Ulloa\nCristián Pérez Vasquez",
                                        "Ayudantes:\nIvonne Flores Roa\nDavid Flores Gallardo",
                                        "Profesor:\nJorge López"
                                ),
                                "Acerca de",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                });
                ayuda.add(acercaDe);
                barra.add(ayuda);
                
                
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.planilla = new Planilla();
            this.add(this.planilla);
        this.pack();
        this.setTitle(TitleBar.TITULO);
        Toolkit def = Toolkit.getDefaultToolkit();
        this.setIconImage(def.getImage(getClass().getResource(TitleBar.ICONO)));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
