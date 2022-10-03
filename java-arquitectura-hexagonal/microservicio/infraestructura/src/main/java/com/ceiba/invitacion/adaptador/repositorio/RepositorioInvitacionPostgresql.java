package com.ceiba.invitacion.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.invitacion.modelo.entidad.Invitacion;
import com.ceiba.invitacion.puerto.repositorio.RepositorioInvitacion;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioInvitacionPostgresql implements RepositorioInvitacion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private MapeoInvitacion mapeoInvitacion;

    private  MapeoTotalInvitacionPorCliente mapeoTotalInvitacionPorCliente;

    @SqlStatement(namespace = "invitacion", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "invitacion", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "invitacion", value = "contarporcliente")
    private static String sqlContar;


    public RepositorioInvitacionPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoInvitacion mapeoInvitacion, MapeoTotalInvitacionPorCliente mapeoTotalInvitacionPorCliente) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoInvitacion = mapeoInvitacion;
        this.mapeoTotalInvitacionPorCliente = mapeoTotalInvitacionPorCliente;
    }

    @Override
    public Long guardar(Invitacion invitacion) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_membresia", invitacion.getMembresia().getId());
        parameterSource.addValue("indentificacion_invitado", invitacion.getIdentificacionInvitado());
        parameterSource.addValue("nombre_invitado", invitacion.getNombreInvitado());
        parameterSource.addValue("fecha", invitacion.getFecha());
        return  this.customNamedParameterJdbcTemplate.crear(parameterSource,sqlCrear);
    }

    @Override
    public Invitacion obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId,paramSource,mapeoInvitacion));
    }

    @Override
    public Object contar(Long idCliente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_cliente", idCliente);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlContar,paramSource,mapeoTotalInvitacionPorCliente));
    }
}
