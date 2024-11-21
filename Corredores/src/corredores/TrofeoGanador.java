/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package corredores;

/**
 *
 * @author anton
 */

 public class TrofeoGanador {
    private Boolean estaElTrofeo;
    
    public TrofeoGanador() {
        this.estaElTrofeo = true;
    }
    
    public synchronized Boolean estaDisponible() {
        return estaElTrofeo;
    }
    
    public synchronized Boolean obtenerTrofeo() {
        if (estaElTrofeo) {
            estaElTrofeo = false;
            return true;
        }
        return false;
    }
}
