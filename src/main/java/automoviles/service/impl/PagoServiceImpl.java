package automoviles.service.impl;

import automoviles.model.*;
import automoviles.repository.*;
import automoviles.service.PagoService;
import automoviles.service.mapper.PagoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private PagoMapper pagoMapper;

    @Override
    public PagoDto crearPago(PagoDto pagoDto) {
        Pago pago = pagoMapper.toEntity(pagoDto);
        Venta venta = ventaRepository.findById(pagoDto.getIdVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        pago.setVenta(venta);
        pago = pagoRepository.save(pago);
        return pagoMapper.toDto(pago);
    }

    @Override
    public List<PagoDto> obtenerPagosPorVenta(Long idVenta) {
        List<Pago> pagos = pagoRepository.findByVentaId(idVenta);
        return pagos.stream()
                .map(pagoMapper::toDto)
                .collect(Collectors.toList());
    }
}