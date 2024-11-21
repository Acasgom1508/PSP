package barcanasLock;

class Cliente extends Thread {
    private BarCanas bar;
    private int cañasABeber;
    private int id;

    public Cliente(BarCanas bar, int cañasABeber, int id) {
        this.bar = bar;
        this.cañasABeber = cañasABeber;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < cañasABeber; i++) {
                bar.tomarCaña(id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
