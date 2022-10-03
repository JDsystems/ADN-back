package com.ceiba.invitacion.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.invitacion.modelo.entidad.Invitacion;
import com.ceiba.invitacion.modelo.entidad.SolicitudInvitar;
import com.ceiba.invitacion.puerto.repositorio.RepositorioInvitacion;

public class ServicioInvitar {

    private final RepositorioInvitacion repositorioInvitacion;


    public ServicioInvitar(RepositorioInvitacion repositorioInvitacion) {
        this.repositorioInvitacion = repositorioInvitacion;
    }

    public Long ejecutar(SolicitudInvitar solicitudInvitar){
        var membresia = solicitudInvitar.getMembresia();
        Long totalDiasInvitados = Long.valueOf(
                repositorioInvitacion.contar(solicitudInvitar.getIdClienteMembresia())+""
        );

        if (membresia.esMembresiaBronce()){
            ValidadorArgumento.validarMayorIgual(totalDiasInvitados, ConstantesInvitacion.DIAS_INVITACION_BRONCE,ConstantesInvitacion.MENSAJE_ERROR_INVITAR);
        } else if (membresia.esMembresiaPlata()) {
            ValidadorArgumento.validarMayorIgual(totalDiasInvitados,ConstantesInvitacion.DIAS_INVITACION_PLATA, ConstantesInvitacion.MENSAJE_ERROR_INVITAR);
        } else if (membresia.esMembresiaOro()) {
            ValidadorArgumento.validarMayorIgual(totalDiasInvitados, ConstantesInvitacion.DIAS_INVITACION_ORO,ConstantesInvitacion.MENSAJE_ERROR_INVITAR);
        }
        var invitacion = Invitacion.crear(solicitudInvitar);
        return repositorioInvitacion.guardar(invitacion);
    }
}
