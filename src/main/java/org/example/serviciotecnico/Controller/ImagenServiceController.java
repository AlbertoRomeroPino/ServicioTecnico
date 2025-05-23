package org.example.serviciotecnico.Controller;

import org.example.serviciotecnico.Exceptions.RecordNotFoundException;
import org.example.serviciotecnico.Model.Entity.Imagendispositivo;
import org.example.serviciotecnico.Service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagen")
public class ImagenServiceController {

    @Autowired
    ImagenService imagenService;

    @DeleteMapping("/{id}")
    public HttpStatus deleteImagen(@PathVariable Long id){
        try{
            imagenService.deleteImagen(id);
            return HttpStatus.ACCEPTED;
        }catch(Exception e){
            return HttpStatus.NOT_FOUND;
        }
    }

    @PostMapping
    public ResponseEntity<Imagendispositivo> createImagen(@RequestBody Imagendispositivo imagen)
    throws RecordNotFoundException {


        Imagendispositivo imagenTemp = imagenService.createImagen(imagen);


        return ResponseEntity.ok(imagenTemp);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Imagendispositivo>> getImagenById(@PathVariable Long id)
    throws RecordNotFoundException {
        List<Imagendispositivo> imagenes = imagenService.getAllByFicha(id);
        return ResponseEntity.ok(imagenes);
    }

}
