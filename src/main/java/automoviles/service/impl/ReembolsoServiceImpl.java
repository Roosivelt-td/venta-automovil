package automoviles.service.impl;

import automoviles.dto.ReembolsoDto;
import automoviles.model.*;
import automoviles.repository.*;
import automoviles.service.ReembolsoService;
import automoviles.service.mapper.ReembolsoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReembolsoServiceImpl implements ReembolsoService {

    @Autowired
    private ReembolsoRepository reembolsoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ReembolsoMapper reembolsoMapper;

    @Override
    public ReembolsoDto crearReembolso(ReembolsoDto reembolsoDto) {
        Reembolso reembolso = reembolsoMapper.toEntity(reembolsoDto);
        Venta venta = ventaRepository.findById(reembolsoDto.getIdVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        reembolso.setVenta(venta);
        reembolso = reembolsoRepository.save(reembolso);
        return reembolsoMapper.toDto(reembolso);
    }

    @Override
    public List<ReembolsoDto> obtenerReembolsosPorVenta(Long idVenta) {
        List<Reembolso> reembolsos = reembolsoRepository.findByVentaId(idVenta);
        return reembolsos.stream()
                .map(reembolsoMapper::toDto)
                .collect(Collectors.toList());
    }
}