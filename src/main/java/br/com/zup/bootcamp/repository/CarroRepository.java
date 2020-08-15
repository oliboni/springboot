package br.com.zup.bootcamp.repository;

import br.com.zup.bootcamp.model.Carro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Long>{
    List<Carro> findByModelo(String modelo);
    List<Carro> findByMarca(String marca);
}
