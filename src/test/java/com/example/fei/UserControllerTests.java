package com.example.fei;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

// RANDOM_PORT 指定随机端口
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wc;

    @BeforeEach
    public void beforeSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }

    @Test
    void fei1() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/feiJson"))
                // .andDo(MockMvcResultHandlers.print())
                // .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("-----------------------");
        System.out.println(result.getResponse().getContentAsString(Charset.forName("UTF-8")));
    }

    @Test
    void fei2() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/select"))
                // .andDo(MockMvcResultHandlers.print())
                // .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("-----------------------");
        System.out.println(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void fei3() { // 测试连接数据库
        Long aLong = jdbcTemplate.queryForObject("select count(*) from user", Long.class);

        System.out.println("========================");
        System.out.println(aLong);
    }
}
