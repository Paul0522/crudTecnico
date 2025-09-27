package com.example.crudTecnico.cuenta.services;

import com.example.crudTecnico.cuenta.models.CuentaModel;
import com.example.crudTecnico.cuenta.models.MovimientoModel;
import com.example.crudTecnico.cuenta.repositories.CuentaRepository;
import com.example.crudTecnico.cuenta.repositories.MovimientoRepository;
import excepcion.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {
    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    public ArrayList<MovimientoModel> obtenerMovimientos(){
        List<MovimientoModel> movimientos = movimientoRepository.findAll();
        System.out.println("Movimiento encontrado: " + movimientos.size());
        movimientos.forEach(m -> System.out.println(m));
        return (ArrayList<MovimientoModel>) movimientos;
    }

    public MovimientoModel crearMovimiento(String numeroCuenta, BigDecimal valor, String tipo){
        CuentaModel cuentaModel = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("No existe cuenta"));

        BigDecimal nuevoSaldo = cuentaModel.getSaldoInicial().add(valor);
        if(nuevoSaldo.compareTo(BigDecimal.ZERO) <= 0){
            throw new InsufficientFundsException("Saldo no disponible");
        }

        cuentaModel.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuentaModel);

        MovimientoModel movimientoModel = new MovimientoModel();
        movimientoModel.setCuenta(cuentaModel);
        movimientoModel.setValor(valor);
        movimientoModel.setSaldo(nuevoSaldo);
        movimientoModel.setTipoMovimiento(tipo);

        return movimientoRepository.save(movimientoModel);

    }

    public Optional<MovimientoModel> obtenerPorId(Long id){
        return movimientoRepository.findById(id);
    }

    public boolean eliminarMovimiento(Long id){
        try {
            movimientoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
