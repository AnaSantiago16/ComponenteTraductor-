
/*
 * Librería de fórmulas estadísticas y financieras
 * Incluye cálculos de promedio, moda, mediana, IVA, porcentajes y descuentos
 */
package Estadistica;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase que proporciona métodos para cálculos estadísticos y financieros comunes.
 * <p>
 * Esta clase incluye funcionalidades para:
 * <ul>
 *   <li>Cálculos con IVA (agregar o quitar)</li>
 *   <li>Operaciones con porcentajes</li>
 *   <li>Cálculos estadísticos básicos (promedio, mediana, moda)</li>
 *   <li>Cálculos de descuentos</li>
 * </ul>
 * 
 * @author Ana Santiago
 * @version 1.0
 */

/**
 * Clase que proporciona métodos para cálculos estadísticos y financieros comunes.
 * 
 * <h2>Ejemplos de uso</h2>
 * <pre>{@code
 * // Cálculo de IVA básico
 * double iva = Formulas.IVA(100.0); // Retorna 16.0
 * 
 * // Cálculo de promedio
 * double[] datos = {1.0, 2.0, 3.0};
 * double promedio = Formulas.promedio(datos); // Retorna 2.0
 * }</pre>
 * 
 * @see PruebaFormulas Para ver ejemplos completos de uso
 */

public class Formulas {
    
    /**
     * Calcula el IVA estándar (16%) de una cantidad.
     * 
     * @param x Cantidad base sobre la cual se calculará el IVA
     * @return El valor del IVA correspondiente al 16% de x
     */
    public static double IVA(double x) {
        return x * 0.16;
    }
    
    /**
     * Calcula el IVA con porcentaje personalizado de una cantidad.
     * 
     * @param x Cantidad base sobre la cual se calculará el IVA
     * @param porcentaje Porcentaje de IVA a aplicar
     * @return El valor del IVA correspondiente al porcentaje especificado de x
     */
    public static double IVA(double x, double porcentaje) {
        return x * (porcentaje / 100);
    }
    
    /**
     * Calcula el valor original (sin IVA) de una cantidad que incluye el 16% de IVA.
     * 
     * @param x Cantidad que incluye el IVA
     * @return El valor original sin el 16% de IVA
     */
    public static double menosIVA(double x) {
        return x / 1.16;
    }
    
    /**
     * Calcula el valor original (sin IVA) de una cantidad que incluye un porcentaje personalizado de IVA.
     * 
     * @param x Cantidad que incluye el IVA
     * @param porcentaje Porcentaje de IVA incluido
     * @return El valor original sin el IVA especificado
     */
    public static double menosIVA(double x, double porcentaje) {
        return x / (1 + (porcentaje / 100));
    }
    
    /**
     * Calcula qué cantidad representa un porcentaje específico de un valor dado.
     * 
     * @param valor Cantidad base
     * @param porcentaje Porcentaje a calcular
     * @return La cantidad que representa el porcentaje especificado del valor
     */
    public static double porcentajeDe(double valor, double porcentaje) {
        return (valor * porcentaje) / 100;
    }
    
    /**
     * Calcula qué porcentaje representa una parte con respecto a un total.
     * 
     * @param parte Cantidad que se quiere expresar como porcentaje
     * @param total Cantidad total de referencia
     * @return El porcentaje que representa la parte con respecto al total
     */
    public static double calcularPorcentaje(double parte, double total) {
        return (parte / total) * 100;
    }
    
    /**
     * Calcula el promedio (media aritmética) de un conjunto de números.
     * 
     * @param numeros Array de números para calcular el promedio
     * @return El valor promedio de los números en el array
     * @throws IllegalArgumentException Si el array está vacío
     */
    public static double promedio(double[] numeros) {
        if (numeros.length == 0) {
            throw new IllegalArgumentException("El array no puede estar vacío");
        }
        double suma = 0;
        for (double num : numeros) {
            suma += num;
        }
        return suma / numeros.length;
    }
    
    /**
     * Calcula la mediana (valor central) de un conjunto de números.
     * 
     * @param numeros Array de números para calcular la mediana
     * @return El valor mediano de los números en el array
     * @throws IllegalArgumentException Si el array está vacío
     */
    public static double mediana(double[] numeros) {
        if (numeros.length == 0) {
            throw new IllegalArgumentException("El array no puede estar vacío");
        }
        Arrays.sort(numeros);
        
        int mitad = numeros.length / 2;
        if (numeros.length % 2 == 1) {
            return numeros[mitad];
        } else {
            return (numeros[mitad - 1] + numeros[mitad]) / 2.0;
        }
    }
    
    /**
     * Calcula la moda (valor más frecuente) de un conjunto de números.
     * 
     * @param numeros Array de números para calcular la moda
     * @return El valor que aparece con más frecuencia en el array
     * @throws IllegalArgumentException Si el array está vacío
     */
    public static double moda(double[] numeros) {
        if (numeros.length == 0) {
            throw new IllegalArgumentException("El array no puede estar vacío");
        }
        
        double maxValue = 0;
        int maxCount = 0;
        
        for (int i = 0; i < numeros.length; ++i) {
            int count = 0;
            for (int j = 0; j < numeros.length; ++j) {
                if (numeros[j] == numeros[i]) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = numeros[i];
            }
        }
        
        return maxValue;
    }
    
    /**
     * Calcula el monto del descuento aplicado a un precio original.
     * 
     * @param precioOriginal Precio antes del descuento
     * @param porcentajeDescuento Porcentaje de descuento a aplicar
     * @return El monto del descuento calculado
     */
    public static double descuento(double precioOriginal, double porcentajeDescuento) {
        return precioOriginal * (porcentajeDescuento / 100);
    }
    
    /**
     * Calcula el precio final después de aplicar un descuento.
     * 
     * @param precioOriginal Precio antes del descuento
     * @param porcentajeDescuento Porcentaje de descuento a aplicar
     * @return El precio resultante después de aplicar el descuento
     */
    public static double precioConDescuento(double precioOriginal, double porcentajeDescuento) {
        return precioOriginal * (1 - (porcentajeDescuento / 100));
    }
    
    /**
     * Método principal que demuestra el uso de las funciones de la clase.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Scanner tx = new Scanner(System.in);
        
        System.out.println("Calculadora de fórmulas estadísticas y financieras");
        
        // Ejemplos de IVA
        System.out.print("\nIngrese cantidad para calcular IVA (16%): ");
        double cantidadIVA = tx.nextDouble();
        System.out.println("IVA de " + cantidadIVA + ": " + IVA(cantidadIVA));
        
        System.out.print("Ingrese cantidad para calcular IVA personalizado: ");
        double cantidadIVAPersonal = tx.nextDouble();
        System.out.print("Ingrese porcentaje de IVA: ");
        double porcentajeIVA = tx.nextDouble();
        System.out.println("IVA de " + cantidadIVAPersonal + " con " + porcentajeIVA + "%: " + 
                         IVA(cantidadIVAPersonal, porcentajeIVA));
        
        // Ejemplo sin IVA
        System.out.print("\nIngrese precio con IVA (16%) para calcular precio sin IVA: ");
        double precioConIVA = tx.nextDouble();
        System.out.println("Precio sin IVA de " + precioConIVA + ": " + menosIVA(precioConIVA));
        
        System.out.print("Ingrese precio con IVA personalizado: ");
        double precioConIVAPersonal = tx.nextDouble();
        System.out.print("Ingrese porcentaje de IVA aplicado: ");
        double porcentajeIVAAplicado = tx.nextDouble();
        System.out.println("Precio sin IVA de " + precioConIVAPersonal + " con " + porcentajeIVAAplicado + "%: " + 
                         menosIVA(precioConIVAPersonal, porcentajeIVAAplicado));
        
        // Ejemplo porcentajes
        System.out.print("\nIngrese valor para calcular porcentaje: ");
        double valorPorcentaje = tx.nextDouble();
        System.out.print("Ingrese porcentaje a calcular: ");
        double porcentajeACalcular = tx.nextDouble();
        System.out.println(porcentajeACalcular + "% de " + valorPorcentaje + ": " + 
                         porcentajeDe(valorPorcentaje, porcentajeACalcular));
        
        System.out.print("Ingrese parte para calcular qué porcentaje es del total: ");
        double parte = tx.nextDouble();
        System.out.print("Ingrese total: ");
        double total = tx.nextDouble();
        System.out.println(parte + " es qué % de " + total + ": " + 
                         calcularPorcentaje(parte, total) + "%");
        
        // Ejemplo estadísticas
        System.out.print("\nIngrese cantidad de datos para estadísticas: ");
        int n = tx.nextInt();
        double[] datos = new double[n];
        System.out.println("Ingrese los datos uno por uno:");
        for (int i = 0; i < n; i++) {
            datos[i] = tx.nextDouble();
        }
        
        System.out.println("\nResultados estadísticos:");
        System.out.println("Promedio: " + promedio(datos));
        System.out.println("Mediana: " + mediana(datos));
        System.out.println("Moda: " + moda(datos));
        
        // Ejemplo descuentos
        System.out.print("\nIngrese precio original para descuento: ");
        double precioOriginal = tx.nextDouble();
        System.out.print("Ingrese porcentaje de descuento: ");
        double porcentajeDescuento = tx.nextDouble();
        System.out.println("Descuento de " + porcentajeDescuento + "% sobre " + precioOriginal + ": " + 
                         descuento(precioOriginal, porcentajeDescuento));
        System.out.println("Precio con descuento de " + porcentajeDescuento + "% sobre " + precioOriginal + ": " + 
                         precioConDescuento(precioOriginal, porcentajeDescuento));
        
        tx.close();
    }
}