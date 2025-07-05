import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClienteService, Cliente, ClienteRequest } from '../../../../services/cliente.service';
import { VentaService, VentaRequest } from '../../../../services/venta.service';
import { AutoService } from '../../../../services/auto.service';

@Component({
  selector: 'app-registrar-venta',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './registrar-venta.html',
  styleUrls: ['./registrar-venta.css']
})
export class RegistrarVentaComponent {
  ventaForm: FormGroup;
  clienteForm: FormGroup;
  clientes: Cliente[] = [];
  autos: any[] = [];
  loading = false;
  mostrarFormularioCliente = false;
  clienteSeleccionado: Cliente | null = null;
  dniBusqueda: number | null = null;
  mostrarMensajeExito = false;
  mensajeExito = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private clienteService: ClienteService,
    private ventaService: VentaService,
    private autoService: AutoService
  ) {
    this.ventaForm = this.fb.group({
      clienteId: [null, Validators.required],
      autoId: ['', Validators.required],
      fechaVenta: ['', Validators.required],
      precioVenta: ['', [Validators.required, Validators.min(0)]],
      metodoPago: ['', Validators.required],
      observaciones: ['']
    });

    this.clienteForm = this.fb.group({
      nombre: ['', Validators.required],
      // apellidos: [''], // Removido porque no existe en la BD
      dni: ['', [Validators.required, Validators.min(10000000), Validators.max(99999999)]],
      telefono: ['', Validators.required],
      direccion: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnInit() {
    this.cargarClientes();
    this.cargarAutos();
  }

  cargarClientes() {
    console.log('Intentando cargar clientes...');
    this.clienteService.obtenerTodosLosClientes().subscribe({
      next: (clientes) => {
        console.log('Clientes cargados:', clientes);
        this.clientes = clientes;
      },
      error: (error) => {
        console.error('Error al cargar clientes:', error);
        console.error('Status:', error.status);
        console.error('Message:', error.message);
        console.error('URL:', error.url);
      }
    });
  }

  cargarAutos() {
    this.autoService.obtenerTodosLosAutos().subscribe({
      next: (autos) => {
        this.autos = autos;
      },
      error: (error) => {
        console.error('Error al cargar autos:', error);
      }
    });
  }

  buscarClientePorDni() {
    if (this.dniBusqueda) {
      this.clienteService.buscarClientesPorDni(this.dniBusqueda).subscribe({
        next: (clientes) => {
          if (clientes.length > 0) {
            this.clienteSeleccionado = clientes[0];
            console.log('Cliente encontrado:', this.clienteSeleccionado);
            console.log('ID del cliente encontrado:', this.clienteSeleccionado.identificador);
            
            // Establecer el clienteId en el formulario
            this.ventaForm.get('clienteId')?.setValue(this.clienteSeleccionado.identificador);
            
            // Forzar la actualización del formulario
            this.ventaForm.get('clienteId')?.markAsTouched();
            this.ventaForm.get('clienteId')?.markAsDirty();
            
            console.log('Formulario después de establecer clienteId:', this.ventaForm.value);
            console.log('clienteId después de patchValue:', this.ventaForm.get('clienteId')?.value);
            this.mostrarFormularioCliente = false;
          } else {
            this.mostrarFormularioCliente = true;
            this.clienteSeleccionado = null;
          }
        },
        error: (error) => {
          console.error('Error al buscar cliente:', error);
        }
      });
    }
  }

  registrarCliente() {
    if (this.clienteForm.valid) {
      const clienteData: ClienteRequest = this.clienteForm.value;
      console.log('Datos del cliente a enviar:', clienteData);
      this.clienteService.crearCliente(clienteData).subscribe({
        next: (clienteCreado) => {
          this.clienteSeleccionado = clienteCreado;
          console.log('Cliente creado:', clienteCreado);
          console.log('ID del cliente creado:', clienteCreado.identificador);
          
          // Establecer el clienteId en el formulario
          this.ventaForm.get('clienteId')?.setValue(clienteCreado.identificador);
          
          // Forzar la actualización del formulario
          this.ventaForm.get('clienteId')?.markAsTouched();
          this.ventaForm.get('clienteId')?.markAsDirty();
          
          console.log('Formulario después de crear cliente:', this.ventaForm.value);
          console.log('clienteId después de patchValue:', this.ventaForm.get('clienteId')?.value);
          this.mostrarFormularioCliente = false;
          this.cargarClientes(); // Recargar la lista de clientes
        },
        error: (error) => {
          console.error('Error al registrar cliente:', error);
        }
      });
    }
  }

  onSubmit() {
    console.log('Estado del formulario de venta:');
    console.log('Válido:', this.ventaForm.valid);
    console.log('Valores:', this.ventaForm.value);
    console.log('Errores:', this.ventaForm.errors);
    
    // Mostrar errores de cada campo
    Object.keys(this.ventaForm.controls).forEach(key => {
      const control = this.ventaForm.get(key);
      if (control?.invalid) {
        console.log(`Campo ${key} inválido:`, control.errors);
      }
    });

    if (this.ventaForm.valid) {
      this.loading = true;
      
      const ventaData: VentaRequest = {
        idCliente: this.ventaForm.get('clienteId')?.value,
        idAuto: this.ventaForm.get('autoId')?.value,
        idUsuario: 1, // TODO: Obtener el ID del usuario logueado
        fecha: this.ventaForm.get('fechaVenta')?.value,
        precioVenta: this.ventaForm.get('precioVenta')?.value,
        metodoPago: this.ventaForm.get('metodoPago')?.value
      };

      console.log('Datos de venta a enviar:', ventaData);

      this.ventaService.crearVenta(ventaData).subscribe({
        next: () => {
          this.loading = false;
          // Mostrar mensaje de éxito
          this.mensajeExito = '¡Venta registrada exitosamente!';
          this.mostrarMensajeExito = true;
          
          // Ocultar mensaje después de 3 segundos y redirigir
          setTimeout(() => {
            this.mostrarMensajeExito = false;
            this.router.navigate(['/ventas']);
          }, 3000);
        },
        error: (error) => {
          console.error('Error al registrar venta:', error);
          this.loading = false;
          alert('Error al registrar la venta. Por favor, inténtalo de nuevo.');
        }
      });
    }
  }

  cancelar() {
    this.router.navigate(['/ventas']);
  }

  mostrarFormularioNuevoCliente() {
    this.mostrarFormularioCliente = true;
    this.clienteSeleccionado = null;
    this.clienteForm.reset();
  }

  debugFormulario() {
    console.log('=== DEBUG FORMULARIO VENTA ===');
    console.log('Formulario válido:', this.ventaForm.valid);
    console.log('Valores del formulario:', JSON.stringify(this.ventaForm.value, null, 2));
    console.log('Cliente seleccionado:', JSON.stringify(this.clienteSeleccionado, null, 2));
    console.log('Cliente ID en formulario:', this.ventaForm.get('clienteId')?.value);
    
    // Verificar cada campo
    Object.keys(this.ventaForm.controls).forEach(key => {
      const control = this.ventaForm.get(key);
      console.log(`Campo ${key}:`, {
        valor: control?.value,
        válido: control?.valid,
        inválido: control?.invalid,
        errores: control?.errors,
        touched: control?.touched,
        dirty: control?.dirty
      });
    });
    console.log('==============================');
  }
} 