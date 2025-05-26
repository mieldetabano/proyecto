package vista;

import controlador.GestorPrueba;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ResumenFrame extends JFrame {
    public ResumenFrame(GestorPrueba gestor) {
        setTitle("Resumen de Resultados");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea txtResumen = new JTextArea();
        txtResumen.setEditable(false);

        StringBuilder sb = new StringBuilder("Resultados por tipo de ítem:\n\n");
        Map<String, int[]> resultados = gestor.calcularResultados();

        for (Map.Entry<String, int[]> entry : resultados.entrySet()) {
            String tipo = entry.getKey();
            int correctas = entry.getValue()[0];
            int total = entry.getValue()[1];
            int incorrectas = total - correctas;

            double porcentajeCorrectas = 100.0 * correctas / total;
            double porcentajeIncorrectas = 100.0 * incorrectas / total;

            sb.append("Tipo: ").append(tipo).append("\n")
                    .append("Correctas: ").append(correctas).append(" (").append(String.format("%.2f", porcentajeCorrectas)).append("%)\n")
                    .append("Incorrectas: ").append(incorrectas).append(" (").append(String.format("%.2f", porcentajeIncorrectas)).append("%)\n\n");
        }

        txtResumen.setText(sb.toString());
        add(new JScrollPane(txtResumen), BorderLayout.CENTER);

        JButton btnSalir = new JButton("Cerrar");
        btnSalir.addActionListener(e -> System.exit(0));
        add(btnSalir, BorderLayout.SOUTH);
    }
}
