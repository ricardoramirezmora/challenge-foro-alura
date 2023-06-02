package com.alura.foro4.topico;

import com.alura.foro4.autor.Autor;
import com.alura.foro4.autor.DatosRegistroAutor;

public record DatosListarTopico(Long id, String titulo, String mensaje, String fecha_creacion, String estatus, String nombreAutor, 
		                        String email, String nombreCurso, String categoria) {

	public DatosListarTopico(Topico topico) {
		this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFecha_creacion().toString(),topico.getEstatus().toString(),
			 topico.getAutor().getNombreAutor(),topico.getAutor().getEmail(),topico.getCurso().getNombreCurso(),topico.getCurso().getCategoria()); 
	}
}
