package mx.udg.cusur.mi_cusur;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Macbook on 06/09/16.
 */
public class Adaptador_Lista_Inicio_Orden_Pago extends ArrayAdapter<String>{

    Context mycontext;
    int mylayoutResourceId;
    ArrayList mycuenta = new ArrayList();
    ArrayList myconcepto = new ArrayList();
    ArrayList mydescripcion = new ArrayList();
    ArrayList myfecha = new ArrayList();
    ArrayList myfechav = new ArrayList();
    ArrayList mymonto = new ArrayList();

    public Adaptador_Lista_Inicio_Orden_Pago(Context context, int layoutResourceId, ArrayList cuenta, ArrayList concepto, ArrayList descripcion, ArrayList fecha, ArrayList fechav, ArrayList monto){
        super(context,layoutResourceId,cuenta);

        this.mycontext = context;
        this.mylayoutResourceId = layoutResourceId;
        this.mycuenta = cuenta;
        this.myconcepto = concepto;
        this.mydescripcion = descripcion;
        this.myfecha = fecha;
        this.myfechav = fechav;
        this.mymonto = monto;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        Lista_Orden_Pago_Holder holder = null;

        if(row == null){

            LayoutInflater inflater = ((Activity)mycontext).getLayoutInflater();
            row = inflater.inflate(mylayoutResourceId,parent,false);

            holder = new Lista_Orden_Pago_Holder();
            holder.cuenta = (TextView) row.findViewById(R.id.item_orden_pago_txtcuenta);
            holder.concepto = (TextView) row.findViewById(R.id.item_orden_pago_txtconcepto);
            holder.descripcion = (TextView) row.findViewById(R.id.item_orden_pago_txtdescripcion);
            holder.fecha = (TextView) row.findViewById(R.id.item_orden_pago_txtfecha);
            holder.fechav = (TextView) row.findViewById(R.id.item_orden_pago_txtvencimiento);
            holder.monto = (TextView) row.findViewById(R.id.item_orden_pago_txtmonto);
            row.setTag(holder);

        }else{

            holder = (Lista_Orden_Pago_Holder)row.getTag();
        }

        holder.cuenta.setText(mycuenta.get(position).toString());
        holder.concepto.setText(myconcepto.get(position).toString());
        holder.descripcion.setText(mydescripcion.get(position).toString());
        holder.fecha.setText(myfecha.get(position).toString());
        holder.fechav.setText(myfechav.get(position).toString());
        holder.monto.setText(mymonto.get(position).toString());

        return row;
    }

    static class Lista_Orden_Pago_Holder{
        TextView cuenta;
        TextView concepto;
        TextView descripcion;
        TextView fecha;
        TextView fechav;
        TextView monto;
    }
}
