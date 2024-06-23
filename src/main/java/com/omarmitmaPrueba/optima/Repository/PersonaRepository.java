package com.omarmitmaPrueba.optima.Repository;

import com.omarmitmaPrueba.optima.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}