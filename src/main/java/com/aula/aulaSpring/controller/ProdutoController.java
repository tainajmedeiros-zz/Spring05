package com.aula.aulaSpring.controller;

import com.aula.aulaSpring.entity.Produto;
import com.aula.aulaSpring.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation(value = "Add a new Product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the registered product", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PostMapping("/produto")
    public Produto addProduto(@RequestBody Produto produto) {
        return produtoService.add(produto);
    }

    @ApiOperation(value = "Finds a product by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the product", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/produto/{id}")
    public Produto getProduto(@PathVariable(value = "id") long id) {
        return produtoService.get(id);
    }

    @ApiOperation(value = "Modify product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the product who was modified", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Log not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PutMapping("/produto/{id}")
    public Produto updateProduto(@RequestBody Produto produto, @PathVariable(value = "id") long id) {
        Optional<Produto> prod = produtoService.findById(id);
        if(prod.isPresent()){
            produto.setID(prod.get().getID());
            return produtoService.update(produto);
        }
        return null;
    }
}
