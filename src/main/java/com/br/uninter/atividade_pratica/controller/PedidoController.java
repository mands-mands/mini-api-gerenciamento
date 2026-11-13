package com.br.uninter.atividade_pratica.controller;

import com.br.uninter.atividade_pratica.dto.request.PedidoRequestDTO;
import com.br.uninter.atividade_pratica.dto.response.PedidoResponseDTO;
import com.br.uninter.atividade_pratica.model.Pedido;
import com.br.uninter.atividade_pratica.repository.ClienteRepository;
import com.br.uninter.atividade_pratica.repository.PedidoRepository;
import com.br.uninter.atividade_pratica.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    private PedidoResponseDTO montaPedidoDto(Pedido pedido){

        var clienteNome = clienteRepository.findById(pedido.getClienteId()).get().getNome();
        var produtoNome = produtoRepository.findById(pedido.getProdutoId()).get().getNome();

        var pedidoDto = new PedidoResponseDTO(
                pedido.getId(),
                pedido.getClienteId(),
                clienteNome,
                pedido.getProdutoId(),
                produtoNome,
                pedido.getQuantidade());

        return pedidoDto;
    }

    @PostMapping()
    public ResponseEntity criarPedido(@RequestBody @Valid PedidoRequestDTO request){

        var clienteId = clienteRepository.findById(request.clienteId());

        if(clienteId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var produtoId = produtoRepository.findById(request.produtoId());

        if(produtoId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        Pedido novoPedido = new Pedido();
        novoPedido.setClienteId(request.clienteId());
        novoPedido.setProdutoId(request.produtoId());
        novoPedido.setQuantidade(request.quantidade());

        pedidoRepository.save(novoPedido);



        return ResponseEntity.status(HttpStatus.CREATED).body(montaPedidoDto(novoPedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity consultaPedidoId(@PathVariable Long id){

        var pedidoId = pedidoRepository.findById(id);

        if(pedidoId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        return ResponseEntity.ok(montaPedidoDto(pedidoId.get()));
    }

    @GetMapping()
    public ResponseEntity<List<PedidoResponseDTO>> listaPedidos(){

        var listaPedidos = pedidoRepository.findAll();

        List<PedidoResponseDTO> listaPedidosDto = new ArrayList<>();

        for(int i = 0; i < listaPedidos.size(); i++){

            listaPedidosDto.add(montaPedidoDto(listaPedidos.get(i)));

        }

        return ResponseEntity.ok(listaPedidosDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPedidoId(@PathVariable Long id){

        var pedidoId = pedidoRepository.findById(id);

        if(pedidoId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        pedidoRepository.deleteById(id);

        return ResponseEntity.ok("Pedido deletado com sucesso");

    }

    @PutMapping("/{id}")
    public ResponseEntity editarPedido(@PathVariable Long id, @RequestBody @Valid PedidoRequestDTO request){

        var pedidoId = pedidoRepository.findById(id);

        if(pedidoId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        var clienteId = clienteRepository.findById(request.clienteId());

        if(clienteId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var produtoId = produtoRepository.findById(request.produtoId());

        if(produtoId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        var pedido = pedidoId.get();
        pedido.setClienteId(request.clienteId());
        pedido.setProdutoId(request.produtoId());
        pedido.setQuantidade(request.quantidade());

        pedidoRepository.save(pedido);

        return ResponseEntity.ok(montaPedidoDto(pedido));

    }


}
