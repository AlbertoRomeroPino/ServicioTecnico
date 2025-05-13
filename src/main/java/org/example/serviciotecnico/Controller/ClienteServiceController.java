package org.example.serviciotecnico.Controller;


import org.example.serviciotecnico.Exceptions.RecordNotFoundException;
import org.example.serviciotecnico.Model.Entity.Cliente;
import org.example.serviciotecnico.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteServiceController {

    @Autowired
    ClienteService clienteService;

    /**
     * Elimina un Cliente de la base de datos.
     *
     * @param id del cliente a eliminar
     * @return HttpStatus.ACCEPTED si se ha eliminado el tecnico
     * HttpStatus.NOT_FOUND si no se ha encontrado el tecnico.
     */
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return HttpStatus.ACCEPTED;
        } catch (RecordNotFoundException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * Crea un nuevo cliente en la base de datos.
     *
     * @param cliente que se va a almacenar
     * @return el cliente creado
     */
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {

        Cliente clienteTemp = clienteService.createCliente(cliente);
        return ResponseEntity.ok(clienteTemp);
    }

    /**
     * Actualiza un cliente en la base de datos.
     *
     * @param cliente a actualizar
     * @return el cliente actualizado
     * @throws RecordNotFoundException
     */
    @PutMapping("/update")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente)
            throws RecordNotFoundException {

        Cliente clienteTemp = clienteService.updateCliente(cliente);
        return ResponseEntity.ok(clienteTemp);
    }

    /**
     * Devuelve una lista de todos los clientes encontrados en la base de datos
     *
     * @return una lista de clientes
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {

        List<Cliente> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Busca un cliente segun su id
     *
     * @param id que se va a buscar
     * @return El cliente con ese id
     * @throws RecordNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id)
            throws RecordNotFoundException {

        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Devuelve una lista de clientes con el nombre. No hace falta poner el nombre completo
     *
     * @param Nombre que se va a buscar
     * @return una lista de clientes que van a tener relacion con el buscado
     */
    @GetMapping("/nombre/{Nombre}")
    public ResponseEntity<List<Cliente>> getClienteByNombre(@PathVariable String Nombre) {

        List<Cliente> clientes = clienteService.getClienteByNombre(Nombre);
        return ResponseEntity.ok(clientes);
    }

    /**
     * Busca un cliente con un dni
     *
     * @param dni del cliente que se va a vuscar
     * @return el cliente encontrado con ese dni
     * @throws RecordNotFoundException
     */
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Cliente> getClienteByDni(@PathVariable String dni)
            throws RecordNotFoundException {

        Cliente cliente = clienteService.getClienteByDni(dni);
        return ResponseEntity.ok(cliente);
    }
}
