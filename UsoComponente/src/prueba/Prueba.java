/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

/**
 *
 * @author Ana
 */
import Estadistica.Formulas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Prueba extends JFrame {

    private JTextField inputField;
    private JTextArea resultArea;
    private JButton calcularBtn, limpiarBtn;

    public Prueba() {
        configurarInterfaz();
    }

    private void configurarInterfaz() {
        setTitle("Calculadora Estadística - Usando Librería Formulas");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con borde y espaciado
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de entrada
        JPanel inputPanel = crearPanelEntrada();
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = crearPanelBotones();
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Panel de resultados
        JPanel resultPanel = crearPanelResultados();
        mainPanel.add(resultPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel crearPanelEntrada() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Datos"));

        JLabel label = new JLabel("Ingrese números separados por comas:");
        label.setFont(new Font("Arial", Font.BOLD, 12));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        //inputField.setToolTipText("Ejemplo: 5.5, 10, 15.2, 20");

        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        calcularBtn = new JButton("Calcular");
        configurarBoton(calcularBtn, new Color(100, 130, 182));
        calcularBtn.addActionListener(this::calcularEstadisticas);

        limpiarBtn = new JButton("Limpiar Campos");
        configurarBoton(limpiarBtn, new Color(192, 57, 43));
        limpiarBtn.addActionListener(e -> {
            inputField.setText("");
            resultArea.setText("");
        });

        panel.add(calcularBtn);
        panel.add(limpiarBtn);

        return panel;
    }

    private void configurarBoton(JButton boton, Color colorFondo) {
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(180, 35));
        boton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Resultados"));

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(245, 245, 245));
        resultArea.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void calcularEstadisticas(ActionEvent e) {
        try {
            String input = inputField.getText().trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar al menos un número");
            }

            String[] numerosStr = input.split(",");
            double[] datosArray = new double[numerosStr.length];
            
            for (int i = 0; i < numerosStr.length; i++) {
                datosArray[i] = Double.parseDouble(numerosStr[i].trim());
            }

            // Convertir a List para mostrar en resultados
            List<Double> datosList = new ArrayList<>();
            for (double num : datosArray) {
                datosList.add(num);
            }

            // Usando los métodos de la librería Formulas
            double promedio = Formulas.promedio(datosArray);
            double mediana = Formulas.mediana(datosArray);
            double moda = Formulas.moda(datosArray);

            mostrarResultados(datosList, promedio, mediana, moda);

        } catch (NumberFormatException ex) {
            mostrarError("Error de formato", "Ingrese números válidos separados por comas.\nEjemplo: 5, 10.5, 15, 20.2");
        } catch (IllegalArgumentException ex) {
            mostrarError("Error de entrada", ex.getMessage());
        }
    }

    private void mostrarResultados(List<Double> datos, double promedio, double mediana, double moda) {
        StringBuilder sb = new StringBuilder();
        sb.append(" Datos ingresados (").append(datos.size()).append(" valores):\n");
        sb.append(datos).append("\n\n");
        
        sb.append("Promedio (media aritmética): ").append(String.format("%.4f", promedio)).append("\n");
        sb.append(" Mediana: ").append(String.format("%.4f", mediana)).append("\n");
        
        // Asumiendo que la moda devuelve 0 cuando no hay moda
        if (moda != 0) {
            sb.append(" Moda: ").append(String.format("%.4f", moda));
        } else {
            sb.append(" Moda: No existe (todos los valores son únicos)");
        }

        resultArea.setText(sb.toString());
    }

    private void mostrarError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, 
            mensaje, 
            titulo, 
            JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            Prueba app = new Prueba();
            app.setVisible(true);
        });
    }
}