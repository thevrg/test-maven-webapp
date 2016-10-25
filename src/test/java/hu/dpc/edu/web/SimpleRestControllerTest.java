package hu.dpc.edu.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrg on 24/10/16.
 */
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleRestControllerTest {

    @Autowired
    WebApplicationContext ctx;

    MockMvc mockMvc;

    @Autowired
    MyModel model;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
        model.setMessage("test message");
    }

    @Test
    public void getSimpleMessage() throws Exception {

        mockMvc.perform(get("/simple/message")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string("test message"));
    }

    @Test
    public void testPUT() throws Exception {

        mockMvc.perform(put("/simple/message")
                .contentType(MediaType.TEXT_PLAIN)
                .content("other message"))
                .andExpect(status().isOk());

        assertEquals("other message", model.getMessage());
    }

}