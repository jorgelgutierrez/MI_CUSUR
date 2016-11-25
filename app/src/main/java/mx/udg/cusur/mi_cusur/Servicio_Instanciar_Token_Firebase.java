package mx.udg.cusur.mi_cusur;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

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

/**
 * Created by GUAPO on 29/08/2016.
 */
public class Servicio_Instanciar_Token_Firebase extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        //Conseguir actualizacion del token...
        String token = FirebaseInstanceId.getInstance().getToken();
        RegistrarToken(token);

    }

    //Metodo para registrar el token en las preferencias y poder hacer el registro con sus preferencias de los temas de notificaciones...
    private void RegistrarToken(String token){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("mx.udg.cusur.mi_cusur.fcm_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mx.udg.cusur.mi_cusur.fcm_token",token);
        editor.commit();

        List<NameValuePair> postParameters = new ArrayList<NameValuePair>(1);
        postParameters.add(new BasicNameValuePair("token",token));
        postParameters.add(new BasicNameValuePair("temas","nada"));
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
 