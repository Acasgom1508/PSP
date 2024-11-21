package barberodurmiente;

class Cliente extends Thread {
    private Barberia barberia;
    private int id;

    public Cliente(Barberia barberia, int id) {
        this.barberia = barberia;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            if (barberia.entrarBarberia(id)) {
                barberia.esperarCorte(id);
                System.out.println("Cliente " + id + " termina su corte");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
