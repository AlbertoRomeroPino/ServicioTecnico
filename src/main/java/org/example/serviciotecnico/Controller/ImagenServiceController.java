package org.example.serviciotecnico.Controller;

import org.example.serviciotecnico.Exceptions.RecordNotFoundException;
import org.example.serviciotecnico.Model.Entity.Ficha;
import org.example.serviciotecnico.Model.Entity.Imagendispositivo;
import org.example.serviciotecnico.Service.FichaService;
import org.example.serviciotecnico.Service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/imagen")
public class ImagenServiceController {

    @Autowired
    ImagenService imagenService;
    @Autowired
    FichaService fichaService;

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
    public ResponseEntity<Imagendispositivo> createImagen(
            @RequestParam("file") MultipartFile file,
            @RequestParam("ficha_id") Long ficha_id)
    throws RecordNotFoundException {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Crear carpeta si no existe
            String uploadDir = "Imagen/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            // Guardar el archivo
            String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String ruta = uploadDir + nombreArchivo;
            file.transferTo(new File(ruta));

            // Buscar ficha y asociarla
            Ficha ficha = fichaService.findById(ficha_id); // Asegúrate de tener este método

            // Crear objeto imagen
            Imagendispositivo imagen = new Imagendispositivo();
            imagen.setFoto(ruta);
            imagen.setFicha(ficha);

            Imagendispositivo imagenGuardada = imagenService.createImagen(imagen);

            return ResponseEntity.ok(imagenGuardada);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

    @GetMapping("{id}")
    public ResponseEntity<List<Imagendispositivo>> getImagenById(@PathVariable Long id)
    throws RecordNotFoundException {
        List<Imagendispositivo> imagenes = imagenService.getAllByFicha(id);
        return ResponseEntity.ok(imagenes);
    }

}
