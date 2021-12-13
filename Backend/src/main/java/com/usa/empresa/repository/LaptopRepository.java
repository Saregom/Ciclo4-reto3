package com.usa.empresa.repository;

import com.usa.empresa.entity.Laptop;
import com.usa.empresa.repository.crud.LaptopCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LaptopRepository {

    @Autowired
    private LaptopCrudRepository laptopCrudRepository;

    public List<Laptop> getAll(){
        return laptopCrudRepository.findAll();
    }

    public Optional<Laptop> getIdLaptop(Integer id) {
        return laptopCrudRepository.findById(id);
    }
    public Laptop save(Laptop laptop) {
        return laptopCrudRepository.save(laptop);
    }

    public void update(Laptop laptop) {
        laptopCrudRepository.save(laptop);
    }

    public void delete(Laptop laptop) {
        laptopCrudRepository.delete(laptop);
    }
    public Optional<Laptop> lastUserId() {
        return laptopCrudRepository.findTopByOrderByIdDesc();
    }
}

