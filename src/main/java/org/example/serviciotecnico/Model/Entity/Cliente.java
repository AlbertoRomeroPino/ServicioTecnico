package org.example.serviciotecnico.Model.Entity;

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
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 20)
    @Column(name = "numero_telefono", length = 20)
    private String numeroTelefono;

    @Size(max = 20)
    @Column(name = "dni", length = 20)
    private String dni;

    @OneToMany(mappedBy = "cliente")
    private Set<org.example.serviciotecnico.Model.Entity.Ficha> fichas = new LinkedHashSet<>();

}