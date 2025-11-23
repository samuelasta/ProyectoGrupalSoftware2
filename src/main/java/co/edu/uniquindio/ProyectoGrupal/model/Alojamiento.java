package co.edu.uniquindio.ProyectoGrupal.model;

import co.edu.uniquindio.ProyectoGrupal.model.enums.Estado;
import co.edu.uniquindio.ProyectoGrupal.model.enums.Servicios;
import co.edu.uniquindio.ProyectoGrupal.model.enums.TipoAlojamiento;

import java.util.List;

public class Alojamiento {

    private String id;
    private String titulo;
    private String descripcion;
    private int capacidad;
    private Estado estado;
    private TipoAlojamiento tipoAlojamiento;
    private List<Servicios> servicios;
    private List<Comentario> comentarios;

}
