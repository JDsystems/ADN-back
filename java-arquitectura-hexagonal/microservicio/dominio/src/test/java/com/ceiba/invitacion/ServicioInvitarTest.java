package com.ceiba.invitacion;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.invitacion.modelo.entidad.Invitacion;
import com.ceiba.invitacion.puerto.repositorio.RepositorioInvitacion;
import com.ceiba.invitacion.servicio.ConstantesInvitacion;
import com.ceiba.invitacion.servicio.ServicioInvitar;
import com.ceiba.membresia.MembresiaTestDataBuilder;
import com.ceiba.membresia.modelo.entidad.TipoMembresia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ServicioInvitarTest {

    @Test
    void deberiaCrearYGuardarLaInvitacionCorrectamente(){
        var membresia = new MembresiaTestDataBuilder()
                .conMembresiaPorDefecto()
                .conTipoMembresia(TipoMembresia.ORO)
                .reconstruir();

        var solicitudInvitacion = new SolicitudInvitacionTestDataBuilder()
                .conMembresia(membresia)
                .conIdentificacionInvitado("1103568900")
                .conNombreInvitado("Invitado por defecto")
                .build();

        var repositorioInvitacion = Mockito.mock(RepositorioInvitacion.class);
        Mockito.when(repositorioInvitacion.guardar(Mockito.any())).thenReturn(1l);

        var repositorioInvitacion1 = Mockito.mock(RepositorioInvitacion.class);
        Mockito.when(repositorioInvitacion.contar(Mockito.any())).thenReturn(ConstantesInvitacion.DIAS_INVITACION_ORO-1l);


        var servicioInvitacion = new ServicioInvitar(repositorioInvitacion);

        var idInvitacionGuardada = servicioInvitacion.ejecutar(solicitudInvitacion);

        ArgumentCaptor<Invitacion> captorInvitacion = ArgumentCaptor.forClass(Invitacion.class);
        Mockito.verify(repositorioInvitacion,Mockito.times(1)).guardar(captorInvitacion.capture());

        Assertions.assertEquals(membresia,captorInvitacion.getValue().getMembresia());
        Assertions.assertEquals(1l,idInvitacionGuardada);
    }


    @Test
    void crearInvitacionClienteConMembresiaBronceSinDiasDisponiblesParaInvitadosDeberiaLanzarError(){

        var membresia = new MembresiaTestDataBuilder()
                .conMembresiaPorDefecto()
                .conTipoMembresia(TipoMembresia.BRONCE)
                .reconstruir();

        var solicitudInvitacion = new SolicitudInvitacionTestDataBuilder()
                .conMembresia(membresia)
                .conIdentificacionInvitado("1103568901")
                .conNombreInvitado("Invitado bronce")
                .build();

        var repositorioInvitacion = Mockito.mock(RepositorioInvitacion.class);
        Mockito.when(repositorioInvitacion.guardar(Mockito.any())).thenReturn(1l);

        var repositorioInvitacion1 = Mockito.mock(RepositorioInvitacion.class);
        Mockito.when(repositorioInvitacion.contar(Mockito.any())).thenReturn(ConstantesInvitacion.DIAS_INVITACION_BRONCE);


        var servicioInvitacion = new ServicioInvitar(repositorioInvitacion);
        BasePrueba.assertThrows(() ->
                        servicioInvitacion.ejecutar(solicitudInvitacion),
                ExcepcionValorInvalido.class,
                ConstantesInvitacion.MENSAJE_ERROR_INVITAR
        );
    }

    @Test
    void crearInvitacionClienteConMembresiaPlataSinDiasDisponiblesParaInvitadosDeberiaLanzarError(){

        var membresia = new MembresiaTestDataBuilder()
                .conMembresiaPorDefecto()
                .conTipoMembresia(TipoMembresia.PLATA)
                .reconstruir();

        var solicitudInvitacion = new SolicitudInvitacionTestDataBuilder()
                .conMembresia(membresia)
                .conIdentificacionInvitado("1103568902")
                .conNombreInvitado("Invitado plata")
                .build();

        var repositorioInvitacion = Mockito.mock(RepositorioInvitacion.class);
        Mockito.when(repositorioInvitacion.guardar(Mockito.any())).thenReturn(1l);

        var repositorioInvitacion1 = Mockito.mock(RepositorioInvitacion.class);
        Mockito.when(repositorioInvitacion.contar(Mockito.any())).thenReturn(ConstantesInvitacion.DIAS_INVITACION_PLATA);


        var servicioInvitacion = new ServicioInvitar(repositorioInvitacion);
        BasePrueba.assertThrows(() ->
                        servicioInvitacion.ejecutar(solicitudInvitacion),
                ExcepcionValorInvalido.class,
                ConstantesInvitacion.MENSAJE_ERROR_INVITAR
        );
    }

    @Test
    void crearInvitacionClienteConMembresiaOroSinDiasDisponiblesParaInvitadosDeberiaLanzarError(){

        var membresia = new MembresiaTestDataBuilder()
                .conMembresiaPorDefecto()
                .conTipoMembresia(TipoMembresia.ORO)
                .reconstruir();

        var solicitudInvitacion = new SolicitudInvitacionTestDataBuilder()
                .conMembresia(membresia)
                .conIdentificacionInvitado("1103568903")
                .conNombreInvitado("Invitado oro")
                .build();

        var repositorioInvitacion = Mockito.mock(RepositorioInvitacion.class);
        Mockito.when(repositorioInvitacion.guardar(Mockito.any())).thenReturn(1l);

        var repositorioInvitacion1 = Mockito.mock(RepositorioInvitacion.class);
        Mockito.when(repositorioInvitacion.contar(Mockito.any())).thenReturn(ConstantesInvitacion.DIAS_INVITACION_ORO);


        var servicioInvitacion = new ServicioInvitar(repositorioInvitacion);
        BasePrueba.assertThrows(() ->
                        servicioInvitacion.ejecutar(solicitudInvitacion),
                ExcepcionValorInvalido.class,
                ConstantesInvitacion.MENSAJE_ERROR_INVITAR
        );
    }
}
