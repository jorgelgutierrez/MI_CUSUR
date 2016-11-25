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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Fragment_Inicio_HOKB extends Fragment {

    String codigo_alumno;
    ListView lista;
    String fragment;
    TextView titulo_lista;
    TextView total_orden_pago;

    public Fragment_Inicio_HOKB() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio_hokb, container, false);

        //Codigo para saber el codigo del alumno..
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        codigo_alumno = sharedPreferences.getString("codigo","");

        //Obteniendo el nombre del fragment a mostrar... 
        fragment = getArguments().getString("Fragment");

        //Inicializando la lista del menu y titulo...
        lista = (ListView) view.findViewById(R.id.lista_inicio_consulta_hokb);
        titulo_lista = (TextView) view.findViewById(R.id.titulo_inicio_consulta_hokb);
        total_orden_pago = (TextView) view.findViewById(R.id.total_inicio_orden_pago);

        //Enviando nombre del fragment para cargar datos y mostrar en lista...
        LlamarConsulta(fragment);

        return view;
    }


    //Metodo para ejecutar consulta correspondiente al parametro...
    public void LlamarConsulta(String fragment){
        VariablesGlobales varibales = VariablesGlobales.getInstance();

        if(fragment.equals("Mi Horario")){

            titulo_lista.setText(fragment);
            new ConsultarMihorario().execute("Consulta_Horario.php?codigo=" + codigo_alumno);
            varibales.setUbicacion_fragment("Horario");
        }
        if(fragment.equals("Orden de Pago")){

            titulo_lista.setText(fragment);
            new ConsultarOrdenPago().execute("Consulta_Orden_Pago.php?codigo=" + codigo_alumno);
            varibales.setUbicacion_fragment("Orden_Pago");
        }
        if(fragment.equals("Kardex")){

            titulo_lista.setText(fragment);
            new ConsultarKardex().execute("Consulta_Kardex.php?codigo=" + codigo_alumno);
            varibales.setUbicacion_fragment("Kardex");
        }
        if(fragment.equals("Boleta de Calificaciones")){

            titulo_lista.setText(fragment);
            new ConsultarBoletaCalificaciones().execute("Consulta_Boleta_Calificaciones.php?codigo=" + codigo_alumno);
            varibales.setUbicacion_fragment("Boleta_Calif");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //Ejecucion de operacion Consultar Mi Horario en un hilo separado de la interfaz del usuario.....
    private class ConsultarMihorario extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Cargando Horario");
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
            ArrayList mymaterias = new ArrayList();
            ArrayList myhorario = new ArrayList();
            ArrayList mydias = new ArrayList();
            ArrayList myedificio = new ArrayList();
            ArrayList myaula = new ArrayList();
            ArrayList myprofesor = new ArrayList();
            ArrayList myfechai = new ArrayList();
            ArrayList myfechaf = new ArrayList();
            try {

                resultadoJSON = new JSONArray(result);

                for(int i = 0; i < resultadoJSON.length(); i++) {
                    JSONObject linea = resultadoJSON.getJSONObject(i);

                    mymaterias.add(linea.getString("Materia"));
                    myhorario.add(linea.getString("Horario"));
                    mydias.add(linea.getString("Lunes")+
                            linea.getString("Martes")+
                            linea.getString("Miercoles") +
                            linea.getString("Jueves") +
                            linea.getString("Viernes")+
                            linea.getString("Sabado"));
                    myedificio.add(linea.getString("Edificio"));
                    myaula.add(linea.getString("Aula"));
                    myprofesor.add(linea.getString("Profesor"));
                    myfechai.add(linea.getString("Fecha_Inicio"));
                    myfechaf.add(linea.getString("Fecha_Fin"));
                }

                //Inicializando el adaptador...
                Adaptador_Lista_Inicio_Mi_Horario adapter = new Adaptador_Lista_Inicio_Mi_Horario(getActivity(),R.layout.items_lista_inicio_mi_horario,mymaterias,myhorario,mydias,myedificio,myaula,myprofesor,myfechai,myfechaf);

                //Agregando los titulos a la lista...
                lista.setAdapter(adapter);

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(),"Vuelve a intentarlo...",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Ejecucion de operacion Consultar Orden de Pago en un hilo separado de la interfaz del usuario....
    private class ConsultarOrdenPago extends AsyncTask<String, Void, String>{

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Cargando Orden de Pago");
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
            ArrayList mycuenta = new ArrayList();
            ArrayList myconcepto = new ArrayList();
            ArrayList mydescripcion = new ArrayList();
            ArrayList myfecha = new ArrayList();
            ArrayList myfechav = new ArrayList();
            ArrayList mymonto = new ArrayList();
            int total = 0;
            try {

                resultadoJSON = new JSONArray(result);

                for(int i = 0; i < resultadoJSON.length(); i++) {
                    JSONObject linea = resultadoJSON.getJSONObject(i);

                    mycuenta.add(linea.getString("Cuenta"));
                    myconcepto.add(linea.getString("Concepto"));
                    mydescripcion.add(linea.getString("Descripcion"));
                    myfecha.add(linea.getString("Fecha"));
                    myfechav.add(linea.getString("Fecha_Vencimiento"));
                    mymonto.add(linea.getString("Monto"));
                    total = total + Integer.parseInt(linea.getString("Monto"));
                }

                //Inicializando el adaptador...
                Adaptador_Lista_Inicio_Orden_Pago adapter = new Adaptador_Lista_Inicio_Orden_Pago(getActivity(),R.layout.items_lista_inicio_orden_pago,mycuenta,myconcepto,mydescripcion,myfecha,myfechav,mymonto);

                //Agregando los titulos a la lista...
                lista.setAdapter(adapter);

                //Agregando el total de la orden de pago...
                total_orden_pago.setVisibility(View.VISIBLE);
                total_orden_pago.setText("Total:   $" + String.valueOf(total));


            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(),"Vuelve a intentarlo...",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Ejecucion de operacion Consultar Kardex en un hilo separado de la interfaz del usuario....
    private class ConsultarKardex extends AsyncTask<String, Void, String>{

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Cargando Kardex");
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
            ArrayList mynrc = new ArrayList();
            ArrayList myclave = new ArrayList();
            ArrayList mymateria = new ArrayList();
            ArrayList mycalificacion = new ArrayList();
            ArrayList mytipo = new ArrayList();
            ArrayList mycreditos = new ArrayList();
            ArrayList myfecha = new ArrayList();
            ArrayList mycalendario = new ArrayList();
            try {

                resultadoJSON = new JSONArray(result);

                for(int i = 0; i < resultadoJSON.length(); i++) {
                    JSONObject linea = resultadoJSON.getJSONObject(i);

                    mynrc.add(linea.getString("NRC"));
                    myclave.add(linea.getString("Clave"));
                    mymateria.add(linea.getString("Materia"));
                    mycalificacion.add(linea.getString("Calificacion"));
                    mytipo.add(linea.getString("Tipo"));
                    mycreditos.add(linea.getString("Creditos"));
                    myfecha.add(linea.getString("Fecha"));
                    mycalendario.add(linea.getString("Calendario"));
                }

                //Inicializando el adaptador...
                Adaptador_Lista_Inicio_Kardex adapter = new Adaptador_Lista_Inicio_Kardex(getActivity(),R.layout.items_lista_inicio_kardex,mynrc,myclave,mymateria,mycalificacion,mytipo,mycreditos,myfecha,mycalendario);

                //Agregando los titulos a la lista...
                lista.setAdapter(adapter);

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(),"Vuelve a intentarlo...",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Ejecucion de operacion Consultar Boleta Calificaciones en un hilo separado de la interfaz del usuario....
    private class ConsultarBoletaCalificaciones extends AsyncTask<String, Void, String>{

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Cargando Boleta de Calificaciones");
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
            ArrayList mymateria = new ArrayList();
            ArrayList myordinario = new ArrayList();
            ArrayList mykardexo = new ArrayList();
            ArrayList myextraordinario = new ArrayList();
            ArrayList mykardexe = new ArrayList();
            try {

                resultadoJSON = new JSONArray(result);

                for(int i = 0; i < resultadoJSON.length(); i++) {
                    JSONObject linea = resultadoJSON.getJSONObject(i);

                    mymateria.add(linea.getString("Materia"));
                    myordinario.add(linea.getString("Ordinario"));
                    mykardexo.add(linea.getString("Kardex_O"));
                    myextraordinario.add(linea.getString("Extraordinario"));
                    mykardexe.add(linea.getString("Kardex_E"));
                }

                //Inicializando el adaptador...
                Adaptador_Lista_Inicio_Boleta_Calificaciones adapter = new Adaptador_Lista_Inicio_Boleta_Calificaciones(getActivity(),R.layout.items_lista_inicio_boleta_calificaciones,mymateria,myordinario,mykardexo,myextraordinario,mykardexe);

                //Agregando los titulos a la lista...
                lista.setAdapter(adapter);


            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(),"Vuelve a intentarlo...",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
