package com.example.asistenteacademicovirtual.model;

import  java.time.LocalDate;

public class Compromiso {
    private Integer idCompromiso;
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private String prioridad;
    private String estado;
    private Integer idEstudiante;
    private Integer idMateria;

    public Compromiso() {}

    public Compromiso(Integer idCompromiso, String titulo, String descripcion, LocalDate fechaLimite,
                      String prioridad, String estado, Integer idEstudiante, Integer idMateria) {
        this.idCompromiso = idCompromiso;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.estado = estado;
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
    }

    public Integer getIdCompromiso() { return idCompromiso; }
    public void setIdCompromiso(Integer idCompromiso) { this.idCompromiso = idCompromiso; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Integer idEstudiante) { this.idEstudiante = idEstudiante; }

    public Integer getIdMateria() { return idMateria; }
    public void setIdMateria(Integer idMateria) { this.idMateria = idMateria; }
}
