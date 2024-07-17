package com.rodrigoc.ForoHub.Challenge.Domain.Topico;

import java.time.LocalDate;
import java.util.Date;

public record DatosRespuestaT(
        Long id_usuario,
        String titulo,
        String mensaje,
        String nombre_curso,
        LocalDate fecha_creacion
) {
}
