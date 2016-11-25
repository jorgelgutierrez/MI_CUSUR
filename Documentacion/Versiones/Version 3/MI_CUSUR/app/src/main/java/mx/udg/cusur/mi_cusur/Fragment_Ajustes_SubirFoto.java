package mx.udg.cusur.mi_cusur;

import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;



public class Fragment_Ajustes_SubirFoto extends Fragment {

    ImageView imagen;
    Button btagregar, btsubir;
    String codigo_alumno;

    private static final int SELECT_IMAGE = 1;

    public Fragment_Ajustes_SubirFoto() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ajustes_subir_foto, container, false);
        btsubir = (Button) view.findViewById(R.id.ajustes_subirfoto_btsubirfoto);
        btagregar = (Button) view.findViewById(R.id.ajustes_subirfoto_btagregarfoto);
        imagen = (ImageView) view.findViewById(R.id.ajustes_subirfoto_imgfoto);

        //Codigo para saber el codigo del alumno..
        VariablesGlobales g = VariablesGlobales.getInstance();
        codigo_alumno = g.getCodigo();

        btagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btsubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
