package corredores;

public class Carrera {
    public static void main(String[] args) {
        System.out.println("Va a comenzar la carrera!");
        
        TrofeoGanador trofeo = new TrofeoGanador();
        
        Corredor corredor1 = new Corredor("Coyote", trofeo);
        Corredor corredor2 = new Corredor("Correcaminos", trofeo);
        
        // Iniciamos los hilos
        corredor1.start();
        corredor2.start();
        
        // Esperamos a que ambos corredores terminen
        try {
            corredor1.join();
            corredor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
