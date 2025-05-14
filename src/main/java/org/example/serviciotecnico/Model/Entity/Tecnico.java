package org.example.serviciotecnico.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tecnico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Tecnico {
    @Id
    @Size(max = 50)
    @Column(name = "apodo", nullable = false, length = 50)
    private String apodo;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Size(max = 20)
    @Column(name = "numero_telefono", length = 20)
    private String numeroTelefono;

    @OneToMany(mappedBy = "tecnicoApodo")
    @JsonManagedReference("tecnico-ficha")
    @JsonIgnoreProperties({"tecnicoApodo", "hibernateLazyInitializer", "handler"})


    private Set<Ficha> fichas = new LinkedHashSet<>();

}