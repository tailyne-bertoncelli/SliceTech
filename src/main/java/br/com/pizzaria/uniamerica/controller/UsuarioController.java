package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.repository.UsuarioRepository;
import br.com.pizzaria.uniamerica.service.UsuarioService;
import com.electronwill.nightconfig.core.conversion.Path;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        UsuarioDTO usuarioDTO = usuarioService.findById(id);
        return  ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping(value = "/lista")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.usuarioService.findAll());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioDTO usuarioDTO){
        usuarioDTO = usuarioService.insert(usuarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(usuarioDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @PutMapping(value =  "/{id}")
    public ResponseEntity<UsuarioDTO> update(@Valid @PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO){
        usuarioDTO = usuarioService.update(id,usuarioDTO);
        return ResponseEntity.ok(usuarioDTO);
    }
}
