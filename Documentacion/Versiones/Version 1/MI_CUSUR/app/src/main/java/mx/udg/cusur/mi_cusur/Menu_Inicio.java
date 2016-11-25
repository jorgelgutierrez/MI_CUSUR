package mx.udg.cusur.mi_cusur;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.snowdream.android.widget.SmartImageView;

import org.json.JSONArray;

import java.io.IOException;

public class Menu_Inicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Fragment_Inicio_InfoAlumno.OnFragmentInteractionListener, Fragment_Inicio_Mi_Horario.OnFragmentInteractionListener, Fragment_Inicio_Orden_Pago.OnFragmentInteractionListener, Fragment_Inicio_Boleta_Calificaciones.OnFragmentInteractionListener, Fragment_Inicio_Kardex.OnFragmentInteractionListener{


    String codigo_alumno;
    TextView nombre, carrera;
    ImageView imagenfondo;
    SmartImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        imagenfondo = (ImageView) findViewById(R.id.nav_imagen_fondo_menu_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Codigo para saber el codigo del alumno..
        VariablesGlobales g = VariablesGlobales.getInstance();
        codigo_alumno = g.getCodigo();

        //Este codigo cambia los datos del header de la navegacion..
        View header = navigationView.inflateHeaderView(R.layout.nav_header_menu);
        nombre = (TextView) header.findViewById(R.id.nav_nombre_alumno);
        carrera = (TextView) header.findViewById(R.id.nav_carrera_alumno);
        imagen = (SmartImageView) header.findViewById(R.id.nav_foto_perfil);

        //Codigo para recibir nombre, carrera y foto del alumno..
        new ConsultarDatosAlumno().execute("Consulta_Info_Alumno.php?codigo=" + codigo_alumno);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Boolean fragmentoseleccionado = false;

        if (id == R.id.nav_acerca_cusur) {
            Intent iramenu = new Intent(Menu_Inicio.this, Menu_AcercaCUSur.class);
            startActivity(iramenu);

        } else if (id == R.id.nav_informacion_alumno) {

            fragment = new Fragment_Inicio_InfoAlumno();
            fragmentoseleccionado = true;
            setTitle("Informacion del Alumno");

        } else if (id == R.id.nav_mi_horario) {
            fragment = new Fragment_Inicio_Mi_Horario();
            fragmentoseleccionado = true;
            setTitle("Mi horario de Clases");

        } else if (id == R.id.nav_orden_pago) {
            fragment = new Fragment_Inicio_Orden_Pago();
            fragmentoseleccionado = true;
            setTitle("Orden de Pago");

        } else if (id == R.id.nav_boleta_calificaciones) {
            fragment = new Fragment_Inicio_Boleta_Calificaciones();
            fragmentoseleccionado = true;
            setTitle("Boleta de Calificaciones");

        } else if (id == R.id.nav_kardex) {
            fragment = new Fragment_Inicio_Kardex();
            fragmentoseleccionado = true;
            setTitle("Kardex");

        } else if (id == R.id.nav_ajustes) {
            Intent iramenu = new Intent(Menu_Inicio.this, Menu_Ajustes.class);
            startActivity(iramenu);

        } else if (id == R.id.nav_calendario_escolar) {
            setTitle("Calendario Escolar");

        } else if (id == R.id.nav_cerrar_sesion) {
            finish();

        } else if (id == R.id.nav_servicios_escolares) {
            setTitle("Servicios Escolares");

        }


        if(fragmentoseleccionado){
            imagenfondo.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor_Menu_Inicio,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //Ejecucion de operacion Consultar Usuario en un hilo separado de la interfaz del usuario.....
    private class ConsultarDatosAlumno extends AsyncTask<String, Void, String> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Menu_Inicio.this);
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
            JSONArray arrayjson = null;
            try {

                arrayjson = new JSONArray(result);
                nombre.setText(arrayjson.getString(1));
                carrera.setText(arrayjson.getString(6));
                if(!arrayjson.getString(14).equals("")) {
                    Rect rect = new Rect(imagen.getLeft(), imagen.getTop(), imagen.getRight(), imagen.getBottom());
                    imagen.setImageUrl(arrayjson.getString(14), rect);
                }else{
                    Toast.makeText(getApplicationContext(),"Alumno sin foto de perfil agregar una en ajustes",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Vuelve a intentarlo...",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
