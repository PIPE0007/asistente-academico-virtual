package com.example.asistenteacademicovirtual.repository;

import com.example.asistenteacademicovirtual.model.Materia;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MateriaRepository implements IMateriaRepository {

    private final JdbcTemplate jdbcTemplate;

    public MateriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Materia guardar(Materia materia) {
        String sql = "INSERT INTO materia (nombre) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            return ps;
        }, keyHolder);

        materia.setIdMateria(keyHolder.getKey().intValue());
        return materia;
    }

    @Override
    public List<Materia> listarTodas() {
        String sql = "SELECT * FROM materia";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapearMateria(rs));
    }

    @Override
    public Materia buscarPorId(Integer id) {
        String sql = "SELECT * FROM materia WHERE idMateria = ?";
        List<Materia> resultado = jdbcTemplate.query(sql, (rs, rowNum) -> mapearMateria(rs), id);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    @Override
    public int actualizar(Integer id, Materia materia) {
        String sql = "UPDATE materia SET nombre = ? WHERE idMateria = ?";
        return jdbcTemplate.update(sql, materia.getNombre(), id);
    }

    @Override
    public int eliminar(Integer id) {
        String sql = "DELETE FROM materia WHERE idMateria = ?";
        return jdbcTemplate.update(sql, id);
    }

    private Materia mapearMateria(java.sql.ResultSet rs) throws java.sql.SQLException {
        Materia m = new Materia();
        m.setIdMateria(rs.getInt("idMateria"));
        m.setNombre(rs.getString("nombre"));
        return m;
    }
    @Override
    public boolean existePorNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM materia WHERE nombre = ?";
        Integer cantidad = jdbcTemplate.queryForObject(sql, Integer.class, nombre);
        return cantidad != null && cantidad > 0;
    }

    @Override
    public boolean tieneCompromisos(Integer id) {
        String sql = "SELECT COUNT(*) FROM compromiso WHERE idMateria = ?";
        Integer cantidad = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return cantidad != null && cantidad > 0;
    }
}
