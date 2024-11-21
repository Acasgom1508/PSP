/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package barberodurmienteLock;

/**
 *
 * @author DAM_M
 */
public class Main {
    public static void main(String[] args) {
        Barberia barberia = new Barberia(5);
        Barbero barbero = new Barbero(barberia);
        barbero.start();

        for (int i = 0; i < 10; i++) {
            new Cliente(barberia, i).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
