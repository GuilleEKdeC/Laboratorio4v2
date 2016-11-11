package dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils.FormBusqueda;

/**
 * Created by martdominguez on 22/09/2016.
 */
public class BuscarDepartamentosTask extends AsyncTask<FormBusqueda,Integer,List<dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento>> {

    private BusquedaFinalizadaListener<dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento> listener;

    public BuscarDepartamentosTask(BusquedaFinalizadaListener<dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento> dListener){
        this.listener = dListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento> departamentos) {
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.busquedaActualizada("departamento "+values[0]);


    }

    @Override
    protected List<dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento> doInBackground(FormBusqueda... busqueda) {
        List<dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento> todos = dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento.getAlojamientosDisponibles();
        List<dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento> resultado = new ArrayList<dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento>();
        int contador = 0;
        dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Ciudad ciudadBuscada = busqueda[0].getCiudad();
        // TODO implementar: buscar todos los departamentos del sistema e ir chequeando las condiciones 1 a 1.
        // si cumplen las condiciones agregarlo a los resultados.
        return resultado;
    }
}
