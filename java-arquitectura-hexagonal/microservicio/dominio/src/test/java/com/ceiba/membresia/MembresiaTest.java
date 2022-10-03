package com.ceiba.membresia;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.cliente.entidad.TipoCliente;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.membresia.modelo.entidad.EstadoMembresia;
import com.ceiba.membresia.modelo.entidad.TipoMembresia;
import com.ceiba.membresia.servicio.ConstatesMembresia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MembresiaTest {

    private static final String MENSAJE = "Usted tiene una deuda pendiente por un valor de COP ";


    @Test
    void deberiaCrearMembresiaCorrectamente(){
        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .conTipoCliente(TipoCliente.COMUN)
                .reconstruir();

        var membresia = new MembresiaTestDataBuilder()
                .conCliente(cliente)
                .conTipoMembresia(TipoMembresia.BRONCE)
                .crear();

        Assertions.assertEquals(cliente,membresia.getCliente());
        Assertions.assertEquals(membresia.getValorPagado().longValue(), ConstatesMembresia.VALOR_MEMBRESIA_BRONCE);
        Assertions.assertTrue(membresia.esMembresiaBronce());
        Assertions.assertTrue(membresia.esActiva());
    }

    @Test
    void crearMembresiaSinClienteDeberiaLanzarError(){
        BasePrueba.assertThrows( () ->
                new MembresiaTestDataBuilder()
                        .conMembresiaPorDefecto()
                        .conCliente(null)
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El cliente es obligatorio"
        );
    }

    @Test
    void crearMembresiaSinTipoDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                new MembresiaTestDataBuilder()
                        .conMembresiaPorDefecto()
                        .conTipoMembresia(null)
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El tipo de membresia es obligatorio"
                );
    }

    @Test
    void deberiaRecosntruirLaMembresiaCorrectamente(){
        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .conTipoCliente(TipoCliente.COMUN)
                .reconstruir();

        var membresia = new MembresiaTestDataBuilder()
                .conId(1l)
                .conCliente(cliente)
                .conFechaInicio(LocalDate.now())
                .conFechaFin(LocalDate.now().plusDays(ConstatesMembresia.DIAS_DEL_MES))
                .conEstado(EstadoMembresia.CANCELADA)
                .conValorPagado(BigDecimal.valueOf(ConstatesMembresia.VALOR_MEMBRESIA_ORO))
                .conTipoMembresia(TipoMembresia.ORO)
                .conDiasHabiles(22l)
                .reconstruir();


        Assertions.assertEquals(cliente,membresia.getCliente());
        Assertions.assertEquals(ConstatesMembresia.VALOR_MEMBRESIA_ORO,membresia.getValorPagado().longValue());
        Assertions.assertTrue(membresia.esCancelada());
        Assertions.assertTrue(membresia.esMembresiaOro());
    }

    @Test
    void reconstruirMembresiaSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                new MembresiaTestDataBuilder()
                        .conMembresiaPorDefecto()
                        .conId(null)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El id es requerido"
                );
    }

    @Test
    void reconstruirMembresiaSinClienteDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conCliente(null)
                                .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El cliente es requerido"
        );
    }

    @Test
    void reconstruirMembresiaSinFechaInicioDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conFechaInicio(null)
                                .reconstruir(),
                ExcepcionValorObligatorio.class,
                "La fecha de inicio es requerida"
        );
    }

    @Test
    void reconstruirMembresiaSinFechafinDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conFechaFin(null)
                                .reconstruir(),
                ExcepcionValorObligatorio.class,
                "La fecha final es requerida"
        );
    }

    @Test
    void reconstruirMembresiaSinEstadoDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conEstado(null)
                                .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El estado es obligatorio"
        );
    }

    @Test
    void reconstruirMembresiaSinValorPagadoDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conValorPagado(null)
                                .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El valor pagado es obligatorio"
        );
    }

    @Test
    void reconstruirMembresiaSinTipoDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conTipoMembresia(null)
                                .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El tipo es obligatorio"
        );
    }

    @Test
    void reconstruirMembresiaSinDiasHabilesDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conDiasHabiles(null)
                                .reconstruir(),
                ExcepcionValorObligatorio.class,
                "Los dias habiles son obligatorios"
        );
    }

    @Test
    void reconstruirMembresiaConValorCeroOmenorDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conValorPagado(BigDecimal.valueOf(-1))
                                .reconstruir(),
                ExcepcionValorInvalido.class,
                "El valor pagado no puede ser menor a cero"
        );
    }

    @Test
    void deberiaCancelarLaMembresiaCorrectamente(){
        var membresia = new MembresiaTestDataBuilder()
                .conMembresiaPorDefecto()
                .crear();

        membresia.cancelar();

        Assertions.assertTrue(membresia.esCancelada());
    }

    @Test
    void cancelarMembresiaBronceConMenosDeCincoDiasPreviosALaFechaFinalDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conTipoMembresia(TipoMembresia.BRONCE)
                                .conFechaFin(LocalDate.now())
                                .reconstruir().cancelar(),
                ExcepcionValorInvalido.class,
                MENSAJE.concat(ConstatesMembresia.VALOR_MULTA_BRONCE+"")
        );
    }

    @Test
    void cancelarMembresiaPlataConMenosDeCincoDiasPreviosALaFechaFinalDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conTipoMembresia(TipoMembresia.PLATA)
                                .conFechaFin(LocalDate.now())
                                .reconstruir().cancelar(),
                ExcepcionValorInvalido.class,
                MENSAJE.concat(ConstatesMembresia.VALOR_MULTA_PLATA+"")
        );
    }

    @Test
    void cancelarMembresiaOroConMenosDeCincoDiasPreviosALaFechaFinalDeberiaLanzarError(){
        BasePrueba.assertThrows(() ->
                        new MembresiaTestDataBuilder()
                                .conMembresiaPorDefecto()
                                .conTipoMembresia(TipoMembresia.ORO)
                                .conFechaFin(LocalDate.now())
                                .reconstruir().cancelar(),
                ExcepcionValorInvalido.class,
                MENSAJE.concat(ConstatesMembresia.VALOR_MULTA_ORO+"")
        );
    }


}
