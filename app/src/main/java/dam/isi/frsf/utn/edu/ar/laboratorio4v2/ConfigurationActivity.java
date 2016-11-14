package dam.isi.frsf.utn.edu.ar.laboratorio4v2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class ConfigurationActivity extends AppCompatActivity implements Serializable {

    Intent tarea;
    EditText et_us;
    EditText et_mail;
    ImageView iv_us;
    TextView tv_notif;



    /*-----------------------------------------On Create------------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        // Manejo del Intent
        tarea = getIntent();
       // String nombre=tarea.getStringExtra("cantidadTrabajos");
        //totalTrabajos = Integer.valueOf(nombre);
        // Fin manejo del Intent

        et_us = (EditText) findViewById(R.id.et_usuario);
        et_mail = (EditText) findViewById(R.id.et_mail);
        iv_us = (ImageView) findViewById(R.id.iv_usuario);
        tv_notif = (TextView) findViewById(R.id.tv_notificaciones);

        /*---------------------------------- On Click Listener -----------------------------------*/
       // tv_notif.setOnClickListener(new View.OnClickListener() {


      // });



    }// Fin ON CREATE

    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_notificaciones:
                //Intent selec_rington = new Intent();
                //  ii.setClass(RoomForSpecificUser.this, DeviceOperation.class);
                // startActivity(selec_rington);
                Toast.makeText(getBaseContext(), "Clickee las NOTIFICACIONES", Toast.LENGTH_LONG).show();
                break;
            case R.id.iv_usuario:
                Toast.makeText(getBaseContext(), "Clickee la IMÁGEN", Toast.LENGTH_LONG).show();
                break;
        }
    }
    /*------------------------------------- Gets y Sets ------------------------------------------*/
    public EditText getEt_us() {
        return et_us;
    }

    public void setEt_us(EditText et_us) {
        this.et_us = et_us;
    }

    public ImageView getIv_us() {
        return iv_us;
    }

    public void setIv_us(ImageView iv_us) {
        this.iv_us = iv_us;
    }

    public TextView getTv_notif() {
        return tv_notif;
    }

    public void setTv_notif(TextView tv_notif) {
        this.tv_notif = tv_notif;
    }

    /*
    * tarea.putExtra("resultado",trabajo);
                    setResult(RESULT_OK, tarea);
                    finish();
    * */



}
