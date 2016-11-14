package dam.isi.frsf.utn.edu.ar.laboratorio4v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils.BuscarDepartamentosTask;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils.FormBusqueda;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento;



public class ListaDepartamentosActivity extends AppCompatActivity implements BusquedaFinalizadaListener<Departamento> {

    private TextView tvEstadoBusqueda;
    private ListView listaAlojamientos;
    private DepartamentoAdapter departamentosAdapter;
    private List<Departamento> lista;


    /*---------------------------------------- On Create -----------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alojamientos);
        lista = new ArrayList<>();
        listaAlojamientos= (ListView) findViewById(R.id.listaAlojamientos);
        tvEstadoBusqueda = (TextView) findViewById(R.id.estadoBusqueda);


        /*--------------------*------- On Item Long Click Listener -------------------------------*/
        listaAlojamientos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "Long Click para dar alta a un depto elegido", Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }//Fin ON CREATE

    /*---------------------------------------- On Start ------------------------------------------*/
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Boolean esBusqueda = intent.getExtras().getBoolean("esBusqueda");
        if(esBusqueda){
            FormBusqueda fb = (FormBusqueda ) intent.getSerializableExtra("frmBusqueda");//obtiene el formulario con los criterios seleccionados
            new BuscarDepartamentosTask(ListaDepartamentosActivity.this).execute(fb);   //invoca una nueva tarea asincrónica (AsyncTask) para que procese la selección y manda a Ejecutar la tarea
            tvEstadoBusqueda.setText("Buscando....");                                   //donde el hilo principal para dicha tarea es el contexto de "ListaDepartamentosActivity"
            tvEstadoBusqueda.setVisibility(View.VISIBLE);
        }else{
            tvEstadoBusqueda.setVisibility(View.GONE);
            lista = Departamento.getAlojamientosDisponibles();
        }
        departamentosAdapter = new DepartamentoAdapter(ListaDepartamentosActivity.this,lista);
        listaAlojamientos.setAdapter(departamentosAdapter);
    }

    /*----------------------------------- Búsqueda Finalizada ------------------------------------*/
    public void busquedaFinalizada(List<Departamento> listaDepartamento) {

        lista = listaDepartamento;                                      //AGREGADO
        if(lista.isEmpty()){                                            //AGREGADO
            tvEstadoBusqueda.setText("No existen Departamentos con esos Criterios");    //AGREGADO
        }
        else{
            tvEstadoBusqueda.setVisibility(View.GONE);                  //AGREGADO
            departamentosAdapter = new DepartamentoAdapter(ListaDepartamentosActivity.this,lista);  //AGREGADO
            listaAlojamientos.setAdapter(departamentosAdapter);         //AGREGADO
        }
    }

    /*---------------------------------- Búsqueda Actualizada ------------------------------------*/
    public void busquedaActualizada(String msg) {
        tvEstadoBusqueda.setText(" Buscando... "+msg);
    }

}
