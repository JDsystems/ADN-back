package com.ceiba.membresia.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.membresia.comando.ComandoCancelar;
import com.ceiba.membresia.comando.ComandoSolicitudMembresia;
import com.ceiba.membresia.comando.manejador.ManejadorCancelar;
import com.ceiba.membresia.comando.manejador.ManejadorMembresia;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membresia")
@Tag(name = "Controlador comando membresia")
public class ComandoControladorMembresia {
    private final ManejadorMembresia manejadorMembresia;

    private final ManejadorCancelar manejadorCancelar;

    public ComandoControladorMembresia(ManejadorMembresia manejadorMembresia, ManejadorCancelar manejadorCancelar) {
        this.manejadorMembresia = manejadorMembresia;
        this.manejadorCancelar = manejadorCancelar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "crear Membresia", description = "Metodo utilizado para crear una membresia")
    public ComandoRespuesta<Long> crearMembresia(@RequestBody ComandoSolicitudMembresia comandoSolicitudMembresia){
        return this.manejadorMembresia.ejecutar(comandoSolicitudMembresia);
    }


    @PostMapping("/cancelar/{id-membresia}")
    @Operation(summary = "Cancelar", description = "Metodo utilizado para cancelar una membresia")
    public void cancelar(@PathVariable("id-membresia") Long idMembresia){
        this.manejadorCancelar.ejecutar(new ComandoCancelar(idMembresia));
    }
}
