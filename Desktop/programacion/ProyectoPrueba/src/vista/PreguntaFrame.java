package vista;

import controlador.GestorPrueba;
import modelo.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreguntaFrame extends JFrame {
    private GestorPrueba gestor;
    private int indice = 0;

    private JLabel lblPregunta;
    private ButtonGroup grupo;
    private JPanel panelOpciones;
    private JButton btnSiguiente;

    public PreguntaFrame(GestorPrueba gestor) {
        this.gestor = gestor;
        setTitle("Aplicación de Prueba");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lblPregunta = new JLabel();
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        panelOpciones = new JPanel(new GridLayout(4, 1));
        btnSiguiente = new JButton("Siguiente");

        add(lblPregunta, BorderLayout.NORTH);
        add(panelOpciones, BorderLayout.CENTER);
        add(btnSiguiente, BorderLayout.SOUTH);

        btnSiguiente.addActionListener(e -> {
            guardarRespuesta();
            if (indice < gestor.getItems().size() - 1) {
                indice++;
                mostrarPregunta();
            } else {
                new ResumenFrame(gestor).setVisible(true);
                this.dispose();
            }
        });

        mostrarPregunta();
    }

    private void mostrarPregunta() {
        Item item = gestor.getItems().get(indice);
        lblPregunta.setText((indice + 1) + ". " + item.getPregunta());
        panelOpciones.removeAll();
        grupo = new ButtonGroup();

        String[] opciones = item.getOpciones();
        String respuestaAnterior = gestor.getRespuestas().getOrDefault(indice, "");

        for (String opcion : opciones) {
            JRadioButton btn = new JRadioButton(opcion);
            if (opcion.equals(respuestaAnterior)) {
                btn.setSelected(true);
            }
            grupo.add(btn);
            panelOpciones.add(btn);
        }

        if (indice == gestor.getItems().size() - 1) {
            btnSiguiente.setText("Enviar respuestas");
        } else {
            btnSiguiente.setText("Siguiente");
        }

        revalidate();
        repaint();
    }

    private void guardarRespuesta() {
        for (AbstractButton btn : java.util.Collections.list(grupo.getElements())) {
            if (btn.isSelected()) {
                gestor.guardarRespuesta(indice, btn.getText());
                break;
            }
        }
    }
}
