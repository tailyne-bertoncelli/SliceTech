package br.com.pizzaria.uniamerica.controller;


import br.com.pizzaria.uniamerica.dto.clienteDTOs.ClienteDTO;
import br.com.pizzaria.uniamerica.dto.usuarioDTOs.UsuarioDTO;
import br.com.pizzaria.uniamerica.entities.Cliente;
import br.com.pizzaria.uniamerica.entities.Pedido;
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
    public ResponseEntity<?> findById(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        return  ResponseEntity.ok(cliente);
    }

    @GetMapping(value = "/lista")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.clienteService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody ClienteDTO clienteDTO){
        try {
            return ResponseEntity.ok(this.clienteService.insert(clienteDTO));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value =  "/{id}")
    public ResponseEntity<ClienteDTO> update(@Valid @PathVariable Long id,@RequestBody ClienteDTO clienteDTO){
//        clienteDTO = clienteService.update(id,clienteDTO);
        return ResponseEntity.ok(clienteDTO);
    }
}
