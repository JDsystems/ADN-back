package com.ceiba.invitacion.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.membresia.modelo.entidad.Membresia;
import lombok.Getter;

@Getter
public final class SolicitudInvitar {
    private final Membresia membresia;
    private final String identificacionInvitado;
    private final String nombreInvitado;

    private SolicitudInvitar(Membresia membresia, String identificacionInvitado, String nombreInvitado) {
        this.membresia = membresia;
        this.identificacionInvitado = identificacionInvitado;
        this.nombreInvitado = nombreInvitado;
    }

    public Long getIdClienteMembresia(){
        return this.membresia.getIdCliente();
    }

    public static SolicitudInvitar crear(Membresia membresia, String identificacionInvitado, String nombreInvitado){
        ValidadorArgumento.validarObligatorio(membresia,"La membresia es obligatoria");
        return  new SolicitudInvitar(membresia,identificacionInvitado,nombreInvitado);
    }

}
