package com.br.uninter.atividade_pratica.controller;

import com.br.uninter.atividade_pratica.dto.request.ClienteRequestDTO;
import com.br.uninter.atividade_pratica.dto.response.ClienteResponseDTO;
import com.br.uninter.atividade_pratica.model.Cliente;
import com.br.uninter.atividade_pratica.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping()
    public ResponseEntity criarCliente(@RequestBody @Valid ClienteRequestDTO request){

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(request.nome());
        novoCliente.setClienteDesde(request.clienteDesde());

        Cliente clienteSalvo = clienteRepository.save(novoCliente);

        var dto = new ClienteResponseDTO(
                clienteSalvo.getId(),
                clienteSalvo.getNome(),
                clienteSalvo.getClienteDesde()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    @GetMapping("/{id}")
    public ResponseEntity consultaClienteId(@PathVariable Long id){

        var clienteId = clienteRepository.findById(id);

        if(clienteId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var cliente = clienteId.get();

        var dtoCliente = new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getClienteDesde());

        return ResponseEntity.ok(dtoCliente);
    }

    @GetMapping()
    public ResponseEntity<List<ClienteResponseDTO>> listaClientes(){

        var listaClientes = clienteRepository.findAll();

        List<ClienteResponseDTO> listaClienteDto = new ArrayList<>();

        for(int i = 0; i < listaClientes.size(); i++){

            var clienteAtual = listaClientes.get(i);
            listaClienteDto.add(new ClienteResponseDTO(clienteAtual.getId(), clienteAtual.getNome(), clienteAtual.getClienteDesde()));
        }

        return ResponseEntity.ok(listaClienteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarClienteId(@PathVariable Long id){

        var clienteId = clienteRepository.findById(id);

        if(clienteId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var cliente = clienteId.get();

        clienteRepository.deleteById(id);

        return ResponseEntity.ok("Cliente deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity editarCliente(@PathVariable Long id, @RequestBody @Valid ClienteRequestDTO request){

        var clienteId = clienteRepository.findById(id);

        if(clienteId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var cliente = clienteId.get();

        cliente.setNome(request.nome());
        cliente.setClienteDesde(request.clienteDesde());

        clienteRepository.save(cliente);

        var clienteDto = new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getClienteDesde());

        return ResponseEntity.ok(clienteDto);
    }
}
