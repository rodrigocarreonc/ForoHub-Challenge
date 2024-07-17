package com.rodrigoc.ForoHub.Challenge.Domain.Topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long id_usuario,
        @NotBlank String nombre_curso) {
}
