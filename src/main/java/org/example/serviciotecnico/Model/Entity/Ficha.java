package org.example.serviciotecnico.Model.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@Entity
@Table(name = "ficha")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Lob
    @Column(name = "rotura_cliente")
    private String roturaCliente;

    @Lob
    @Column(name = "diagnostico_tecnico")
    private String diagnosticoTecnico;

    @Column(name = "presupuesto", precision = 10, scale = 2)
    private BigDecimal presupuesto;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference("cliente-ficha")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "tecnico_apodo")
    @JsonBackReference("tecnico-ficha")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    private Tecnico tecnicoApodo;

//    @OneToMany(mappedBy = "ficha")
//
//    private Set<Imagendispositivo> imagendispositivos = new LinkedHashSet<>();

}