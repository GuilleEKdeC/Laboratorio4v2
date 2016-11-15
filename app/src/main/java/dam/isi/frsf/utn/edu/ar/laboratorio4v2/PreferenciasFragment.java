package dam.isi.frsf.utn.edu.ar.laboratorio4v2;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PreferenciasFragment extends PreferenceFragment {

    /*------------------------------------- ON CREATE --------------------------------------------*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferencias);
    }//Fin ON CREATE
}
