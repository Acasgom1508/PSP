package barberodurmienteLock;

class Cliente extends Thread {
    private final Barberia barberia;
    private final int id;

    public Cliente(Barberia barberia, int id) {
        this.barberia = barberia;
        this.id = id;
    }

    @Override
    public void run() {
        if (barberia.entrarBarberia(id)) {
            barberia.esperarCorte(id);
            System.out.println("Cliente " + id + " termina su corte");
        }
    }
}
