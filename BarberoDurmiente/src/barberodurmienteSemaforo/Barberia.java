package barberodurmienteSemaforo;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Barberia {
    private final int sillasEspera;
    private int clientesEsperando = 0;
    private Random random = new Random();

    private Semaphore barbero = new Semaphore(0);
    private Semaphore cliente = new Semaphore(0);
    private Semaphore sillasEsperaLock = new Semaphore(1);
    private Semaphore corte = new Semaphore(1);

    public Barberia(int numSillas) {
        this.sillasEspera = numSillas;
    }

    public void entrarCliente(int clienteId) throws InterruptedException {
        sillasEsperaLock.acquire();
        
        if (clientesEsperando >= sillasEspera) {
            sillasEsperaLock.release();
            System.out.println("Cliente " + clienteId + " se va (no hay sillas)");
            return;
        }

        clientesEsperando++;
        System.out.println("Cliente " + clienteId + " esperando. Sillas ocupadas: " + clientesEsperando);
        sillasEsperaLock.release();

        barbero.release();
        cliente.acquire();
        
        sillasEsperaLock.acquire();
        clientesEsperando--;
        sillasEsperaLock.release();

        corte.acquire();
        System.out.println("Cliente " + clienteId + " siendo atendido");
        corte.release();
    }

    public void atenderClientes() throws InterruptedException {
        while (true) {
            try {
                System.out.println("Barbero dormido...");
                barbero.acquire();
                cliente.release();

                if (random.nextBoolean()) {
                    System.out.println("Barbero se hace el dormido (3-6 seg)");
                    Thread.sleep(random.nextInt(3000, 6000));
                }

                int tiempoCorte = random.nextInt(5000, 10000);
                System.out.println("Barbero cortando pelo (duración: " + tiempoCorte/1000 + " seg)");
                Thread.sleep(tiempoCorte);

                System.out.println("Barbero limpiando después del corte (5 seg)");
                Thread.sleep(5000);

                System.out.println("Barbero descansando (10-20 seg)");
                Thread.sleep(random.nextInt(10000, 20000));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}