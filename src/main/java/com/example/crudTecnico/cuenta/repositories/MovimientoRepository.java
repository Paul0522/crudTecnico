package com.example.crudTecnico.cuenta.repositories;

import com.example.crudTecnico.cuenta.models.CuentaModel;
import com.example.crudTecnico.cuenta.models.MovimientoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface MovimientoRepository extends JpaRepository<MovimientoModel, Long> {
    ArrayList<MovimientoModel> findByCuentaAndFechaBetween(CuentaModel cuenta, LocalDateTime desde, LocalDateTime hasta);
}
