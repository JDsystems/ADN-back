package com.ceiba.invitacion.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.membresia.modelo.entidad.Membresia;

import java.time.LocalDate;

public final class Invitacion {


    private Long id;

    private Membresia membresia;

    private String identificacionInvitado;

    private String nombreInvitado;

    private LocalDate fecha;

    public Long getId() {
        return id;
    }


    public Membresia getMembresia() {
        return membresia;
    }

    public String getIdentificacionInvitado() {
        return identificacionInvitado;
    }

    public String getNombreInvitado() {
        return nombreInvitado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    private Invitacion(Membresia membresia, String identificacionInvitado, String nombreInvitado) {
        this.membresia = membresia;
        this.identificacionInvitado = identificacionInvitado;
        this.nombreInvitado = nombreInvitado;
        this.fecha = LocalDate.now();
    }

    private Invitacion(Long id,Membresia membresia, String identificacionInvitado, String nombreInvitado, LocalDate fecha) {
        this.id = id;
        this.membresia = membresia;
        this.identificacionInvitado = identificacionInvitado;
        this.nombreInvitado = nombreInvitado;
        this.fecha = fecha;
    }

    public static Invitacion crear(SolicitudInvitar solicitudInvitar){
        ValidadorArgumento.validarObligatorio(solicitudInvitar.getIdentificacionInvitado(),"La identificacion de invitado es obligatoria");
        ValidadorArgumento.validarVacio(solicitudInvitar.getIdentificacionInvitado(),"La identificacion de invitado es obligatoria");
        ValidadorArgumento.validarObligatorio(solicitudInvitar.getNombreInvitado(),"El nombre de invitado es obligatorio");
        ValidadorArgumento.validarVacio(solicitudInvitar.getNombreInvitado(),"El nombre de invitado es obligatorio");
        return new Invitacion(solicitudInvitar.getMembresia(),solicitudInvitar.getIdentificacionInvitado(),solicitudInvitar.getNombreInvitado());
    }


    public static Invitacion reconstruir(Long id, Membresia membresia, String identificacionInvitado, String nombreInvitado, LocalDate fecha) {
        System.out.println(membresia);
        ValidadorArgumento.validarObligatorio(id, "El id es obligatorio");
        ValidadorArgumento.validarObligatorio(membresia,"La membresia es obligatoria");
        ValidadorArgumento.validarObligatorio(identificacionInvitado,"La identificacion de invitado es obligatoria");
        ValidadorArgumento.validarObligatorio(nombreInvitado,"El nombre de invitado es obligatorio");
        ValidadorArgumento.validarObligatorio(fecha, "La fecha es obligatoria");
        return new Invitacion(id,membresia,identificacionInvitado,nombreInvitado,fecha);
    }
}
