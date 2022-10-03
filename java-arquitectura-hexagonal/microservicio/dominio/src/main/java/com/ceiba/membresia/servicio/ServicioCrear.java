package com.ceiba.membresia.servicio;

import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import com.ceiba.membresia.modelo.entidad.Membresia;
import com.ceiba.membresia.modelo.entidad.SolicitudMembresia;

public class ServicioCrear {

    private final RepositorioMembresia repositorioMembresia;

    public ServicioCrear(RepositorioMembresia repositorioMembresia) {
        this.repositorioMembresia = repositorioMembresia;
    }


    public Long ejecutar(SolicitudMembresia solicitudMembresia){
        var membresia = Membresia.crear(solicitudMembresia);
        return repositorioMembresia.guardar(membresia);
    }
}
