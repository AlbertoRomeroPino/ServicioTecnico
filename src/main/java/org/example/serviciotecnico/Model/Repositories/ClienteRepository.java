package org.example.serviciotecnico.Model.Repositories;

import java.util.List;

import org.example.serviciotecnico.Model.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Busca un cliente por su DNI.
     *
     * @param dni el DNI del cliente
     * @return el cliente encontrado, o null si no se encuentra
     */
    @Query(
            value = ("SELECT * FROM cliente AS cl WHERE cl.dni = ?1"),
            nativeQuery = true
    )
    Cliente findByDni(String dni);

    /**
     * Busca un cliente por su nombre. Se va a filtrar si pones una parte del nombre
     *
     * @param nombre el nombre del cliente
     * @return el cliente encontrado, o null si no se encuentra
     */

    @Query(
            value = "SELECT * FROM cliente AS cl WHERE cl.nombre LIKE %?1%",
            nativeQuery = true
    )
    List<Cliente> findByNombre(String nombre);

}
