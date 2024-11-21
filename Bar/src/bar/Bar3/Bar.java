package bar.Bar3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
    int aforoMax;
    int clientesDentro = 0; // Clientes actualmente dentro del bar
    private final Lock lock = new ReentrantLock();
    private final Condition puedenEntrar = lock.newCondition(); // Condición para controlar acceso al bar
    private int ewookEnCola = 0; // Número de Ewooks esperando
    private int goraxEnCola = 0; // Número de Gorax esperando

    public Bar(int aforoMax) {
        this.aforoMax = aforoMax;
    }

    public void entrar(int id, String tipo) {
        lock.lock();
        try {
            if (tipo.equals("Ewook")) {
                ewookEnCola++;
            } else {
                goraxEnCola++;
            }

            // Esperar mientras el bar esté lleno o si es Gorax y hay Ewooks en cola
            while (clientesDentro >= aforoMax || (tipo.equals("Gorax") && ewookEnCola > 0)) {
                puedenEntrar.await();
            }

            // Cuando se puede entrar, actualizar estado
            clientesDentro++;
            if (tipo.equals("Ewook")) {
                ewookEnCola--;
            } else {
                goraxEnCola--;
            }

            System.out.println("--> " + tipo + " (" + id + ") entra en el bar. Clientes dentro: " + clientesDentro);


            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void salir(int id, String tipo) {
        lock.lock();
        try {
            clientesDentro--;
            System.out.println("<-- " + tipo + " (" + id + ") ha salido del bar. Clientes dentro: " + clientesDentro);

            // Notificar a los clientes en espera que ahora hay espacio
            puedenEntrar.signalAll();

        } finally {
            lock.unlock();
        }
    }
}
