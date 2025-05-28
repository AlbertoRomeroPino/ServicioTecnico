package org.example.serviciotecnico.Service;

import org.example.serviciotecnico.Exceptions.RecordNotFoundException;
import org.example.serviciotecnico.Model.Entity.Ficha;
import org.example.serviciotecnico.Model.Entity.Imagendispositivo;
import org.example.serviciotecnico.Model.Repositories.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;
    @Autowired
    private FichaService fichaService;

    /**
     * Elimina una imagen de la base de datos.
     *
     * @param id de la imagen que se va a borrar.
     */
    public void deleteImagen(Long id) throws RecordNotFoundException {
        Optional<Imagendispositivo> imagen = imagenRepository.findById(id);

        // Borrar archivo físico
        File archivo = new File(imagen.get().getFoto());
        if (archivo.exists()) {
            archivo.delete();
        }

        // Borrar registro en base de datos
        imagenRepository.delete(imagen.get());

    }

    /**
     * Crea una nueva imagen en la base de datos.
     *
     * @param imagen que se va a crear.
     * @return la imagen creada.
     */
    public Imagendispositivo createImagen(Imagendispositivo imagen) {
        if (imagen == null) {
            throw new IllegalArgumentException("La imagen no puede ser nula.");
        }
        try {
            // Validar que la ficha y su ID no sean nulos
            if (imagen.getFicha() == null || imagen.getFicha().getId() == null) {
                throw new IllegalArgumentException("La ficha y su ID son requeridos.");
            }

            // Validar que la foto no sea nula
            if (imagen.getFoto() == null || imagen.getFoto().trim().isEmpty()) {
                throw new IllegalArgumentException("La foto es requerida.");
            }

            // Obtener la ficha completa y validar que exista
            Ficha fichaCompleta = fichaService.findById(imagen.getFicha().getId());
            if (fichaCompleta == null) {
                throw new IllegalArgumentException("La ficha con ID " + imagen.getFicha().getId() + " no existe.");
            }

            // --- NUEVO: Guardar la imagen en la carpeta 'imagen' como PNG ---
            byte[] imageBytes = java.util.Base64.getDecoder().decode(imagen.getFoto());
            String nombreArchivo = java.util.UUID.randomUUID() + ".png";
            String ruta = "imagen/" + nombreArchivo;

            // Decodificar base64 a BufferedImage y guardar como PNG
            try (java.io.ByteArrayInputStream bis = new java.io.ByteArrayInputStream(imageBytes)) {
                java.awt.image.BufferedImage bufferedImage = javax.imageio.ImageIO.read(bis);
                if (bufferedImage == null) {
                    throw new IllegalArgumentException("El contenido base64 no es una imagen válida.");
                }
                java.io.File outputfile = new java.io.File(ruta);
                javax.imageio.ImageIO.write(bufferedImage, "png", outputfile);
            }

            // Guardar solo la ruta en la base de datos
            imagen.setFoto(ruta);

            imagen.setFicha(fichaCompleta);
            return imagenRepository.save(imagen);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la imagen en la base de datos.", e);
        }
    }

    /**
     * Actualiza una imagen en la base de datos.
     *
     * @param id de la imagen que se va a actualizar.
     * @return la imagen actualizada.
     * @throws RecordNotFoundException
     */
    public List<Imagendispositivo> getAllByFicha(Long id)
            throws RecordNotFoundException {

        List<Imagendispositivo> imagendispositivos = imagenRepository.findByFichaId(id);

        if (imagendispositivos.size() > 0) {
            return imagendispositivos;
        } else {
            return new ArrayList<Imagendispositivo>();
        }

    }
}
