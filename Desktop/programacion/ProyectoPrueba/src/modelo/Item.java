package modelo;

public abstract class Item {
    protected String pregunta;
    protected String tipo;
    protected boolean correcta;

    public Item(String pregunta, String tipo) {
        this.pregunta = pregunta;
        this.tipo = tipo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean fueCorrecta() {
        return correcta;
    }

    public abstract boolean verificarRespuesta(String respuesta);
    public abstract String[] getOpciones();
}

