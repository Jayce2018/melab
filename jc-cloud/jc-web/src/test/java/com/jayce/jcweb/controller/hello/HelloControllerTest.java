package com.jayce.jcweb.controller.hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayce.jcweb.JcWebApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JcWebApplication.class})
@WebAppConfiguration
class HelloControllerTest {
    /*@Autowired
    private WebApplicationContext context;*/

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void testSend() throws Exception {
        Long id = 1l;
        //调用接口，传入添加的用户参数
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/hello/say/hello")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(id))
        );
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    void sayHello() throws Exception {
        //调用接口，传入添加的用户参数
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/hello/say/hello")
                .accept(MediaType.APPLICATION_JSON)
        );
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }
}