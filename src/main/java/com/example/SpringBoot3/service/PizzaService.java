package com.example.SpringBoot3.service;

import com.example.SpringBoot3.dto.PizzaDto;
import com.example.SpringBoot3.entity.Pizza;
import com.example.SpringBoot3.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    //전체 조회
    public List<Pizza> index() {
        return pizzaRepository.findAll();
    }

    //단일 조회
    public Pizza show(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    //생성
    @Transactional
    public Pizza create(PizzaDto dto) {
        //dto -> Entity
        Pizza entity = dto.toEntity();

        //Entity -> DB
        return pizzaRepository.save(entity);
    }

    //수정
    @Transactional
    public Pizza update(Long id, PizzaDto dto) {
        //dto -> Entity
        Pizza entity = dto.toEntity();

        //find data
        Pizza target = pizzaRepository.findById(id).orElse(null);

        //exception
        if (target == null || id != entity.getId()) {
            return null;
        }

        //update data
        target.patch(entity);
        return pizzaRepository.save(target);
    }

    //삭제
    @Transactional
    public Pizza delete(Long id) {
        //find data
        Pizza target = pizzaRepository.findById(id).orElse(null);

        //exception
        if (target == null || id != target.getId()) {
            return null;
        }

        pizzaRepository.delete(target);
        return target;
    }
}
