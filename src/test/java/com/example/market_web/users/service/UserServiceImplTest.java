package com.example.market_web.users.service;

import com.example.market_web.users.entity.UserEntity;
import com.example.market_web.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a UserEntity in method findUserById")
    public void returnUserEntityInFindUserById() {
        Integer id = 1;
        UserEntity expectedResponse = new UserEntity();
        expectedResponse.setId(id);
        expectedResponse.setDocument(BigInteger.valueOf(1033100001L));
        expectedResponse.setName("name");

        when(userRepository.findById(id)).thenReturn(Optional.of(expectedResponse));

        assertEquals(expectedResponse, userService.findUserById(id));
    }

    @Test
    @DisplayName("throw an Exception in method findUserById")
    public void throwExceptionInFindUserById() {
        Integer id = 1;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.findUserById(id));
    }
}
