package mx.udg.cusur.mi_cusur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.snowdream.android.widget.SmartImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Menu_Inicio extends AppCompatActivity{


    String codigo_alumno;
    TextView nombre, carrera;
    SmartImageView imagen;
    TextView item;
    ListView Lista_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Inicializando el drawer layout...
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Inicializando el toggle (boton del drawer)...
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        drawer.openDrawer(GravityCompat.START);

        //Codigo para saber el codigo del alumno..
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        codigo_alumno = sharedPreferences.getString("codigo","");

        //Inicializando los objetos del header...
        nombre = (TextView) findViewById(R.id.nav_nombre_alumno);
        carrera = (TextView) findViewById(R.id.nav_carrera_alumno);
        imagen = (SmartImageView) findViewById(R.id.nav_foto_perfil);

        //Agregando los titulos y iconos al array...
        String titulos_menu_navegacion_datos[] = getResources().getStringArray(R.array.Titulos_Navegacion_Principal);

        //Inicializando el adaptador menu inicio...
        Adaptador_Lista_Menu_Inicio adapter = new Adaptador_Lista_Menu_Inicio(this,R.layout.items_lista_menu_inicio,titulos_menu_navegacion_datos);

        //Inicializando la lista del menu inicio y noticias...
        Lista_Menu = (ListView) findViewById(R.id.lista_menu_inicio);

        //Agregando los titulos a la lista menu inicio...
        Lista_Menu.setAdapter(adapter);

        //Codigo para recibir nombre, carrera y foto del alumno..
        new ConsultarDatosDelHeader().execute("Consulta_Info_Alumno.php?codigo=" + codigo_alumno);

        //Cargando el contenido de inicio noticias...
        Fragment fragment = new Fragment_Inicio();
        getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor_Menu_Inicio,fragment).commit();

        //Evento de click en elemento de la lista...
        Lista_Menu.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position, long id) {
                //Obteniendo item seleccionado y enviado a metodo para saber que mostrar en pantalla...
                item = (TextView) view.findViewById(R.id.item_titulo_menu_inicio);
                MostrarContenidoItem(item.getText().toString());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.botones_app_bar_menu_inicio, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        //Cargando el contenido de inicio noticias...
        VariablesGlobales varibales = VariablesGlobales.getInstance();
        String ubicacion = varibales.getUbicacion_fragment();
        if(!ubicacion.equals("Inicio")){
            Fragment fragment = new Fragment_Inicio();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor_Menu_Inicio,fragment).commit();
            varibales.setUbicacion_fragment("Inicio");
        }else{
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
            dialogo1.setTitle("MICUSur");
            dialogo1.setMessage("¿ Deseas Cerrar Sesión ?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    finish();
                }
            });
            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {

                }
            });
            dialogo1.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.app_bar_ajustes:
                MostrarContenidoItem("Notificaciones");
                return true;
            case R.id.app_bar_home:
                Fragment fragment = new Fragment_Inicio();
                getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor_Menu_Inicio,fragment).commit();
                VariablesGlobales varibales = VariablesGlobales.getInstance();
                varibales.setUbicacion_fragment("Inicio");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Ejecucion de operacion Consultar Usuario para el header en un hilo separado de la interfaz del usuario.....
    private class ConsultarDatosDelHeader extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Menu_Inicio.this);
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
                nombre.setText(arrayjson.getString(1));
                carrera.setText(arrayjson.getString(6));
                if(!arrayjson.getString(12).equals("")) {
                    Rect rect = new Rect(imagen.getLeft(), imagen.getTop(), imagen.getRight(), imagen.getBottom());
                    imagen.setImageUrl("http://micusur.esy.es/Imagenes/"+arrayjson.getString(14), rect);
                }else{
                    //Toast.makeText(getApplicationContext(),"Alumno sin foto de perfil agregar una en ajustes",Toast.LENGTH_SHORT).show();
                    int icon = R.mipmap.ic_menu_inicio_foto_perfil_default;
                    imagen.setImageResource(icon);
                }

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Vuelve a intentarlo...",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void MostrarContenidoItem(String item_seleccionado){

        VariablesGlobales varibales = VariablesGlobales.getInstance();
        Fragment fragment = null;
        Boolean fragmentoseleccionado = false;

        if(item_seleccionado.equals("Informacion del Alumno")){

            fragment = new Fragment_Inicio_InfoAlumno();
            fragmentoseleccionado = true;
            varibales.setUbicacion_fragment("Info_Alumno");
        }
        if(item_seleccionado.equals("Mi Horario") || item_seleccionado.equals("Orden de Pago") || item_seleccionado.equals("Kardex") || item_seleccionado.equals("Boleta de Calificaciones")){

            fragment = new Fragment_Inicio_HOKB();
            fragmentoseleccionado = true;
        }
        if(item_seleccionado.equals("Notificaciones")){
            fragment = new Fragment_Ajustes();
            fragmentoseleccionado = true;
            varibales.setUbicacion_fragment("Notificaciones");
        }
        if(item_seleccionado.equals("Cerrar Sesion")){
           finish();
        }
        if(item_seleccionado.equals("Como llegar")){
            Intent iramenu = new Intent(Menu_Inicio.this, MapsActivity.class);
            startActivity(iramenu);
        }

        if(fragmentoseleccionado){
            //Enviardatos a fragment...
            Bundle args = new Bundle();
            args.putString("Fragment", item_seleccionado);
            fragment.setArguments(args);;
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor_Menu_Inicio,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }



}
