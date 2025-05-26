package modelo;

public class ItemSeleccion extends Item {
    private String[] opciones;
    private String respuestaCorrecta;

    public ItemSeleccion(String pregunta, String[] opciones, String respuestaCorrecta) {
        super(pregunta, "Seleccion");
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public boolean verificarRespuesta(String respuesta) {
        correcta = respuesta.equals(respuestaCorrecta);
        return correcta;
    }

    @Override
    public String[] getOpciones() {
        return opciones;
    }
}
