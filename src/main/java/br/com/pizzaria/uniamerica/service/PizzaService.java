package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.pizzaDTOs.PizzaDTO;
import br.com.pizzaria.uniamerica.entities.Pizza;
import br.com.pizzaria.uniamerica.entities.Sabor;
import br.com.pizzaria.uniamerica.repository.PizzaRepository;
import br.com.pizzaria.uniamerica.repository.SaborRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private SaborRepository saborRepository;

    public PizzaDTO findById(Long id){
        Pizza pizza = this.pizzaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        PizzaDTO dto = copyToDto(pizza);
        return dto;
    }


    public List<PizzaDTO> findAll(){
        List<Pizza> pizzaList = this.pizzaRepository.findAll();
        List<PizzaDTO> pizzaDTOList = new ArrayList<>();
        for (Pizza p: pizzaList) {
            var pizza = copyToDto(p);
            pizzaDTOList.add(pizza);
        }
        return pizzaDTOList;
    }

    @Transactional
    public PizzaDTO cadastra(PizzaDTO pizzaDTO){
        Sabor sabor = this.saborRepository.findById(pizzaDTO.getSabor())
                .orElseThrow(()-> new RuntimeException("Sabor não encontrado!"));

        Pizza pizza = new Pizza(pizzaDTO.getDescricao(), pizzaDTO.getValor(), sabor, pizzaDTO.getTamanhoPizza());
        this.pizzaRepository.save(pizza);
        PizzaDTO dto = copyToDto(pizza);
        return dto;
    }

    @Transactional
    public PizzaDTO altera(Pizza pizza){
        Pizza pizza1 = this.pizzaRepository.findById(pizza.getId())
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));


        pizza1.setTamanhoPizza(pizza.getTamanhoPizza());
        pizza1.setValor(pizza.getValor());
        pizza1.setDescricao(pizza.getDescricao());

        this.pizzaRepository.save(pizza1);
        PizzaDTO dto = copyToDto(pizza1);
        return dto;
    }

    @Transactional
    public String desativa(Long id){
        Pizza pizza = this.pizzaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        pizza.setAtivo(false);
        this.pizzaRepository.save(pizza);
        return "A pizza foi desativada com sucesso!";
    }


    private PizzaDTO copyToDto(Pizza pizza) {
        PizzaDTO pizzaDTO = new PizzaDTO(pizza);
        return pizzaDTO;
    }
}
