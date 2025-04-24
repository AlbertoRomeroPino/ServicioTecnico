package org.example.serviciotecnico.Service;

import org.example.serviciotecnico.Exceptions.RecordNotFoundException;
import org.example.serviciotecnico.Model.Entity.Imagendispositivo;
import org.example.serviciotecnico.Model.Repositories.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    /**
     * Elimina una imagen de la base de datos.
     * @param id de la imagen que se va a borrar.
     */
    public void deleteImagen(Long id){
        Optional<Imagendispositivo> imagendispositivo = imagenRepository.findById(id);
        if(imagendispositivo.isPresent()){
            imagenRepository.deleteById(id);
        }else{
            throw new RuntimeException("No hay imagen con ese id: " + id);
        }
    }

    /**
     * Crea una nueva imagen en la base de datos.
     * @param imagen que se va a crear.
     * @return la imagen creada.
     */
    public Imagendispositivo createImagen(Imagendispositivo imagen){
        if(imagen == null){
            throw new IllegalArgumentException("La imagen no puede ser nula.");
        }

        try{
            return imagenRepository.save(imagen);
        }catch(Exception e){
            throw new RuntimeException("Error al guardar la imagen en la base de datos.", e);
        }
    }

    /**
     * Actualiza una imagen en la base de datos.
     * @param id de la imagen que se va a actualizar.
     * @return la imagen actualizada.
     * @throws RecordNotFoundException
     */
    public List<Imagendispositivo> getAllByFicha(Long id)
        throws RecordNotFoundException {

        List<Imagendispositivo> imagendispositivos = imagenRepository.findByFichaId(id);

        if (imagendispositivos.size() > 0){
            return imagendispositivos;
        }else {
            return new ArrayList<Imagendispositivo>();
        }

    }
}
