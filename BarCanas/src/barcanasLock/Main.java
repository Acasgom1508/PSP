package barcanasLock;

public class Main {
    public static void main(String[] args) {
        BarCanas bar = new BarCanas(10);
        Camarero camarero = new Camarero(bar);
        camarero.start();

        for (int i = 0; i < 7; i++) {
            int cañasABeber = (int) (Math.random() * 5) + 1;
            Thread cliente = new Thread(new Cliente(bar, cañasABeber,(i+1)));
            cliente.start();
        }
    }
}
