package bar.Bar3;

public class Cliente implements Runnable {
    private int id;
    Bar bar;
    String tipo;


    public Cliente(Bar bar, int id, String tipo) {
        this.bar = bar;
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        bar.entrar(id,tipo);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bar.salir(id, tipo);
    }
}
