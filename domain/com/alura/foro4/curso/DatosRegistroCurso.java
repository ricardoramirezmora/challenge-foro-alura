package com.alura.foro4.curso;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso( @NotNull String nombreCurso, @NotNull String categoria) { 

}
