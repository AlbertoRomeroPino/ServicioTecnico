package org.example.serviciotecnico.Controller;

import org.example.serviciotecnico.Exceptions.RecordNotFoundException;
import org.example.serviciotecnico.Model.Entity.Tecnico;
import org.example.serviciotecnico.Service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnico")
public class TecnicoServiceController {

    @Autowired
    TecnicoService tecnicoService;

    /**
     * Elimina un tecnico de la base de datos.
     *
     * @param apodo del tecnico a eliminar.
     * @return HttpStatus.ACCEPTED si se ha eliminado el tecnico
     * HttpStatus.NOT_FOUND si no se ha encontrado el tecnico.
     */
    @DeleteMapping("/{apodo}")
    public HttpStatus deleteTecnico(@PathVariable String apodo) {
        try {
            tecnicoService.deleteTecnico(apodo);
            return HttpStatus.ACCEPTED;
        } catch (RecordNotFoundException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * Crea un nuevo tecnico en la base de datos.
     *
     * @param tecnico el tecnico a crear.
     * @return el tecnico creado.
     * @throws RecordNotFoundException
     */
    @PostMapping
    public ResponseEntity<Tecnico> createTecnico(@RequestBody Tecnico tecnico)
            throws RecordNotFoundException {

        Tecnico tecnicoTemp = tecnicoService.createTecnico(tecnico);
        return ResponseEntity.ok(tecnicoTemp);
    }

    /**
     * Actualiza un tecnico en la base de datos.
     *
     * @param tecnico el tecnico a actualizar.
     * @return el tecnico actualizado.
     * @throws RecordNotFoundException
     */
    @PutMapping
    public ResponseEntity<Tecnico> updateTecnico(@RequestBody Tecnico tecnico)
            throws RecordNotFoundException {

        Tecnico tecnicoTemp = tecnicoService.updateTecnico(tecnico);
        return ResponseEntity.ok(tecnicoTemp);

    }

    /**
     * Busca todos los tecnicos en la base de datos.
     *
     * @return una lista de tecnicos.
     */
    @GetMapping
    public ResponseEntity<List<Tecnico>> getTecnico() {
        List<Tecnico> tecnicos = tecnicoService.findAll();
        return ResponseEntity.ok(tecnicos);
    }

    /**
     * Busca un tecnico por su apodo
     *
     * @param apodo del tecnico  que se quiere buscar.
     * @return el tecnico encontrado.
     * @throws RecordNotFoundException
     */
    @GetMapping("/byApodo/{apodo}")
    public ResponseEntity<Tecnico> getTecnico(@PathVariable String apodo)
            throws RecordNotFoundException {

        Tecnico tecnico = tecnicoService.findByApodo(apodo);
        return ResponseEntity.ok(tecnico);

    }

    /**
     * Busca un tecnico por su numero de telefono
     * @param numeroTelefono del tecnico que se quiere buscar.
     * @return el tecnico encontrado.
     * @throws RecordNotFoundException
     */
    @GetMapping("/byPhone/{numeroTelefono}")
    public ResponseEntity<Tecnico> getTecnicoByPhone(@PathVariable String numeroTelefono)
        throws RecordNotFoundException {

        Tecnico tecnico = tecnicoService.findByNumeroTelefono(numeroTelefono);
        return ResponseEntity.ok(tecnico);
    }

}
