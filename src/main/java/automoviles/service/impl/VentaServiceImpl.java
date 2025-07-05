package automoviles.service.impl;

import automoviles.dto.request.VentaRequest;
import automoviles.dto.response.CompraResponse;
import automoviles.dto.response.VentaResponse;
import automoviles.model.*;
import automoviles.repository.*;
import automoviles.service.AutoService;
import automoviles.service.VentaService;
import automoviles.service.mapper.VentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private VentaMapper ventaMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private AutoService autoService;

    @Override
    public VentaResponse obtenerVentaPorId(Long id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
        return ventaMapper.toVentaToVentaResponse(venta);
    }

    @Override
    public Collection<VentaResponse> obtenerTodosLosVentas() {
        Collection<Venta> ventas = ventaRepository.findAll();
        if (ventas.isEmpty()) {
            throw new RuntimeException("No hay ventas registradas");
        }
        return ventaMapper.toListVentaToVentaResponse(ventas);
    }

    @Override
    public void crearVenta(VentaRequest request) {
        Venta venta = new Venta();

        // Cargar las entidades relacionadas
        Cliente cliente = clienteRepository.findById(request.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + request.getIdCliente()));
        Auto auto = autoRepository.findById(request.getIdAuto()).orElseThrow(() -> new RuntimeException("Auto no encontrado con ID: " + request.getIdAuto()));
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getIdUsuario()));

        // Configurar la venta
        venta.setCliente(cliente);
        venta.setAuto(auto);
        venta.setUsuario(usuario);
        venta.setFecha(request.getFecha());
        venta.setPrecioVenta(request.getPrecioVenta());

        ventaRepository.save(venta);

        // Actualizar el stock del auto vendido (reducir en 1)
        autoService.actualizarStock(request.getIdAuto(), 1);

        // Registrar el pago asociado a la venta
        Pago pago = new Pago();
        pago.setVenta(venta);
        pago.setMetodoPago(request.getMetodoPago());
        pago.setMonto(request.getPrecioVenta());
        pago.setFecha(request.getFecha());
        pagoRepository.save(pago);
    }

    @Override
    public void actualizarVenta(Long id, VentaRequest request) {
        Venta ventaExistente = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));

        // Obtener el auto anterior de la venta para restaurar su stock
        Auto autoAnterior = ventaExistente.getAuto();
        if (autoAnterior != null) {
            // Restaurar el stock del auto anterior (sumar 1)
            autoAnterior.setStock(autoAnterior.getStock() + 1);
            if (autoAnterior.getStock() > 0 && "Vendido".equals(autoAnterior.getEstado())) {
                autoAnterior.setEstado("Disponible");
            }
            autoRepository.save(autoAnterior);
        }

        // Cargar las entidades relacionadas
        Cliente cliente = clienteRepository.findById(request.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + request.getIdCliente()));
        Auto auto = autoRepository.findById(request.getIdAuto()).orElseThrow(() -> new RuntimeException("Auto no encontrado con ID: " + request.getIdAuto()));
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getIdUsuario()));

        // Actualizar la venta
        ventaExistente.setCliente(cliente);
        ventaExistente.setAuto(auto);
        ventaExistente.setUsuario(usuario);
        ventaExistente.setFecha(request.getFecha());
        ventaExistente.setPrecioVenta(request.getPrecioVenta());

        ventaRepository.save(ventaExistente);

        // Actualizar el stock del nuevo auto (reducir en 1)
        autoService.actualizarStock(request.getIdAuto(), 1);
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
        
        // Restaurar el stock del auto cuando se elimina la venta
        if (venta != null && venta.getAuto() != null) {
            Auto auto = venta.getAuto();
            auto.setStock(auto.getStock() + 1);
            if (auto.getStock() > 0 && "Vendido".equals(auto.getEstado())) {
                auto.setEstado("Disponible");
            }
            autoRepository.save(auto);
            System.out.println("INFO: Stock restaurado para auto ID " + auto.getId() + " al eliminar venta ID " + id);
        }
        
        ventaRepository.deleteById(id);
    }
}