package mx.udg.cusur.mi_cusur;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


public class Fragment_Inicio_Boleta_Calificaciones extends Fragment {

    String codigo_alumno;
    TableLayout tabla;
    Float sizetexto = 15.0f;

    public Fragment_Inicio_Boleta_Calificaciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio_boleta_calificaciones, container, false);
        tabla = (TableLayout) view.findViewById(R.id.boleta_tabla);

        //Codigo para saber el codigo del alumno..
        VariablesGlobales g = VariablesGlobales.getInstance();
        codigo_alumno = g.getCodigo();
        //Consultar informacion del alumno desde servidor...
        new ConsultarInfoAlumno().execute("Consulta_Boleta_Calificaciones.php?codigo=" + codigo_alumno);
        return view;
    }

    //Ejecucion de operacion Consultar Informacion Alumno en un hilo separado de la interfaz del usuario.....
    private class ConsultarInfoAlumno extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Obteniendo Datos del Alumno " + codigo_alumno);
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
            try {

                resultadoJSON = new JSONArray(result);

                for(int i = 0; i < resultadoJSON.length(); i++) {
                    JSONObject linea = resultadoJSON.getJSONObject(i);
                    TableRow fila = new TableRow(getActivity());
                    TextView col1 = new TextView(getActivity());
                    col1.setText(linea.getString("Materia"));
                    col1.setTextSize(sizetexto);
                    TextView col2 = new TextView(getActivity());
                    col2.setText(linea.getString("Ordinario"));
                    col2.setTextSize(sizetexto);
                    TextView col3 = new TextView(getActivity());
                    col3.setText(linea.getString("Kardex_O"));
                    col3.setTextSize(sizetexto);
                    TextView col4 = new TextView(getActivity());
                    col4.setText(linea.getString("Extraordinario"));
                    col4.setTextSize(sizetexto);
                    TextView col5 = new TextView(getActivity());
                    col5.setText(linea.getString("Kardex_E"));
                    col5.setTextSize(sizetexto);

                    fila.addView(col1);
                    fila.addView(col2);
                    fila.addView(col3);
                    fila.addView(col4);
                    fila.addView(col5);
                    tabla.addView(fila);

                }

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
