package com.ceiba.cliente.adaptador.dao;

import com.ceiba.cliente.dto.ResumenClienteDTO;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoClientePostgresql implements DaoCliente {

    private final CustomNamedParameterJdbcTemplate jdbcTemplate;
    private final  MapeoCliente mapeoCliente;

    @SqlStatement(namespace = "cliente", value = "listartodos")
    private  static String obtenerClientes;

    public DaoClientePostgresql(CustomNamedParameterJdbcTemplate jdbcTemplate, MapeoCliente mapeoCliente) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeoCliente = mapeoCliente;
    }


    @Override
    public List<ResumenClienteDTO> obtenerClientes() {
        return this.jdbcTemplate.getNamedParameterJdbcTemplate()
                .query(obtenerClientes,mapeoCliente);
    }
}
