package com.ceiba.membresia.modelo.entidad;

import com.ceiba.cliente.entidad.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SolicitudMembresia {
    private final Cliente cliente;
    private final String tipoMembresia;
}
