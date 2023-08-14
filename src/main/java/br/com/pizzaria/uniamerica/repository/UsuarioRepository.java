package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Long, Usuario> {
}
