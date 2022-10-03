package com.ceiba.invitacion.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.invitacion.puerto.repositorio.RepositorioInvitacion;
import com.ceiba.membresia.controlador.ComandoMembresiaTestDataBuilder;
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
@WebMvcTest(ComandoControladorInvitacion.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorInvitacionTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioInvitacion repositorioInvitacion;


    @Test
    void crearInvitacionExitosamente() throws Exception{

        var comandoMembresiaTestDataBuilder = new ComandoMembresiaTestDataBuilder()
                .crearPorDefecto()
                .build();

        var comandoInvitacionTestDataBuilder = new ComandoIvitacionTestDataBuilder()
                .crearPorDefecto()
                .build();

        var resultadoMvc = mockMvc.perform(post("/membresia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoMembresiaTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();


        var resultado = mockMvc.perform(post("/invitacion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoInvitacionTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();

        var respuesta = objectMapper.readValue(jsonResult,RespuestaInvitacion.class);

        var invitacionGuardada = repositorioInvitacion.obtener(respuesta.getValor());


        Assertions.assertEquals(1l,invitacionGuardada.getMembresia().getId());
        Assertions.assertEquals(invitacionGuardada.getIdentificacionInvitado(),comandoInvitacionTestDataBuilder.getIdentificacionInvitado());
        Assertions.assertEquals(invitacionGuardada.getNombreInvitado(),comandoInvitacionTestDataBuilder.getNombreInvitado());

    }


}
