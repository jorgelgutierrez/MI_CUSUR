package mx.udg.cusur.mi_cusur;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Macbook on 06/09/16.
 */
public class Adaptador_Lista_Ajustes_Notificaciones extends ArrayAdapter<String>{

    Context mycontext;
    int mylayoutResourceId;
    ArrayList mytemas = new ArrayList();
    SharedPreferences sharedPreferences = getContext().getSharedPreferences("mx.udg.cusur.mi_cusur.fcm_pref", Context.MODE_PRIVATE);
    SharedPreferences Preferences = getContext().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
    String codigo_alumno = Preferences.getString("codigo","");
    String checked = sharedPreferences.getString("checkbox"+codigo_alumno,"");
    String[] ischecked = null;
    Boolean datos = false;


    public Adaptador_Lista_Ajustes_Notificaciones(Context context, int layoutResourceId, ArrayList temas){
        super(context,layoutResourceId,temas);

        this.mycontext = context;
        this.mylayoutResourceId = layoutResourceId;
        this.mytemas = temas;
        if(checked.equals("")){
            datos = false;
        }else{
            ischecked = checked.split(",");
            datos = true;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        Titulos_Ajustes_Notificaciones_Holder holder = null;

        if(row == null){

            LayoutInflater inflater = ((Activity)mycontext).getLayoutInflater();
            row = inflater.inflate(mylayoutResourceId,parent,false);

            holder = new Titulos_Ajustes_Notificaciones_Holder();
            holder.tema = (CheckBox) row.findViewById(R.id.item_ajustes_checkbox_tema);
            row.setTag(holder);

        }else{

            holder = (Titulos_Ajustes_Notificaciones_Holder) row.getTag();
        }
        holder.tema.setText(mytemas.get(position).toString());
        if (datos) {
            if (ischecked[position].equals("x")) {
                holder.tema.setChecked(true);
            } else {
                holder.tema.setChecked(false);
            }
        }

        return row;
    }

    static class Titulos_Ajustes_Notificaciones_Holder{
        CheckBox tema;
    }
}
