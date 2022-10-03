package com.ceiba.cliente.adaptador.dao;

import com.ceiba.cliente.dto.ResumenClienteDTO;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoCliente implements RowMapper<ResumenClienteDTO>, MapperResult {
    @Override
    public ResumenClienteDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var nombre = resultSet.getString("nombre");
        return new ResumenClienteDTO(id, nombre);
    }
}
