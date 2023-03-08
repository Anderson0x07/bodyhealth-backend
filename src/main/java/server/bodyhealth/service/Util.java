package server.bodyhealth.service;



import java.util.Date;

public class Util {
    public Util(){

    }
    public int obtenerDiferenciaDias(Date fecha_fin){

        Date fechaactual = new Date(System.currentTimeMillis());

        int milisecondsByDay = 86400000;
        int dias = (int) ((fecha_fin.getTime()-fechaactual.getTime()) / milisecondsByDay);

        return dias;
    }

    public Date obtenerFechaActual() {

        Date fechaActual = new Date(System.currentTimeMillis());

        return fechaActual;
    }

}
