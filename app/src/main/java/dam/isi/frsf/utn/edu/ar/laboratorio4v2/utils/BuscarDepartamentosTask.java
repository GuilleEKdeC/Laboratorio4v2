package dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils.FormBusqueda;


public class BuscarDepartamentosTask extends AsyncTask<FormBusqueda,Integer,List<Departamento>> {

    private BusquedaFinalizadaListener<Departamento> listener;

    /*------------------------------------- Constructor ------------------------------------------*/
    public BuscarDepartamentosTask(BusquedaFinalizadaListener<Departamento> dListener){
        this.listener = dListener;
    }

    /*------------------------------------ On Pre Execute ----------------------------------------*/
    // Se ejecuta en el hilo principal antes que el hilo en Segundo plano de doInBackground() ejecute.
    // Se puede utilizar para inicialización de elementos necesarios en el proceso.
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /*----------------------------------- On Post Execute ----------------------------------------*/
    // Es invocado desde el hilo de la UI luego de que doInBackground() finalize su ejecución, es decir, cuando finalice nuestra tarea.
    // Recibe como parametro el valor retornado por doInBackground() / por ejemplo un flag que indica exito o fracaso.
    protected void onPostExecute(List<Departamento> departamentos) {
    }

    /*---------------------------------- On Progress Update --------------------------------------*/
    // Es invocado en el hilo principal de la UI y puede mostrarle al usuario el progreso de la operación.
    // Recibe como argumento los datos del resultado de doInBackground()
    // Se ejecutará cada vez que llamemos al método publishProgress() desde el método doInBackground()
    protected void onProgressUpdate(Integer... values) {
        listener.busquedaActualizada("departamento "+values[0]);


    }

    /*---------------------------------- Do In Background ----------------------------------------*/
    // Código principal de nuestra tarea que recibe 3 parámetros (c/u un arreglo de var args) que se corresponden con los tipos definidos en AsyncTask.
    // Se ejecuta en un hilo secundario.
    // Debe retornar un valor que indique el resultado de la operación (de tipo generic), similar a los var args que recibe como argumento.
    protected List<Departamento> doInBackground(FormBusqueda... busqueda) {

        List<Departamento> todos = Departamento.getAlojamientosDisponibles();
        List<Departamento> resultado = new ArrayList<dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo.Departamento>();
        int contador = 0;
        Ciudad ciudadBuscada = busqueda[0].getCiudad();
        // TODO implementar: buscar todos los departamentos del sistema e ir chequeando las condiciones 1 a 1.
        // si cumplen las condiciones agregarlo a los resultados.
        return resultado;
    }
}

/*
onCancelled().
•Se ejecutará cuando se cancele la ejecución de la tarea antes de su finalización normal


classMiAsynTaskextendsAsyncTask<String,Double,Boolean> {

    protectedBooleandoInBackground(String... strings) {

        inti = 0;
        inttotal = strings.length;
        for(Strings : strings){
            tareaLarga(s.length());
            i++;
            publishProgress(((i*1.0)/total)*100.0);
            if(isCancelled()) break;
        }
        return true;
   };
}

*/