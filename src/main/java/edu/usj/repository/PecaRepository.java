package edu.usj.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import edu.usj.entity.Peca;

public interface PecaRepository extends CrudRepository<Peca, Long> {

    List<Peca> findAll();

    List<Peca> findByNome(String nome);

    List<Peca> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

}
