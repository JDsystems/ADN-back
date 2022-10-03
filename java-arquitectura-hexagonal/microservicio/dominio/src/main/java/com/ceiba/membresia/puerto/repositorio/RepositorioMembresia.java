package com.ceiba.membresia.puerto.repositorio;

import com.ceiba.membresia.modelo.entidad.Membresia;

public interface RepositorioMembresia {

    Long guardar(Membresia membresia);

    Membresia obtener(Long id);
    void actualizarEstado(Membresia membresia);
}
