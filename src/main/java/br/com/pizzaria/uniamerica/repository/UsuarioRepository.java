package br.com.pizzaria.uniamerica.repository;

import br.com.pizzaria.uniamerica.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
