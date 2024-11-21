package barberodurmiente;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Barberia {
    private int sillasEspera;
    private int clientesEsperando = 0;
    private boolean sillaBarberoOcupada = false;
    private boolean clienteEsperandoCorte = false;
    private Random random = new Random();

    public Barberia(int numSillas) {
        this.sillasEspera = numSillas;
    }

    public synchronized boolean entrarBarberia(int clienteId) throws InterruptedException {
        if (clientesEsperando >= sillasEspera) {
            System.out.println("Cliente " + clienteId + " se va (no hay sillas)");
            return false;
        }

        clientesEsperando++;
        System.out.println("Cliente " + clienteId + " esperando. Sillas ocupadas: " + clientesEsperando);

        while (sillaBarberoOcupada) {
            wait();
        }

        clientesEsperando--;
        sillaBarberoOcupada = true;
        clienteEsperandoCorte = true;
        System.out.println("Cliente " + clienteId + " sentado para corte");
        notifyAll();
        return true;
    }

    public synchronized void esperarCorte(int clienteId) throws InterruptedException {
        while (!clienteEsperandoCorte) {
            wait();
        }
    }

    public synchronized void cortarPelo() throws InterruptedException {
        while (!clienteEsperandoCorte) {
            System.out.println("Barbero dormido...");
            wait();
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
        notifyAll();
        
        System.out.println("Barbero descansando (10-20 seg)");
        Thread.sleep(random.nextInt(10000, 20000));
    }
}