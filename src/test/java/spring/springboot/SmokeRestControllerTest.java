package spring.springboot;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.springboot.controller.RestController;

@SpringBootTest
class SmokeRestControllerTest {

    @Autowired
    private RestController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
