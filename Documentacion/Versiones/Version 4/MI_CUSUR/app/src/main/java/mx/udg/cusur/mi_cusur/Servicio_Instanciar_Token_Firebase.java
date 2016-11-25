package mx.udg.cusur.mi_cusur;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by GUAPO on 29/08/2016.
 */
public class Servicio_Instanciar_Token_Firebase extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        //Conseguir actualizacion del token...
        String token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("mx.udg.cusur.mi_cusur.fcm_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mx.udg.cusur.mi_cusur.fcm_token",token);
        editor.commit();

    }




}
 