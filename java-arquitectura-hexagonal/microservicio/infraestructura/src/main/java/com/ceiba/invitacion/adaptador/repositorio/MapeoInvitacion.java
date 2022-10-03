package com.ceiba.invitacion.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.invitacion.modelo.entidad.Invitacion;
import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoInvitacion implements RowMapper<Invitacion>, MapperResult {


    private final RepositorioMembresia repositorioMembresia;

    public MapeoInvitacion(RepositorioMembresia repositorioMembresia) {
        this.repositorioMembresia = repositorioMembresia;
    }

    @Override
    public Invitacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var idMembresia = resultSet.getLong("id_membresia");
        var identificacionInvitado = resultSet.getString("indentificacion_invitado");
        var nombreInvitado = resultSet.getString("nombre_invitado");
        var fecha = resultSet.getDate("fecha").toLocalDate();

        var membresia = repositorioMembresia.obtener(idMembresia);
        return  Invitacion.reconstruir(id,membresia, identificacionInvitado,nombreInvitado,fecha);
    }

}
