package com.example.crudTecnico.cliente;

import com.example.crudTecnico.cliente.models.ClienteModel;
import com.example.crudTecnico.cliente.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteIntegrationTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void testGuardarYBuscarCliente() {
        ClienteModel cliente = new ClienteModel();
        cliente.setClienteId("CLI002");
        cliente.setContrasenia("abcd");
        cliente.setEstado(true);
        cliente.setNombre("Maria Lopez");
        cliente.setIdentificacion("1729999999");
        cliente.setEdad(25);
        cliente.setTelefono("0991234567");
        cliente.setDireccion("Guayaquil");

        ClienteModel saved = clienteRepository.save(cliente);

        assertThat(saved.getId()).isNotNull();
        assertThat(clienteRepository.existsByClienteId("CLI002")).isTrue();
    }
}
