package dam.isi.frsf.utn.edu.ar.laboratorio4v2;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;  //AGREGADA
import android.view.MenuItem;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Arrays;

import android.widget.Toast;

import dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Usuario;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils.FormBusqueda;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //  OnNavigationItemSelectedListener: listener para manejar las selecciones de los ítems del NavigationView


    private FormBusqueda frmBusq;
    private SeekBar skPrecioMin;
    private SeekBar skPrecioMax;
    private TextView tvPrecioMinimo;
    private TextView tvPrecioMaximo;
    private Spinner cmbCiudad;
    private ArrayAdapter<Ciudad> adapterCiudad;
    private EditText txtHuespedes;
    private Switch swFumadores;
    private Button btnBuscar;
    private Usuario usuario;


    /*------------------------------------- ON CREATE --------------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //Crea una nueva variable Toolbar tomando como referencia la definida en app_bar_main.xml
        setSupportActionBar(toolbar); // indica a Android que vamos a utilizar nuestra propia Toolbar.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout); //Crea una nueva variable DrawerLayout (contenedor de nuestro "contenido Principal" y de nuestro "navegador"), el activity_main.xml

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){//Compatibiliza el Drawer Layout con la barra de acciones (Toolbar)
        /*******************Agregado*******************/
        /*   public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("Después de cerrar el Navegador (ManiActivity)");//cambia el título a la toolbar
                invalidateOptionsMenu(); //indica a Android que los contenidos del menú han cambiado y que el menú debe ser redibujado. Crea una llamada al método onPrepareOptionsMenú
            }

           public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Antes de abrir el Navegador (ManiActivity)"); //cambia el título a la toolbar
                invalidateOptionsMenu(); //indica a Android que los contenidos del menú han cambiado y que el menú debe ser redibujado. Crea una llamada al método onPrepareOptionsMenú
            }*/
        /****************Fin de Agregado****************/
        };
        //drawer.setDrawerListener(toggle); //le setea un listenner al DrawerLayout //línea ORIGINAL pero método deprecated => cambié por addDrawerListener
        drawer.addDrawerListener(toggle); //le setea un listenner al DrawerLayout   // línea AGREGADA
        toggle.syncState(); //Sirve para sincronizar el "Indicador" (las tres barritas horizontales que despliega al navegador) del Drawer Layout
        toggle.setDrawerIndicatorEnabled(true); // setea que el Indicador esté visible // línea AGREGADA

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view); //Crea una nueva variable NavigationView, tomando como referencia la definida en activity_main.xml.
        navigationView.setNavigationItemSelectedListener(this); //le setea un listener

        /*----------------------------*/
        ImageView iv_usuario = (ImageView) findViewById(R.id.imageView);
        TextView tv_nombre = (TextView) findViewById(R.id.tv_nombre_usuario);
        TextView tv_mail = (TextView) findViewById(R.id.tv_mail_usuario);
        /*----------------------------*/


        frmBusq= new FormBusqueda(); //Crea un formulario con los criterios de búsqueda, vacío.

        tvPrecioMinimo = (TextView ) findViewById(R.id.txtPrecioMin);
        skPrecioMin = (SeekBar) findViewById(R.id.precioMin);
        skPrecioMin.setOnSeekBarChangeListener(listenerSB);

        tvPrecioMaximo= (TextView ) findViewById(R.id.txtPrecioMax);
        skPrecioMax= (SeekBar) findViewById(R.id.precioMax);
        skPrecioMax.setOnSeekBarChangeListener(listenerSB);

        adapterCiudad = new ArrayAdapter<Ciudad>(MainActivity.this,android.R.layout.simple_spinner_item, Arrays.asList(Ciudad.CIUDADES));
        cmbCiudad = (Spinner) findViewById(R.id.comboCiudad);
        cmbCiudad.setAdapter(adapterCiudad);
        cmbCiudad.setOnItemSelectedListener(comboListener);

        txtHuespedes = (EditText) findViewById(R.id.cantHuespedes);

        swFumadores = (Switch) findViewById(R.id.aptoFumadores);

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(btnBusarListener);

        frmBusq.setCiudad(Ciudad.CIUDADES[0]);  //Paris //AGREGADO
        frmBusq.setPrecioMinimo(0.0);   //AGREGADO
        frmBusq.setPrecioMaximo(0.0);   //AGREGADO

        usuario = new Usuario();

    }//Fin ON CREATE


    /*--------------------------------------------------------------------------------------------*/
    /*------------------------------ On Seek Bar Change Listener ---------------------------------*/
    private SeekBar.OnSeekBarChangeListener listenerSB =  new SeekBar.OnSeekBarChangeListener(){

        /*----------------------------------------*/
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(seekBar.getId()==R.id.precioMin) {
                tvPrecioMinimo.setText("Precio Minimo "+progress);
                frmBusq.setPrecioMinimo(Double.valueOf(progress));
            }
            if(seekBar.getId()==R.id.precioMax) {
                tvPrecioMaximo.setText("Precio Maximo"+progress);
                frmBusq.setPrecioMaximo(Double.valueOf(progress));
            }
        }

        /*---------------------------------------*/
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        /*---------------------------------------*/
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    /*--------------------------------------------------------------------------------------------*/
    /*-------------------------------- On Item Selected Listener ---------------------------------*/
    private AdapterView.OnItemSelectedListener comboListener = new  AdapterView.OnItemSelectedListener(){

        /*---------------------------------------*/
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Ciudad item = (Ciudad) parent.getItemAtPosition(pos);
            frmBusq.setCiudad(item);
            Log.d("MainActivity","ciudad seteada "+item);
        }
        /*---------------------------------------*/
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    /*--------------------------------------------------------------------------------------------*/
    /*------------------------------------ On Click Listener -------------------------------------*/
    private View.OnClickListener btnBusarListener = new View.OnClickListener() {

        public void onClick(View view) {

            Toast.makeText(getBaseContext(), "Clickee BUSCAR", Toast.LENGTH_LONG).show();

            if (TextUtils.isEmpty(txtHuespedes.getText().toString())){               //AGREGADO
                Toast.makeText(getBaseContext(), "Ingrese Cantidad de Huéspedes", Toast.LENGTH_LONG).show();
                txtHuespedes.requestFocus();
            }
            else{
                frmBusq.setHuespedes(Integer.parseInt(txtHuespedes.getText().toString()));  //AGREGADO
                frmBusq.setPermiteFumar(swFumadores.isChecked());

                Intent i = new Intent(MainActivity.this,ListaDepartamentosActivity.class);
                i.putExtra("esBusqueda",true);
                i.putExtra("frmBusqueda",frmBusq);
                startActivity(i);   // startActivityForResult(tarea,0); Otra forma que usé en el labo 3
            }
        }
    };

    /*----------------------- On Create Option Menu (creación Menú Ppal)--------------------------*/
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /*------------------- On Options Item Selected (Listener del Menú Ppal)-----------------------*/
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on
        // the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent configurar = new Intent(this,ConfiguracionActivity.class);
            //  tarea.putExtra("cantidadTrabajos",Integer.toString(listaTrabajos.size()));
           // startActivityForResult(configurar,0);
            startActivity(configurar);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*---------------------- on Navigation Item Selected (listener del Navegador)-----------------*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_deptos:
                Intent i1 = new Intent(MainActivity.this,ListaDepartamentosActivity.class);
                i1.putExtra("esBusqueda",false );
                startActivity(i1);
                break;
            case R.id.nav_ofertas:
                Toast.makeText(getBaseContext(), "Clickee OFERTAS", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_destinos:
                Toast.makeText(getBaseContext(), "Clickee DESTINOS", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_reservas:
                Toast.makeText(getBaseContext(), "Clickee RESERVAS", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_perfil:
                Toast.makeText(getBaseContext(), "Clickee PERFIL", Toast.LENGTH_LONG).show();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);//Cierra el NavigationView
        return true;//Devuelve TRUE en caso de que fue usado y FALSO en caso contrario
    }

    /*---------------------- on Back Pressed (botón retroceso del celular)------------------------*/
    //Al presionar el botón de volver atras del celular, lo primero que hace es cerrar el Navegador si estaba abierto
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
/* CODIGO AGREGADO MIO

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



 */