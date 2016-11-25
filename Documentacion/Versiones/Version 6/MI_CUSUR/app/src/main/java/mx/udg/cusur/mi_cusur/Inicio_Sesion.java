package mx.udg.cusur.mi_cusur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;

public class Inicio_Sesion extends AppCompatActivity {

    ImageButton iniciar_sesion;
    Button ayuda;
    EditText codigo, nip;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Enlazando los objetos del activity a variables para la clase...
        iniciar_sesion = (ImageButton) findViewById(R.id.inicio_BTIniciar_sesion);
        ayuda = (Button) findViewById(R.id.inicio_BTAyuda);
        codigo = (EditText) findViewById(R.id.inicio_TXTCodigo);
        nip = (EditText) findViewById(R.id.inicio_TXTNip);

        //Metodo para verificar si tiene conexion a internet....
        Metodos metodos = new Metodos();
        Boolean conexion = metodos.isOnline(getApplicationContext());
        if (!conexion) {
            Toast.makeText(getApplicationContext(),"Para ingresar a MICUSur requieres de conexion a internet",Toast.LENGTH_SHORT).show();
        }
        //Boton iniciar sesion...
        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConsultarUsuario().execute("Consulta_Inicio_Sesion.php?codigo="+codigo.getText().toString()+ "&nip="+nip.getText().toString());
            }
        });

        //Ir a ayuda
        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iramenu = new Intent(Inicio_Sesion.this, Inicio_Ayuda.class);
                startActivity(iramenu);
            }
        });


    }

    //Ejecucion de operacion Consultar Usuario para el inicio de sesion en un hilo separado de la interfaz del usuario.....
    private class ConsultarUsuario extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Inicio_Sesion.this);
            pDialog.setMessage("   Cargando");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                Metodos obtenerjson = new Metodos();
                return obtenerjson.getJSONfromUrl(urls[0]);
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
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("codigo",arrayjson.getString(0));
                editor.commit();
                Intent iramenu = new Intent(Inicio_Sesion.this, Menu_Inicio.class);
                startActivity(iramenu);

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Los datos proporcionados no son validos",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
