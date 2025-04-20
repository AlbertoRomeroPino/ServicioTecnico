package org.example.serviciotecnico.Service;

import org.example.serviciotecnico.Model.Entity.Cliente;
import org.example.serviciotecnico.Model.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Crea un nuevo cliente en la base de datos.
     *
     * @param cliente el cliente a crear
     * @return el cliente creado
     */
    public Cliente createCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el cliente en la base de datos.", e);
        }
    }

    /**
     * Actualiza un cliente en la base de datos.
     *
     * @param cliente el cliente a actualizar
     * @return el cliente actualizado
     */
    public Cliente updateCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getId());

        if (clienteOptional != null) {
            Cliente newCliente = clienteOptional.get();
            newCliente.setNombre(cliente.getNombre());
            newCliente.setNumeroTelefono(cliente.getNumeroTelefono());
            newCliente.setDni(cliente.getDni());

            return clienteRepository.save(newCliente);
        } else {
            throw new RuntimeException("No hay cliente con ese id: " + cliente.getId());
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param id del cliente a eliminar
     * @return el cliente eliminado
     */
    public Cliente deleteCliente(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            clienteRepository.delete(cliente);
            return cliente;
        } else {
            throw new RuntimeException("No hay cliente con ese id: " + id);
        }
    }

    /**
     * Busca todos los clientes en la base de datos.
     *
     * @return una lista de clientes
     */
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.size() > 0) {
            return clientes;
        } else {
            throw new RuntimeException("No hay clientes en la base de datos.");
        }
    }

    /**
     * Busca un cliente por su id.
     *
     * @param id del cliente
     * @return el cliente encontrado
     */
    public Cliente getClienteById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        } else {
            throw new RuntimeException("No hay cliente con ese id: " + id);
        }
    }

    /**
     * Busca un cliente por su nombre (Si hay más de un cliente con el mismo nombre,
     * devuelve una lista).
     *
     * @param nombre del cliente
     * @return una lista de clientes encontrados
     */
    public List<Cliente> getClienteByNombre(String nombre) {
        List<Cliente> clientes = clienteRepository.findByNombre(nombre);
        if (clientes.size() > 0) {
            return clientes;
        } else {
            throw new RuntimeException("No hay cliente con ese nombre: " + nombre);
        }
    }

    /**
     * Busca un cliente por su DNI.
     *
     * @param dni del cliente
     * @return el cliente encontrado
     */
    public Cliente getClienteByDni(String dni) {
        Cliente cliente = clienteRepository.findByDni(dni);
        if (cliente != null) {
            return cliente;
        } else {
            throw new RuntimeException("No hay cliente con ese dni: " + dni);
        }
    }
}
