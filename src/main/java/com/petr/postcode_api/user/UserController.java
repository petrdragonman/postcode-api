package com.petr.postcode_api.user;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petr.postcode_api.common.Result;
import com.petr.postcode_api.common.StatusCode;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    
    @GetMapping
    public Result getAllUsers() {
        List<User> foundUsers = this.userService.getAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", foundUsers);
    }

    @GetMapping("{id}")
    public Result getUserById(@PathVariable Long id) throws Exception {
        User foundUser = this.userService.getById(id);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", foundUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Result createUser(@RequestBody @Valid CreateUserDTO data) {
        User newUser = this.userService.createUser(data);
        return new Result(true, StatusCode.SUCCESS,"Add Success", newUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public Result updateUserById(@PathVariable Long id, @Valid @RequestBody UpdateUserDTO data) {
        User updatedUser = this.userService.updateUser(id, data);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        boolean wasDeleted = this.userService.deleteById(id);
        return new Result(wasDeleted, StatusCode.SUCCESS, "Delete Success");
    }
}