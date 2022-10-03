package com.ceiba.membresia.servicio;

import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import com.ceiba.membresia.modelo.entidad.Membresia;
import com.ceiba.membresia.modelo.entidad.SolicitudCancelar;

public class ServicioCancelar {

    private final RepositorioMembresia repositorioMembresia;

    public ServicioCancelar(RepositorioMembresia repositorioMembresia) {
        this.repositorioMembresia = repositorioMembresia;
    }


    public void ejecutar(SolicitudCancelar solicitudCancelar){
        var membresia = solicitudCancelar.getMembresia();
        membresia.cancelar();
        repositorioMembresia.actualizarEstado(membresia);
    }
}
