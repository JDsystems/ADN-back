package com.ceiba.invitacion.servicio;
  public final class ConstantesInvitacion {

     public static final long DIAS_INVITACION_BRONCE = 1;
     public static final long DIAS_INVITACION_PLATA = 5;
     public static final long DIAS_INVITACION_ORO = 15;

     public static final String MENSAJE_ERROR_INVITAR = "Cliente no cuenta con dias disponible para invitar amigos";
     private ConstantesInvitacion() {
         throw new UnsupportedOperationException();
     }
}
