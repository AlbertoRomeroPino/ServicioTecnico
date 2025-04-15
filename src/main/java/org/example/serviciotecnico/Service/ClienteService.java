package org.example.serviciotecnico.Service;

import org.example.serviciotecnico.Model.Entity.Cliente;
import org.example.serviciotecnico.Model.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(cliente == null) {
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
        if(cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getId());

       if (clienteOptional != null) {
            Cliente newCliente = clienteOptional.get();
            newCliente.setNombre(cliente.getNombre());
            newCliente.setNumeroTelefono(cliente.getNumeroTelefono());
            newCliente.setDni( cliente.getDni());

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
}
