package org.example.serviciotecnico.Service;

import org.example.serviciotecnico.Exceptions.RecordNotFoundException;
import org.example.serviciotecnico.Model.Entity.Tecnico;
import org.example.serviciotecnico.Model.Repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;


    /**
     * Crea un nuevo tecnico en la base de datos.
     *
     * @param tecnico el tecnico a crear
     * @return el tecnico creado
     */
    public Tecnico createTecnico(Tecnico tecnico) {
        if (tecnico == null) {
            throw new IllegalArgumentException("El tecnico no puede ser nulo.");
        }

        try {
            return tecnicoRepository.save(tecnico);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el tecnico en la base de datos.", e);
        }
    }

    /**
     * Actualiza un tecnico en la base de datos.
     *
     * @param tecnico el tecnico a actualizar
     * @return el tecnico actualizado
     */
    public Tecnico updateTecnico(Tecnico tecnico) {
        if (tecnico == null) {
            throw new IllegalArgumentException("El tecnico no puede ser nulo.");
        }

        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(tecnico.getApodo());

        if (tecnicoOptional != null) {
            Tecnico newTecnico = tecnicoOptional.get();
            newTecnico.setNombre(tecnico.getNombre());
            newTecnico.setApellido(tecnico.getApellido());
            newTecnico.setNumeroTelefono(tecnico.getNumeroTelefono());

            return tecnicoRepository.save(newTecnico);
        } else {
            throw new RecordNotFoundException("No hay tecnico con ese id: ", tecnico.getApodo());
        }
    }

    /**
     * Elimina un tecnico de la base de datos.
     *
     * @param apodo del tecnico a eliminar
     * @return el tecnico eliminado
     */
    public Tecnico deleteTecnico(String apodo) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(apodo);
        if (tecnico.isPresent()) {
            Tecnico tecnicoDelete = tecnico.get();
            tecnicoRepository.delete(tecnico.get());
            return tecnicoDelete;
        } else {
            throw new RecordNotFoundException("No hay tecnico con ese apodo: ", apodo);
        }
    }

    /**
     * Muestra todos los tecnicos de la base de datos.
     *
     * @return una lista de tecnicos
     */
    public List<Tecnico> findAll() {
        List<Tecnico> tecnicoLista = tecnicoRepository.findAll();
        if (tecnicoLista.size() > 0) {
            return tecnicoLista;
        } else {
            return List.of();
        }
    }

    /**
     * Busca un tecnico por su apodo.
     *
     * @param apodo del tecnico
     * @return el tecnico encontrado
     */
    public Tecnico findByApodo(String apodo) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(apodo);
        if (tecnico.isPresent()) {
            return tecnico.get();
        } else {
            throw new RecordNotFoundException("No hay tecnico con ese id: ", apodo);
        }
    }

    /**
     * Busca un tecnico por su numero de telefono
     *
     * @param numeroTelefono del tecnico
     * @return el tecnico encontrado
     */
    public Tecnico findByNumeroTelefono(String numeroTelefono) {
        Optional<Tecnico> tecnico = tecnicoRepository.findByNumeroTelefono(numeroTelefono);
        if (tecnico.isPresent()) {
            return tecnico.get();
        } else {
            throw new RecordNotFoundException("No hay tecnico con ese nuemro de telefono", numeroTelefono);
        }
    }
}
