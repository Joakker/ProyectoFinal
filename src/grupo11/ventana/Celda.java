/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo11.ventana;

import java.util.ArrayList;
import static grupo11.util.HolderTabla.getModelo;
import static grupo11.ventana.Calculadora.expresionNumerica;

/**
 *
 * Clase auxiliar para actualizar los valores de las celdas.
 * 
 * @author Grupo 11
 * @see grupo11.ventana.Calculadora
 * @see grupo11.util.HolderTabla
 */
public class Celda {
    private final ArrayList<Celda> pars = new ArrayList<>();
    public final int x, y;
    public String linea;
    public Celda(int x, int y) { this.linea = ""; this.x = x; this.y = y;}
    public void update() { for (Celda c: this.pars) c.getUpdate(); }
    public void getUpdate() {
        getModelo().setValueAt(
                expresionNumerica(this.linea),
                this.x,
                this.y
        );
    }
    public void clearPars() { pars.clear(); }
    public void addPars(Celda c) { pars.add(c); }
    public ArrayList<Celda> getPars() { return this.pars; }
    @Override
    public String toString() {
        return String.format("Celda(%d, %d) - linea: %s", this.x, this.y, this.linea);
    }
}
