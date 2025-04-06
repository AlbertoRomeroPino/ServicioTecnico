package org.example.serviciotecnico.Model.Entity;

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

@Getter
@Setter
@Entity
@Table(name = "ficha")
public class Ficha {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "tecnico_apodo")
    private org.example.serviciotecnico.Model.Entity.Tecnico tecnicoApodo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "ficha")
    private Set<org.example.serviciotecnico.Model.Entity.Imagendispositivo> imagendispositivos = new LinkedHashSet<>();

}