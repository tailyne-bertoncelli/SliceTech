package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDTO;
import br.com.pizzaria.uniamerica.dto.produtoDTOs.ProdutoDetalhesDTO;
import br.com.pizzaria.uniamerica.entities.Produto;
import br.com.pizzaria.uniamerica.repository.ProdutoRepository;
import br.com.pizzaria.uniamerica.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<ProdutoDTO> buscaPeloId(@RequestParam Long id){
        try{
            return ResponseEntity.ok(this.produtoService.findById(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista-todos")
    public ResponseEntity<List<ProdutoDTO>> listaTodos(){
        try {
            return ResponseEntity.ok(this.produtoService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastra(@RequestBody @Validated ProdutoDTO produtoDTO){
        try {
            return ResponseEntity.ok(this.produtoService.cadastra(produtoDTO));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/altera")
    public ResponseEntity<ProdutoDetalhesDTO> altera(@RequestBody @Validated Produto produto){
        try {
            return ResponseEntity.ok(this.produtoService.altera(produto));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/desativa")
    public ResponseEntity<String> desativa(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.produtoService.desativa(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
