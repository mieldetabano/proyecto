package utilidades;

import modelo.*;
import java.io.*;
import java.util.*;

public class ArchivoUtil {
    public static Object[] leerArchivo(String ruta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;
        String asignatura = "", evaluacion = "";
        List<Item> items = new ArrayList<>();

        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("Asignatura:")) {
                asignatura = linea.split(":")[1].trim();
            } else if (linea.startsWith("Evaluación:") || linea.startsWith("Evaluacion:")) {
                evaluacion = linea.split(":")[1].trim();
            } else if (linea.startsWith("#ITEM")) {
                String tipo = br.readLine().split(":")[1].trim();
                String pregunta = br.readLine().split(":")[1].trim();

                if (tipo.equals("Seleccion")) {
                    String[] opciones = br.readLine().split(":")[1].split(";");
                    String respuesta = br.readLine().split(":")[1].trim();
                    items.add(new ItemSeleccion(pregunta, opciones, respuesta));
                } else if (tipo.equals("VF")) {
                    String respuesta = br.readLine().split(":")[1].trim();
                    items.add(new ItemVF(pregunta, respuesta));
                }
            }
        }
        br.close();
        return new Object[]{asignatura, evaluacion, items};
    }
}
