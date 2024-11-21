package barcanas;

class Camarero extends Thread {
    private BarCanas bar;

    public Camarero(BarCanas bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        try {
            while (true) {
                bar.reponer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
