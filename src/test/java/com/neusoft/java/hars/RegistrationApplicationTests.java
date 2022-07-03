package com.neusoft.java.hars;

import com.neusoft.java.hars.entity.Appointment;
import com.neusoft.java.hars.entity.Myyuyue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class RegistrationApplicationTests {

    @Test
    void contextLoads() {
        Page<Myyuyue> listPage = Page.empty();
        System.out.println(listPage);
    }

}
