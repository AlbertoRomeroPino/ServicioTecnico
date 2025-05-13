package org.example.serviciotecnico.Model.Repositories;

import org.example.serviciotecnico.Model.Entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, String> {

    @Query(
            value = "SELECT * from Tecnico AS tec where tec.apodo = ?1",
            nativeQuery = true
    )
    Optional<Tecnico> findByApodo(String apodo);

    /**
     * Busca un tecnico por su numero de telefono
     *
     * @param numeroTelefono el telefono del tecnico
     * @return el tecnico con ese numero de telefono
     */
    @Query(
            value = "SELECT * from Tecnico AS tec where tec.numero_telefono = ?1",
            nativeQuery = true
    )
    Optional<Tecnico> findByNumeroTelefono(String numeroTelefono);
}
