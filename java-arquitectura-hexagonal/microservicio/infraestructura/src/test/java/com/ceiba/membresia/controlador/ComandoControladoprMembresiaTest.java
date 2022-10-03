package com.ceiba.membresia.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.membresia.puerto.repositorio.RepositorioMembresia;
import com.ceiba.membresia.servicio.ConstatesMembresia;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorMembresia.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladoprMembresiaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioMembresia repositorioMembresia;


    @Test
    void crearMembresiaExitosamente() throws Exception{
        var comandoMembresiaTestDataBuilder = new ComandoMembresiaTestDataBuilder()
                .crearPorDefecto()
                .build();


        var resultado = mockMvc.perform(post("/membresia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoMembresiaTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();

        var respuesta = objectMapper.readValue(jsonResult, RespuestaMembresia.class);

        var membresiaGuardada = repositorioMembresia.obtener(5l);

        Assertions.assertEquals(membresiaGuardada.getValorPagado().longValue(), ConstatesMembresia.VALOR_MEMBRESIA_ORO);
        Assertions.assertTrue(membresiaGuardada.esActiva());
        Assertions.assertTrue(membresiaGuardada.esMembresiaOro());
        Assertions.assertEquals(5l,membresiaGuardada.getId());
    }

    @Test
    void cancelarMembresiaExitosamente() throws Exception{
        var comandoMembresiaTestDataBuilder = new ComandoMembresiaTestDataBuilder()
                .crearPorDefecto()
                .build();


        var resultado = mockMvc.perform(post("/membresia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoMembresiaTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        mockMvc.perform(post("/membresia/cancelar/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        var membresiaCancelada = repositorioMembresia.obtener(1l);
        System.out.println(membresiaCancelada.getEstado());
        Assertions.assertTrue(membresiaCancelada.esCancelada());
    }

}
