package com.ceiba.cliente;

import com.ceiba.ApplicationMock;
import com.ceiba.membresia.controlador.ComandoControladorMembresia;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorMembresia.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorClienteTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void deberiaListarTodosLosClientesDeFormaExitosa()throws Exception {
        var resultado = mockMvc.perform(get("/cliente/todos"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray())
                .andExpect(jsonPath("$[*]", Matchers.hasSize(4)))
                .andExpect(jsonPath("$[*].id", Matchers.contains(1,2,3,4)))
                .andExpect(jsonPath("$[*].nombre", Matchers.contains("Cliente 1","Cliente 2","Cliente 3","Cliente 4")))
                .andExpect(jsonPath("$[*].nombre", Matchers.containsInRelativeOrder("Cliente 1","Cliente 2","Cliente 3","Cliente 4")))
                .andExpect(jsonPath("$[0].nombre", Matchers.equalTo("Cliente 1")))
                .andExpect(jsonPath("$[1].nombre", Matchers.equalTo("Cliente 2")))
                .andExpect(jsonPath("$[2].nombre", Matchers.equalTo("Cliente 3")))
                .andExpect(jsonPath("$[3].nombre", Matchers.equalTo("Cliente 4")))
                .andReturn();

    }
}
