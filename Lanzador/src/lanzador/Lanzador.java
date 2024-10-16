/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lanzador;

/**
 *
 * @author DAM_M
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Lanzador {
    
    private static final int MAX_INSTANCIAS = 10;
    
    public static void main(String[] args) {
        
        System.out.print("Numero de Instancias: ");
        int numInstancias = leerNumero();
        System.out.print("Numero de cadenas por instancia: ");
        int numCadenas = leerNumero();
        
        if (numInstancias > MAX_INSTANCIAS) {
            System.out.println("El número máximo de instancias es " + MAX_INSTANCIAS);
            numInstancias = MAX_INSTANCIAS;
        }
        
        for (int i = 0; i < numInstancias; i++) {
            try {
                System.out.println("\nInstancia-->" + (i+1));
                ProcessBuilder process = new ProcessBuilder("java", "C:\\Users\\usuario\\Documents\\GitHub\\PSP\\Generador\\src\\generador\\Generador.java", String.valueOf(numCadenas));
                Process p = process.start();
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String linea = br.readLine();
                 while (linea != null) {
                     System.out.println(linea);
                    linea = br.readLine();
                };
                process.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public static int leerNumero() {
        Scanner sc = new Scanner(System.in);
        int entero = 3;
        try {
            entero = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error al leer el numero. Se usará 3");
        }
        return entero;
    }
}
