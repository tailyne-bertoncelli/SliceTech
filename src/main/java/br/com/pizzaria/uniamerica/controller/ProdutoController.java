package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDTO;
import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDetalhesDTO;
import br.com.pizzaria.uniamerica.entities.Produto;
import br.com.pizzaria.uniamerica.repository.ProdutoRepository;
import br.com.pizzaria.uniamerica.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<?> buscaPeloId(@RequestParam Long id){
        try{
            return ResponseEntity.ok(this.produtoService.findById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/lista-todos")
    public ResponseEntity<?> listaTodos(){
        try {
            return ResponseEntity.ok(this.produtoService.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Validated ProdutoDTO produtoDTO){
        try {
            return ResponseEntity.ok(this.produtoService.cadastra(produtoDTO));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/altera")
    public ResponseEntity<?> altera(@RequestBody @Validated Produto produto){
        try {
            return ResponseEntity.ok(this.produtoService.altera(produto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/desativa")
    public ResponseEntity<?> desativa(@RequestParam Long id){
        try {
            this.produtoService.desativa(id);
            return ResponseEntity.ok("ID desativado com sucesso!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
