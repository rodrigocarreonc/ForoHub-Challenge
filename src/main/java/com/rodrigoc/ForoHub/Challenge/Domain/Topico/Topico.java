package com.rodrigoc.ForoHub.Challenge.Domain.Topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fecha_crecion;
    private Long id_usuario;
    private String nombre_curso;
    private boolean activo;

    public Topico(DatosTopico datosTopico){
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.fecha_crecion = LocalDate.now(ZoneOffset.of("-05:00"));
        this.id_usuario = datosTopico.id_usuario();
        this.nombre_curso = datosTopico.nombre_curso();
        this.activo = true;
    }

    public void actualizarTopico(DatosActualizarT datosActualizarT){
        if(datosActualizarT.titulo() != null)
            this.titulo = datosActualizarT.titulo();
        if (datosActualizarT.mensaje() != null)
            this.mensaje = datosActualizarT.mensaje();
        if(datosActualizarT.nombreCurso() != null)
            this.nombre_curso = datosActualizarT.nombreCurso();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha_crecion() {
        return fecha_crecion;
    }

    public void setFecha_crecion(LocalDate fecha_crecion) {
        this.fecha_crecion = fecha_crecion;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void ocultarTopico(){
        this.activo = false;
    }
}
