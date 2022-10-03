package com.ceiba.invitacion;

import com.ceiba.invitacion.modelo.entidad.SolicitudInvitar;
import com.ceiba.membresia.modelo.entidad.Membresia;

public class SolicitudInvitacionTestDataBuilder {
    private   Membresia membresia;
    private  String identificacionInvitado;
    private  String nombreInvitado;


    public SolicitudInvitacionTestDataBuilder conMembresia(Membresia membresia) {
        this.membresia = membresia;
        return this;
    }

    public SolicitudInvitacionTestDataBuilder conIdentificacionInvitado(String identificacionInvitado) {
        this.identificacionInvitado = identificacionInvitado;
        return this;
    }

    public SolicitudInvitacionTestDataBuilder conNombreInvitado(String nombreInvitado) {
        this.nombreInvitado = nombreInvitado;
        return this;
    }

    public SolicitudInvitar build(){
        return   SolicitudInvitar.crear(membresia,identificacionInvitado,nombreInvitado);
    }
}
