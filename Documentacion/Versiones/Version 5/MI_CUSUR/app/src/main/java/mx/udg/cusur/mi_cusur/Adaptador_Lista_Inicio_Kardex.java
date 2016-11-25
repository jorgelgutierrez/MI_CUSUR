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
public class Adaptador_Lista_Inicio_Kardex extends ArrayAdapter<String>{

    Context mycontext;
    int mylayoutResourceId;
    ArrayList mynrc = new ArrayList();
    ArrayList myclave = new ArrayList();
    ArrayList mymateria = new ArrayList();
    ArrayList mycalificacion = new ArrayList();
    ArrayList mytipo = new ArrayList();
    ArrayList mycreditos = new ArrayList();
    ArrayList myfecha = new ArrayList();
    ArrayList mycalendario = new ArrayList();

    public Adaptador_Lista_Inicio_Kardex(Context context, int layoutResourceId, ArrayList nrc, ArrayList clave, ArrayList materia, ArrayList calficacion, ArrayList tipo, ArrayList creditos, ArrayList fecha, ArrayList calendario){
        super(context,layoutResourceId,nrc);

        this.mycontext = context;
        this.mylayoutResourceId = layoutResourceId;
        this.mynrc = nrc;
        this.myclave = clave;
        this.mymateria = materia;
        this.mycalificacion = calficacion;
        this.mytipo = tipo;
        this.mycreditos = creditos;
        this.myfecha = fecha;
        this.mycalendario = calendario;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        Lista_Kardex_Holder holder = null;

        if(row == null){

            LayoutInflater inflater = ((Activity)mycontext).getLayoutInflater();
            row = inflater.inflate(mylayoutResourceId,parent,false);

            holder = new Lista_Kardex_Holder();
            holder.nrc = (TextView) row.findViewById(R.id.item_kardex_txtnrc);
            holder.clave = (TextView) row.findViewById(R.id.item_kardex_txtclave);
            holder.materia = (TextView) row.findViewById(R.id.item_kardex_txtmateria);
            holder.calificacion = (TextView) row.findViewById(R.id.item_kardex_txtcalificacion);
            holder.tipo = (TextView) row.findViewById(R.id.item_kardex_txttipo);
            holder.creditos = (TextView) row.findViewById(R.id.item_kardex_txtcreditos);
            holder.fecha = (TextView) row.findViewById(R.id.item_kardex_txtfecha);
            holder.calendario = (TextView) row.findViewById(R.id.item_kardex_txtcalendario);
            row.setTag(holder);

        }else{

            holder = (Lista_Kardex_Holder) row.getTag();
        }

        holder.nrc.setText(mynrc.get(position).toString());
        holder.clave.setText(myclave.get(position).toString());
        holder.materia.setText(mymateria.get(position).toString());
        holder.calificacion.setText(mycalificacion.get(position).toString());
        holder.tipo.setText(mytipo.get(position).toString());
        holder.creditos.setText(mycreditos.get(position).toString());
        holder.fecha.setText(myfecha.get(position).toString());
        holder.calendario.setText(mycalendario.get(position).toString());

        return row;
    }

    static class Lista_Kardex_Holder{
        TextView nrc;
        TextView clave;
        TextView materia;
        TextView calificacion;
        TextView tipo;
        TextView creditos;
        TextView fecha;
        TextView calendario;
    }
}
