package com.br.uninter.atividade_pratica.controller;

import com.br.uninter.atividade_pratica.dto.request.ProdutoRequestDTO;
import com.br.uninter.atividade_pratica.dto.response.ProdutoResponseDTO;
import com.br.uninter.atividade_pratica.model.Produto;
import com.br.uninter.atividade_pratica.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping()
    public ResponseEntity criarProduto(@RequestBody @Valid ProdutoRequestDTO request){

        Produto novoProduto = new Produto();
        novoProduto.setNome(request.nome());
        novoProduto.setPreco(request.preco());
        novoProduto.setEstoque(request.estoque());

        Produto produtoSalvo = produtoRepository.save(novoProduto);

        var produtoDto = new ProdutoResponseDTO(produtoSalvo.getId(), produtoSalvo.getNome(), produtoSalvo.getPreco(), produtoSalvo.getEstoque());

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity consultaProdutoId(@PathVariable Long id){

        var produtoId = produtoRepository.findById(id);

        if(produtoId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        var produto = produtoId.get();

        var produtoDto = new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque());

        return ResponseEntity.ok(produtoDto);
    }

    @GetMapping()
    public ResponseEntity<List<ProdutoResponseDTO>> listaProdutos(){

        var listaProdutos = produtoRepository.findAll();

        List<ProdutoResponseDTO> listaProdutoDto = new ArrayList<>();

        for(int i = 0; i < listaProdutos.size(); i++){

            var produtoAtual = listaProdutos.get(i);
            listaProdutoDto.add(new ProdutoResponseDTO(produtoAtual.getId(), produtoAtual.getNome(), produtoAtual.getPreco(), produtoAtual.getEstoque()));
        }

        return ResponseEntity.ok(listaProdutoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProdutoId(@PathVariable Long id){

        var produtoId = produtoRepository.findById(id);

        if(produtoId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        produtoRepository.deleteById(id);

        return ResponseEntity.ok("Produto deletado com sucesso");

    }

    @PutMapping("/{id}")
    public ResponseEntity editarProdutoId(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO request){

        var produtoId = produtoRepository.findById(id);

        if (produtoId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        var produto = produtoId.get();

         produto.setNome(request.nome());
         produto.setPreco(request.preco());
         produto.setEstoque(request.estoque());

         produtoRepository.save(produto);

         var produtoDto = new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque());

         return ResponseEntity.ok(produtoDto);



    }
}
