package mx.udg.cusur.mi_cusur;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Macbook on 06/09/16.
 */
public class Adaptador_Lista_Inicio_Boleta_Calificaciones extends  ArrayAdapter<String>{

    Context mycontext;
    int mylayoutResourceId;
    ArrayList mymateria = new ArrayList();
    ArrayList myordinario = new ArrayList();
    ArrayList mykardexo = new ArrayList();
    ArrayList myextraordinario = new ArrayList();
    ArrayList mykardexe = new ArrayList();

    public Adaptador_Lista_Inicio_Boleta_Calificaciones(Context context, int layoutResourceId, ArrayList materia, ArrayList ordinario, ArrayList kardexo, ArrayList extraordinario, ArrayList kardexe){
        super(context,layoutResourceId,materia);

        this.mycontext = context;
        this.mylayoutResourceId = layoutResourceId;
        this.mymateria = materia;
        this.myordinario = ordinario;
        this.mykardexo = kardexo;
        this.myextraordinario = extraordinario;
        this.mykardexe = kardexe;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        Lista_Boleta_Calificaciones_Holder holder = null;

        if(row == null){

            LayoutInflater inflater = ((Activity)mycontext).getLayoutInflater();
            row = inflater.inflate(mylayoutResourceId,parent,false);

            holder = new Lista_Boleta_Calificaciones_Holder();
            holder.materia = (TextView) row.findViewById(R.id.item_boleta_calificaciones_txtmateria);
            holder.ordinario = (TextView) row.findViewById(R.id.item_boleta_calificaciones_txtordinario);
            holder.kardexo = (TextView) row.findViewById(R.id.item_boleta_calificaciones_txtkardexo);
            holder.extraordinario = (TextView) row.findViewById(R.id.item_boleta_calificaciones_txtextraordinario);
            holder.kardexe = (TextView) row.findViewById(R.id.item_boleta_calificaciones_txtkardexe);
            row.setTag(holder);

        }else{

            holder = (Lista_Boleta_Calificaciones_Holder) row.getTag();
        }

        holder.materia.setText(mymateria.get(position).toString());
        holder.ordinario.setText(myordinario.get(position).toString());
        holder.kardexo.setText(mykardexo.get(position).toString());
        holder.extraordinario.setText(myextraordinario.get(position).toString());
        holder.kardexe.setText(mykardexe.get(position).toString());

        return row;
    }

    static class Lista_Boleta_Calificaciones_Holder{
        TextView materia;
        TextView ordinario;
        TextView kardexo;
        TextView extraordinario;
        TextView kardexe;
    }
}
