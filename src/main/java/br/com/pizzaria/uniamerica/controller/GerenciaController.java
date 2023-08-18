package br.com.pizzaria.uniamerica.controller;

import br.com.pizzaria.uniamerica.service.GerenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/relatorio")
public class GerenciaController {
    @Autowired
    private GerenciaService gerenciaService;

    @GetMapping("/pedidos-dia")
    public ResponseEntity<?> pedidosDoDia(@RequestParam LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.pedidosDoDia(date));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pedidos-encerrados")
    public ResponseEntity<?> pedidosEncerrados(@RequestBody LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.pedidosEncerrados(date));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pedidos-entrega")
    public ResponseEntity<?> pedidosEntrega(@RequestBody LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.pedidosEntrega(date));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pedidos-retira")
    public ResponseEntity<?> pedidosRetira(@RequestBody LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.pedidosRetira(date));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/vendas-cartao")
    public ResponseEntity<?> pedidosCartao(@RequestBody LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.totalPedidosCartao(date));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/vendas-dinheiro")
    public ResponseEntity<?> pedidosDinheiro(@RequestBody LocalDate date){
        try {
            return ResponseEntity.ok(this.gerenciaService.totalPedidosDinheiro(date));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
