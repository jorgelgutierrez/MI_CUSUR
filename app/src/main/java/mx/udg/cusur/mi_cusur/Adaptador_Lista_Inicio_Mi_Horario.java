package mx.udg.cusur.mi_cusur;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Macbook on 06/09/16.
 */
public class Adaptador_Lista_Inicio_Mi_Horario extends ArrayAdapter<String>{

    Context mycontext;
    int mylayoutResourceId;
    ArrayList mymaterias = new ArrayList();
    ArrayList myhorario = new ArrayList();
    ArrayList mydias = new ArrayList();
    ArrayList myedificio = new ArrayList();
    ArrayList myaula = new ArrayList();
    ArrayList myprofesor = new ArrayList();
    ArrayList myfechai = new ArrayList();
    ArrayList myfechaf = new ArrayList();

    public Adaptador_Lista_Inicio_Mi_Horario(Context context, int layoutResourceId, ArrayList materias, ArrayList horario, ArrayList dias, ArrayList edificio, ArrayList aula, ArrayList profesor, ArrayList fechai, ArrayList fechaf){
        super(context,layoutResourceId,materias);

        this.mycontext = context;
        this.mylayoutResourceId = layoutResourceId;
        this.mymaterias = materias;
        this.myhorario = horario;
        this.mydias = dias;
        this.myedificio = edificio;
        this.myaula = aula;
        this.myprofesor = profesor;
        this.myfechai = fechai;
        this.myfechaf = fechaf;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        Lista_Mi_Horario_Holder holder = null;

        if(row == null){

            LayoutInflater inflater = ((Activity)mycontext).getLayoutInflater();
            row = inflater.inflate(mylayoutResourceId,parent,false);

            holder = new Lista_Mi_Horario_Holder();
            holder.materias = (TextView) row.findViewById(R.id.item_mi_horario_txtmateria);
            holder.horario = (TextView) row.findViewById(R.id.item_mi_horario_txthorario);
            holder.dias = (TextView) row.findViewById(R.id.item_mi_horario_txtdias);
            holder.edificio = (TextView) row.findViewById(R.id.item_mi_horario_txtedificio);
            holder.aula = (TextView) row.findViewById(R.id.item_mi_horario_txtaula);
            holder.profesor = (TextView) row.findViewById(R.id.item_mi_horario_txtprofesor);
            holder.fechai = (TextView) row.findViewById(R.id.item_mi_horario_txtfechai);
            holder.fechaf = (TextView) row.findViewById(R.id.item_mi_horario_txtfechaf);
            row.setTag(holder);

        }else{

            holder = (Lista_Mi_Horario_Holder)row.getTag();
        }

        holder.materias.setText(mymaterias.get(position).toString());
        holder.horario.setText(myhorario.get(position).toString());
        holder.dias.setText(mydias.get(position).toString());
        holder.edificio.setText(myedificio.get(position).toString());
        holder.aula.setText(myaula.get(position).toString());
        holder.profesor.setText(myprofesor.get(position).toString());
        holder.fechai.setText(myfechai.get(position).toString());
        holder.fechaf.setText(myfechaf.get(position).toString());

        return row;
    }

    static class Lista_Mi_Horario_Holder{
        TextView materias;
        TextView horario;
        TextView dias;
        TextView edificio;
        TextView aula;
        TextView profesor;
        TextView fechai;
        TextView fechaf;
    }
}
