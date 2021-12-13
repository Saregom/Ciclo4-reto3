package com.usa.empresa.controller;

import com.usa.empresa.entity.Laptop;
import com.usa.empresa.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laptop")
@CrossOrigin("*")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @GetMapping("/all")
    public List<Laptop> getAll() {
        return laptopService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Laptop> getIdLaptop(@PathVariable("id") Integer id) {
        return laptopService.getIdLaptop(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Laptop save(@RequestBody Laptop laptop) {
        return laptopService.save(laptop);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Laptop update(@RequestBody Laptop laptop) {
        return laptopService.update(laptop);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer id) {
        return laptopService.delete(id);
    }

}
