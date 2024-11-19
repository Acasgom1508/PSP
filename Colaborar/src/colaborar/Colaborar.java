package colaborar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public class Colaborar {

    public static void main(String[] args) {
        // Nombre del archivo final
        String fileName = args[0];

        // Número de instancias
        int numInstancias = 10;

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            for (int i = 1; i <= numInstancias; i++) {
                int numPalabras = i * 10; // Número de palabras a generar por la instancia
                String nombreInstancia = "Instancia_" + i;

                // Simula la generación y escritura de palabras
                synchronized (raf) {
                    try (FileLock lock = raf.getChannel().lock()) { // Bloquea el archivo
                        raf.seek(raf.length()); // Posiciónate al final del archivo
                        ProcessBuilder process = new ProcessBuilder("java",
                                "C:\\Users\\usuario\\Documents\\GitHub\\PSP\\Lenguaje\\src\\lenguaje.jar", "1");
                        Process p = process.start();
                        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        String palabra = br.readLine();
                        for (int j = 0; j < numPalabras; j++) {
                            raf.writeBytes("Escrito por " + nombreInstancia + "=" + palabra + "\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al acceder al archivo: " + e.getMessage());
        }

        System.out.println("Generación finalizada con éxito en " + fileName);
    }
}
