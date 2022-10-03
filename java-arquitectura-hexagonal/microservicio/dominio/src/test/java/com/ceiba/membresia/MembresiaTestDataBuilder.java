package com.ceiba.membresia;

import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.membresia.modelo.entidad.EstadoMembresia;
import com.ceiba.membresia.modelo.entidad.Membresia;
import com.ceiba.membresia.modelo.entidad.TipoMembresia;
import com.ceiba.membresia.servicio.ConstatesMembresia;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class MembresiaTestDataBuilder {

    private Long id;

    private Cliente cliente;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private EstadoMembresia estado;

    private BigDecimal valorPagado;

    private TipoMembresia tipoMembresia;

    private  Long diasHabiles;


    public MembresiaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public MembresiaTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public MembresiaTestDataBuilder conFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public MembresiaTestDataBuilder conFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public MembresiaTestDataBuilder conEstado(EstadoMembresia estado) {
        this.estado = estado;
        return this;
    }


    public MembresiaTestDataBuilder conValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
        return this;
    }

    public MembresiaTestDataBuilder conTipoMembresia(TipoMembresia tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
        return this;
    }

    public MembresiaTestDataBuilder conDiasHabiles(Long diasHabiles) {
        this.diasHabiles = diasHabiles;
        return this;
    }


    public MembresiaTestDataBuilder conMembresiaPorDefecto() {
        this.id = 25l;
        this.cliente =  new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        this.fechaInicio = LocalDate.now();
        this.fechaFin = this.fechaInicio.plusDays(ConstatesMembresia.DIAS_DEL_MES);
        this.estado = EstadoMembresia.ACTIVA;
        this.valorPagado = BigDecimal.valueOf(ConstatesMembresia.VALOR_MEMBRESIA_PLATA);
        this.tipoMembresia = TipoMembresia.PLATA;
        this.diasHabiles = calcularDiasHabiles(fechaInicio,this.fechaFin);
        return this;
    }

    public Membresia crear(){
       return Membresia.crear(new SolicitudMembresiaTestDataBuilder()
                .conCliente(cliente)
                .conTipoMembresia(tipoMembresia == null ? null : tipoMembresia.toString())
                .build()
        );
    }


    public Membresia reconstruir(){
        return Membresia.reconstruir(id,cliente,fechaInicio,fechaFin,estado == null ? null: estado.toString(),valorPagado,tipoMembresia == null ? null: tipoMembresia.toString(),diasHabiles);
    }

    private Long calcularDiasHabiles(LocalDate fechaInicio, LocalDate fechaFin){
        LocalDate resultado = fechaInicio;
        long dias = 0;
        while (resultado.isBefore(fechaFin)){
            resultado = resultado.plusDays(1);
            if (!(resultado.getDayOfWeek() == DayOfWeek.SATURDAY || resultado.getDayOfWeek() == DayOfWeek.SUNDAY)){
                ++dias;
            }
        }
        return dias;
    }
}
