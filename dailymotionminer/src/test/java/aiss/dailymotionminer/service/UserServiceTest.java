package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.dailymotion.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Get an user by ID")
    void getUser() {
        User user = userService.getUser("x4x8op0");
        assertNotNull(user, "The user cannot be null");
        System.out.println(user);
    }
}
