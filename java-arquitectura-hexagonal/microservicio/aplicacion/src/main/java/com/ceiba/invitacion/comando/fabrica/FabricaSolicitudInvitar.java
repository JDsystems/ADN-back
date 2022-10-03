package com.ceiba.invitacion.comando.fabrica;

import com.ceiba.invitacion.comando.ComandoSolicitudInvitar;
import com.ceiba.invitacion.modelo.entidad.SolicitudInvitar;
import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudInvitar {

    private RepositorioMembresia repositorioMembresia;

    public FabricaSolicitudInvitar(RepositorioMembresia repositorioMembresia) {
        this.repositorioMembresia = repositorioMembresia;
    }

    public SolicitudInvitar crear(ComandoSolicitudInvitar comandoSolicitudInvitar){
        return  SolicitudInvitar.crear(
                repositorioMembresia.obtener(comandoSolicitudInvitar.getIdMembresia()),
                comandoSolicitudInvitar.getIdentificacionInvitado(),
                comandoSolicitudInvitar.getNombreInvitado()
        );
    }
}
