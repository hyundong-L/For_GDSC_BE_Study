package com.example.SpringBoot3.api;

import com.example.SpringBoot3.dto.PizzaDto;
import com.example.SpringBoot3.entity.Pizza;
import com.example.SpringBoot3.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;

    //전체 조회
    @GetMapping("/api/pizza")
    public ResponseEntity<List<Pizza>> index() {
        return ResponseEntity.status(HttpStatus.OK).body(pizzaService.index());
    }

    //단일 조회
    @GetMapping("/api/pizza/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pizzaService.show(id));
    }

    //생성
    @PostMapping("/api/pizza")
    public ResponseEntity<Pizza> create(@RequestBody PizzaDto dto) {
        Pizza created = pizzaService.create(dto);

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    //수정
    @PatchMapping("/api/pizza/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Long id, @RequestBody PizzaDto dto) {
        Pizza updated = pizzaService.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }


    //삭제
    @DeleteMapping("/api/pizza/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Long id) {
        Pizza deleted = pizzaService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
