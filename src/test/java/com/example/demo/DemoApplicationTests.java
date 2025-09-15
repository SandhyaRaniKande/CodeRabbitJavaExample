package com.example.demo;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

    @Mock
    UserRepository repo;
    @InjectMocks
    UserServiceImpl svc;

    @Test
    void createUserHappyPath() {
        when(repo.save(any())).thenAnswer(inv -> { User u = inv.getArgument(0); u.setId(1L); return u; });
        UserDto req = new UserDto(); req.setUsername("a"); req.setPassword("p");
        User out = svc.createUser(req);
        assertNotNull(out.getId());
        assertEquals("a", out.getUsername());
    }

}
