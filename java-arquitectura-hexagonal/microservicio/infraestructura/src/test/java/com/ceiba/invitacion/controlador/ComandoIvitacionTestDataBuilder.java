package com.ceiba.invitacion.controlador;

import com.ceiba.invitacion.comando.ComandoSolicitudInvitar;

public class ComandoIvitacionTestDataBuilder {
    private Long idMembresia;
    private String identificacionInvitado;
    private String nombreInvitado;

    public ComandoIvitacionTestDataBuilder() {
    }


    public ComandoIvitacionTestDataBuilder crearPorDefecto() {

        this.idMembresia = 1l;
        this.identificacionInvitado = "123456789";
        this.nombreInvitado = "Invitado por defecto";
        return this;
    }

    public ComandoSolicitudInvitar build(){
        return  new ComandoSolicitudInvitar(this.idMembresia,this.identificacionInvitado,this.nombreInvitado);
    }
}
