package mx.udg.cusur.mi_cusur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;


public class Fragment_Inicio extends Fragment {

    ListView Lista_Inicio_Noticias;

    public Fragment_Inicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        Lista_Inicio_Noticias = (ListView) view.findViewById(R.id.lista_inicio_noticias);

        //Consultar las noticias...
        new ConsultarNoticias().execute("Consulta_Noticias.php");

        return view;
    }

    public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
    }


    //Ejecucion de operacion Consultar Noticias en un hilo separado de la interfaz del usuario....
    private class ConsultarNoticias extends AsyncTask<String, Void, String>{

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Cargando Noticias");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                Metodos obtenerjson = new Metodos();
                return obtenerjson.getJSONfromUrl((urls[0]));
            } catch (Exception e) {
                return "Problemas con la conexion a internet";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            pDialog.dismiss();
            JSONArray resultadoJSON = null;
            ArrayList myurl_imagen = new ArrayList();
            ArrayList mytitulo = new ArrayList();
            ArrayList mydescripcion = new ArrayList();
            ArrayList mysinopsis = new ArrayList();
            ArrayList myfecha = new ArrayList();
            try {

                resultadoJSON = new JSONArray(result);

                int contador = 1;
                for(int i = 0;i <= resultadoJSON.length()-1 ; i++) {
                    if(contador <= 3) {
                        JSONObject linea = resultadoJSON.getJSONObject(i);
                        mytitulo.add(linea.getString("Titulo"));
                        mydescripcion.add(linea.getString("Descripcion"));
                        myurl_imagen.add(linea.getString("Nombre_Imagen"));
                        mysinopsis.add(linea.getString("Sinopsis"));
                        myfecha.add(linea.getString("Fecha_Post"));
                    }else if(contador > 3){
                        i = 0;
                    }
                    contador = contador + 1;
                }

                //Inicializando el adaptador...
                Adaptador_Lista_Inicio_Noticias adapter_noticias = new Adaptador_Lista_Inicio_Noticias(getActivity(),R.layout.items_lista_inicio_noticias,mytitulo,mydescripcion,myurl_imagen,mysinopsis,myfecha);

                //Agregando los titulos a la lista...
                Lista_Inicio_Noticias.setAdapter(adapter_noticias);


            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(),"Vuelve a intentarlo...",Toast.LENGTH_SHORT).show();
            }
        }
    }



}
