package bar.Bar1;

public class Cliente implements Runnable {
    private int id;
    Bar bar;

    public Cliente(Bar bar, int id) {
        this.bar = bar;
        this.id = id;
    }

    @Override
    public void run() {
        bar.entrar(id);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bar.salir(id);
    }
}
