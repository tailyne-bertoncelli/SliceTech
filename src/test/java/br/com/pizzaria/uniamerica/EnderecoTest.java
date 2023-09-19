package br.com.pizzaria.uniamerica;

import br.com.pizzaria.uniamerica.controller.EnderecoController;
import br.com.pizzaria.uniamerica.dto.enderecoDTOs.EnderecoDTO;
import br.com.pizzaria.uniamerica.entities.Endereco;
import br.com.pizzaria.uniamerica.repository.EnderecoRepository;

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
class EnderecoTest {

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoController enderecoController;

    @BeforeEach
    void obj(){
        Endereco endereco = new Endereco("Avenida Brasil", 321L, "85862-570", "Esquina");
        endereco.setId(1L);
        endereco.setAtivo(true);

        List<Endereco> enderecoList = new ArrayList<>();
        enderecoList.add(new Endereco("Avenida Republica Argentina", 888L, "85869-580", "Casa marrom"));
        enderecoList.add(new Endereco("Avenida Alemanha", 2489L, "85854-890", "Casa azul"));
        enderecoList.add(new Endereco("Rua 265", 152L, "85869-412", "Sobrado preto e branco"));

        Mockito.when(enderecoRepository.findAll()).thenReturn(enderecoList);
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
    }

    @Test
    void testFindById(){
        var endereco = enderecoController.buscaPeloId(1L);
        Assert.assertEquals(1L, endereco.getBody().getId(), 0);
    }

    @Test
    void testFindAll(){
        var endereco = enderecoController.buscaTodos();
        Assert.assertEquals(3, endereco.getBody().size(), 0);
    }

    @Test
    void testCadastra(){
        EnderecoDTO enderecoDTO = new EnderecoDTO(1L, "Avenida Brasil", 321L, "85862-570", "Esquina");
        var endereco = enderecoController.cadastra(enderecoDTO);
        EnderecoDTO enderecoDTO1 = new EnderecoDTO(endereco.getBody().getId(), endereco.getBody().getLogradouro(), endereco.getBody().getNumero(), endereco.getBody().getCep(), endereco.getBody().getComplemento());
        Assert.assertEquals(enderecoDTO1.getId(), endereco.getBody().getId());
    }

    @Test
    void testAltera(){
        Endereco endereco = new Endereco("Avenida Brasil", 321L, "85862-570", "Esquina");
        endereco.setId(1L);
        var testa = enderecoController.altera(endereco);
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        Assert.assertEquals(enderecoDTO.getId(), testa.getBody().getId());
    }

    @Test
    void testDesativa(){
        Endereco endereco = new Endereco("Avenida Brasil", 321L, "85862-570", "Esquina");
        endereco.setId(1L);
        endereco.setAtivo(true);

        var teste = enderecoController.desativa(1L);
        Assert.assertEquals("Endereço desativado com sucesso!", teste.getBody());
    }
}
