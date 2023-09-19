package br.com.pizzaria.uniamerica.controller;


import br.com.pizzaria.uniamerica.dto.clienteDTOs.ClienteDTO;

import br.com.pizzaria.uniamerica.entities.Cliente;

import br.com.pizzaria.uniamerica.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping(value = "api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<ClienteDTO> findById(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.clienteService.findById(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ClienteDTO>> findAll(){
        try {
            return ResponseEntity.ok(this.clienteService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@RequestBody @Validated ClienteDTO clienteDTO){
        try {
            return ResponseEntity.ok(this.clienteService.insert(clienteDTO));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/altera")
    public ResponseEntity<ClienteDTO> update(@RequestBody @Validated Cliente cliente){
        try {
            return ResponseEntity.ok(this.clienteService.update(cliente));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/logicDelete")
    public ResponseEntity<String> deleteLogic(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.clienteService.LogicDelete(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
