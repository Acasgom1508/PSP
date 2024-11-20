package pajaros;
public class Main {
    public static void main(String[] args) {
        Thread noEducado = new Thread(new Pajaros("Periquito","NoEducado"));
        noEducado.start();
        Thread educado = new Thread(new Pajaros("Loro","Educado"));
        educado.start();
        Thread muyEducado = new Thread(new Pajaros("Gorrión","MuyEducado"));
        muyEducado.start();
    }
}
