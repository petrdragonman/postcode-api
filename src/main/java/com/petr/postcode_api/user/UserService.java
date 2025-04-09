package com.petr.postcode_api.user;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.petr.postcode_api.common.exceptions.UserNotFoundException;

@Service
public class UserService {
    private UserRepository repo;
    private ModelMapper mapper;

    public UserService(UserRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<User> getAll() {
        return this.repo.findAll();
    }

    public User getById(Long id) {
        return this.repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(CreateUserDTO data) {
        // encode password before saving it
        User newUser = mapper.map(data, User.class);
        return this.repo.saveAndFlush(newUser);
    }

    public User updateUser(Long id, UpdateUserDTO data) {
        User foundUser = this.getById(id);
        mapper.map(data, foundUser);
        this.repo.save(foundUser);
        return foundUser;
    }

    public boolean deleteById(Long id) {
        User foundUser = this.getById(id);
        this.repo.delete(foundUser);
        return true;    
    }
   
}