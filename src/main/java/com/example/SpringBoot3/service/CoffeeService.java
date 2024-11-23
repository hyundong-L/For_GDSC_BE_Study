package com.example.SpringBoot3.service;

import com.example.SpringBoot3.dto.CoffeeDto;
import com.example.SpringBoot3.entity.Coffee;
import com.example.SpringBoot3.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto dto) {
        //1. DTO -> Entity
        Coffee coffeeEntity = dto.toEntity();

        if (coffeeEntity.getId() != null) {
            return null;
        }

        //2. Entity -> DB
        return coffeeRepository.save(coffeeEntity);
    }

    public Coffee update(Long id, CoffeeDto dto) {
        //1. DTO -> Entity
        Coffee coffeeEntity = dto.toEntity();

        //2. find Entity in DB
        Coffee target = coffeeRepository.findById(id).orElse(null);

        //3. invalid requests
        if (target == null || id != coffeeEntity.getId()) {
            return null;
        }

        //4. update
        target.patch(coffeeEntity);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        //1. find Data in DB
        Coffee target = coffeeRepository.findById(id).orElse(null);

        //2. invalid requests
        if (target == null || id != target.getId()) {
            return null;
        }

        //3. return
        coffeeRepository.delete(target);
        return target;
    }
}
