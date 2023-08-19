package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.pedidoDTOs.PedidoDTO;
import br.com.pizzaria.uniamerica.entities.Pedido;
import br.com.pizzaria.uniamerica.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        Pedido pedido = pedidoService.findById(id);
        return  ResponseEntity.ok(pedido);
    }

    @GetMapping(value = "/lista")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.pedidoService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody PedidoDTO pedidoDTO){
        try {
            return ResponseEntity.ok(this.pedidoService.insert(pedidoDTO));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value =  "/{id}")
    public ResponseEntity<PedidoDTO> update(@Valid @PathVariable Long id,@RequestBody PedidoDTO pedidoDTO){
        pedidoDTO = pedidoService.update(id,pedidoDTO);
        return ResponseEntity.ok(pedidoDTO);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?>Cancelar(@PathVariable Long id){
        try {
            this.pedidoService.statusCancelado(id);
            return ResponseEntity.ok("Pedido desativado com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Pedido não encontrado");
        }
    }

    @PutMapping("/encerrar/{id}")
    public ResponseEntity<?>Encerrar(@PathVariable Long id){
        try {
            this.pedidoService.statusSituacao(id);
            return ResponseEntity.ok("Pedido encerrado com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Pedido não encontrado");
        }
    }
}
