package com.usa.empresa.services;

import com.usa.empresa.entity.User;
import com.usa.empresa.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> getIdUser(int idUser) {
        return userRepository.getIdUser(idUser);
    }

    public User save(User user) {
        Optional<User> orderIdMaxima = userRepository.lastUserId();
        if (user.getId() == null) {
            if (!orderIdMaxima.isPresent()) {
                user.setId(1);
            }else {
                user.setId(orderIdMaxima.get().getId() + 1);
            }
        }
        
        if (user.getId() == null) {
            return user;
        }else{
            Optional<User> usa = userRepository.getIdUser(user.getId());
            if(!usa.isPresent()){
                if (existeEmail(user.getEmail()) == false){
                    return userRepository.save(user);
                }else{
                    return user;
                }
            }else{
                return user;
            }
        }
    }

    public User update(User user) {
        if (user.getId() != null) {
            Optional<User> userAux = userRepository.getIdUser(user.getId());
            if (userAux.isPresent()) {
                 if (user.getIdentification() != null) {
                    userAux.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userAux.get().setName(user.getName());
                }
                if (user.getBirthtDay()!= null) {
                    userAux.get().setBirthtDay(user.getBirthtDay());
                }
                if (user.getMonthBirthtDay()!= null) {
                    userAux.get().setMonthBirthtDay(user.getMonthBirthtDay());
                }
                if (user.getEmail() != null) {
                    userAux.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userAux.get().setPassword(user.getPassword());
                }
                if (user.getAddress() != null) {
                    userAux.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userAux.get().setCellPhone(user.getCellPhone());
                }
                if (user.getZone() != null) {
                    userAux.get().setZone(user.getZone());
                }
                if (user.getType() != null) {
                    userAux.get().setType(user.getType());
                }

                return userRepository.save(userAux.get());
            }
        }
        return user;
    }

    public boolean deleteUser(int id) {
        Optional<User> user = getIdUser(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }
    
    public boolean existeEmail(String email) {
        return userRepository.existeEmail(email);
    }

    public User autenticarUsuario(String email, String password) {
        Optional<User> usuario = userRepository.autenticarUsuario(email, password);

        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return new User();
        }
    }
}
