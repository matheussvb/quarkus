package org.acme.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jdk.nashorn.internal.runtime.options.Option;
import org.acme.dto.CadastrarProdutoDTO;
import org.acme.entity.Produto;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("produtos")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ProdutoResource {

    @GET
    public List<Produto> buscarTodosProdutos() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void adicionarProdutos(CadastrarProdutoDTO cadastrarProdutoDTO) {
        Produto p = new Produto();
        p.nome = cadastrarProdutoDTO.nome;
        p.valor = cadastrarProdutoDTO.valor;
        p.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void alterarProdutoById(@PathParam("id") Long id,
                                   CadastrarProdutoDTO dto) {
        Optional<PanacheEntityBase> p = Produto.findByIdOptional(id);
        if (!p.isPresent())
            throw new RuntimeException("Produto não localizado!");

        Produto produto = (Produto) p.get();
        produto.nome = dto.nome;
        produto.valor = dto.valor;
        produto.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletarProdutoById(@PathParam("id") Long id) {
        Optional<Produto> produtoOp = Produto.findByIdOptional(id);
        produtoOp.ifPresent(Produto::delete);
    }

}
