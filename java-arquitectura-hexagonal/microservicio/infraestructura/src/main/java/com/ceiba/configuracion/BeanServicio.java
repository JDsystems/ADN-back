package com.ceiba.configuracion;

import com.ceiba.invitacion.puerto.repositorio.RepositorioInvitacion;
import com.ceiba.invitacion.servicio.ServicioInvitar;
import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import com.ceiba.membresia.servicio.ServicioCancelar;
import com.ceiba.membresia.servicio.ServicioCrear;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrear servicioCrear(RepositorioMembresia repositorioMembresia){
        return new ServicioCrear(repositorioMembresia);
    }

    @Bean
    public ServicioCancelar servicioCancelar(RepositorioMembresia repositorioMembresia){
        return new ServicioCancelar(repositorioMembresia);
    }

    @Bean
    public ServicioInvitar servicioInvitar(RepositorioInvitacion repositorioInvitacion){
        return new ServicioInvitar(repositorioInvitacion);
    }


}
