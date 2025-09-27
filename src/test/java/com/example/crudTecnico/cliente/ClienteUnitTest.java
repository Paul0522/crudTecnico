package com.example.crudTecnico.cliente;

import com.example.crudTecnico.cliente.models.ClienteModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteUnitTest {

    @Test
    public void testCrearCliente() {
        ClienteModel cliente = new ClienteModel();
        cliente.setClienteId("CLI011");
        cliente.setContrasenia("1234");
        cliente.setEstado(true);
        cliente.setNombre("Juan Pérez");
        cliente.setIdentificacion("0503311938");
        cliente.setEdad(30);
        cliente.setTelefono("0987654321");
        cliente.setDireccion("Quito");

        assertEquals("CLI011", cliente.getClienteId());
        assertEquals("1234", cliente.getContrasenia());
        assertTrue(cliente.getEstado());
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("0503311938", cliente.getIdentificacion());
    }
}
