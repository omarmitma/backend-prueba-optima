package com.omarmitmaPrueba.optima.Controller;

import com.omarmitmaPrueba.optima.Service.CuotaService;
import com.omarmitmaPrueba.optima.Model.Cuota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/cuotas")
public class CuotaController {

    @Autowired
    private CuotaService cuotaService;

    @GetMapping("/getCuotasByCredito/{creditoId}")
    public ResponseEntity<List<Cuota>> getCuotasByCredito(@PathVariable Long creditoId) {
        try {
            List<Cuota> data = cuotaService.getCuotasByCreditoId(creditoId);
            return ResponseEntity.ok(data);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/editar")
    public ResponseEntity<List<Cuota>> editarCuota(@RequestBody List<Cuota> cuota) {
        try {
            List<Cuota> cuotaEditada = cuotaService.editarCuota(cuota);
            return ResponseEntity.ok(cuotaEditada);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}