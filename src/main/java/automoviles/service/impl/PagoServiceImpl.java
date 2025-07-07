package automoviles.service.impl;

import automoviles.dto.request.PagoRequest;
import automoviles.dto.response.PagoResponse;
import automoviles.model.Pago;
import automoviles.model.Venta;
import automoviles.repository.PagoRepository;
import automoviles.repository.VentaRepository;
import automoviles.service.PagoService;
import automoviles.service.mapper.PagoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private PagoMapper pagoMapper;

    @Override
    public Collection<PagoResponse> obtenerTodosLosPagos() {
        Collection<Pago> pagos = pagoRepository.findAll();
        if (pagos.isEmpty()) {
            throw new RuntimeException("No hay pagos registrados");
        }
        return pagoMapper.toListPagoToPagoResponse(pagos);
    }

    @Override
    public PagoResponse obtenerPagoPorId(Long id) {
        Pago pago = pagoRepository.findById(id).orElse(null);
        if (pago == null) {
            throw new RuntimeException("Pago no encontrado con ID: " + id);
        }
        return pagoMapper.toPagoToPagoResponse(pago);
    }

    @Override
    public void crearPago(PagoRequest request) {
        Pago pago = new Pago();

        // Obtener la venta asociada
        Venta venta = ventaRepository.findById(request.getIdVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + request.getIdVenta()));

        // Configurar el pago
        pago.setVenta(venta);
        pago.setMetodoPago(request.getMetodoPago());
        pago.setMonto(request.getMonto());
        pago.setFecha(request.getFecha());

        pagoRepository.save(pago);
    }

    @Override
    public void actualizarPago(Long id, PagoRequest request) {
        Pago pagoExistente = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));

        // Obtener la venta asociada
        Venta venta = ventaRepository.findById(request.getIdVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + request.getIdVenta()));

        // Actualizar el pago
        pagoExistente.setVenta(venta);
        pagoExistente.setMetodoPago(request.getMetodoPago());
        pagoExistente.setMonto(request.getMonto());
        pagoExistente.setFecha(request.getFecha());

        pagoRepository.save(pagoExistente);
    }

    @Override
    public void eliminarPago(Long id) {
        Pago pago = pagoRepository.findById(id).orElse(null);
        if (!pagoRepository.existsById(id)) {
            throw new RuntimeException("Pago no encontrado con ID: " + id);
        }
        pagoRepository.deleteById(id);
    }
}