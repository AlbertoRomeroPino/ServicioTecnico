package org.example.serviciotecnico.Model.Repositories;

import org.example.serviciotecnico.Model.Entity.Imagendispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepository extends JpaRepository<Imagendispositivo, Long> {

    /**
     * Obtiene todas las imagenes de un ficha en especifico.
     * @param id Ficha del cual se quieren obtener las imagenes.
     * @return una lista de imagenes.
     */
    @Query(
            value = "Select * from imagendispositivo where ficha_id = ?1",
            nativeQuery = true
    )
    List<Imagendispositivo> findByFichaId(Long id);

}
