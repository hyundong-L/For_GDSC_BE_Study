package com.example.SpringBoot3.dto;

import com.example.SpringBoot3.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class PizzaDto {
    private Long id;
    private String name;
    private String price;

    public Pizza toEntity() {
        return new Pizza(id, name, price);
    }
}
