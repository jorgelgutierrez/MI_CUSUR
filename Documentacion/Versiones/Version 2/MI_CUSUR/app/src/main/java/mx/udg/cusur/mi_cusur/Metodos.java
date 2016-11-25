package mx.udg.cusur.mi_cusur;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Metodos {


    // constructor
    public Metodos() {

    }

    //Ejecuta la URL estableciendo la conexion y recibe el json en InputStream y posteriormente ser convertido a String...
    public String getJSONfromUrl(String myurl) throws IOException {
        StringBuilder result = new StringBuilder();
        String urlservidor = "http://micusur.esy.es/";
        myurl = urlservidor + myurl;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int respuesta = conn.getResponseCode();


            if (respuesta == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = new BufferedInputStream(conn.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String linea;
                while((linea = reader.readLine()) != null){
                    result.append(linea);
                }



            }


        }catch (Exception e) {
            return "Sin respuesta del servidor";
        }

        return result.toString();

    }

    //Metodo para verificar si hay conexion a internet...
    public boolean isOnline(Context contexto) {
        Context context = contexto;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

}
