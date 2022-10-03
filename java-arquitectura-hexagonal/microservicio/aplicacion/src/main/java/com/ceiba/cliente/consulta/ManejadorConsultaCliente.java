package com.ceiba.cliente.consulta;

import com.ceiba.cliente.dto.ResumenClienteDTO;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultaCliente {

    private final DaoCliente daoCliente;

    public ManejadorConsultaCliente(DaoCliente daoCliente) {
        this.daoCliente = daoCliente;
    }

    public List<ResumenClienteDTO> ejecutar(){
        return daoCliente.obtenerClientes();
    }
}
