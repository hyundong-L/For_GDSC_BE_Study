package com.example.SpringBoot3.repository;

import com.example.SpringBoot3.entity.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    @Override
    ArrayList<Pizza> findAll();
}
