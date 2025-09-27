package com.example.crudTecnico.cliente.controllers;

import com.example.crudTecnico.cliente.models.ClienteModel;
import com.example.crudTecnico.cliente.repositories.ClienteRepository;
import com.example.crudTecnico.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;

    //Metodo GET para obtener los clientes
    @GetMapping()
    public ArrayList<ClienteModel> obtenerClientes() {
        return clienteService.obtenerClientes();
    }

    //Metodo POS para guardar la información de clientes
    @PostMapping()
    public ResponseEntity<?> guardarCliente(@Validated @RequestBody ClienteModel cliente){
        try{
            ClienteModel clienteModel = this.clienteService.guardarCliente(cliente);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    //Metodo GET para obtener la información de clientes por el ID
    @GetMapping("/{id}")
    public Optional<ClienteModel> obtenerClientePorId(@PathVariable("id") Long id){
        return this.clienteService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarClientePorId(@PathVariable("id") Long id){
        boolean okCliente = this.clienteService.eliminarCliente(id);
        if(okCliente){
            return "Cliente Eliminado "+id;
        }else {
            return "Cliente no encontrado "+id;
        }
    }
}
