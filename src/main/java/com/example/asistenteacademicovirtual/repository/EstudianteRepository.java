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
public class EstudianteRepository implements IEstudianteRepository {

    private final JdbcTemplate jdbcTemplate;

    public EstudianteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
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

    @Override
    public List<Estudiante> listarTodos() {
        String sql = "SELECT * FROM estudiante";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapearEstudiante(rs));
    }

    @Override
    public Estudiante buscarPorId(Integer id) {
        String sql = "SELECT * FROM estudiante WHERE idEstudiante = ?";
        List<Estudiante> resultado = jdbcTemplate.query(sql, (rs, rowNum) -> mapearEstudiante(rs), id);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    @Override
    public int actualizar(Integer id, Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre = ?, correo = ? WHERE idEstudiante = ?";
        return jdbcTemplate.update(sql, estudiante.getNombre(), estudiante.getCorreo(), id);
    }

    @Override
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
    @Override
    public boolean existePorCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM estudiante WHERE correo = ?";
        Integer cantidad = jdbcTemplate.queryForObject(sql, Integer.class, correo);
        return cantidad != null && cantidad > 0;
    }
}