package br.com.pizzaria.uniamerica.service;

import br.com.pizzaria.uniamerica.dto.pizzaDTOs.PizzaDTO;
import br.com.pizzaria.uniamerica.entities.Pizza;
import br.com.pizzaria.uniamerica.repository.PizzaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    private ModelMapper modelMapper;

    public PizzaDTO findById(Long id){
        Pizza pizza = this.pizzaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        return modelMapper.map(pizza, PizzaDTO.class);
    }

    public List<PizzaDTO> findAll(){
        List<Pizza> pizzaList = this.pizzaRepository.findAll();
        List<PizzaDTO> pizzaDTOList = new ArrayList<>();
        for (Pizza p: pizzaList) {
            var pizza = modelMapper.map(p, PizzaDTO.class);
            pizzaDTOList.add(pizza);
        }
        return pizzaDTOList;
    }

    @Transactional
    public PizzaDTO cadastra(PizzaDTO pizzaDTO){
        Pizza pizza = new Pizza(pizzaDTO);
        this.pizzaRepository.save(pizza);
        return modelMapper.map(pizza, PizzaDTO.class);
    }

    @Transactional
    public PizzaDTO altera(Pizza pizza){
        Pizza pizza1 = this.pizzaRepository.findById(pizza.getId())
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));


        pizza1.setTamanhoPizza(pizza.getTamanhoPizza());
        pizza1.setValor(pizza.getValor());
        pizza1.setDescricao(pizza.getDescricao());

        this.pizzaRepository.save(pizza1);
        return modelMapper.map(pizza1, PizzaDTO.class);
    }

    @Transactional
    public String desativa(Long id){
        Pizza pizza = this.pizzaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("O ID informado não foi encontrado!"));
        pizza.setAtivo(false);
        this.pizzaRepository.save(pizza);
        return "A pizza foi desativada com sucesso!";
    }
}
