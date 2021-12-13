package com.usa.empresa.services;

import com.usa.empresa.entity.Laptop;
import com.usa.empresa.repository.LaptopRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository LaptopRepository;

    public List<Laptop> getAll() {
        return LaptopRepository.getAll();
    }

    public Optional<Laptop> getIdLaptop(Integer id) {
        return LaptopRepository.getIdLaptop(id);
    }

    public Laptop save(Laptop laptop) {
        Optional<Laptop> orderIdMaxima = LaptopRepository.lastUserId();
        if (laptop.getId() == null) {
            if (!orderIdMaxima.isPresent()) {
                laptop.setId(1);
            }else {
                laptop.setId(orderIdMaxima.get().getId() + 1);
            }
        }
        
        Optional<Laptop> lap = LaptopRepository.getIdLaptop(laptop.getId());
        if(!lap.isPresent()){
            return LaptopRepository.save(laptop);
        }else{
            return laptop;
        }
    }

    public Laptop update(Laptop laptop) {
        if (laptop.getModel() != null) {
            Optional<Laptop> laptopAux = LaptopRepository.getIdLaptop(laptop.getId());
            if (laptopAux.isPresent()) {
                if (laptop.getBrand()!= null) {
                    laptopAux.get().setBrand(laptop.getBrand());
                }
                if (laptop.getModel()!= null) {
                    laptopAux.get().setModel(laptop.getModel());
                }
                if (laptop.getProcesor()!= null) {
                    laptopAux.get().setProcesor(laptop.getProcesor());
                }
                if (laptop.getOs()!= null) {
                    laptopAux.get().setOs(laptop.getOs());
                }
                if (laptop.getDescription()!= null) {
                    laptopAux.get().setDescription(laptop.getDescription());
                }
                if (laptop.getMemory()!= null) {
                    laptopAux.get().setMemory(laptop.getMemory());
                }
                if (laptop.getHardDrive()!= null) {
                    laptopAux.get().setHardDrive(laptop.getHardDrive());
                }
                if (laptop.getAvailability()!= null) {
                    laptopAux.get().setAvailability(laptop.getAvailability());
                }
                if (laptop.getPrice() > 0.0 & laptop.getPrice() != null) {
                    laptopAux.get().setPrice(laptop.getPrice());
                }
                if (laptop.getQuantity() >= 0 & laptop.getQuantity() != null) {
                    laptopAux.get().setQuantity(laptop.getQuantity());
                }
                if (laptop.getPhotography() != null) {
                    laptopAux.get().setPhotography(laptop.getPhotography());
                }
                //accesoryDb.get().setAvailability(laptop.isAvailability());
                LaptopRepository.update(laptopAux.get());
                return laptopAux.get();
            } else {
                return laptop;
            }
        } else {
            return laptop;
        }
    }

    public boolean delete(Integer id) {
        Optional<Laptop> laptop = getIdLaptop(id);
        if (laptop.isPresent()) {
            LaptopRepository.delete(laptop.get());
            return true;
        }
        return false;
    }
}