package com.example.crudTecnico.cuenta.repositories;

import com.example.crudTecnico.cuenta.models.CuentaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<CuentaModel, Long> {
    Optional<CuentaModel> findByNumeroCuenta(String numeroCuenta);
    ArrayList<CuentaModel> findByClienteId(String clienteId);
    boolean existsByNumeroCuenta(String identificacion);
}
