package com.omarmitmaPrueba.optima.Repository;

import com.omarmitmaPrueba.optima.Model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
    List<Credito> findByPersonaId(Long personaId);
    
    @Query("SELECT c FROM Credito c JOIN FETCH c.persona p JOIN FETCH c.proyecto pr WHERE c.fechaDesembolso IS NOT NULL")
    List<Credito> findCreditosActivos();
    
    Optional<Credito> findById(Long id);
}