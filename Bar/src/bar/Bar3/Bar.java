/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bar.bar2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author DAM_M
 */
public class Bar {
    int aforoMax;
    private final Semaphore semaphore;

    public Bar(int aforoMax) {
        this.aforoMax = aforoMax;
        this.semaphore = new Semaphore(aforoMax); // Inicializamos el semáforo con el aforo máximo
    }

    public void entrar(int id) {
        try {
            System.out.println("Cliente " + id + " intentando entrar...");
            semaphore.acquire(); // Intentamos adquirir un permiso
            System.out.println("-->Cliente " + id + " ha entrado.");
            // Simula el tiempo que el cliente pasa dentro del bar
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void salir(int id) {
        System.out.println("<-Cliente " + id + " ha salido.");
        semaphore.release(); // Liberamos el permiso para que otro cliente pueda entrar
    }
}
