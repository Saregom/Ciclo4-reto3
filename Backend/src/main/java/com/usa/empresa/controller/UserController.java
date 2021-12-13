package com.usa.empresa.controller;

import com.usa.empresa.entity.User;
import com.usa.empresa.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grupo 5
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {
     
    /**
     * Instancia de clase UserService.
    */
    @Autowired
    private UserService userService;

    /**
     * Lista todos los usuarios registrados.
     *
     * @return userService.getAll()
     */
    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    /**
     * Devuelve un usuario pasando por parametro el Id.
     *
     * @param idUser
     * @return userService.getIdUser(idUser)
     */
    @GetMapping("/{id}")
    public Optional<User> getIdUser(@PathVariable("id") int idUser) {
        return userService.getIdUser(idUser);
    }

    /**
     * Registrar un nuevo usuario.
     *
     * @param user
     * @return userService.save(user)
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * Actualiza un usuario.
     *
     * @param user
     * @return userService.update(user)
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * Borrar un usuario.
     *
     * @param id
     * @return userService.deleteUser(id)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

    /**
     * Autentica el password y el email.
     *
     * @param email
     * @param password
     * @return userService.autenticarUsuario(email, password)
     */
    @GetMapping("/{email}/{password}")
    public User autenticarUsuario(@PathVariable("email") String email, @PathVariable("password") String password) {
        return userService.autenticarUsuario(email, password);
    }

    /**
     * Verifica si existe un email.
     *
     * @param email
     * @return userService.existeEmail(email)
     */
    @GetMapping("/emailexist/{email}")
    public boolean existEmail(@PathVariable("email") String email) {
        return userService.existeEmail(email);
    }
}
