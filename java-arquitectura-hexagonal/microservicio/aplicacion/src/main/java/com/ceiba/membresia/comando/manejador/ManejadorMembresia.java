package com.ceiba.membresia.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.membresia.comando.ComandoSolicitudMembresia;
import com.ceiba.membresia.comando.fabrica.FabricaSolicitudCrear;
import com.ceiba.membresia.servicio.ServicioCrear;
import org.springframework.stereotype.Component;

@Component
public class ManejadorMembresia  implements ManejadorComandoRespuesta<ComandoSolicitudMembresia, ComandoRespuesta<Long>> {

    private final FabricaSolicitudCrear fabricaSolicitudCrear;

    private ServicioCrear servicioCrear;

    public ManejadorMembresia(FabricaSolicitudCrear fabricaSolicitudCrear, ServicioCrear servicioCrear) {
        this.fabricaSolicitudCrear = fabricaSolicitudCrear;
        this.servicioCrear = servicioCrear;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudMembresia comando) {
        return new ComandoRespuesta<>(
                servicioCrear.ejecutar(
                        fabricaSolicitudCrear.crear(comando)
                )
        );
    }
}
