package br.com.zup.bootcamp.service;

import br.com.zup.bootcamp.model.Carro;
import br.com.zup.bootcamp.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    //Injetamos o repositório
    @Autowired
    private CarroRepository carroRepository;
    //método para buscar todos os dados
    public Iterable<Carro> findAll() {
        return carroRepository.findAll();
    }

    //método para buscar apenas pelo ID
    public ResponseEntity<Carro> getCarroById(Long id){
        Optional<Carro> carro = carroRepository.findById(id);
        return carro.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Metodo para buscar através da marca
    public ResponseEntity<List<Carro>> findCarro(String descr, long tp){
        try {
            List<Carro> carro = new ArrayList<Carro>();

            if (tp == 1)//Se tipo for 1, busca pela marca
                carro.addAll(carroRepository.findByMarca(descr));
            else if (tp == 2)//Se o tipo for 2, busca pelo modelo
                carro.addAll(carroRepository.findByModelo(descr));

            if (carro.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(carro, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //método para insert
    public void create(Carro carro){
        carroRepository.save(carro);
    }

    //método delete
    public void delete(Long carroId) {
        carroRepository.deleteById(carroId);
    }

    //método update
    public void update(long id, @RequestBody Carro carro) {
        Optional<Carro> carroData = carroRepository.findById(id);

        if (carroData.isPresent()) {
            Carro _carro = carroData.get();
            _carro.setMarca(carro.getMarca());
            _carro.setModelo(carro.getmodelo());
            new ResponseEntity<>(carroRepository.save(_carro), HttpStatus.OK);
        } else {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //método que realiza um count dos registros
    public Long count() {
        return carroRepository.count();
    }
}
