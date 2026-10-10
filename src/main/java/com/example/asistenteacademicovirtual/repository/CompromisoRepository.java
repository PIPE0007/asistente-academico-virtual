package com.example.asistenteacademicovirtual.repository;

import com.example.asistenteacademicovirtual.model.Compromiso;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CompromisoRepository implements ICompromisoRepository {

    private final JdbcTemplate jdbcTemplate;

    public CompromisoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    @Override
    public Compromiso guardar(Compromiso compromiso) {
        String sql = "INSERT INTO compromiso (titulo, descripcion, fechaLimite, prioridad, estado, idEstudiante, idMateria) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, compromiso.getTitulo());
            ps.setString(2, compromiso.getDescripcion());
            ps.setDate(3, java.sql.Date.valueOf(compromiso.getFechaLimite()));
            ps.setString(4, compromiso.getPrioridad());
            ps.setString(5, compromiso.getEstado());
            ps.setInt(6, compromiso.getIdEstudiante());
            ps.setInt(7, compromiso.getIdMateria());
            return ps;
        }, keyHolder);

        compromiso.setIdCompromiso(keyHolder.getKey().intValue());
        return compromiso;
    }

    // READ - todos
    @Override
    public List<Compromiso> listarTodos() {
        String sql = "SELECT * FROM compromiso";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapearCompromiso(rs));
    }

    // READ - por id
    @Override
    public Compromiso buscarPorId(Integer id) {
        String sql = "SELECT * FROM compromiso WHERE idCompromiso = ?";
        List<Compromiso> resultado = jdbcTemplate.query(sql, (rs, rowNum) -> mapearCompromiso(rs), id);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    // UPDATE
    @Override
    public int actualizar(Integer id, Compromiso compromiso) {
        String sql = "UPDATE compromiso SET titulo = ?, descripcion = ?, fechaLimite = ?, prioridad = ?, " +
                "estado = ?, idEstudiante = ?, idMateria = ? WHERE idCompromiso = ?";
        return jdbcTemplate.update(sql,
                compromiso.getTitulo(),
                compromiso.getDescripcion(),
                compromiso.getFechaLimite(),
                compromiso.getPrioridad(),
                compromiso.getEstado(),
                compromiso.getIdEstudiante(),
                compromiso.getIdMateria(),
                id);
    }

    // DELETE (opcional, lo pide la rúbrica si se puede)
    @Override
    public int eliminar(Integer id) {
        String sql = "DELETE FROM compromiso WHERE idCompromiso = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Método auxiliar para mapear una fila del ResultSet a un objeto Compromiso
    private Compromiso mapearCompromiso(java.sql.ResultSet rs) throws java.sql.SQLException {
        Compromiso c = new Compromiso();
        c.setIdCompromiso(rs.getInt("idCompromiso"));
        c.setTitulo(rs.getString("titulo"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setFechaLimite(rs.getDate("fechaLimite").toLocalDate());
        c.setPrioridad(rs.getString("prioridad"));
        c.setEstado(rs.getString("estado"));
        c.setIdEstudiante(rs.getInt("idEstudiante"));
        c.setIdMateria(rs.getInt("idMateria"));
        return c;
    }
    @Override
    public List<Compromiso> buscarPorEstado(String estado) {
        String sql = "SELECT * FROM compromiso WHERE estado = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapearCompromiso(rs), estado);
    }

    @Override
    public int actualizarEstado(Integer id, String estado) {
        String sql = "UPDATE compromiso SET estado = ? WHERE idCompromiso = ?";
        return jdbcTemplate.update(sql, estado, id);
    }
}