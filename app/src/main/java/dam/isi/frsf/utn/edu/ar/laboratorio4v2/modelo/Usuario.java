package dam.isi.frsf.utn.edu.ar.laboratorio4v2.modelo;

import java.io.Serializable;
import java.util.List;


public class Usuario implements Serializable {

    private Integer id;
    private String correo;
    private List<Reserva> reservas;
    private Integer puntosSuperPremio;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Integer getPuntosSuperPremio() {
        return puntosSuperPremio;
    }

    public void setPuntosSuperPremio(Integer puntosSuperPremio) {
        this.puntosSuperPremio = puntosSuperPremio;
    }
}
