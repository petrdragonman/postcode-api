package com.petr.postcode_api.user;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {
    @Mock
    private UserRepository repo;

    @Spy
    @InjectMocks
    private UserService userService;

    List<User> users;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.users = new ArrayList<>();
        User u1 = new User();
        u1.setUsername("petr");
        u1.setPassword("password1");
        users.add(u1);
        User u2 = new User();
        u1.setUsername("tom");
        u1.setPassword("password2");
        users.add(u2);
    }

    @Test
    public void getAll_callsFindAll() {
        userService.getAll();
        verify(repo).findAll();
    }

    @Test
    public void findAllSuccess() {
        given(repo.findAll()).willReturn(users);
        List<User> result = userService.getAll();
        assertThat(result.size()).isEqualTo(this.users.size());
        verify(repo, times(1)).findAll();
    }
}
