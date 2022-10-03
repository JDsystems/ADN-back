package com.ceiba.membresia.adaptador.dao;

import com.ceiba.cliente.dto.ResumenClienteDTO;
import com.ceiba.cliente.puerto.RepositorioCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.membresia.modelo.dto.ResumenMembresiaDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoMembresiaActiva implements RowMapper<ResumenMembresiaDTO>, MapperResult {

    private final RepositorioCliente repositorioCliente;


    public MapeoMembresiaActiva(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    @Override
    public ResumenMembresiaDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var cliente = repositorioCliente.obtener(resultSet.getLong("id_cliente"));
        var fechaInicio = resultSet.getString("fecha_inicio");
        var fechaFin = resultSet.getString("fecha_fin");
        var tipo = resultSet.getString("tipo");
        var resumenClienteDTO = new ResumenClienteDTO(cliente.getId(), cliente.getNombre());
        return new ResumenMembresiaDTO(id,resumenClienteDTO,fechaInicio,fechaFin,tipo);
    }


}
