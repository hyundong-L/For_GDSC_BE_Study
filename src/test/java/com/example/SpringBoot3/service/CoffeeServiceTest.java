package com.example.SpringBoot3.service;

import com.example.SpringBoot3.dto.CoffeeDto;
import com.example.SpringBoot3.entity.Coffee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoffeeServiceTest {
    @Autowired
    private CoffeeService coffeeService;

    @Test
    void index() {
        //1
        Coffee a = new Coffee(1L, "아메리카노", "4500");
        Coffee b = new Coffee(2L, "라떼", "5000");
        Coffee c = new Coffee(3L, "카페 모카", "5500");

        List<Coffee> expected = new ArrayList<>(Arrays.asList(a, b, c));

        //2
        List<Coffee> coffees = coffeeService.index();

        //3
        assertEquals(expected.toString(), coffees.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        //1
        Long id = 1L;
        Coffee expected = new Coffee(id, "아메리카노", "4500");

        //2
        Coffee coffee = coffeeService.show(id);

        //3
        assertEquals(expected.toString(), coffee.toString());

    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        //1
        Long id = -1L;
        Coffee expected = null;

        //2
        Coffee coffee = coffeeService.show(id);

        //3
        assertEquals(expected, coffee);
    }

    @Test
    @Transactional
    void create_성공_name과_price만_있는_dto_입력() {
        //1
        String name = "자바칩";
        String price = "6500";

        CoffeeDto dto = new CoffeeDto(null, name, price);
        Coffee expected = new Coffee(4L, name, price);

        //2
        Coffee coffee = coffeeService.create(dto);

        //3
        assertEquals(expected.toString(), coffee.toString());
    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        //1
        Long id = 4L;
        String name = "자바칩";
        String price = "6500";

        CoffeeDto dto = new CoffeeDto(id, name, price);
        Coffee expected = null;

        //2
        Coffee coffee = coffeeService.create(dto);

        //3
        assertEquals(expected, coffee);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_name_price가_있는_dto_입력() {
        //1
        Long id = 1L;
        String name = "롱블랙";
        String price = "3000";

        CoffeeDto dto = new CoffeeDto(id, name, price);
        Coffee expected = new Coffee(id, name, price);

        //2
        Coffee coffee = coffeeService.update(id, dto);

        //3
        assertEquals(expected.toString(), coffee.toString());
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_name만_있는_dto_입력() {
        //1
        Long id = 1L;
        String name = "롱블랙";
        String price = null;

        CoffeeDto dto = new CoffeeDto(id, name, price);
        Coffee expected = new Coffee(id, name, "4500");

        //2
        Coffee coffee = coffeeService.update(id, dto);

        //3
        assertEquals(expected.toString(), coffee.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id의_dto_입력() {
        //1
        Long id = -1L;
        String name = "롱블랙";
        String price = "3000";

        CoffeeDto dto = new CoffeeDto(id, name, price);
        Coffee expected = null;

        //2
        Coffee coffee = coffeeService.update(id, dto);

        //3
        assertEquals(expected, coffee);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        //1
        Long id = 1L;
        Coffee expected = new Coffee(id, "아메리카노", "4500");

        //2
        Coffee coffee = coffeeService.delete(id);

        //3
        assertEquals(expected.toString(), coffee.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력() {
        //1
        Long id = -1L;
        Coffee expected = null;

        //2
        Coffee coffee = coffeeService.delete(id);

        //3
        assertEquals(expected, coffee);
    }
}