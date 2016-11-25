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
        int imagen = R.mipmap.ic_launcher;
        holder.imagen.setImageResource(imagen);


        return row;
    }

    static class Titulos_Menu_Navegacion_Holder{
        ImageView imagen;
        TextView textView;
    }
}
