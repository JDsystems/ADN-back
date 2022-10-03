package com.ceiba.membresia.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.membresia.modelo.entidad.Membresia;
import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioMembresiaPostgresql implements RepositorioMembresia {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private  final MapeoMembresia mapeoMembresia;


    @SqlStatement(namespace = "membresia", value = "crear")
    private static String sqlCrear;


    @SqlStatement(namespace = "membresia", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "membresia", value = "actualizarestado")
    private static  String sqlActualizarEstado;

    public RepositorioMembresiaPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoMembresia mapeoMembresia) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoMembresia = mapeoMembresia;
    }


    @Override
    public Long guardar(Membresia membresia) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_cliente", membresia.getCliente().getId());
        parameterSource.addValue("fecha_inicio", membresia.getFechaInicio());
        parameterSource.addValue("fecha_fin", membresia.getFechaFin());
        parameterSource.addValue("estado", membresia.getEstado().toString());
        parameterSource.addValue("valor_pagado", membresia.getValorPagado());
        parameterSource.addValue("tipo", membresia.getTipo().toString());
        parameterSource.addValue("dias_habiles", membresia.getDiasHabiles());
        this.customNamedParameterJdbcTemplate.crear(parameterSource,sqlCrear);
        return  membresia.getDiasHabiles();
    }

    @Override
    public Membresia obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return  EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId, paramSource, mapeoMembresia));

    }

    @Override
    public void actualizarEstado(Membresia membresia) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", membresia.getId());
        paramSource.addValue("estado", membresia.getEstado().toString());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarEstado,paramSource);
    }
}
