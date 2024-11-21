package barberodurmienteSemaforo;

class Barbero extends Thread {
    private final Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        try {
            barberia.atenderClientes();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}