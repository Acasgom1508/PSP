package barberodurmienteLock;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Barberia {
    private final int sillasEspera;
    private int clientesEsperando = 0;
    private boolean sillaBarberoOcupada = false;
    private boolean clienteEsperandoCorte = false;
    private Random random = new Random();

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition barberoCondition = lock.newCondition();
    private final Condition clienteCondition = lock.newCondition();

    public Barberia(int numSillas) {
        this.sillasEspera = numSillas;
    }

    public boolean entrarBarberia(int clienteId) {
        lock.lock();
        try {
            if (clientesEsperando >= sillasEspera) {
                System.out.println("Cliente " + clienteId + " se va (no hay sillas)");
                return false;
            }

            clientesEsperando++;
            System.out.println("Cliente " + clienteId + " esperando. Sillas ocupadas: " + clientesEsperando);

            while (sillaBarberoOcupada) {
                clienteCondition.await();
            }

            clientesEsperando--;
            sillaBarberoOcupada = true;
            clienteEsperandoCorte = true;
            System.out.println("Cliente " + clienteId + " sentado para corte");
            barberoCondition.signal();
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void esperarCorte(int clienteId) {
        lock.lock();
        try {
            while (!clienteEsperandoCorte) {
                clienteCondition.await();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void cortarPelo() {
        lock.lock();
        try {
            while (!clienteEsperandoCorte) {
                System.out.println("Barbero dormido...");
                barberoCondition.await();
            }

            if (random.nextBoolean()) {
                System.out.println("Barbero se hace el dormido (3-6 seg)");
                Thread.sleep(random.nextInt(3000, 6000));
            }

            int tiempoCorte = random.nextInt(5000, 10000);
            System.out.println("Barbero cortando pelo (duración: " + tiempoCorte/1000 + " seg)");
            Thread.sleep(tiempoCorte);

            System.out.println("Barbero limpiando después del corte (5 seg)");
            Thread.sleep(5000);

            clienteEsperandoCorte = false;
            sillaBarberoOcupada = false;
            clienteCondition.signalAll();

            System.out.println("Barbero descansando (10-20 seg)");
            Thread.sleep(random.nextInt(10000, 20000));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
