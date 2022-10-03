package com.ceiba.cliente.puerto.dao;

import com.ceiba.cliente.dto.ResumenClienteDTO;

import java.util.List;

public interface DaoCliente {

    List<ResumenClienteDTO> obtenerClientes();
}
