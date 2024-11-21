package corredores;

public class Corredor extends Thread {
    private String nombre;
    private Boolean esGanador;
    private TrofeoGanador trofeo;
    
    public Corredor(String nombre, TrofeoGanador trofeo) {
        this.nombre = nombre;
        this.esGanador = false;
        this.trofeo = trofeo;
    }
    
    @Override
    public void run() {
        // Simulamos la carrera
        for (int i = 0; i <= 900; i += 100) {
            try {
                Thread.sleep((long)(Math.random() * 500)); // Tiempo aleatorio entre pasos
                System.out.println(i + " m : " + nombre);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Verificamos si el trofeo está disponible
        if (trofeo.estaDisponible()) {
            if (trofeo.obtenerTrofeo()) {
                esGanador = true;
                System.out.println(nombre + " ha terminado la carrera ¡es el GANADOR!");
            }
        } else {
            System.out.println(nombre + " ha terminado la carrera ...ánimo");
        }
    }
    
    public Boolean esGanador() {
        return esGanador;
    }
}
