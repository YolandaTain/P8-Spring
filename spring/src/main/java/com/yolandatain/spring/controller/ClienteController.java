package com.yolandatain.spring.controller;

import com.yolandatain.spring.entity.Cliente;
import com.yolandatain.spring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")

public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @SuppressWarnings("null")
    @PostMapping("/cliente/nuevo")
    public ResponseEntity<?> agregarCliente(@RequestBody Cliente cliente) {
        Cliente clienteGuardado = clienteRepository.save(cliente);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Cliente agregado correctamente");
        response.put("cliente", clienteGuardado);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/clientes/editar/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        @SuppressWarnings("null")

        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente clienteActual = clienteExistente.get();
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmail(cliente.getEmail());
            clienteRepository.save(clienteActual);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Cliente editado correctamente");
            response.put("cliente", clienteActual);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @SuppressWarnings("null")
    @GetMapping("/clientes/{id}")
    public Cliente obtenerClientePorId(@PathVariable Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer id) {
        return clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return ResponseEntity.ok("Cliente eliminado correctamente");
        }).orElseGet(() -> new ResponseEntity<>("Cliente no encontrado con el ID:" + id, HttpStatus.NOT_FOUND));
    }
};
