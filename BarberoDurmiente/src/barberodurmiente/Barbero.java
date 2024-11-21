package barberodurmiente;

class Barbero extends Thread {
    private Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            try {
                barberia.cortarPelo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}