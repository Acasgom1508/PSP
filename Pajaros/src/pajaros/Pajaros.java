/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pajaros;

/**
 *
 * @author DAM_M
 */
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum TipoPajaro {
    PERIQUITO, LORO, GORRION
}

class Pajaro extends Thread {
    private final TipoPajaro tipo;
    private final int id;
    private final int nivelEducacion;
    private static final Random random = new Random();
    private static final Lock lock = new ReentrantLock();
    private static volatile boolean[] cantando;
    private static volatile TipoPajaro ultimoTipoCantando = null;
    private static CyclicBarrier barrier;
    private static volatile boolean iniciarCanto = false;
    private static final Map<TipoPajaro, List<Pajaro>> pajarosEsperando = new ConcurrentHashMap<>();

    public Pajaro(TipoPajaro tipo, int id, int nivelEducacion) {
        this.tipo = tipo;
        this.id = id;
        this.nivelEducacion = nivelEducacion;

        if (cantando == null) {
            cantando = new boolean[TipoPajaro.values().length];
        }
    }

    public static void setBarrier(int numPajaros) {
        barrier = new CyclicBarrier(numPajaros);
    }

    public static void setIniciarCanto(boolean iniciar) {
        iniciarCanto = iniciar;
    }

    private void esperarInicio() {
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean puedeCantarNivel1() {
        return true;
    }

    private boolean puedeCantarNivel2() {
        return !cantando[tipo.ordinal()];
    }

    private boolean puedeCantarNivel3() {
        for (boolean c : cantando) {
            if (c)
                return false;
        }
        return true;
    }

    private Pajaro seleccionarSiguientePajaro() {
        List<TipoPajaro> tiposDisponibles = new ArrayList<>();
        for (TipoPajaro t : TipoPajaro.values()) {
            if (t != tipo && !pajarosEsperando.getOrDefault(t, Collections.emptyList()).isEmpty()) {
                tiposDisponibles.add(t);
            }
        }

        if (tiposDisponibles.isEmpty()) {
            if (!pajarosEsperando.getOrDefault(tipo, Collections.emptyList()).isEmpty()) {
                tiposDisponibles.add(tipo);
            }
        }

        if (!tiposDisponibles.isEmpty()) {
            TipoPajaro tipoSeleccionado = tiposDisponibles.get(random.nextInt(tiposDisponibles.size()));
            List<Pajaro> pajarosDelTipo = pajarosEsperando.get(tipoSeleccionado);
            if (!pajarosDelTipo.isEmpty()) {
                return pajarosDelTipo.remove(0);
            }
        }
        return null;
    }

    private void cantar() throws InterruptedException {
        lock.lock();
        try {
            cantando[tipo.ordinal()] = true;
            System.out.println(tipo + " " + id + " está cantando");
            Thread.sleep(2000); // Tiempo de canto
            cantando[tipo.ordinal()] = false;
            ultimoTipoCantando = tipo;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000); // Espera inicial

            if (nivelEducacion >= 4) {
                esperarInicio();
                pajarosEsperando.computeIfAbsent(tipo, k -> new ArrayList<>()).add(this);

                while (!iniciarCanto) {
                    Thread.sleep(100);
                }
            }

            boolean haTerminado = false;
            while (!haTerminado) {
                lock.lock();
                try {
                    boolean puedoCantar = switch (nivelEducacion) {
                        case 1 -> puedeCantarNivel1();
                        case 2 -> puedeCantarNivel2();
                        case 3 -> puedeCantarNivel3();
                        case 4, 5 -> true;
                        default -> false;
                    };

                    if (puedoCantar) {
                        cantar();
                        haTerminado = true;

                        if (nivelEducacion >= 4) {
                            Pajaro siguiente = seleccionarSiguientePajaro();
                            if (siguiente != null) {
                                siguiente.interrupt(); // Señal para que cante el siguiente
                            }
                        }
                    }
                } finally {
                    lock.unlock();
                }

                if (!haTerminado) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // Despertado para cantar
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}