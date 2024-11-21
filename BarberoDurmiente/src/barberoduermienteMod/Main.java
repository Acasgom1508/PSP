/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package barberoduermienteMod;

/**
 *
 * @author DAM_M
 */
public class Main {
    public static void main(String[] args) {
        Barberia barberia = new Barberia(3); // Reducimos a 3 sillas para ver más intentos
        Barbero barbero = new Barbero(barberia);
        barbero.start();

        // Creamos más clientes para generar más competencia por las sillas
        for (int i = 0; i < 15; i++) {
            new Cliente(barberia, i).start();
            try {
                Thread.sleep(1000); // Llegada más frecuente de clientes
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
