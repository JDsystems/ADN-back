package com.ceiba.invitacion.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoTotalInvitacionPorCliente implements RowMapper<Integer>, MapperResult {
    @Override
    public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return resultSet.getInt("total");
    }
}
