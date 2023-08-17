package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.estoqueProdutoDTOs.EstoqueProdutoDTO;
import br.com.pizzaria.uniamerica.entities.EstoqueProduto;
import br.com.pizzaria.uniamerica.service.EstoqueProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque-produto")
public class EstoqueProdutoController {
    @Autowired
    private EstoqueProdutoService estoqueProdutoService;

    @GetMapping
    public ResponseEntity<?> buscaPeloId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.estoqueProdutoService.findById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/lista-todos")
    public ResponseEntity<?> listaTodos(){
        try {
            return ResponseEntity.ok(this.estoqueProdutoService.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping @Transactional
    public ResponseEntity<?> cadastra(@RequestBody EstoqueProdutoDTO estoqueProdutoDTO){
        try{
            return ResponseEntity.ok(this.estoqueProdutoService.cadastra(estoqueProdutoDTO));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/altera") @Transactional
    public ResponseEntity<?> altera(@RequestBody EstoqueProduto estoqueProduto){
        try {
            return ResponseEntity.ok(this.estoqueProdutoService.altera(estoqueProduto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/desativa") @Transactional
    public ResponseEntity<?> desativa(@RequestParam Long id){
        try {
            this.estoqueProdutoService.desativa(id);
            return ResponseEntity.ok("ID desativado com sucesso!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
