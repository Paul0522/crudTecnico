package com.example.crudTecnico.cliente.repositories;

import com.example.crudTecnico.cliente.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    Optional<ClienteModel> findClienteModelByClienteId(String clienteId);
    boolean existsByIdentificacion(String identificacion);
    boolean existsByClienteId(String clienteId);
    long countByClienteId(String clienteId);
}
