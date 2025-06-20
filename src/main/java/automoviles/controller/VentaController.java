package automoviles.controller;

import automoviles.dto.request.UsuarioRequest;
import automoviles.dto.request.VentaRequest;
import automoviles.dto.response.UsuarioResponse;
import automoviles.dto.response.VentaResponse;
import automoviles.service.UsuarioService;
import automoviles.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;
