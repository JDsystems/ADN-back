package com.ceiba.membresia.servicio;

 public final class ConstatesMembresia {

    public static final long VALOR_MEMBRESIA_BRONCE = 50000;
    public static final long VALOR_MEMBRESIA_PLATA = 70000;
    public static final long VALOR_MEMBRESIA_ORO = 90000;

    public static final double PORCENTAJE_MULTA_MEMBRESIA_BRONCE = 0.03;
    public static final double PORCENTAJE_MULTA_MEMBRESIA_PLATA = 0.04;
    public static final double PORCENTAJE_MULTA_MEMBRESIA_ORO = 0.05;

    public static final long DIAS_DEL_MES = 30;

    public static final long DIAS_MAXIMOS_PERMITIDOS_PARA_CANCELACION = 5;


    public static final double VALOR_MULTA_BRONCE = VALOR_MEMBRESIA_BRONCE * PORCENTAJE_MULTA_MEMBRESIA_BRONCE;
    public static final double VALOR_MULTA_PLATA = VALOR_MEMBRESIA_PLATA * PORCENTAJE_MULTA_MEMBRESIA_PLATA;
    public static final double VALOR_MULTA_ORO = VALOR_MEMBRESIA_ORO * PORCENTAJE_MULTA_MEMBRESIA_ORO;
    private ConstatesMembresia() {
        throw new UnsupportedOperationException();
    }

}
