package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.entities.Pedido;
import br.com.pizzaria.uniamerica.service.GerenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/relatorio")
@CrossOrigin(origins = "http://localhost:4200")
public class GerenciaController {
    @Autowired
    private GerenciaService gerenciaService;

    @GetMapping("/pedidos-dia")
    public ResponseEntity<List<Pedido>> pedidosDoDia(@RequestParam LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.pedidosDoDia(date));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/pedidos-encerrados")
    public ResponseEntity<List<Pedido>> pedidosEncerrados(@RequestParam LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.pedidosEncerrados(date));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/pedidos-entrega")
    public ResponseEntity<List<Pedido>> pedidosEntrega(@RequestParam LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.pedidosEntrega(date));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/pedidos-retira")
    public ResponseEntity<List<Pedido>> pedidosRetira(@RequestParam LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.pedidosRetira(date));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/pedidos-cancelados")
    public ResponseEntity<List<Pedido>> pedidosCancelados(@RequestParam LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.pedidosCancelados(date));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/vendas-totais")
    public ResponseEntity<String> valoresVendas(@RequestParam LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.valorTotalPedidos(date));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
