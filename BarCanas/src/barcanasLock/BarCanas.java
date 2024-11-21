package barcanasLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarCanas {
    private final int totalCañas;
    private int cañasServidas;
    private boolean barrileNecesitaReposicion;
    
    private final Lock lock = new ReentrantLock();
    private final Condition condicionBarril = lock.newCondition();

    public BarCanas(int nCañas) {
        this.totalCañas = nCañas;
        this.cañasServidas = 0;
        this.barrileNecesitaReposicion = false;
    }

    public void tomarCaña(int id) throws InterruptedException {
        lock.lock();
        try {
            while (barrileNecesitaReposicion) {
                condicionBarril.await();
            }

            cañasServidas++;
            System.out.println("Sirviendo caña " + cañasServidas + " al cliente " + id + "...");
            Thread.sleep(500);

            if (cañasServidas >= totalCañas) {
                barrileNecesitaReposicion = true;
                System.out.println("Barril agotado. El camarero esta reponiendo...");
                condicionBarril.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void reponer() throws InterruptedException {
        lock.lock();
        try {
            while (!barrileNecesitaReposicion) {
                condicionBarril.await();
            }

            cañasServidas = 0;
            barrileNecesitaReposicion = false;
            Thread.sleep(1500);
            System.out.println("Barril reemplazado.");
            
            condicionBarril.signalAll();
        } finally {
            lock.unlock();
        }
    }
}