package com.aula.aulaSpring.controller;

import com.aula.aulaSpring.entity.Venda;
import com.aula.aulaSpring.service.VendaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @ApiOperation(value = "Add a new Order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the registered order", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PostMapping("/venda")
    public Venda addVenda(@RequestBody Venda venda) {
        return vendaService.add(venda);
    }

    @ApiOperation(value = "Finds a order by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the user", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/venda/{id}")
    public Venda getVenda(@PathVariable(value = "id") long id) {
        return vendaService.get(id);
    }

    @ApiOperation(value = "Modify order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the order who was modified", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Log not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PutMapping("/venda/{id}")
    public Venda updateVenda(@RequestBody Venda venda, @PathVariable(value = "id") long id) {
        Optional<Venda> compra = vendaService.findById(id);
        if(compra.isPresent()){
            venda.setID(compra.get().getID());
            return vendaService.update(venda);
        }
        return null;
    }
}