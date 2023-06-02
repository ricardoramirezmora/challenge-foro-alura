package com.alura.foro4.topico;

import com.alura.foro4.autor.DatosRegistroAutor;
import com.alura.foro4.curso.DatosRegistroCurso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(@NotBlank String titulo, @NotBlank String mensaje, @NotNull DatosRegistroAutor autor, @NotNull DatosRegistroCurso curso) {
	 
}
