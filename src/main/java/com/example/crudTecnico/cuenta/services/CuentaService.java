package com.example.crudTecnico.cuenta.services;

import com.example.crudTecnico.cuenta.models.CuentaModel;
import com.example.crudTecnico.cuenta.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    //Obtener datos de la cuenta
    public ArrayList<CuentaModel> obtenerCuentas(){
        return (ArrayList<CuentaModel>) cuentaRepository.findAll();
    }

    //Guardar la información del cuenta
    public CuentaModel guardarCuenta(CuentaModel cuenta){
        System.out.println("Cuenta a guardar:" +  cuenta);
        if(cuentaRepository.existsByNumeroCuenta(cuenta.getNumeroCuenta())){
            throw new IllegalArgumentException("La cuenta ya esta registrada: "
                    +cuenta.getNumeroCuenta());
        }
        return cuentaRepository.save(cuenta);
    }

    //Obtener por ID de cuenta
    public Optional<CuentaModel> obtenerPorId(Long id){
        return cuentaRepository.findById(id);
    }

    public boolean eliminarCuenta(Long id){
        try {
            cuentaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
