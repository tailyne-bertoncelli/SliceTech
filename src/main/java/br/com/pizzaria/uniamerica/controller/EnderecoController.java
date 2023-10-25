package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.enderecoDTOs.EnderecoDTO;
import br.com.pizzaria.uniamerica.entities.Endereco;
import br.com.pizzaria.uniamerica.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/endereco")
@CrossOrigin(origins = "http://localhost:4200")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<EnderecoDTO> buscaPeloId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.enderecoService.findById(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista-todos")
    public ResponseEntity<List<EnderecoDTO>> buscaTodos(){
        try {
            return ResponseEntity.ok(this.enderecoService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> cadastra(@RequestBody @Validated EnderecoDTO enderecoDTO){
        try {
            return ResponseEntity.ok(this.enderecoService.cadastra(enderecoDTO));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/altera")
    public ResponseEntity<EnderecoDTO> altera(@RequestBody @Validated Endereco endereco){
        try {
            return ResponseEntity.ok(this.enderecoService.altera(endereco));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/desativa/{id}")
    public ResponseEntity<String> desativa(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.enderecoService.desativa(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
