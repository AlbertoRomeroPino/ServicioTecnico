package org.example.serviciotecnico.Model.Repositories;

import org.example.serviciotecnico.Model.Entity.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

    /**
     * Obtiene todas las fichas de un cliente en especifico
     *
     * @param idCliente del cual se quieren obtener las fichas.
     * @return una lista de fichas.
     */
    @Query(
            value = ("Select * from Ficha As fi where fi.cliente_id = ?1"),
            nativeQuery = true
    )
    List<Ficha> getFichaByIdDeCliente(Long idCliente);

    /**
     * Obtiene todas las fichas de un tecnico en especifico.
     *
     * @param idTecnico del cual se quieren obtener las fichas.
     * @return una lista de fichas.
     */
    @Query(
            value = ("Select * from Ficha As fi where fi.tecnico_apodo = ?1"),
            nativeQuery = true
    )
    List<Ficha> getFichasbyTecnico(String idTecnico);

    /**
     * Obtiene todas las fichas de un dia especifico.
     *
     * @param Day
     * @return
     */
    @Query(
            value = ("Select * from Ficha As fi where fi.fecha_entrada = ?1"),
            nativeQuery = true
    )
    List<Ficha> getFichaByDay(LocalDate Day);

    /**
     * Obtiene todas las fichas de un rango de fechas.
     *
     * @param date1 fecha de entrada.
     * @param date2 fecha de salida.
     * @return una lista de fichas.
     */
    @Query(
            value = ("Select * from Ficha As fi where fi.fecha_entrada between ?1 and ?2"),
            nativeQuery = true
    )
    List<Ficha> findByDate(LocalDate date1, LocalDate date2);
}
