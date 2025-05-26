package vista;

import controlador.GestorPrueba;
import modelo.Item;
import utilidades.ArchivoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

public class MainFrame extends JFrame {
    private GestorPrueba gestor;

    private JLabel lblAsignatura, lblEvaluacion, lblResumen;
    private JButton btnCargarArchivo, btnIniciarPrueba;

    public MainFrame() {
        setTitle("Administrador de Pruebas");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelCentro = new JPanel(new GridLayout(5, 1));
        lblAsignatura = new JLabel("Asignatura: ");
        lblEvaluacion = new JLabel("Evaluación: ");
        lblResumen = new JLabel("Resumen de ítemes:");
        panelCentro.add(lblAsignatura);
        panelCentro.add(lblEvaluacion);
        panelCentro.add(lblResumen);

        JPanel panelBotones = new JPanel();
        btnCargarArchivo = new JButton("Cargar archivo");
        btnIniciarPrueba = new JButton("Iniciar prueba");
        btnIniciarPrueba.setEnabled(false);

        panelBotones.add(btnCargarArchivo);
        panelBotones.add(btnIniciarPrueba);

        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnCargarArchivo.addActionListener(e -> cargarArchivo());
        btnIniciarPrueba.addActionListener(e -> {
            new PreguntaFrame(gestor).setVisible(true);
            this.dispose();
        });
    }

    private void cargarArchivo() {
        JFileChooser chooser = new JFileChooser();
        int opcion = chooser.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            try {
                Object[] datos = ArchivoUtil.leerArchivo(archivo.getAbsolutePath());
                String asignatura = (String) datos[0];
                String evaluacion = (String) datos[1];
                List<Item> items = (List<Item>) datos[2];

                gestor = new GestorPrueba(asignatura, evaluacion, items);

                lblAsignatura.setText("Asignatura: " + asignatura);
                lblEvaluacion.setText("Evaluación: " + evaluacion);

                long seleccion = items.stream().filter(i -> i.getTipo().equals("Seleccion")).count();
                long vf = items.stream().filter(i -> i.getTipo().equals("VF")).count();

                lblResumen.setText("Ítems: Selección múltiple = " + seleccion + ", Verdadero/Falso = " + vf + ", Total = " + items.size());

                btnIniciarPrueba.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + ex.getMessage());
            }
        }
    }
}
