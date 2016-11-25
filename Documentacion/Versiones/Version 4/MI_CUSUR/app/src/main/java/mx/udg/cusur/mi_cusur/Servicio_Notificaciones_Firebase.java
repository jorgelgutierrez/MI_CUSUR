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
public class Servicio_Notificaciones_Firebase extends FirebaseMessagingService{

    String grupo_notificaciones = "Notificaciones";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String titulo = remoteMessage.getNotification().getTitle();
        String msn = remoteMessage.getNotification().getBody();
        int icon = R.drawable.ic_notificaciones;
        String[] info = msn.split("#");
        String mensaje = info[0];
        String informacion = info[1];
        long[] vibracion = {500,500,500,500,500,500};
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Creando manager de la notificacion para notificarla...
        Notification notificacion;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificacion = new NotificationCompat.Builder(this)
                        .setSmallIcon(icon)
                        .setContentTitle(titulo)
                        .setContentText(mensaje)
                        .setContentInfo(informacion)
                        .setColor(getResources().getColor(R.color.colorFondoBaar))
                        .setVibrate(vibracion)
                        .setSound(alarmSound)
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
 /* // Nueva instancia del intent apuntado hacia Eslabon
        Intent intent = new Intent(this, Menu_Inicio.class);

        // Crear pila
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Añadir actividad padre
        stackBuilder.addParentStack(Menu_Inicio.class);

        // Referenciar Intent para la notificación
        stackBuilder.addNextIntent(intent);

        // Obtener PendingIntent resultante de la pila
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Asignación del pending intent
        builder.setContentIntent(resultPendingIntent);*/
