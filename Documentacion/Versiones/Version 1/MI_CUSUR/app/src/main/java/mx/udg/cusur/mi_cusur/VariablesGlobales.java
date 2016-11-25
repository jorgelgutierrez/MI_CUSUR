package mx.udg.cusur.mi_cusur;

/**
 * Created by Macbook on 8/11/16.
 */
public class VariablesGlobales {
    private static VariablesGlobales instance;
    private static String codigo;

    private VariablesGlobales(){

    }

    public void setCodigo(String cod){
        VariablesGlobales.codigo = cod;

    }

    public String getCodigo(){
        return VariablesGlobales.codigo;
    }

    public static synchronized VariablesGlobales getInstance(){
        if(instance==null){
            instance= new VariablesGlobales();
        }
        return instance;
    }
}
