package br.com.pizzaria.uniamerica.controller;


import br.com.pizzaria.uniamerica.dto.clienteDTOs.ClienteDTO;
import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
        ClienteDTO clienteDTO = clienteService.findById(id);
        return  ResponseEntity.ok(clienteDTO);
    }

    @GetMapping(value = "/lista")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.clienteService.findAll());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@Valid @RequestBody ClienteDTO clienteDTO){
        clienteDTO = clienteService.insert(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(clienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteDTO);
    }

    @PutMapping(value =  "/{id}")
    public ResponseEntity<ClienteDTO> update(@Valid @PathVariable Long id,@RequestBody ClienteDTO clienteDTO){
        clienteDTO = clienteService.update(id,clienteDTO);
        return ResponseEntity.ok(clienteDTO);
    }
}
