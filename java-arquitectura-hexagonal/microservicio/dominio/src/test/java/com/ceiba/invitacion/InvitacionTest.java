package com.ceiba.invitacion;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.membresia.MembresiaTestDataBuilder;
import com.ceiba.membresia.modelo.entidad.Membresia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class InvitacionTest {

    @Test
    void deberiaCrearInvitacionCorrectamente(){
        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        Membresia membresia = new MembresiaTestDataBuilder()
                .conMembresiaPorDefecto()
                .reconstruir();


        var invitacion = new InvitacionTestDataBuilder()
                .conMembresia(membresia)
                .conIdentificacionInvitado("1102378654")
                .conNombreInvitado("Invitado 2")
                .crear();

        Assertions.assertEquals(membresia,invitacion.getMembresia());
        Assertions.assertEquals(LocalDate.now(),invitacion.getFecha());
    }

    @Test
    void crearInvitacionSinMembresiaDeberiaLanzarError(){

        BasePrueba.assertThrows(() -> new InvitacionTestDataBuilder()
                .conInvitacionPordefecto()
                .conMembresia(null)
                .crear(),
                ExcepcionValorObligatorio.class,
                "La membresia es obligatoria"
        );

    }

    @Test
    void crearInvitacionSinIdentificacionInvitadoDeberiaLanzarError(){
        BasePrueba.assertThrows(()-> new InvitacionTestDataBuilder()
                .conInvitacionPordefecto()
                .conIdentificacionInvitado(null)
                .crear(),
                ExcepcionValorObligatorio.class,
                "La identificacion de invitado es obligatoria"
        );
    }

    @Test
    void crearInvitacionSinNombreInvitadoDeberiaLanzarError(){
        BasePrueba.assertThrows(()-> new InvitacionTestDataBuilder()
                .conInvitacionPordefecto()
                .conNombreInvitado(null)
                .crear(),
                ExcepcionValorObligatorio.class,
                "El nombre de invitado es obligatorio"
        );
    }

    @Test
    void deberiaReconstruirLaInvitacionCorrectamente(){
        var membresia = new MembresiaTestDataBuilder().conMembresiaPorDefecto().reconstruir();

        var invitacion = new InvitacionTestDataBuilder()
                .conId(1l)
                .conMembresia(membresia)
                .conIdentificacionInvitado("1108724698")
                .conNombreInvitado("Leonel Messi")
                .confecha(LocalDate.now())
                .reconstruir();


        Assertions.assertEquals(membresia, invitacion.getMembresia());
        Assertions.assertEquals(LocalDate.now(),invitacion.getFecha());
    }

    @Test
    void reconstruirInvitacionSinIdDeberiaLanzarError(){

        BasePrueba.assertThrows(() -> new InvitacionTestDataBuilder()
                        .conInvitacionPordefecto()
                        .conId(null)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El id es obligatorio"
        );

    }

    @Test
    void reconstruirInvitacionSinMembresiaDeberiaLanzarError(){

        BasePrueba.assertThrows(() -> new InvitacionTestDataBuilder()
                        .conInvitacionPordefecto()
                        .conMembresia(null)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "La membresia es obligatoria"
        );
    }

    @Test
    void reconstruirInvitacionSinIdentificacionInvitadoDeberiaLanzarError(){

        BasePrueba.assertThrows(() -> new InvitacionTestDataBuilder()
                        .conInvitacionPordefecto()
                        .conIdentificacionInvitado(null)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "La identificacion de invitado es obligatoria"
        );
    }

    @Test
    void reconstruirInvitacionSinNombreInvitadoDeberiaLanzarError(){

        BasePrueba.assertThrows(() -> new InvitacionTestDataBuilder()
                        .conInvitacionPordefecto()
                        .conNombreInvitado(null)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El nombre de invitado es obligatorio"
        );
    }

    @Test
    void reconstruirInvitacionSinFechaDeberiaLanzarError(){

        BasePrueba.assertThrows(() -> new InvitacionTestDataBuilder()
                        .conInvitacionPordefecto()
                        .confecha(null)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "La fecha es obligatoria"
        );
    }
}
