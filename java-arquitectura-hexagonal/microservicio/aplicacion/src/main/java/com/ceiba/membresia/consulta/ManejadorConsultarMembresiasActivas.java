package com.ceiba.membresia.consulta;

import com.ceiba.membresia.modelo.dto.ResumenMembresiaDTO;
import com.ceiba.membresia.puerto.dao.DaoMembresia;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarMembresiasActivas {
    private  final DaoMembresia daoMembresia;

    public ManejadorConsultarMembresiasActivas(DaoMembresia daoMembresia) {
        this.daoMembresia = daoMembresia;
    }

    public List<ResumenMembresiaDTO> ejecutar(){
        return daoMembresia.obtenerMembresiasActivas();
    }
}
