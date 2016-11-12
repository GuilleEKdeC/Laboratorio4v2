package dam.isi.frsf.utn.edu.ar.laboratorio4v2.utils;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio4v2.ListaDepartamentosActivity;
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
    //    listener.busquedaActualizada("Vamos a comenzar a procesar");    //AGREGADO
        super.onPreExecute();
    }

    /*----------------------------------- On Post Execute ----------------------------------------*/
    // Es invocado desde el hilo de la UI luego de que doInBackground() finalize su ejecución, es decir, cuando finalice nuestra tarea.
    // Recibe como parametro el valor retornado por doInBackground() / por ejemplo un flag que indica exito o fracaso.
    protected void onPostExecute(List<Departamento> departamentos) {
        listener.busquedaActualizada("Terminé la Búsqueda");    //AGREGADO
        listener.busquedaFinalizada(departamentos);             //AGREGADO
    }

    /*---------------------------------- On Progress Update --------------------------------------*/
    // Es invocado en el hilo principal de la UI y puede mostrarle al usuario el progreso de la operación.
    // Recibe como argumento los datos del resultado de doInBackground()
    // Se ejecutará cada vez que llamemos al método publishProgress() desde el método doInBackground()
    protected void onProgressUpdate(Integer... values) {

        listener.busquedaActualizada("departamento " + values[0]);
        /*for(Integer value: values) {
            listener.busquedaActualizada("departamento "+value.toString());
        }*/
    }

    /*------------------------------------- On Cancelled -----------------------------------------*/
    //AGREAGADO
    //Se ejecutará cuando se cancele la ejecución de la tarea antes de su finalización normal.
    protected void onCancelled() {
        listener.busquedaActualizada("Cancelado");
    }

    /*---------------------------------- Do In Background ----------------------------------------*/
    // Código principal de nuestra tarea que recibe 3 parámetros (c/u un arreglo de var args) que se corresponden con los tipos definidos en AsyncTask.
    // Se ejecuta en un hilo secundario.
    // Debe retornar un valor que indique el resultado de la operación (de tipo generic), similar a los var args que recibe como argumento.
    protected List<Departamento> doInBackground(FormBusqueda... busqueda) {

        List<Departamento> todos = Departamento.getAlojamientosDisponibles();
        List<Departamento> resultado = new ArrayList<Departamento>();
        int contador = 0;

        Ciudad ciudadBuscada = busqueda[0].getCiudad();
        Integer cantHuespedes = busqueda[0].getHuespedes();     //AGREGADO
        Boolean aptoFumadores = busqueda[0].getPermiteFumar();  //AGREGADO
        Double precioMin = busqueda[0].getPrecioMinimo();       //AGREGADO
        Double precioMax = busqueda[0].getPrecioMaximo();       //AGREGADO

        Departamento depto;                     //AGREGADO
        int total_deptos = todos.size();        //AGREGADO
        // FOR íntegro, AGREGADO
        for(int i = 0; i < total_deptos; i++) {
            depto = todos.get(i);
            if(depto.getCiudad().equals(ciudadBuscada)){   //Si es la Ciudad solicitada
                if (depto.getCapacidadMaxima() >= cantHuespedes){  //Si dispone capacidad de cantidad de huéspedes
        /*           if(depto.getNoFumador() != (busqueda[0].getPermiteFumar())){   //Si es o no fumador
                         if((depto.getPrecio() <= busqueda[0].getPrecioMaximo())&& (depto.getPrecio() >= busqueda[0].getPrecioMinimo())){ // No aplico descuentos, porque en el adaptador tampoco figuran. Sino simplemente sería sacar el cálculo
                        */   resultado.add(depto);
                       /*  }
                }*/
                }
            }
            //publishProgress(((i*1.0)/total_deptos)*100.0);    //AGREGADO
        //    publishProgress(i);                                 //AGREGADO
        //    if(isCancelled()) break;                          //AGREGADO
        }
     //   resultado = todos;
        return resultado;
    }
}

/*
Estos métodos tienen una particularidad esencial para nuestros intereses. El método doInBackground() se ejecuta en un hilo secundario
(por tanto no podremos interactuar con la interfaz), pero sin embargo todos los demás se ejecutan en el hilo principal, lo que quiere
decir que dentro de ellos podremos hacer referencia directa a nuestros controles de usuario para actualizar la interfaz. Por su parte,
dentro de doInBackground() tendremos la posibilidad de llamar periódicamente al método publishProgress() para que automáticamente
desde el método onProgressUpdate() se actualice la interfaz si es necesario.

*/