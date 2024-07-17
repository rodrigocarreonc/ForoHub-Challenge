package com.rodrigoc.ForoHub.Challenge.Domain.Topico;

import java.time.LocalDate;

public record DatosListadoTopico(Long id, String titulo, String mensaje, LocalDate fecha_crecion) {
    public DatosListadoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_crecion());
    }
}
