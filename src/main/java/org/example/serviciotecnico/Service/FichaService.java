package org.example.serviciotecnico.Service;

import org.example.serviciotecnico.Model.Entity.Ficha;
import org.example.serviciotecnico.Model.Repositories.ClienteRepository;
import org.example.serviciotecnico.Model.Repositories.FichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    /**
     * Crea una nueva ficha en la base de datos.
     *
     * @param ficha la ficha a crear
     * @return la ficha creada
     */
    public Ficha createFicha(Ficha ficha) {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha no puede ser nula.");
        }

        try {
            return fichaRepository.save(ficha);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la ficha en la base de datos.", e);
        }
    }

    /**
     * Actualiza una ficha en la base de datos.
     *
     * @param ficha la ficha a actualizar
     * @return la ficha actualizada
     */
    public Ficha updateFicha(Ficha ficha) {
        if (ficha == null) {
            throw new IllegalArgumentException("La ficha no puede ser nula.");
        }

        Optional<Ficha> fichaOptional = fichaRepository.findById(ficha.getId());

        if (fichaOptional != null) {
            Ficha newFicha = fichaOptional.get();
            newFicha.setFechaEntrada(ficha.getFechaEntrada()); // Comprobar a futuro para que no se pueda cambiar
            newFicha.setFechaSalida(ficha.getFechaSalida());
            newFicha.setRoturaCliente(ficha.getRoturaCliente());
            newFicha.setDiagnosticoTecnico(ficha.getDiagnosticoTecnico());
            newFicha.setPresupuesto(ficha.getPresupuesto());


            // Comprobar si el cliente es diferente
            if (!fichaOptional.get().getCliente().getNombre().equals(ficha.getCliente().getNombre())) {
                newFicha.setCliente(ficha.getCliente());
            }

            // Comprobar si el tecnico es diferente
            if (!fichaOptional.get().getTecnicoApodo().getApodo().equals(ficha.getTecnicoApodo().getApodo())) {
                newFicha.setTecnicoApodo(ficha.getTecnicoApodo());
            }


            return fichaRepository.save(newFicha);
        } else {
            throw new RuntimeException("No hay ficha con ese id: " + ficha.getId());
        }
    }

}
