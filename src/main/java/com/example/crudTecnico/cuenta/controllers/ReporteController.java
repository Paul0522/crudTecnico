package com.example.crudTecnico.cuenta.controllers;

import com.example.crudTecnico.cuenta.models.CuentaModel;
import com.example.crudTecnico.cuenta.models.MovimientoModel;
import com.example.crudTecnico.cuenta.repositories.CuentaRepository;
import com.example.crudTecnico.cuenta.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    MovimientoRepository movimientoRepository;

    @GetMapping
    public Map<String, Object> reporte(
            @RequestParam String clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate hasta
            ){
        ArrayList<CuentaModel> cuentas = cuentaRepository.findByClienteId(clienteId);
        Map<String,Object> result = new HashMap<>();
        ArrayList<Map<String,Object>> detallesCuentas = new ArrayList<>();

        for(CuentaModel cuenta: cuentas){
            ArrayList<MovimientoModel> movimientos = movimientoRepository.findByCuentaAndFechaBetween(
                    cuenta,
                    desde.atStartOfDay(),
                    hasta.atTime(23, 59, 59)
            );
            Map<String,Object> detallesMovimientos = new HashMap<>();
            detallesMovimientos.put("numeroCuenta", cuenta.getNumeroCuenta());
            detallesMovimientos.put("tipoCuenta", cuenta.getTipoCuenta());
            detallesMovimientos.put("saldo", cuenta.getSaldoInicial());
            detallesMovimientos.put("movimientos",movimientos);
            detallesCuentas.add(detallesMovimientos);
        }

        result.put("clienteId",clienteId);
        result.put("cuentas",detallesCuentas);

        return result;

    }
}
