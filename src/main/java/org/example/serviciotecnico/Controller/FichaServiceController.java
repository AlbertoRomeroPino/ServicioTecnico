package org.example.serviciotecnico.Controller;

import org.example.serviciotecnico.Exceptions.RecordNotFoundException;
import org.example.serviciotecnico.Model.Entity.Ficha;
import org.example.serviciotecnico.Service.FichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ficha")
public class FichaServiceController {

    @Autowired
    FichaService fichaService;

    /**
     * Elimina una ficha de la base de datos.
     * @param id de la ficha que se va a borrar.
     * @return HttpStatus.ACCEPTED si se ha eliminado la ficha
     */
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteFicha(@PathVariable Long id) {

        try {
            fichaService.deleteFicha(id);
            return HttpStatus.ACCEPTED;
        } catch (RecordNotFoundException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * Actualiza una ficha en la base de datos.
     * @param ficha que se va a actualizar.
     * @return la ficha actualizada.
     * @throws RecordNotFoundException
     */
    @PostMapping("/update")
    public ResponseEntity<Ficha> updateFicha(@RequestBody Ficha ficha)
            throws RecordNotFoundException {

        Ficha fichaTemp = fichaService.updateFicha(ficha);
        return ResponseEntity.ok(fichaTemp);

    }

    /**
     * Crea una nueva ficha en la base de datos.
     * @param ficha que se va a crear.
     * @return la ficha creada.
     * @throws RecordNotFoundException
     */
    @PutMapping("/create")
    public ResponseEntity<Ficha> createFicha(@RequestBody Ficha ficha)
            throws RecordNotFoundException {

        Ficha fichaTemp = fichaService.createFicha(ficha);
        return ResponseEntity.ok(fichaTemp);
    }

    /**
     * Obtiene todas las fichas de un cliente en especifico
     * @return una lista de fichas.
     */
    @GetMapping
    public ResponseEntity<List<Ficha>> getFicha() {
        List<Ficha> fichas = fichaService.findAll();
        return ResponseEntity.ok(fichas);
    }

    /**
     * Obtiene todas las fichas de un tecnico en especifico.
     * @param id de la ficha que se quiere obtener.
     * @return la ficha encontrada.
     * @throws RecordNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ficha> getFichaById(@PathVariable Long id)
            throws RecordNotFoundException {
        Ficha fichaTemp = fichaService.findById(id);
        return ResponseEntity.ok(fichaTemp);
    }

    /**
     * Obtiene todas las fichas de un cliente en especifico.
     * @param id del cliente que se quiere obtener.
     * @return una lista de fichas.
     * @throws RecordNotFoundException
     */
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Ficha>> getFichaByCLiente(@PathVariable Long id)
            throws RecordNotFoundException {

        List<Ficha> fichas = fichaService.findByCliente(id);
        return ResponseEntity.ok(fichas);

    }

    /**
     * Obtiene todas las fichas de un tecnico en especifico.
     * @param apodo del tecnico que se quiere obtener.
     * @return una lista de fichas.
     * @throws RecordNotFoundException
     */
    @GetMapping("/tecnico/{apodo}")
    public ResponseEntity<List<Ficha>> getFichaByTecnico(@PathVariable String apodo)
            throws RecordNotFoundException {
        List<Ficha> fichas = fichaService.findByTecnico(apodo);
        return ResponseEntity.ok(fichas);

    }

    /**
     * Obtiene todas las fichas de un dia especifico.
     * @param dia del que se quiere obtener las fichas.
     * @return una lista de fichas.
     * @throws RecordNotFoundException
     */
    @GetMapping("/fecha/{dia}")
    public ResponseEntity<List<Ficha>> getFichaByDia(@PathVariable LocalDate dia)
            throws RecordNotFoundException {

        List<Ficha> fichas = fichaService.findByOneDay(dia);
        return ResponseEntity.ok(fichas);

    }

    /**
     * Obtiene todas las fichas de un rango de fechas.
     * @param dia1 fecha de entrada.
     * @param dia2 fecha de salida.
     * @return una lista de fichas.
     * @throws RecordNotFoundException
     */
    @GetMapping("/fecha/{dia1}/{dia2}")
    public ResponseEntity<List<Ficha>> getFichaByDia(@PathVariable LocalDate dia1, @PathVariable LocalDate dia2)
            throws RecordNotFoundException {
        List<Ficha> fichas = fichaService.findByDate(dia1, dia2);
        return ResponseEntity.ok(fichas);
    }

}
