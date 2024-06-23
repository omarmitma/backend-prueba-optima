package com.omarmitmaPrueba.optima.Controller;

import com.omarmitmaPrueba.optima.Service.CreditoService;
import com.omarmitmaPrueba.optima.Model.Credito;
import com.omarmitmaPrueba.optima.Model.Cuota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private CreditoService creditoService;


    @GetMapping("/getCreditosByPersona/{personaId}")
    public ResponseEntity<List<Credito>> getCreditosByPersona(@PathVariable Long personaId) {
        try {
            List<Credito> data = creditoService.getCreditosByPersonaId(personaId);
            return ResponseEntity.ok(data);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getCreditosActivos")
    public ResponseEntity<List<Credito>> getCreditosActivos() {
        try {
            List<Credito> data = creditoService.getCreditosActivos();
            return ResponseEntity.ok(data);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getCreditosById/{id}")
    public ResponseEntity<Optional<Credito>> getCreditosById(@PathVariable Long id) {
        try {
            Optional<Credito> data = creditoService.getCreditosById(id);
            return ResponseEntity.ok(data);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/editar")
    public ResponseEntity<Credito> editarCuota(@RequestBody Credito credito) {
        try {
            Credito creditoEditada = creditoService.editarCredito(credito);
            return ResponseEntity.ok(creditoEditada);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}