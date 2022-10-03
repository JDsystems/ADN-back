package com.ceiba.membresia.controlador;

import com.ceiba.membresia.comando.ComandoSolicitudMembresia;
import com.ceiba.membresia.modelo.entidad.TipoMembresia;

public class ComandoMembresiaTestDataBuilder {

    private Long idCliente;
    private TipoMembresia tipoMembresia;

    public ComandoMembresiaTestDataBuilder() {
    }

    public ComandoMembresiaTestDataBuilder crearPorDefecto() {
        this.idCliente = 1l;
        this.tipoMembresia = TipoMembresia.ORO;
        return this;
    }

    public ComandoSolicitudMembresia build() {
        return new ComandoSolicitudMembresia(this.idCliente,this.tipoMembresia == null ? null : this.tipoMembresia.toString());
    }
}
