package com.example.crudTecnico.cuenta.controllers;

import com.example.crudTecnico.cliente.models.ClienteModel;
import com.example.crudTecnico.cliente.repositories.ClienteRepository;
import com.example.crudTecnico.cliente.services.ClienteService;
import com.example.crudTecnico.cuenta.models.CuentaModel;
import com.example.crudTecnico.cuenta.repositories.CuentaRepository;
import com.example.crudTecnico.cuenta.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    private CuentaService cuentaService;

    @Autowired
    ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    //Metodo GET para obtener las cuentas
    @GetMapping()
    public ArrayList<CuentaModel> obtenerCuentas() {
        return cuentaService.obtenerCuentas();
    }

    //Metodo POS para guardar la información de cuentas
    @PostMapping()
    public ResponseEntity<?> guardarCuenta(@Validated @RequestBody CuentaModel cuenta){

        String clienteId = cuenta.getClienteId();
        try {
            boolean existe = clienteRepository.existsByClienteId(clienteId);
            System.out.println(existe);
            if(existe){
                CuentaModel cuentaModel = this.cuentaService.guardarCuenta(cuenta);
                return ResponseEntity.ok(cuenta);
            } else {
                throw new IllegalArgumentException("El cliente no existe: "
                        +clienteId);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Metodo GET para obtener la información de cuenta por el ID
    @GetMapping("/{id}")
    public Optional<CuentaModel> obtenerCuentaPorId(@PathVariable("id") Long id){
        return this.cuentaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarCuentaPorId(@PathVariable("id") Long id){
        boolean okCliente = this.cuentaService.eliminarCuenta(id);
        if(okCliente){
            return "Cuenta Eliminada"+id;
        }else {
            return "Cuenta no encontrado"+id;
        }
    }

}
