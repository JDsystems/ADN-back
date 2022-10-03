package com.ceiba.cliente.controlador;

import com.ceiba.cliente.consulta.ManejadorConsultaCliente;
import com.ceiba.cliente.dto.ResumenClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "controlador consulta cliente")
public class ConsultaControladorCliente {

    private final ManejadorConsultaCliente manejadorConsultaCliente;

    public ConsultaControladorCliente(ManejadorConsultaCliente manejadorConsultaCliente) {
        this.manejadorConsultaCliente = manejadorConsultaCliente;
    }

    @GetMapping("/todos")
    @Operation(summary = "Todos", description = "Metodo para consultar todos los cleintes")
    public List<ResumenClienteDTO> listarClientes(){
        return  manejadorConsultaCliente.ejecutar();
    }
}
