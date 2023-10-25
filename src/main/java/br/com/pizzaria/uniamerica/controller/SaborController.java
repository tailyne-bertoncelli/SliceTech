package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.dto.saborDTOs.SaborDTO;
import br.com.pizzaria.uniamerica.entities.Sabor;
import br.com.pizzaria.uniamerica.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/sabor")
@CrossOrigin(origins = "http://localhost:4200")
public class SaborController {
    @Autowired
    private SaborService saborService;

    @GetMapping
    public ResponseEntity<SaborDTO> buscaPeloId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.saborService.findById(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista-sabores")
    public ResponseEntity<List<SaborDTO>> findAll(){
        try {
            return ResponseEntity.ok(this.saborService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<SaborDTO> cadastra(@RequestBody SaborDTO saborDTO){
        try {
            return ResponseEntity.ok(this.saborService.cadastra(saborDTO));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<SaborDTO> altera(@RequestBody Sabor sabor){
        try {
            return ResponseEntity.ok(this.saborService.altera(sabor));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/desativa")
    public ResponseEntity<String> desativar(@RequestParam Long id){
        try {
            return ResponseEntity.ok(this.saborService.desativa(id));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}