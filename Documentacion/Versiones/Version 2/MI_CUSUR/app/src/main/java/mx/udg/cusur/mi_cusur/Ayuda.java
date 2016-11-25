package mx.udg.cusur.mi_cusur;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ayuda extends AppCompatActivity {

    Button irasiiau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        irasiiau = (Button) findViewById(R.id.ayuda_BTIrSIIAU);

        //Metodo de iniciar sesion...
        irasiiau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://siiauescolar.siiau.udg.mx/wus/gupprincipal.inicio"));
                startActivity(intent);
            }
        });

    }
}

