package barcanas;

public class BarCanas {
    private final int totalCañas;
    private int cañasServidas;
    private boolean barrileNecesitaReposicion;
    private final Object lock = new Object();

    public BarCanas(int nCañas) {
        this.totalCañas = nCañas;
        this.cañasServidas = 0;
        this.barrileNecesitaReposicion = false;
    }

    public void tomarCaña(int id) throws InterruptedException {
        synchronized (lock) {
            // Si el barril está agotado, intentar reponerlo inmediatamente
            if (barrileNecesitaReposicion) {
                reponer();
            }

            // Servir caña
            cañasServidas++;
            System.out.println("Sirviendo caña " + cañasServidas + " al cliente " + id + "...");
            Thread.sleep(500);

            // Comprobar si el barril está vacío
            if (cañasServidas >= totalCañas) {
                barrileNecesitaReposicion = true;
                System.out.println("Barril agotado. Reponiendo...");
            }
        }
    }

    public void reponer() throws InterruptedException {
        synchronized (lock) {
            if (barrileNecesitaReposicion) {
                cañasServidas = 0;
                barrileNecesitaReposicion = false;
                Thread.sleep(1500);
                System.out.println("Barril reemplazado.");
            }
        }
    }
}