package com.ceiba.membresia;

import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.membresia.modelo.entidad.Membresia;
import com.ceiba.membresia.modelo.entidad.TipoMembresia;
import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import com.ceiba.membresia.servicio.ConstatesMembresia;
import com.ceiba.membresia.servicio.ServicioCrear;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ServicioCrearMembresiaTest {

    @Test
    void deberiaGenerarMembresiaYGuaradr(){
        var cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        var solicituMembresia = new SolicitudMembresiaTestDataBuilder()
                .conCliente(cliente)
                .conTipoMembresia(TipoMembresia.ORO.name())
                .build();


        var respositorioMembresia = Mockito.mock(RepositorioMembresia.class);
        Mockito.when(respositorioMembresia.guardar(Mockito.any())).thenReturn(1l);

        var servicioMembresia = new ServicioCrear(respositorioMembresia);

        var idMembresiaGuardada = servicioMembresia.ejecutar(solicituMembresia);

        ArgumentCaptor<Membresia> captorMembresia = ArgumentCaptor.forClass(Membresia.class);
        Mockito.verify(respositorioMembresia,Mockito.times(1)).guardar(captorMembresia.capture());

        Assertions.assertEquals(cliente, captorMembresia.getValue().getCliente());
        Assertions.assertEquals(ConstatesMembresia.VALOR_MEMBRESIA_ORO, captorMembresia.getValue().getValorPagado().longValue());
        Assertions.assertEquals(1l,idMembresiaGuardada);
        Assertions.assertTrue(captorMembresia.getValue().esMembresiaOro());
        Assertions.assertTrue(captorMembresia.getValue().esActiva());
    }
}
