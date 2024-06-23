package com.omarmitmaPrueba.optima.Service;

import com.omarmitmaPrueba.optima.Repository.CreditoRepository;
import com.omarmitmaPrueba.optima.Model.Credito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository creditoRepository;

    public List<Credito> getCreditosByPersonaId(Long personaId) {
        return creditoRepository.findByPersonaId(personaId);
    }
    
    public List<Credito> getCreditosActivos() {
        return creditoRepository.findCreditosActivos();
    }
    
    public Optional<Credito> getCreditosById(Long id) {
        return creditoRepository.findById(id);
    }
    
    public Credito editarCredito(Credito credito) {
        // Validar si la cuota existe en la base de datos
        if (!creditoRepository.existsById(credito.getId())) {
            throw new NoSuchElementException("La cuota no existe en la base de datos.");
        }

        // Actualizar la cuota en la base de datos
        return creditoRepository.save(credito);
    }
}
