package com.ceiba.membresia.comando.fabrica;

import com.ceiba.cliente.puerto.RepositorioCliente;
import com.ceiba.membresia.comando.ComandoSolicitudMembresia;
import com.ceiba.membresia.modelo.entidad.SolicitudMembresia;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudCrear {

    private RepositorioCliente repositorioCliente;

    public FabricaSolicitudCrear(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }


    public SolicitudMembresia crear (ComandoSolicitudMembresia comandoSolicitudMembresia){
       return new SolicitudMembresia(
               repositorioCliente.obtener(comandoSolicitudMembresia.getIdCliente()),
               comandoSolicitudMembresia.getTipoMembresia()
       );
    }
}
