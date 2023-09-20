package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.pedidoDTOs.PedidoDTO;
import br.com.pizzaria.uniamerica.dto.pizzaDTOs.PizzaDTO;
import br.com.pizzaria.uniamerica.entities.Pedido;
import br.com.pizzaria.uniamerica.entities.Pizza;
import br.com.pizzaria.uniamerica.service.PedidoService;
import br.com.pizzaria.uniamerica.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<PedidoDTO> findById(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.pedidoService.findById(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PedidoDTO>> listaTodos(){
        try {
            return ResponseEntity.ok(this.pedidoService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> cadastra(@RequestBody @Validated PedidoDTO pedidoDTO){
        try {
            return ResponseEntity.ok(this.pedidoService.insert(pedidoDTO));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PedidoDTO> update(@RequestBody @Validated Pedido pedido){
        try {
            return ResponseEntity.ok(this.pedidoService.update(pedido));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/desativa")
    public ResponseEntity<String> LogicDelete(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.pedidoService.logicDelete(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
