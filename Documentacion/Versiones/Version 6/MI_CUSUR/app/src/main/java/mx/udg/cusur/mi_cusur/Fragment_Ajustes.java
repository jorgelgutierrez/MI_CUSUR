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

import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;


public class Fragment_Ajustes extends Fragment {

    Button recibir_notificaciones;
    ListView lista_temas;

    public Fragment_Ajustes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);
        recibir_notificaciones = (Button) view.findViewById(R.id.ajustes_btrecibir_notificaciones);
        lista_temas = (ListView) view.findViewById(R.id.lista_ajustes_temas_notificaciones);

        new ConsultarTemas().execute("Consulta_Tema_Notificaciones.php");

        recibir_notificaciones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Registrar en la base de datos el token del usuario...
                new RegistrarToken().execute();
                FirebaseMessaging.getInstance().subscribeToTopic("nuevo");

            }
        });

        return view;
    }

    //Ejecucion de operacion Consultar Informacion Alumno en un hilo separado de la interfaz del usuario.....
    private class RegistrarToken extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Activando ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... urls) {
            RecorrerListView();
            return null;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            pDialog.dismiss();
            super.onPostExecute(result);
        }
    }

    //Ejecucion de operacion Consultar Informacion Alumno en un hilo separado de la interfaz del usuario.....
    private class ConsultarTemas extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
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
            JSONArray resultadoJSON = null;
            ArrayList mytemas = new ArrayList();
            try {

                resultadoJSON = new JSONArray(result);

                 for(int i = resultadoJSON.length()-1; i >=1 ; i--) {
                    JSONObject linea = resultadoJSON.getJSONObject(i);

                    mytemas.add(linea.getString("Field"));
                }

                //Inicializando el adaptador...
                Adaptador_Lista_Ajustes_Notificaciones adapter = new Adaptador_Lista_Ajustes_Notificaciones(getActivity(),R.layout.items_lista_ajustes_notificaciones,mytemas);

                //Agregando los titulos a la lista...
                lista_temas.setAdapter(adapter);

            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        }
    }

    public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
    }

    public void RecorrerListView (){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("mx.udg.cusur.mi_cusur.fcm_pref", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("mx.udg.cusur.mi_cusur.fcm_token","No se guardo el token del dispositivo");
        int count = lista_temas.getAdapter().getCount();
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>(1);
        postParameters.add(new BasicNameValuePair("token",token));
        String temas = "";
        String checked = "";
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i < count; i++)
        {
            ViewGroup row = (ViewGroup) lista_temas.getChildAt(i);
            CheckBox tvTest = (CheckBox) row.findViewById(R.id.item_ajustes_checkbox_tema);
            //  Get your controls from this ViewGroup and perform your task on them =)

            if (tvTest.isChecked())
            {
                temas = temas + "checked" + tvTest.getText().toString();
                checked = checked +tvTest.getText().toString();
            }else{
                temas = temas + tvTest.getText().toString();
                checked = checked + "";
            }
            if(i+1 < count){
                temas = temas + ",";
                checked = checked + ",";
            }

        }
        editor.putString("checkbox",checked);
        editor.commit();
        postParameters.add(new BasicNameValuePair("temas",temas));
        String url_servidor = getString(R.string.url_servidor)+ "Registro_Notificaciones.php";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(url_servidor);
        try {
            request.setEntity(new UrlEncodedFormEntity(postParameters));
            httpClient.execute(request);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
