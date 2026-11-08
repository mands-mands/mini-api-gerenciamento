package com.br.uninter.atividade_pratica.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clientId;

    @Column(name = "produto_id", nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    private Integer quantidade;

    public Pedido(){

    }

    public Long getId(){
        return id;
    }

    public Long getClientId(){
        return clientId;
    }

    public void setClientId(Long clientId){
        this.clientId = clientId;
    }

    public Long getProdutoId(){
        return produtoId;
    }

    public void setProdutoId(Long produtoId){
        this.produtoId = produtoId;
    }

    public Integer getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }
}
