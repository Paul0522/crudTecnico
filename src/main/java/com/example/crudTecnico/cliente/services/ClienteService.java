package com.example.crudTecnico.cliente.services;

import com.example.crudTecnico.cliente.models.ClienteModel;
import com.example.crudTecnico.cliente.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    //Obtener todos los cliente
    public ArrayList<ClienteModel> obtenerClientes(){
        return (ArrayList<ClienteModel>) clienteRepository.findAll();
    }

    //Guardar la información del cliente
    public ClienteModel guardarCliente(ClienteModel cliente){
        System.out.println("Cliente a guardar:" +  cliente);
        if(clienteRepository.existsByIdentificacion(cliente.getIdentificacion())){
            throw new IllegalArgumentException("La identificacion ya esta registrada: "
                    +cliente.getIdentificacion());
        }
        if(clienteRepository.existsByClienteId(cliente.getClienteId())){
            throw new IllegalArgumentException("El código de cliente ya esta registrado: "
                    +cliente.getClienteId());
        }

        return clienteRepository.save(cliente);
    }

    //Obtener por ID de cliente
    public Optional<ClienteModel> obtenerPorId(Long id){
        return clienteRepository.findById(id);
    }

    public boolean eliminarCliente(Long id){
        try {
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
