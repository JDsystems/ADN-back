package com.ceiba.membresia.controlador;

import com.ceiba.ApplicationMock;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorMembresia.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorMembresiaTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void deberiaListarTodasLasMembresiasActivasDeFormaExitosa()throws Exception {
        var resultado = mockMvc.perform(get("/membresia/activa"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray())
                .andExpect(jsonPath("$[*]", Matchers.hasSize(4)))
                .andExpect(jsonPath("$[*].id",Matchers.contains(4,3,2,1)))
                .andExpect(jsonPath("$[*].cliente.nombre", Matchers.contains("Cliente 4","Cliente 3","Cliente 2","Cliente 1")))
                .andExpect(jsonPath("$[0].tipo", Matchers.equalTo("ORO")))
                .andReturn();
    }
}
