package com.omarmitmaPrueba.optima.Service;

import com.omarmitmaPrueba.optima.Repository.CuotaRepository;
import com.omarmitmaPrueba.optima.Model.Cuota;
import com.omarmitmaPrueba.optima.Model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuotaService {

    @Autowired
    private CuotaRepository cuotaRepository;

    public List<Cuota> getCuotasByCreditoId(Long creditoId) {
        return cuotaRepository.findByCreditoId(creditoId);
    }
    
//    public List<Cuota> editarCuota(List<Cuota> cuotas) {
//        
//        List<Long> ids = cuotas.stream().map(Cuota::getId).collect(Collectors.toList());
//
//        // Filtrar las cuotas que existen en la base de datos
//        List<Cuota> cuotasExistentes = cuotaRepository.findAllById(ids);
//
//        // Actualizar las cuotas existentes
//        cuotasExistentes.forEach(cuotaExistente -> {
//            // Buscar la cuota correspondiente en la lista recibida
//            Cuota cuotaActualizada = cuotas.stream()
//                    .filter(cuota -> cuota.getId().equals(cuotaExistente.getId()))
//                    .findFirst()
//                    .orElseThrow(() -> new IllegalArgumentException("No se encontró la cuota correspondiente."));
//
//            // Actualizar los campos de la cuota existente
//            cuotaExistente.setVouchers(cuotaActualizada.getVouchers());
//            cuotaExistente.setMontoPagado(cuotaActualizada.getMontoPagado());
//        });
//
//        // Guardar las cuotas actualizadas (incluyendo las nuevas que no existían previamente)
//        List<Cuota> cuotasActualizadas = cuotaRepository.saveAll(cuotasExistentes);
//
//        return cuotasActualizadas;
//    }
    
    public List<Cuota> editarCuota(List<Cuota> cuotas) {
        List<Long> ids = cuotas.stream().map(Cuota::getId).collect(Collectors.toList());

        // Filtrar las cuotas que existen en la base de datos
        List<Cuota> cuotasExistentes = cuotaRepository.findAllById(ids);

        // Actualizar las cuotas existentes
        cuotasExistentes.forEach(cuotaExistente -> {
            // Buscar la cuota correspondiente en la lista recibida
            Cuota cuotaActualizada = cuotas.stream()
                    .filter(cuota -> cuota.getId().equals(cuotaExistente.getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró la cuota correspondiente."));

            // Actualizar los campos de la cuota existente
            // Obtener la lista de vouchers recibida
            List<Voucher> vouchersActualizados = cuotaActualizada.getVouchers();

            // Eliminar los vouchers que no están en la lista recibida
            cuotaExistente.getVouchers().removeIf(voucherExistente ->
                    vouchersActualizados.stream().noneMatch(voucherActualizado ->
                            voucherActualizado.getId().equals(voucherExistente.getId())
                    )
            );

            // Añadir o actualizar los vouchers recibidos
            cuotaExistente.getVouchers().clear();
            cuotaExistente.getVouchers().addAll(vouchersActualizados);

            // Actualizar otros campos necesarios
            cuotaExistente.setMontoPagado(cuotaActualizada.getMontoPagado());
        });

        // Guardar las cuotas actualizadas (incluyendo las nuevas que no existían previamente)
        List<Cuota> cuotasActualizadas = cuotaRepository.saveAll(cuotasExistentes);

        return cuotasActualizadas;
    }

}