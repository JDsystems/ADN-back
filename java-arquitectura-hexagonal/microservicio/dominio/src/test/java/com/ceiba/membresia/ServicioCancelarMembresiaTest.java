package com.ceiba.membresia;

import com.ceiba.membresia.modelo.entidad.EstadoMembresia;
import com.ceiba.membresia.modelo.entidad.Membresia;
import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import com.ceiba.membresia.servicio.ConstatesMembresia;
import com.ceiba.membresia.servicio.ServicioCancelar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;

public class ServicioCancelarMembresiaTest {

    @Test
    void deberiaCancelarMembresiaExitosamente(){
        var repositorioMembresia = Mockito.mock(RepositorioMembresia.class);
        Mockito.when(repositorioMembresia.guardar(Mockito.any())).thenReturn(1l);

        var membresia = new MembresiaTestDataBuilder()
                .conMembresiaPorDefecto()
                .conEstado(EstadoMembresia.ACTIVA)
                .conFechaInicio(LocalDate.now())
                .conFechaFin(LocalDate.now().plusDays(ConstatesMembresia.DIAS_DEL_MES))
                .reconstruir();

        var SolicitudCancelar = new SolicitudCancelarMembresiaTestDataBuilder()
                .conMembresia(membresia)
                .build();

        var servicioCancelar = new ServicioCancelar(repositorioMembresia);

        servicioCancelar.ejecutar(SolicitudCancelar);

        ArgumentCaptor<Membresia> captorMembresia = ArgumentCaptor.forClass(Membresia.class);
        Mockito.verify(repositorioMembresia,Mockito.times(1)).actualizarEstado(captorMembresia.capture());

        Assertions.assertTrue(captorMembresia.getValue().esCancelada());
    }


}
