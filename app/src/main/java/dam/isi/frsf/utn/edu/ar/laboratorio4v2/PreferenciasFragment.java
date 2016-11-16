package dam.isi.frsf.utn.edu.ar.laboratorio4v2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class PreferenciasFragment extends PreferenceFragment /*implements SharedPreferences.OnSharedPreferenceChangeListener*/ {

    /*------------------------------------- ON CREATE --------------------------------------------*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferencias);

        // set texts correctly
    //    onSharedPreferenceChanged(null, "");



    //    SharedPreferences sp = getPreferenceManager().getSharedPreferences();
    }//Fin ON CREATE

/*
    public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {
        if (key.equals(R.string.clave_usuario)){
            Preference connectionPref = findPreference(key);
            connectionPref.setSummary("Modifiqué el USUARIO");
        }
        if (key.equals(R.string.clave_mail)){
            Preference connectionPref = findPreference(key);
            connectionPref.setSummary("Modifiqué el MAIL");
        }
        if (key.equals(R.string.clave_notificaciones)){
            Preference connectionPref = findPreference(key);
            connectionPref.setSummary("Modifiqué la NOTIF");
        }
    }

    public void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void onPause() {
        super.onPause();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
*/

}



