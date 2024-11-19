package lenguaje;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Lenguaje {

    public static void main(String[] args) {
        // Obtenemos los parámetros de la línea de comandos
        String nombreInstancia = args[0];  // Nombre de la instancia
        int numPalabras = Integer.parseInt(args[1]);  // Número de palabras a generar
        String fileName = args[2];  // Nombre del archivo para escribir las palabras

        // Creo el array de cadenas de texto
        String[] cadenas = new String[numPalabras];

        // Genero las palabras aleatorias
        for (int i = 0; i < cadenas.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                sb.append((char) (Math.random() * 26 + 'a'));
            }
            cadenas[i] = sb.toString();
            System.out.println(cadenas[i]);  // Imprime cada palabra generada
        }

        // Escribo las palabras generadas en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String palabra : cadenas) {
                bw.write("Escrito por " + nombreInstancia + ": " + palabra);
                bw.newLine();  // Escribe una palabra por línea
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
