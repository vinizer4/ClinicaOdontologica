package io.github.vinizer4;

import io.github.vinizer4.rest.controller.DentistaController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(DentistaController.class)
public class DentistaControllerTest {
    private MockMvc mvc;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void buscarDentista() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/dentistas", "Java")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}
