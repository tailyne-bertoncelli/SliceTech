package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs.EstoqueProdutoDTO;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.service.EstoqueProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/estoque-produto")
@CrossOrigin(origins = "http://localhost:4200")
public class EstoqueProdutoController {
    @Autowired
    private EstoqueProdutoService estoqueProdutoService;

    @GetMapping
    public ResponseEntity<EstoqueProdutoDTO> buscaPeloId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.estoqueProdutoService.findById(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista-todos")
    public ResponseEntity<List<EstoqueProdutoDTO>> listaTodos(){
        try {
            return ResponseEntity.ok(this.estoqueProdutoService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<EstoqueProdutoDTO> cadastra(@RequestBody @Validated EstoqueProdutoDTO estoqueProdutoDTO){
        try{
            return ResponseEntity.ok(this.estoqueProdutoService.cadastra(estoqueProdutoDTO));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/altera")
    public ResponseEntity<EstoqueProdutoDTO> altera(@RequestBody @Validated EstoqueProduto estoqueProduto){
        try {
            return ResponseEntity.ok(this.estoqueProdutoService.altera(estoqueProduto));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/desativa")
    public ResponseEntity<String> desativa(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.estoqueProdutoService.desativa(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
