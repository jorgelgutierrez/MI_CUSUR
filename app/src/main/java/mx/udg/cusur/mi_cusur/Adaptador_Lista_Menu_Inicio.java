package mx.udg.cusur.mi_cusur;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Macbook on 06/09/16.
 */
public class Adaptador_Lista_Menu_Inicio extends ArrayAdapter<String>{

    Context mycontext;
    int mylayoutResourceId;
    String mytitulos[] = null;

    public Adaptador_Lista_Menu_Inicio(Context context, int layoutResourceId, String[] titulos){
        super(context,layoutResourceId,titulos);

        this.mycontext = context;
        this.mylayoutResourceId = layoutResourceId;
        this.mytitulos = titulos;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        Titulos_Menu_Navegacion_Holder holder = null;

        if(row == null){

            LayoutInflater inflater = ((Activity)mycontext).getLayoutInflater();
            row = inflater.inflate(mylayoutResourceId,parent,false);

            holder = new Titulos_Menu_Navegacion_Holder();
            holder.imagen = (ImageView) row.findViewById(R.id.item_imagen);
            holder.textView = (TextView) row.findViewById(R.id.item_titulo_menu_inicio);
            row.setTag(holder);

        }else{

            holder = (Titulos_Menu_Navegacion_Holder)row.getTag();
        }

        holder.textView.setText(mytitulos[position]);
        int icono = 0;

        if(mytitulos[position].equals("Informacion del Alumno")){
            icono = R.mipmap.ic_menu_info;
        }else if(mytitulos[position].equals("Mi Horario")){
            icono = R.mipmap.ic_menu_horario;
        }else if(mytitulos[position].equals("Orden de Pago")){
            icono = R.mipmap.ic_menu_orden;
        }else if(mytitulos[position].equals("Kardex")){
            icono = R.mipmap.ic_menu_kardex;
        }else if(mytitulos[position].equals("Boleta de Calificaciones")){
            icono = R.mipmap.ic_menu_boleta;
        }else if(mytitulos[position].equals("Cerrar Sesion")){
            icono = R.mipmap.ic_menu_cerrar_sesion;
        }
        holder.imagen.setImageResource(icono);


        return row;
    }

    static class Titulos_Menu_Navegacion_Holder{
        ImageView imagen;
        TextView textView;
    }
}
