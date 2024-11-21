package barberoduermienteMod;

import java.util.Random;

class Cliente extends Thread {
    private final Barberia barberia;
    private final int id;
    private final Random random = new Random();

    public Cliente(Barberia barberia, int id) {
        this.barberia = barberia;
        this.id = id;
    }

    private void darVuelta() throws InterruptedException {
        int tiempoPaseo = random.nextInt(5000, 20000);
        System.out.println("Cliente " + id + " dando una vuelta de " + (tiempoPaseo/1000) + " segundos");
        Thread.sleep(tiempoPaseo);
    }

    @Override
    public void run() {
        try {
            int intentos = 0;
            while (true) {
                int resultado = barberia.intentarEntrar(id, intentos);
                if (resultado == 0) {
                    // Consiguió entrar
                    barberia.esperarCorte(id);
                    System.out.println("Cliente " + id + " termina su corte");
                    break;
                } else if (resultado == -1) {
                    // Se va definitivamente
                    break;
                } else {
                    // Dar una vuelta y reintentar
                    intentos = resultado;
                    darVuelta();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
