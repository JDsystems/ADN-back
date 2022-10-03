package com.ceiba.membresia.modelo.entidad;

import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.membresia.servicio.ConstatesMembresia;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public final class Membresia {


    private Long id;

    private Cliente cliente;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private EstadoMembresia estado;

    private BigDecimal valorPagado;

    private TipoMembresia tipo;

    private Long diasHabiles;


    private Membresia(Long id, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin, EstadoMembresia estado, BigDecimal valorPagado, TipoMembresia tipo, Long diasHabiles) {
        this.id = id;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.valorPagado = valorPagado;
        this.tipo = tipo;
        this.diasHabiles = diasHabiles;
    }

    private Membresia(Cliente cliente, String tipo) {
        this.cliente = cliente;
        this.fechaInicio = LocalDate.now();
        this.tipo = TipoMembresia.valueOf(tipo);
        calcularValorTotal();
        this.fechaFin = calcularFechaFinal();
        this.diasHabiles = (long) calcularDiasHabiles();
        this.estado = EstadoMembresia.ACTIVA;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Long getIdCliente() {

        return cliente.getId();
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public EstadoMembresia getEstado() {
        return estado;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public TipoMembresia getTipo() {
        return tipo;
    }

    public Long getDiasHabiles() {
        return diasHabiles;
    }

    public boolean esMembresiaBronce(){
        return this.tipo.equals(TipoMembresia.BRONCE);
    }

    public boolean esMembresiaPlata(){
        return this.tipo.equals(TipoMembresia.PLATA);
    }

    public boolean esMembresiaOro(){
        return this.tipo.equals(TipoMembresia.ORO);
    }

    public boolean esActiva(){
        return this.estado.equals(EstadoMembresia.ACTIVA);
    }

    public boolean esCancelada(){
        return this.estado.equals(EstadoMembresia.CANCELADA);
    }

    private void calcularValorTotal(){
        if (this.esMembresiaBronce()){
            this.valorPagado = BigDecimal.valueOf(ConstatesMembresia.VALOR_MEMBRESIA_BRONCE);
        } else if (this.esMembresiaPlata()) {
            this.valorPagado = BigDecimal.valueOf(ConstatesMembresia.VALOR_MEMBRESIA_PLATA);
        } else if (this.esMembresiaOro()) {
            this.valorPagado = BigDecimal.valueOf(ConstatesMembresia.VALOR_MEMBRESIA_ORO);
        }
    }

    private LocalDate calcularFechaFinal(){
        LocalDate resultado = this.fechaInicio;
        int dias = 0;
        while (dias < ConstatesMembresia.DIAS_DEL_MES){
            resultado = resultado.plusDays(1);
            ++dias;
        }
        return resultado;
    }

    private int calcularDiasHabiles(){
        LocalDate resultado = this.fechaInicio;
        int dias = 0;
        while (resultado.isBefore(this.fechaFin)){
            resultado = resultado.plusDays(1);
            if (!(resultado.getDayOfWeek() == DayOfWeek.SATURDAY || resultado.getDayOfWeek() == DayOfWeek.SUNDAY)){
                ++dias;
            }
        }
        return dias;
    }

    private double calcularMulta(){
        double valorMulta = 0;
        if (this.esMembresiaBronce()){
            valorMulta = ConstatesMembresia.VALOR_MULTA_BRONCE;
        } else if (this.esMembresiaPlata()) {
            valorMulta = ConstatesMembresia.VALOR_MULTA_PLATA;
        } else if (this.esMembresiaOro()) {
            valorMulta = ConstatesMembresia.VALOR_MULTA_ORO;
        }
        return valorMulta;
    }

    public void cancelar(){
        double valorMulta = 0;
        LocalDate fecha = this.fechaFin.minusDays(ConstatesMembresia.DIAS_MAXIMOS_PERMITIDOS_PARA_CANCELACION);
        if (fecha.isBefore(LocalDate.now())){
            valorMulta  = calcularMulta();
            throw new ExcepcionValorInvalido("Usted tiene una deuda pendiente por un valor de COP "+valorMulta);
        }
        this.estado = EstadoMembresia.CANCELADA;
    }

    private static boolean existeValor(List lista,String valor){
        for (Object item : lista) {
            if (item.toString().equals(valor)){
                return true;
            }
        }
        return false;
    }

    public static Membresia crear(SolicitudMembresia solicitudMembresia){
        ValidadorArgumento.validarObligatorio(solicitudMembresia.getCliente(), "El cliente es obligatorio");
        ValidadorArgumento.validarObligatorio(solicitudMembresia.getTipoMembresia(), "El tipo de membresia es obligatorio");
        if (!existeValor(Arrays.stream(TipoMembresia.values()).toList(),solicitudMembresia.getTipoMembresia().toString())){
            throw new ExcepcionValorInvalido("El valor del tipo de membresia no es valido");
        }
        return new Membresia(solicitudMembresia.getCliente(),solicitudMembresia.getTipoMembresia());
    }

    public static Membresia reconstruir(Long id, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin, String estado, BigDecimal valorPagado, String tipo, Long diasHabiles){
        ValidadorArgumento.validarObligatorio(id, "El id es requerido");
        ValidadorArgumento.validarObligatorio(cliente, "El cliente es requerido");
        ValidadorArgumento.validarObligatorio(fechaInicio, "La fecha de inicio es requerida");
        ValidadorArgumento.validarObligatorio(fechaFin, "La fecha final es requerida");
        ValidadorArgumento.validarObligatorio(estado, "El estado es obligatorio");
        ValidadorArgumento.validarObligatorio(valorPagado, "El valor pagado es obligatorio");
        ValidadorArgumento.validarObligatorio(tipo, "El tipo es obligatorio");
        ValidadorArgumento.validarObligatorio(diasHabiles, "Los dias habiles son obligatorios");
        if (valorPagado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExcepcionValorInvalido("El valor pagado no puede ser menor a cero");
        }
        if (!existeValor(Arrays.stream(EstadoMembresia.values()).toList(),estado)){
            throw new ExcepcionValorInvalido("El valor del estado de membresia no es valido");
        }
        if (!existeValor(Arrays.stream(TipoMembresia.values()).toList(),tipo)){
            throw new ExcepcionValorInvalido("El valor del tipo de membresia no es valido");
        }

        return  new Membresia(id,cliente,fechaInicio,fechaFin,EstadoMembresia.valueOf(estado),valorPagado,TipoMembresia.valueOf(tipo),diasHabiles);
    }
}
