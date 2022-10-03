package com.ceiba.membresia.modelo.dto;


import com.ceiba.cliente.dto.ResumenClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResumenMembresiaDTO {

    private Long id;
    private ResumenClienteDTO cliente;
    private String fechaInicio;
    private String fechaFin;
    private String tipo;

}
