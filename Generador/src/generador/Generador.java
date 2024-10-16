/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generador;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author DAM_M
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Debes especificar el numero de instancias y cadenas por instancia");
            return;
        }
        
        int cont = 0;
        int numeroCadenas = Integer.parseInt(args[0]);
        
        Random random = new Random();
        //Declaro variables
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        boolean numero = true;
        //int cadenas = 0;
        StringBuilder texto_mostrar = new StringBuilder(10);

        //Controlo la excepción a la hora de leer entero por si se introduce una letra
        /*
        do {
            try {
                //pido el numero de cadenas
                System.out.print("Número de cadenas a imprimir: ");
                cadenas = sc.nextInt();
            } catch (InputMismatchException e) {
                //indico que se añada un numero y limpio el escaner
                System.out.println("Añada un numero");
                numero = false;
                sc.next();
            }
        } while (!numero);
        */
        
        //genero el numero de cadenas guardado en la variable "cadenas" usando un bucle tipo for
        for (int i = 0; i < numeroCadenas; i++) {
            //escribo 10 letras random de la variable "caracteres" usando un bucle tipo for
            for (int j = 0; j < 10; j++) {
                int indice = random.nextInt(caracteres.length());
                texto_mostrar.append(caracteres.charAt(indice));
            }
            System.out.println(texto_mostrar);
            texto_mostrar.delete(0, texto_mostrar.length());
        }
        System.out.println("");

    }

}
