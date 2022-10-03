package com.ceiba.invitacion.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.invitacion.comando.ComandoSolicitudInvitar;
import com.ceiba.invitacion.comando.fabrica.FabricaSolicitudInvitar;
import com.ceiba.invitacion.servicio.ServicioInvitar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.membresia.comando.ComandoSolicitudMembresia;
import org.springframework.stereotype.Component;

@Component
public class ManejadorInvitar implements ManejadorComandoRespuesta<ComandoSolicitudInvitar, ComandoRespuesta<Long>> {

    private final FabricaSolicitudInvitar fabricaSolicitudInvitar;

    private ServicioInvitar servicioInvitar;

    public ManejadorInvitar(FabricaSolicitudInvitar fabricaSolicitudInvitar, ServicioInvitar servicioInvitar) {
        this.fabricaSolicitudInvitar = fabricaSolicitudInvitar;
        this.servicioInvitar = servicioInvitar;
    }


    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudInvitar comando) {
        return new ComandoRespuesta<>(
                servicioInvitar.ejecutar(
                        fabricaSolicitudInvitar.crear(comando)
                )
        );
    }
}
