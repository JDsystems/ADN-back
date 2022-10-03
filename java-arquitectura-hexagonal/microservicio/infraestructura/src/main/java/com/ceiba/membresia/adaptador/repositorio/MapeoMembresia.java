package com.ceiba.membresia.adaptador.repositorio;

import com.ceiba.cliente.puerto.RepositorioCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.membresia.modelo.entidad.Membresia;
import com.ceiba.membresia.modelo.entidad.TipoMembresia;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoMembresia implements RowMapper<Membresia>, MapperResult {

    private final RepositorioCliente repositorioCliente;

    public MapeoMembresia(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    @Override
    public Membresia mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var idCliente = resultSet.getLong("id_cliente");
        var fechaInicio = resultSet.getDate("fecha_inicio").toLocalDate();
        var fechaFin = resultSet.getDate("fecha_fin").toLocalDate();
        var estado = resultSet.getString("estado");
        var valorPagado = resultSet.getBigDecimal("valor_pagado");
        var tipo = resultSet.getString("tipo");
        var diasHabiles = resultSet.getLong("dias_habiles");
        return Membresia.reconstruir(id,repositorioCliente.obtener(idCliente),fechaInicio,fechaFin,estado,valorPagado,tipo,diasHabiles);
    }
}
