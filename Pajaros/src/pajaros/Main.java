package pajaros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] nivelesEducacion = {1, 2, 3, 4, 5};
        
        for (int nivel : nivelesEducacion) {
            System.out.println("\nPrueba con nivel de educación " + nivel);
            List<Pajaro> pajaros = new ArrayList<>();
            
            // Crear 10 pájaros de cada tipo
            for (TipoPajaro tipo : TipoPajaro.values()) {
                for (int i = 0; i < 10; i++) {
                    pajaros.add(new Pajaro(tipo, (i+1), nivel));
                }
            }
            
            if (nivel >= 4) {
                Pajaro.setBarrier(pajaros.size());
            }
            
            long inicio = System.currentTimeMillis();
            
            // Iniciar todos los hilos
            for (Pajaro p : pajaros) {
                p.start();
            }
            
            if (nivel >= 4) {
                Thread.sleep(8000); // Esperar 8 segundos antes de dar la señal
                Pajaro.setIniciarCanto(true);
                // Despertar al primer pájaro aleatoriamente
                pajaros.get(new Random().nextInt(pajaros.size())).interrupt();
            }
            
            // Esperar a que todos terminen
            for (Pajaro p : pajaros) {
                p.join();
            }
            
            long fin = System.currentTimeMillis();
            System.out.println("Tiempo total para nivel " + nivel + ": " + 
                             ((fin - inicio) / 1000.0) + " segundos");
            
            // Resetear las variables estáticas para la siguiente prueba
            Pajaro.setIniciarCanto(false);
        }
    }
}
