package dam.isi.frsf.utn.edu.ar.laboratorio4v2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;


public class ConfiguracionActivity extends AppCompatActivity {


    /*------------------------------------- ON CREATE --------------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()    //sustituye el contenido de la pantalla (android.R.id.content) por el de nuestro fragment de preferencias recién definido
                .beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment())
                .commit();

       /* SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);*/
    }//Fin ON CREATE

}
