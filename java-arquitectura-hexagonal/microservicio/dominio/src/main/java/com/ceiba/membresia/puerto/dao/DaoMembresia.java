package com.ceiba.membresia.puerto.dao;

import com.ceiba.membresia.modelo.dto.ResumenMembresiaDTO;

import java.util.List;

public interface DaoMembresia {
    List<ResumenMembresiaDTO> obtenerMembresiasActivas();
}
