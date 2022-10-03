package com.ceiba.invitacion.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.invitacion.comando.ComandoSolicitudInvitar;
import com.ceiba.invitacion.comando.manejador.ManejadorInvitar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invitacion")
@Tag(name = "Controlador comando invitación")
public class ComandoControladorInvitacion {

    private final ManejadorInvitar  manejadorInvitar;

    public ComandoControladorInvitacion(ManejadorInvitar manejadorInvitar) {
        this.manejadorInvitar = manejadorInvitar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Crear Invitacion", description = "Metodo utilizado para crear una invitacion")
    public ComandoRespuesta<Long> crearInvitacion(@RequestBody ComandoSolicitudInvitar controladorInvitacion){
        return  this.manejadorInvitar.ejecutar(controladorInvitacion);
    }
}
