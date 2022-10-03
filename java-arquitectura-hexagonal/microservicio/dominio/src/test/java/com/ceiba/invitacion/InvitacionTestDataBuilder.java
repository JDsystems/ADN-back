package com.ceiba.invitacion;

import com.ceiba.invitacion.modelo.entidad.Invitacion;
import com.ceiba.membresia.MembresiaTestDataBuilder;
import com.ceiba.membresia.modelo.entidad.Membresia;

import java.time.LocalDate;

public class InvitacionTestDataBuilder {

    private Long id;


    private Membresia membresia;

    private String identificacionInvitado;

    private String nombreInvitado;

    private LocalDate fecha;

    public InvitacionTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public InvitacionTestDataBuilder conMembresia(Membresia membresia) {
        this.membresia = membresia;
        return this;
    }

    public InvitacionTestDataBuilder conIdentificacionInvitado(String identificacionInvitado) {
        this.identificacionInvitado = identificacionInvitado;
        return this;
    }

    public InvitacionTestDataBuilder conNombreInvitado(String nombreInvitado) {
        this.nombreInvitado = nombreInvitado;
        return this;
    }

    public InvitacionTestDataBuilder confecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public InvitacionTestDataBuilder conInvitacionPordefecto() {
        this.id = 20l;
        this.membresia = new MembresiaTestDataBuilder().conMembresiaPorDefecto().reconstruir();
        this.identificacionInvitado = "12345678";
        this.nombreInvitado = "Invitado 1";
        this.fecha = LocalDate.now();
        return this;
    }

    public Invitacion reconstruir(){
        return Invitacion.reconstruir(id,membresia,identificacionInvitado,nombreInvitado,fecha);
    }

    public Invitacion crear(){
        return Invitacion.crear(new SolicitudInvitacionTestDataBuilder()
                .conMembresia(membresia)
                .conIdentificacionInvitado(identificacionInvitado)
                .conNombreInvitado(nombreInvitado)
                .build()
        );
    }
}
