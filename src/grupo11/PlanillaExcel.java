package grupo11;

import grupo11.ventana.Ventana;

/**
 *
 * Representa el programa. Es la base de toda la aplicación
 * 
 * @author Joaquín León Ulloa
 */
public abstract class PlanillaExcel {

    /**
     *
     * Punto de entrada de la aplicación, crea una instancia de Ventana,
     * en la cual ocurre toda la acción
     * 
     * @param args No es usado
     * 
     */
    public static void main(String[] args) {
        System.out.println(Math.sin(Math.PI));
        new Ventana(); }
}
