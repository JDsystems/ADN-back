package com.ceiba.membresia.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.membresia.modelo.dto.ResumenMembresiaDTO;
import com.ceiba.membresia.puerto.dao.DaoMembresia;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoMembresiaPostgresql implements DaoMembresia {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoMembresiaActiva mapeoMembresiaActiva;

    @SqlStatement(namespace = "membresia",value = "obteneractivas")
    private static  String sqlObtenerActivas;

    public DaoMembresiaPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoMembresiaActiva mapeoMembresiaActiva) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoMembresiaActiva = mapeoMembresiaActiva;
    }

    @Override
    public List<ResumenMembresiaDTO> obtenerMembresiasActivas() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerActivas,mapeoMembresiaActiva);
    }
}
