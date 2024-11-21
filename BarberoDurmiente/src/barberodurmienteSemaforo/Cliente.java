package barberodurmienteSemaforo;

class Cliente extends Thread {
    private final Barberia barberia;
    private final int id;

    public Cliente(Barberia barberia, int id) {
        this.barberia = barberia;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            barberia.entrarCliente(id);
            System.out.println("Cliente " + id + " termina su corte");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
