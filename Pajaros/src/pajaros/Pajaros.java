/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pajaros;

/**
 *
 * @author DAM_M
 */
public class Pajaros implements Runnable {

    private String tipoPajaro;
    private String nivelEducacion;

    @Override
    public void run() {
        synchronized (tipoPajaro) {
            switch (nivelEducacion) {
                case "NoEducado":
                    System.out.println(tipoPajaro + ": Soy un pajaro de las 3000");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    notifyAll();
                    break;
                case "Educado":
                    System.out.println(tipoPajaro + ": Hola, soy de dos hermanas");
                    notifyAll();
                    break;
                case "MuyEducado":
                    System.out.println(tipoPajaro + ": Soy muy educado");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
                default:
                    break;
            }
        }

    }

    // constructor
    public Pajaros(String tipoPajaro, String nivelEducacion) {
        this.nivelEducacion = nivelEducacion;
        this.tipoPajaro = tipoPajaro;
    }

}
