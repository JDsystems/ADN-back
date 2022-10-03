package com.ceiba.membresia.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.membresia.comando.ComandoCancelar;
import com.ceiba.membresia.comando.fabrica.FabricaSolicitudCancelar;
import com.ceiba.membresia.servicio.ServicioCancelar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCancelar  implements ManejadorComando<ComandoCancelar> {

    private final ServicioCancelar servicioCancelar;

    private final FabricaSolicitudCancelar fabricaSolicitudCancelar;


    public ManejadorCancelar(ServicioCancelar servicioCancelar, FabricaSolicitudCancelar fabricaSolicitudCancelar) {
        this.servicioCancelar = servicioCancelar;
        this.fabricaSolicitudCancelar = fabricaSolicitudCancelar;
    }

    @Override
    public void ejecutar(ComandoCancelar comando) {
        servicioCancelar.ejecutar(fabricaSolicitudCancelar.cancelar(comando));
    }
}
