package edu.usj.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import edu.usj.entity.Contato;

public interface ContatoRepository extends CrudRepository<Contato, Long> {

    List<Contato> findAll();

    List<Contato> findByNome(String nome);

    List<Contato> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

}
