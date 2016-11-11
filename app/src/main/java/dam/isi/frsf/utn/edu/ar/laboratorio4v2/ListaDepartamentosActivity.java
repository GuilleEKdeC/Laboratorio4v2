package dam.isi.frsf.utn.edu.ar.laboratorio4v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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

        // Manejo del Intent
    /*    tarea = getIntent();
        String nombre=tarea.getStringExtra("cantidadTrabajos");
        totalTrabajos = Integer.valueOf(nombre);*/
        // Fin manejo del Intent

    }

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
            lista= Departamento.getAlojamientosDisponibles();
        }
        departamentosAdapter = new DepartamentoAdapter(ListaDepartamentosActivity.this,lista);
        listaAlojamientos.setAdapter(departamentosAdapter);
    }

    /*----------------------------------- Búsqueda Finalizada ------------------------------------*/
    public void busquedaFinalizada(List<Departamento> listaDepartamento) {
        //TODO implementar
    }

    /*---------------------------------- Búsqueda Actualizada ------------------------------------*/
    public void busquedaActualizada(String msg) {
        tvEstadoBusqueda.setText(" Buscando..."+msg);
    }

}
