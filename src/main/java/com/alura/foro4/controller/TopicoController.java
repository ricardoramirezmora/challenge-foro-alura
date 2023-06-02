package com.alura.foro4.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro4.topico.DatosActualizarTopico;
import com.alura.foro4.topico.DatosListarTopico;
import com.alura.foro4.topico.DatosRegistroTopico;
import com.alura.foro4.topico.DatosRespuestaTopico;
import com.alura.foro4.topico.Topico;
import com.alura.foro4.topico.TopicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	TopicoRepository topicoRepository;
	
	@PostMapping
	public ResponseEntity<DatosRespuestaTopico> guardarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
		
		Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
		
		DatosRespuestaTopico datosRespuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), 
							     topico.getFecha_creacion().toString(), topico.getEstatus().toString(), topico.getAutor().getNombreAutor(), 
							     topico.getAutor().getEmail(), topico.getCurso().getNombreCurso(), topico.getCurso().getCategoria());
		
		URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(url).body(datosRespuesta); 
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosListarTopico>> listarTopicos(Pageable paginacion) {
		return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListarTopico::new)); 
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
		Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
		
		topico.actualizarDatos(datosActualizarTopico);
		
		return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), 
							     topico.getFecha_creacion().toString(), topico.getEstatus().toString(), topico.getAutor().getNombreAutor(), 
							     topico.getAutor().getEmail(), topico.getCurso().getNombreCurso(), topico.getCurso().getCategoria())); 
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminar(@PathVariable Long id) {
		Topico topico = topicoRepository.getReferenceById(id);
		topicoRepository.delete(topico);
		
		return ResponseEntity.noContent().build(); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
		Topico topico = topicoRepository.getReferenceById(id);
		
		var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), 
							     topico.getFecha_creacion().toString(), topico.getEstatus().toString(), topico.getAutor().getNombreAutor(), 
							     topico.getAutor().getEmail(), topico.getCurso().getNombreCurso(), topico.getCurso().getCategoria());
		
		return ResponseEntity.ok(datosTopico);  
	}

}
