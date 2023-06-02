package com.alura.foro4.topico;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje, String fecha_creacion, String estatus, String nombreAutor, 
        						   String email, String nombreCurso, String categoria) { 

}
