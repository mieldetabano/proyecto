package controlador;

import modelo.*;
import java.util.*;

public class GestorPrueba {
    private String asignatura;
    private String evaluacion;
    private List<Item> items;
    private Map<Integer, String> respuestas;

    public GestorPrueba(String asignatura, String evaluacion, List<Item> items) {
        this.asignatura = asignatura;
        this.evaluacion = evaluacion;
        this.items = items;
        this.respuestas = new HashMap<>();
    }

    public String getAsignatura() { return asignatura; }
    public String getEvaluacion() { return evaluacion; }
    public List<Item> getItems() { return items; }

    public void guardarRespuesta(int index, String respuesta) {
        respuestas.put(index, respuesta);
        items.get(index).verificarRespuesta(respuesta);
    }

    public Map<String, int[]> calcularResultados() {
        Map<String, int[]> resultados = new HashMap<>();
        for (Item item : items) {
            String tipo = item.getTipo();
            resultados.putIfAbsent(tipo, new int[2]); // [correctas, total]
            if (item.fueCorrecta()) resultados.get(tipo)[0]++;
            resultados.get(tipo)[1]++;
        }
        return resultados;
    }

    public Map<Integer, String> getRespuestas() {
        return respuestas;
    }
}
