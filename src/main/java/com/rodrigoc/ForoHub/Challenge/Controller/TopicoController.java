package com.rodrigoc.ForoHub.Challenge.Controller;

import com.rodrigoc.ForoHub.Challenge.Domain.Topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity <DatosRespuestaT> registrarTopico(@RequestBody @Valid DatosTopico datosTopico, UriComponentsBuilder uriComponentsBuilder){
        LocalDate fechaCreacion = LocalDate.now(ZoneOffset.of("-05:00"));

        System.out.println(fechaCreacion);

        Topico topico = topicoRepository.save(new Topico(datosTopico));
        DatosRespuestaT datosRespuestaT = new DatosRespuestaT(topico.getId_usuario(), topico.getTitulo(),
                topico.getMensaje(), topico.getNombre_curso(), fechaCreacion);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaT);
    }

    @GetMapping
    public ResponseEntity <Page<DatosListadoTopico>> listadoTopico(@PageableDefault(size = 10)Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarT datosActualizarT){
        Topico topico = topicoRepository.getReferenceById(datosActualizarT.id());
        topico.actualizarTopico(datosActualizarT);
        return ResponseEntity.ok(new DatosRespuestaT(topico.getId_usuario(), topico.getTitulo(), topico.getMensaje(),
                topico.getNombre_curso(), topico.getFecha_crecion()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.ocultarTopico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaT> retornarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaT(topico.getId_usuario(), topico.getTitulo(),
                topico.getMensaje(), topico.getNombre_curso(), topico.getFecha_crecion());
        return ResponseEntity.ok(datosTopico);
    }

}