package com.ceiba.membresia;

import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.membresia.modelo.entidad.SolicitudMembresia;

public class SolicitudMembresiaTestDataBuilder {

    private Cliente cliente;

    private String tipoMembresia;

    public SolicitudMembresiaTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public SolicitudMembresiaTestDataBuilder conTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
        return this;
    }

    public SolicitudMembresia build(){
        return new SolicitudMembresia(cliente,tipoMembresia);
    }
}
