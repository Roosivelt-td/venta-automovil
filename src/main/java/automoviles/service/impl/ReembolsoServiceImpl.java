package automoviles.service.impl;

import automoviles.dto.request.ReembolsoRequest;
import automoviles.dto.response.ReembolsoResponse;
import automoviles.dto.response.UsuarioResponse;
import automoviles.dto.response.VentaResponse;
import automoviles.model.Reembolso;
import automoviles.model.Usuario;
import automoviles.model.Venta;
import automoviles.repository.ReembolsoRepository;
import automoviles.repository.VentaRepository;
import automoviles.service.ReembolsoService;
import automoviles.service.mapper.ReembolsoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReembolsoServiceImpl implements ReembolsoService {

    @Autowired
    private ReembolsoRepository reembolsoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ReembolsoMapper reembolsoMapper;

    @Override
    public ReembolsoResponse obtenerReembolsoPorId(Long id) {
        Reembolso reembolso = reembolsoRepository.findById(id).orElse(null);
        if (reembolso == null) {
            throw new RuntimeException("Reembolso no encontrado con ID: " + id);
        }
        return reembolsoMapper.toReembolsoToReembolsoResponse(reembolso);
    }

    @Override
    public Collection<ReembolsoResponse> obtenerTodosLosReembolsos() {
        Collection<Reembolso> listReembolsoResponse = reembolsoRepository.findAll();
        if (listReembolsoResponse.isEmpty()) {
            throw new RuntimeException("No hay reembolsos registrados");
        }
        return reembolsoMapper.toListReembolsoToReembolsoResponse(listReembolsoResponse);
    }

    @Override
    public void crearReembolso(ReembolsoRequest request) {
        Venta venta = ventaRepository.findById(request.getIdVenta()).orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + request.getIdVenta()));

        Reembolso reembolso = new Reembolso();
        reembolso.setVenta(venta);
        reembolso.setMotivo(request.getMotivo());
        reembolso.setMonto(request.getMonto());
        reembolso.setFecha(request.getFecha());

        reembolsoRepository.save(reembolso);
    }

    @Override
    public void actualizarReembolso(Long id, ReembolsoRequest request) {
        Reembolso reembolsoExistente = reembolsoRepository.findById(id).orElseThrow(() -> new RuntimeException("Reembolso no encontrado con ID: " + id));

        Venta venta = ventaRepository.findById(request.getIdVenta()).orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + request.getIdVenta()));

        reembolsoExistente.setVenta(venta);
        reembolsoExistente.setMotivo(request.getMotivo());
        reembolsoExistente.setMonto(request.getMonto());
        reembolsoExistente.setFecha(request.getFecha());

        reembolsoRepository.save(reembolsoExistente);
    }

    @Override
    public void eliminarReembolso(Long id) {
        Reembolso reembolso = reembolsoRepository.findById(id).orElse(null);
        if (!reembolsoRepository.existsById(id)) {
            throw new RuntimeException("Reembolso no encontrado con ID: " + id);
        }
        reembolsoRepository.deleteById(id);
    }
}