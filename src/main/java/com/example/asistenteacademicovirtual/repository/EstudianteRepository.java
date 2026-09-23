package com.example.asistenteacademicovirtual.repository;

import com.example.asistenteacademicovirtual.model.Estudiante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EstudianteRepository {

    private final JdbcTemplate jdbcTemplate;

    public EstudianteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Estudiante guardar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante (nombre, correo) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getCorreo());
            return ps;
        }, keyHolder);

        estudiante.setIdEstudiante(keyHolder.getKey().intValue());
        return estudiante;
    }

    public List<Estudiante> listarTodos() {
        String sql = "SELECT * FROM estudiante";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapearEstudiante(rs));
    }

    public Estudiante buscarPorId(Integer id) {
        String sql = "SELECT * FROM estudiante WHERE idEstudiante = ?";
        List<Estudiante> resultado = jdbcTemplate.query(sql, (rs, rowNum) -> mapearEstudiante(rs), id);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public int actualizar(Integer id, Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre = ?, correo = ? WHERE idEstudiante = ?";
        return jdbcTemplate.update(sql, estudiante.getNombre(), estudiante.getCorreo(), id);
    }

    public int eliminar(Integer id) {
        String sql = "DELETE FROM estudiante WHERE idEstudiante = ?";
        return jdbcTemplate.update(sql, id);
    }

    private Estudiante mapearEstudiante(java.sql.ResultSet rs) throws java.sql.SQLException {
        Estudiante e = new Estudiante();
        e.setIdEstudiante(rs.getInt("idEstudiante"));
        e.setNombre(rs.getString("nombre"));
        e.setCorreo(rs.getString("correo"));
        return e;
    }
}