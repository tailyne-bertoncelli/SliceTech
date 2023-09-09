package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.pizzaDTOs.PizzaDTO;
import br.com.pizzaria.uniamerica.entities.Pizza;
import br.com.pizzaria.uniamerica.service.PizzaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/pizza")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<PizzaDTO> buscaPeloId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.pizzaService.findById(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista-todos")
    public ResponseEntity<List<PizzaDTO>> listaTodos(){
        try {
            return ResponseEntity.ok(this.pizzaService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<PizzaDTO> cadastra(@RequestBody @Validated PizzaDTO pizzaDTO){
        try {
            return ResponseEntity.ok(this.pizzaService.cadastra(pizzaDTO));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/altera")
    public ResponseEntity<PizzaDTO> altera(@RequestBody @Validated Pizza pizza){
        try {
            return ResponseEntity.ok(this.pizzaService.altera(pizza));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/desativa")
    public ResponseEntity<String> desativa(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.pizzaService.desativa(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
