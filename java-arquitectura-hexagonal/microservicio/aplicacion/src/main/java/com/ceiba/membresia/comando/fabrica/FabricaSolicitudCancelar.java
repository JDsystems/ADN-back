package com.ceiba.membresia.comando.fabrica;

import com.ceiba.membresia.comando.ComandoCancelar;
import com.ceiba.membresia.modelo.entidad.SolicitudCancelar;
import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudCancelar {

    private RepositorioMembresia repositorioMembresia;

    public FabricaSolicitudCancelar(RepositorioMembresia repositorioMembresia) {
        this.repositorioMembresia = repositorioMembresia;
    }

    public SolicitudCancelar cancelar(ComandoCancelar comandoCancelar){
        return new SolicitudCancelar(
                repositorioMembresia.obtener(comandoCancelar.getIdMembresia())
        );
    }
}
