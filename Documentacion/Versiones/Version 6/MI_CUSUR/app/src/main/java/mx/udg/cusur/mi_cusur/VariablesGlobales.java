package mx.udg.cusur.mi_cusur;

import android.content.res.Resources;

/**
 * Created by Macbook on 8/11/16.
 */
public class VariablesGlobales {
    private static VariablesGlobales instance;
    private static int numero_notificaciones = 1;
    private static String ubicacion_fragment = "Inicio";

    private VariablesGlobales(){

    }
    public void setNumero_notificaciones(int numero){
        VariablesGlobales.numero_notificaciones = numero;
    }
    public void setUbicacion_fragment(String ubicacion_fragment){
        VariablesGlobales.ubicacion_fragment = ubicacion_fragment;
    }

    public String getUbicacion_fragment(){return VariablesGlobales.ubicacion_fragment;}

    public int getNumero_Notificaciones(){
        return VariablesGlobales.numero_notificaciones;
    }

    public static synchronized VariablesGlobales getInstance(){
        if(instance==null){
            instance= new VariablesGlobales();
        }
        return instance;
    }
}
