package mx.udg.cusur.mi_cusur;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;

/**
 * Created by Macbook on 06/09/16.
 */
public class Adaptador_Lista_Inicio_Noticias extends ArrayAdapter<String>{

    Context mycontext;
    int mylayoutResourceId;
    ArrayList myurl_imagen = new ArrayList();
    ArrayList mytitulo = new ArrayList();
    ArrayList mysinopsis = new ArrayList();
    ArrayList myfecha = new ArrayList();
    ArrayList mydescripcion = new ArrayList();

    public Adaptador_Lista_Inicio_Noticias(Context context, int layoutResourceId, ArrayList titulo, ArrayList descripcion, ArrayList url_imagen, ArrayList sinopsis, ArrayList fecha){
        super(context,layoutResourceId,titulo);

        this.mycontext = context;
        this.mylayoutResourceId = layoutResourceId;
        this.myurl_imagen = url_imagen;
        this.mytitulo = titulo;
        this.mydescripcion = descripcion;
        this.mysinopsis = sinopsis;
        this.myfecha = fecha;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        Lista_Noticias_Holder holder = null;

        if(row == null){

            LayoutInflater inflater = ((Activity)mycontext).getLayoutInflater();
            row = inflater.inflate(mylayoutResourceId,parent,false);

            holder = new Lista_Noticias_Holder();
            holder.titulo = (TextView) row.findViewById(R.id.item_inicio_noticias_titulo);
            holder.sinopsis = (TextView) row.findViewById(R.id.item_inicio_noticias_sinopsis);
            holder.fecha = (TextView) row.findViewById(R.id.item_inicio_noticias_fecha_post);
            holder.descripcion = (TextView) row.findViewById(R.id.item_inicio_noticias_descripcion);
            holder.imagen = (SmartImageView) row.findViewById(R.id.item_inicio_noticias_imagen);
            row.setTag(holder);

        }else{

            holder = (Lista_Noticias_Holder) row.getTag();
        }

        holder.titulo.setText(mytitulo.get(position).toString());
        holder.fecha.setText(myfecha.get(position).toString());
        holder.sinopsis.setText(mysinopsis.get(position).toString());
        holder.descripcion.setText(mydescripcion.get(position).toString());
        Rect rect = new Rect(holder.imagen.getLeft(), holder.imagen.getTop(), holder.imagen.getRight(), holder.imagen.getBottom());
        holder.imagen.setImageUrl("http://micusur.esy.es/Imagenes/"+myurl_imagen.get(position).toString(), rect);

        return row;
    }

    static class Lista_Noticias_Holder {
        TextView titulo;
        TextView sinopsis;
        TextView fecha;
        TextView descripcion;
        SmartImageView imagen;
    }
}
