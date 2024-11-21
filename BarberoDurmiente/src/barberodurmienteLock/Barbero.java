package barberodurmienteLock;

class Barbero extends Thread {
    private final Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            barberia.cortarPelo();
        }
    }
}