/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bar.Bar1;

/**
 *
 * @author DAM_M
 */
public class Bar {
    private final int aforoMax;
    private int clientesdentro = 0;

    // consrtuctor
    public Bar(int aforoMax) {
        this.aforoMax = aforoMax;
    }

    public synchronized void entrar(int id) {
        while (aforoMax == clientesdentro) {
            try {
                System.out.println("El cliente " + id + " esta esperando porque no hay aforo suficiente");
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        clientesdentro++;
        System.out.println("Cliente " + id + " ha entrado...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void salir(int id) {
        clientesdentro--;
        System.out.println("Cliente " + id + " ha salido");
        notifyAll();
    }
}
