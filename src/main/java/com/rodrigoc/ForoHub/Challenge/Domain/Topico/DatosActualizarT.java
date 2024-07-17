package com.rodrigoc.ForoHub.Challenge.Domain.Topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarT(@NotNull Long id,
        String titulo,
        String mensaje,
        String nombreCurso) {
}
