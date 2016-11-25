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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.IOException;


public class Fragment_Inicio_InfoAlumno extends Fragment {

    TextView codigo, nombre, situacion, nivel, admision, ultimociclo, carrera, centro, sede, creditos, creditosmn, promedio;
    String codigo_alumno;

    public Fragment_Inicio_InfoAlumno() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio_info_alumno, container, false);
        codigo = (TextView) view.findViewById(R.id.info_alumno_codigo);
        nombre = (TextView) view.findViewById(R.id.info_alumno_nombre);
        situacion = (TextView) view.findViewById(R.id.info_alumno_situacion);
        nivel = (TextView) view.findViewById(R.id.info_alumno_nivel);
        admision = (TextView) view.findViewById(R.id.info_alumno_admision);
        ultimociclo = (TextView) view.findViewById(R.id.info_alumno_ultimo_ciclo);
        carrera = (TextView) view.findViewById(R.id.info_alumno_carrera);
        centro = (TextView) view.findViewById(R.id.info_alumno_centro);
        sede = (TextView) view.findViewById(R.id.info_alumno_sede);
        creditos = (TextView) view.findViewById(R.id.info_alumno_creditos);
        creditosmn = (TextView) view.findViewById(R.id.info_alumno_creditos_minimos);
        promedio = (TextView) view.findViewById(R.id.info_alumno_promedio);
        //Codigo para saber el codigo del alumno..
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        codigo_alumno = sharedPreferences.getString("codigo","");
        //Consultar informacion del alumno desde servidor...
        new ConsultarInfoAlumno().execute("Consulta_Info_Alumno.php?codigo=" + codigo_alumno);
        return view;
    }

    //Ejecucion de operacion Consultar Informacion Alumno en un hilo separado de la interfaz del usuario.....
    private class ConsultarInfoAlumno extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Obteniendo Datos del Alumno");
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
            JSONArray arrayjson = null;
            try {

                arrayjson = new JSONArray(result);
                codigo.setText(arrayjson.getString(0));
                nombre.setText(arrayjson.getString(1));
                situacion.setText(arrayjson.getString(2));
                nivel.setText(arrayjson.getString(3));
                admision.setText(arrayjson.getString(4));
                ultimociclo.setText(arrayjson.getString(5));
                carrera.setText(arrayjson.getString(6));
                centro.setText(arrayjson.getString(7));
                sede.setText(arrayjson.getString(8));
                creditos.setText(arrayjson.getString(9));
                creditosmn.setText(arrayjson.getString(10));
                promedio.setText(arrayjson.getString(11));

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(),"Vuelve a intentarlo...",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }

}
