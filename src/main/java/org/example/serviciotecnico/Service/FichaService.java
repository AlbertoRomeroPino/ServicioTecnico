package org.example.serviciotecnico.Service;

import org.example.serviciotecnico.Model.Entity.Cliente;
import org.example.serviciotecnico.Model.Entity.Ficha;
import org.example.serviciotecnico.Model.Entity.Imagendispositivo;
import org.example.serviciotecnico.Model.Entity.Tecnico;
import org.example.serviciotecnico.Model.Repositories.FichaRepository;
import org.example.serviciotecnico.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;
    @Autowired
    private ClienteService clienteRepository;
    @Autowired
    private TecnicoService tecnicoRepository;

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
            if (ficha.getCliente() != null && ficha.getCliente().getId() != null) {
                Cliente clienteCompleto = clienteRepository.getClienteById(ficha.getCliente().getId());
                ficha.setCliente(clienteCompleto);
            }

            if (ficha.getTecnicoApodo() != null && ficha.getTecnicoApodo().getApodo() != null) {
                Tecnico tecnicoCompleto = tecnicoRepository.findByApodo(ficha.getTecnicoApodo().getApodo());
                ficha.setTecnicoApodo(tecnicoCompleto);
            }

            if (Utils.compareDates(ficha.getFechaEntrada(), ficha.getFechaSalida())){
                LocalDate dateTemp = ficha.getFechaEntrada();
                ficha.setFechaEntrada(ficha.getFechaSalida());
                ficha.setFechaSalida(dateTemp);
                return fichaRepository.save(ficha);
            }else{

                return fichaRepository.save(ficha);
            }


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

        Optional<Ficha> fichaOptional = fichaRepository.findById(Long.valueOf(ficha.getId()));

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

    /**
     * Elimina una ficha tecnica de la base de datos
     *
     * @param id de la ficha que se va a borrar
     * @return la ficha borrada
     */
    public Ficha deleteFicha(Long id) {
        Optional<Ficha> fichaOptional = fichaRepository.findById(id);
        if (fichaOptional != null) {
            fichaRepository.deleteById(id);
            return fichaOptional.get();
        } else {
            throw new RuntimeException("No hay ficha con ese id: " + id);
        }
    }

    /**
     * Busca todas las fichas en la base de datos
     *
     * @return una lista de ficha
     */
    public List<Ficha> findAll() {
        List<Ficha> fichas = fichaRepository.findAll();
        if (fichas.size() > 0) {
            return fichas;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Busca una ficha por su id
     *
     * @param id de la ficha que se quiere buscar.
     * @return la ficha encontrada.
     */
    public Ficha findById(Long id) {
        Optional<Ficha> fichaOptional = fichaRepository.findById(id);
        if (fichaOptional != null) {
            return fichaOptional.get();
        } else {
            throw new RuntimeException("No hay ficha con ese id: " + id);
        }
    }

    /**
     * Busca una ficha por su id de cliente.
     *
     * @param idCliente que se quiere buscar.
     * @return una lista de fichas encontradas.
     */
    public List<Ficha> findByCliente(Long idCliente) {
        List<Ficha> fichas = fichaRepository.getFichaByIdDeCliente(idCliente);
        if (fichas.size() > 0) {
            return fichas;
        } else {
            throw new RuntimeException("No hay ficha con ese cliente de id: " + idCliente);
        }
    }

    /**
     * Busca una ficha por su id de tecnico.
     *
     * @param apodoTecnico que se quiere buscar.
     * @return una lista de fichas encontradas.
     */
    public List<Ficha> findByTecnico(String apodoTecnico) {
        List<Ficha> fichas = fichaRepository.getFichasbyTecnico(apodoTecnico);
        if (fichas.size() > 0) {
            return fichas;
        } else {
            throw new RuntimeException("No hay ficha con ese tecnico: " + apodoTecnico  );
        }
    }

    /**
     * Busca una ficha por su dia.
     * @param day dia de la ficha que se quiere buscar.
     * @return una lista de fichas encontradas.
     */
    public List<Ficha> findByOneDay (LocalDate day){
    List<Ficha> fichas = fichaRepository.getFichaByDay(day);
    if (fichas.size() > 0) {
        return fichas;
    } else {
        throw new RuntimeException("No hay ficha con esa dia: " + day);
    }

    }

    /**
     * Busca una ficha por su fecha de entrada y salida.
     *
     * @param date1 fecha de entrada.
     * @param date2 fecha de salida.
     * @return una lista de fichas encontradas.
     */
    public List<Ficha> findByDate(LocalDate date1, LocalDate date2) {
        if (Utils.compareDates(date1,date2)){
            LocalDate dateTemp = date1;
            date1 = date2;
            date2 = dateTemp;
        }

        List<Ficha> fichas = fichaRepository.findByDate(date1, date2);
        if (fichas.size() > 0) {
            return fichas;
        } else {
            throw new RuntimeException("No hay ficha con ese rango de fecha: " + date1 + " a " + date2);
        }
    }


}
