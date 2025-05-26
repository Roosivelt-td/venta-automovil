package automoviles.service.impl;

import automoviles.model.*;
import automoviles.repository.*;
import automoviles.service.VentaService;
import automoviles.service.mapper.VentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VentaMapper ventaMapper;

    @Override
    public VentaDto crearVenta(VentaDto ventaDto) {
        Venta venta = ventaMapper.toEntity(ventaDto);

        // Cargar las entidades relacionadas
        Cliente cliente = clienteRepository.findById(ventaDto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Auto auto = autoRepository.findById(ventaDto.getIdAuto())
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));
        Usuario usuario = usuarioRepository.findById(ventaDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        venta.setCliente(cliente);
        venta.setAuto(auto);
        venta.setUsuario(usuario);

        venta = ventaRepository.save(venta);
        return ventaMapper.toDto(venta);
    }

    @Override
    public VentaDto obtenerVentaPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return ventaMapper.toDto(venta);
    }

    @Override
    public List<VentaDto> obtenerTodasLasVentas() {
        return ventaRepository.findAll().stream()
                .map(ventaMapper::toDto)
                .collect(Collectors.toList());
    }
}