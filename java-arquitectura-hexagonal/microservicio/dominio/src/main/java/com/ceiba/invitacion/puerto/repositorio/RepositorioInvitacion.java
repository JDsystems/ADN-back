package com.ceiba.invitacion.puerto.repositorio;

import com.ceiba.invitacion.modelo.entidad.Invitacion;

public interface RepositorioInvitacion {

    Long guardar(Invitacion invitacion);
    Invitacion obtener(Long id);
    Object contar(Long idCliente);
}
