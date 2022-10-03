package com.ceiba.membresia;

import com.ceiba.membresia.modelo.entidad.Membresia;
import com.ceiba.membresia.modelo.entidad.SolicitudCancelar;

public class SolicitudCancelarMembresiaTestDataBuilder {

    private Membresia membresia;

    public SolicitudCancelarMembresiaTestDataBuilder conMembresia(Membresia membresia) {
        this.membresia = membresia;
        return this;
    }

    public SolicitudCancelar build(){
        return  new SolicitudCancelar(membresia);
    }
}
