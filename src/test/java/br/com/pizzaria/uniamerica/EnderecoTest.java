package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.dto.enderecoDTOs.EnderecoDTO;
import br.com.pizzaria.uniamerica.entities.Endereco;
import br.com.pizzaria.uniamerica.repository.EnderecoRepository;
import br.com.pizzaria.uniamerica.service.EnderecoService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EnderecoTest {

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Autowired
    private final EnderecoService enderecoService = new EnderecoService();

    @BeforeEach
    void obj(){
        Optional<Endereco> endereco = Optional.of(new Endereco("Avenida Brasil", 321L, "85862-570", "Esquina"));
        Long id = 1L;
        Mockito.when(enderecoRepository.findById(id)).thenReturn(endereco);

        List<Endereco> enderecoList = new ArrayList<>();
        enderecoList.add(new Endereco("Avenida Republica Argentina", 888L, "85869-580", "Casa marrom"));
        enderecoList.add(new Endereco("Avenida Alemanha", 2489L, "85854-890", "Casa azul"));
        enderecoList.add(new Endereco("Rua 265", 152L, "85869-412", "Sobrado preto e branco"));
        Mockito.when(enderecoRepository.findAll()).thenReturn(enderecoList);
    }

    @BeforeEach
    void endereco(){
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLogradouro("Avenida Brasil");
        endereco.setNumero(1070L);
        endereco.setCep("85862-570");
        endereco.setComplemento("Lado direito");

        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
    }

    @Test
    void testFindById(){
        var endereco = enderecoService.findById(1L);
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco.getId(), endereco.getLogradouro(), endereco.getNumero(), endereco.getCep(), endereco.getComplemento());
        Long id = enderecoDTO.getId().longValue();
        Assert.assertEquals(1L, id, 0);

    }
}
