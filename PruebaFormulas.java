package Estadistica;

/**
 * Clase de prueba para demostrar el uso de Formulas.
 * 
 * <p>Esta clase muestra ejemplos prácticos de cómo utilizar los métodos
 * de la clase {@link Formulas}.</p>
 * 
 * @see Formulas
 */
public class PruebaFormulas {
    
    /**
     * Ejemplo de uso de los métodos de Formulas.
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Ejemplo cálculo IVA
        double iva = Formulas.IVA(100.0);
        System.out.println("IVA de 100: " + iva);
        
        // Ejemplo promedio
        double[] datos = {10.0, 20.0, 30.0};
        double prom = Formulas.promedio(datos);
        System.out.println("Promedio: " + prom);
    }
}