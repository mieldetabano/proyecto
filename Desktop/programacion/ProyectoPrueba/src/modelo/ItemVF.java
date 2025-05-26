package modelo;

public class ItemVF extends Item {
    private String respuestaCorrecta;

    public ItemVF(String pregunta, String respuestaCorrecta) {
        super(pregunta, "VF");
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public boolean verificarRespuesta(String respuesta) {
        correcta = respuesta.equalsIgnoreCase(respuestaCorrecta);
        return correcta;
    }

    @Override
    public String[] getOpciones() {
        return new String[]{"V", "F"};
    }
}
