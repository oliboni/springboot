package br.com.zup.bootcamp.Controller;

import br.com.zup.bootcamp.model.Carro;
import br.com.zup.bootcamp.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CarroController {

    @Autowired
    CarroService carroService;

    @GetMapping("/carros")
    public Iterable<Carro> allCars() {
        return carroService.findAll();
    }

    @GetMapping("/carros/{id}")
    public ResponseEntity<Carro> getCarro(@PathVariable final Long id) {
        return carroService.getCarroById(id);
    }

    @GetMapping("/carros/")
    public ResponseEntity<List<Carro>> getMarca(@RequestParam String descr, @RequestParam Long tp) {
       return carroService.findCarro(descr, tp);
    }

    @GetMapping("/carros/count")
    public Long count() {
        return carroService.count();
    }

    @PostMapping(value = "/carros")
    public void create(@RequestBody Carro carro) {
        carroService.create(carro);
        System.out.println("Carro criado com Sucesso");
    }

    @DeleteMapping("/carros/{id}")
    public void delete(@PathVariable final Long id) {
        carroService.delete(id);
        System.out.println("Carro removido com Sucesso");
    }

    @PutMapping("/carros/{id}")
    public void update(@PathVariable final Long id, @RequestBody Carro carro) {
        carroService.update(id, carro);
        System.out.println("Carro alterado com sucesso!");
    }
}
