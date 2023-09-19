package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;

import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> insert(@Valid @RequestBody UsuarioDTO usuarioDTO){
        try {
            return ResponseEntity.ok(this.usuarioService.insert(usuarioDTO));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping(value =  "/{id}")
    public ResponseEntity<UsuarioDTO> update(@Valid @PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO){
        usuarioDTO = usuarioService.update(id,usuarioDTO);
        return ResponseEntity.ok(usuarioDTO);
    }
}
