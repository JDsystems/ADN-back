package com.ceiba.membresia.controlador;

import com.ceiba.membresia.consulta.ManejadorConsultarMembresiasActivas;
import com.ceiba.membresia.modelo.dto.ResumenMembresiaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/membresia")
@Tag(name = "controlador consulta membresias")
public class ConsultaControladorMembresia {

    private final ManejadorConsultarMembresiasActivas manejadorConsultarMembresiasActivas;

    public ConsultaControladorMembresia(ManejadorConsultarMembresiasActivas manejadorConsultarMembresiasActivas) {
        this.manejadorConsultarMembresiasActivas = manejadorConsultarMembresiasActivas;
    }

    @GetMapping("/activa")
    @Operation(summary = "Activas", description = "Metodo para consultar las membresias activas")
    public List<ResumenMembresiaDTO> obtenerActivas(){
        return manejadorConsultarMembresiasActivas.ejecutar();
    }
}
