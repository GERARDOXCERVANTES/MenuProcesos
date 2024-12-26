/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;


/**
 *
 * @author cerva
 */
public class Procesos {
    String nombre;
    int tiempoLlegada;
    int tiempoEjecucion;
    public int tiempoF;
    int tiempoE;
    int tiempoS;
    double rendimiento;
    int OriginalBurst;
    
   


    public Procesos() {

        
    }


    
    
    public Procesos(String nombre, int tiempoLlegada, int tiempoEjecucion) {
        this.nombre = nombre;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoEjecucion = tiempoEjecucion;
        this.OriginalBurst = tiempoEjecucion;  // Guardar el tiempo de ráfaga original

    }


    


    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public int getTiempoF() {
        return tiempoF;
    }




    public void setTiempoF(int tiempoF) {
        this.tiempoF = tiempoF;
    }

    public int getTiempoE() {
        return tiempoE;
    }



    public void setTiempoE(int tiempoE) {
        this.tiempoE = tiempoE;
    }




    public int getTiempoS() {
        return tiempoS;
    }




    public void setTiempoS(int tiempoS) {
        this.tiempoS = tiempoS;
    }

    public int getOriginalBurst() {
        return OriginalBurst;
    }




    public void setOriginalBurst(int originalBurst) {
        OriginalBurst = originalBurst;
    }




   


    
    
}


