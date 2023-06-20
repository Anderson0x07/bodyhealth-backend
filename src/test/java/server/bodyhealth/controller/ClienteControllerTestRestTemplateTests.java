package server.bodyhealth.controller;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.entity.MetodoPago;

import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTestRestTemplateTests {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    @Order(2)
    public void testListarClientes() {
        ResponseEntity<List<ClienteDto>> response = testRestTemplate.exchange("/cliente/all", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        List<ClienteDto> clientes = response.getBody();

        Assert.assertNotNull(clientes);
        Assert.assertFalse(clientes.isEmpty());
        Assert.assertEquals(6, clientes.size());
    }

}
