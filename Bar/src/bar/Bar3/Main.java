package bar.bar2;

public class Main {
    public static void main(String[] args) {
        Bar bar1 = new Bar(5);
        for (int i = 0; i < 10; i++) {
            Thread cliente = new Thread(new Cliente(bar1, (i+1)));
            cliente.start(); 
        }
    }
}
