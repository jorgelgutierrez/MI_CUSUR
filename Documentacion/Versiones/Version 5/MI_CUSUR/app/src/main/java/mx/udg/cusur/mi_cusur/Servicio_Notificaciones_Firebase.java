package mx.udg.cusur.mi_cusur;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by GUAPO on 30/08/2016.
 */
public class Servicio_Notificaciones_Firebase extends FirebaseMessagingService {

    String grupo_notificaciones = "Notificaciones";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String titulo = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();
        MostrarNotificacion(titulo,body);

    }

    private void MostrarNotificacion(String titulo, String Body){

        int icon = R.drawable.ic_notificaciones;
        String[] info = Body.split("#");
        String mensaje = info[0];
        String informacion = info[1];
        long[] vibracion = {500,500,500,500,500,500};
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Creando manager de la notificacion para notificarla...
        Notification notificacion;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this,Inicio_Sesion.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_app)
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setContentInfo(informacion)
                .setContentIntent(pendingIntent)
                .setColor(getResources().getColor(R.color.colorFondoBaar))
                .setVibrate(vibracion)
                .setSound(alarmSound)
                .setAutoCancel(true)
                .setLights(Color.WHITE, 1000, 500)
                .build();

        //Codigo para saber el numero para la notificacion ...
        VariablesGlobales g = VariablesGlobales.getInstance();
        int numero = g.getNumero_Notificaciones();
        // Construir la notificación y emitirla
        notificationManager.notify(numero, notificacion);
        //Incrementar en uno el numero de notificaciones que van para la siguiente notificacion..
        numero++;
        //Actualizar el numero...
        g.setNumero_notificaciones(numero);
    }

}