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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;


public class Fragment_Ajustes_Notificaciones extends Fragment {

    Button recibir_notificaciones;
    CheckBox deportes, tecnologia, politica, literatura;
    String stdeportes, sttecnolgia, stpolitica, stliteratura;

    public Fragment_Ajustes_Notificaciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ajustes_notificaciones, container, false);
        recibir_notificaciones = (Button) view.findViewById(R.id.ajustes_btrecibir_notificaciones);
        deportes = (CheckBox) view.findViewById(R.id.ajustes_notificaciones_cbdeportes);
        tecnologia = (CheckBox) view.findViewById(R.id.ajustes_notificaciones_cbtecnologia);
        politica = (CheckBox) view.findViewById(R.id.ajustes_notificaciones_cbpolitica);
        literatura = (CheckBox) view.findViewById(R.id.ajustes_notificaciones_cbliteratura);


        recibir_notificaciones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Registrar en la base de datos el token del usuario...
                new RegistrarToken().execute();
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

            SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
            String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
            String url_servidor = getString(R.string.url_servidor)+ "Registro_Notificaciones.php";
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(url_servidor);
            EstatusCheckBox();
            List<NameValuePair> postParameters = new ArrayList<NameValuePair>(1);
            postParameters.add(new BasicNameValuePair("token",token));
            postParameters.add(new BasicNameValuePair("deportes",stdeportes));
            postParameters.add(new BasicNameValuePair("tecnologia",sttecnolgia));
            postParameters.add(new BasicNameValuePair("literatura",stliteratura));
            postParameters.add(new BasicNameValuePair("politica",stpolitica));
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
            return null;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            pDialog.dismiss();
            super.onPostExecute(result);
        }
    }


    public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }

    public void EstatusCheckBox (){

        if (deportes.isChecked()){

            stdeportes = "x";

        }else{

            stdeportes = "";

        }

        if (tecnologia.isChecked()){

            sttecnolgia = "x";

        }else{

            sttecnolgia = "";

        }

        if (politica.isChecked()){

            stpolitica = "x";

        }else{

            stpolitica = "";

        }

        if (literatura.isChecked()){

            stliteratura = "x";

        }else{

            stliteratura = "";

        }
    }

}
