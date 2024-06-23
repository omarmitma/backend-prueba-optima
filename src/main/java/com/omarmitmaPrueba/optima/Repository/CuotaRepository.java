package com.omarmitmaPrueba.optima.Repository;

import com.omarmitmaPrueba.optima.Model.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuotaRepository extends JpaRepository<Cuota, Long> {
    List<Cuota> findByCreditoId(Long creditoId);
}