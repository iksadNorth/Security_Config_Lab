package com.example.testsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestSecurityApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads1() throws Exception {
        mvc.perform(get("/api/case1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void contextLoads2() throws Exception {
        mvc.perform(get("/api/case2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void contextLoads3() throws Exception {
        mvc.perform(get("/api/case3"))
                .andDo(print())
                .andExpect(status().is(403));
    }

}
