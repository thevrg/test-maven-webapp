package hu.dpc.edu.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vrg on 24/10/16.
 */
@WebAppConfiguration
@ContextConfiguration(classes = UserControllerTest.TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    @Configuration
    @ComponentScan
    @EnableWebMvc
    public static class TestConfig {
        @Bean
        public MockingPostProcessor modelReplacer() {
            return new MockingPostProcessor(MyModel.class);
        }
    }

    @Autowired
    WebApplicationContext ctx;

    MockMvc mockMvc;

    @Autowired
    MyModel model;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
        reset(model);
    }

    @Test
    public void getUserShouldUseFindUserById() throws Exception {
        //Given
        User testUser = new User("Bela", "Kovacs", 1234L);
        String testUserJSON = new ObjectMapper().writeValueAsString(testUser);

        when(model.findUserById(1234L)).thenReturn(testUser);

        //When
        mockMvc.perform(get("/users/1234")
                .accept(MediaType.APPLICATION_JSON))
        //Then
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().json(testUserJSON));

        verify(model).findUserById(1234L);
    }

    @Test
    public void testPUTWithExistingUser() throws Exception {
        //Given
        User changedUser = new User("Ferenc", "Nagy");
        String changedUserJSON = new ObjectMapper().writeValueAsString(changedUser);

        User userShouldBePassedToModel = new User("Ferenc", "Nagy", 1234L);


        //When
        mockMvc.perform(put("/users/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .content(changedUserJSON))
        //Then
                .andExpect(status().isOk());

        verify(model).updateUser(userShouldBePassedToModel);
    }

    @Test
    public void testPUTWithNonExistingUser() throws Exception {
        //Given
        Mockito.doThrow(new UserNotFoundException("User is not found", 12345L))
                .when(model).updateUser(any(User.class));

        User changedUser = new User("Ferenc", "Nagy");
        String changedUserJSON = new ObjectMapper().writeValueAsString(changedUser);



        //When
        mockMvc.perform(put("/users/12345")
                .contentType(MediaType.APPLICATION_JSON)
                .content(changedUserJSON))
                //Then
                .andExpect(status().isNotFound());

    }

}