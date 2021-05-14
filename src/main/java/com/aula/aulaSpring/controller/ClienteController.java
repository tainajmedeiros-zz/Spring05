package com.aula.aulaSpring.controller;

import com.aula.aulaSpring.entity.Cliente;
import com.aula.aulaSpring.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping( "/api")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Add a new User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the registered user", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PostMapping("/cliente")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente pessoa){
        try{
            return new ResponseEntity<>((Cliente) clienteService.add(pessoa), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "Finds a user by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the user", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "User not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable(value = "id") long id) {
        try{
            return new ResponseEntity<>((Cliente) clienteService.get(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Modify user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the user who was modified", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Log not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente pessoa, @PathVariable(value = "id") long id) {
        try{
            Optional<Cliente> cliente = clienteService.findById(id);
            if(cliente.isPresent()){
                pessoa.setID(cliente.get().getID());
                return new ResponseEntity<>((Cliente) clienteService.update(pessoa), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Modify quantity user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return quantity the user who was in the system", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Log not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/quantidadeClientes")
    public long qtdeCliente() {
        return clienteService.quantidaClientes();
    }
}
