package com.omarmitmaPrueba.optima.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuota_id")
    @JsonBackReference
    private Cuota cuota;

    private Double monto;
    private String archivo;
    private String ruta;
    private String fechaSubida;
}