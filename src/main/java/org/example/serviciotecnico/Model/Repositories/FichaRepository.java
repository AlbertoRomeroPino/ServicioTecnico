package org.example.serviciotecnico.Model.Repositories;

import org.example.serviciotecnico.Model.Entity.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {
}
