/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lenguaje;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author DAM_M
 */
public class Lenguaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creo el array de cadena
        String[] cadena = new String[Integer.parseInt(args[1])];

        //Genero las palabras aleatorias
        for (int i = 0; i < cadena.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                sb.append((char) (Math.random() * 26 + 'a'));
            }
            cadena[i] = "Escrito por " + args[0] + ": " + sb.toString();
        }

        //Escribo el codigo en el archivo con nombre en args[2] usando bufferedWritter
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(args[2]))) {
            //Escribo cada cadena en una linea diferente
            for (String linea : cadena) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) { //capturo la error de entrada y salida
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        };
    }
    
}
