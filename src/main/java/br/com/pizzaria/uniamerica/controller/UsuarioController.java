package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;

import br.com.pizzaria.uniamerica.entities.Usuario;
import br.com.pizzaria.uniamerica.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<UsuarioDTO> findById(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.usuarioService.findById(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UsuarioDTO>> FindAll(){
        try {
            return ResponseEntity.ok(this.usuarioService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> Insert(@RequestBody @Validated UsuarioDTO usuarioDTO){
        try {
            return ResponseEntity.ok(this.usuarioService.cadastrar(usuarioDTO));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioDTO> Update(@RequestBody @Validated Usuario usuario){
        try {
            return ResponseEntity.ok(this.usuarioService.alterar(usuario));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/LogicDelete")
    public ResponseEntity<String> LogicDelete(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.usuarioService.desativar(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
