package com.example.crudTecnico.cuenta.controllers;

import com.example.crudTecnico.cuenta.models.CuentaModel;
import com.example.crudTecnico.cuenta.models.MovimientoModel;
import com.example.crudTecnico.cuenta.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @GetMapping()
    public ArrayList<MovimientoModel> obtenerMovimientos(){
        return movimientoService.obtenerMovimientos();
    }

    @PostMapping
    public ResponseEntity<?> crearMovimiento(@RequestBody CreateMovementRequest req) {
        System.out.println("Movimiento:" +  req);
        try {
            MovimientoModel movimiento = movimientoService.crearMovimiento(
                    req.getNumeroCuenta(),
                    req.getValor(),
                    req.getTipoMovimiento()
            );

            System.out.println("Movimientos:" +  req);
            return ResponseEntity.ok(movimiento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    static class CreateMovementRequest{
        private String numeroCuenta;
        private BigDecimal valor;
        private String tipoMovimiento;

        public String getNumeroCuenta() {
            return numeroCuenta;
        }

        public void setNumeroCuenta(String numeroCuenta) {
            this.numeroCuenta = numeroCuenta;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public String getTipoMovimiento() {
            return tipoMovimiento;
        }

        public void setTipoMovimiento(String tipoMovimiento) {
            this.tipoMovimiento = tipoMovimiento;
        }
    }

    @GetMapping("/{id}")
    public Optional<MovimientoModel> obtenerMovimientoPorId(@PathVariable("id") Long id){
        return this.movimientoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarMovimiento(@PathVariable("id") Long id){
        boolean okCliente = this.movimientoService.eliminarMovimiento(id);
        if(okCliente){
            return "Movimiento Eliminado"+id;
        }else {
            return "Movimiento no encontrado"+id;
        }
    }

}
