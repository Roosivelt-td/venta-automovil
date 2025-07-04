import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UsuarioService } from '../../../services/usuario';
import { User } from '../../../models/user';

@Component({
  selector: 'app-usuarios-list',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './usuarios.html',
  styleUrls: ['./usuarios.css']
})
export class UsuariosListComponent implements OnInit {
  mostrarModalRegistro: boolean = false;
  mostrarModalEdicion: boolean = false;
  mostrarModalConfirmacion: boolean = false;
  usuarios: User[] = [];
  usersDisponibles: any[] = [];
  usuarioForm!: FormGroup;
  usuarioEditando: User | null = null;
  usuarioEliminando: User | null = null;
  isLoading: boolean = false;
  usuarioEliminandoId: number | null = null;

  private usuarioService = inject(UsuarioService);
  private fb = inject(FormBuilder);

  ngOnInit() {
    this.inicializarFormulario();
    this.cargarUsuarios();
    this.cargarUsersDisponibles();
  }

  inicializarFormulario() {
    this.usuarioForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(2)]],
      apellido: ['', [Validators.required, Validators.minLength(2)]],
      sexo: ['', Validators.required],
      direccion: ['', [Validators.required, Validators.minLength(5)]],
      celular: ['', [Validators.required, Validators.pattern(/^[0-9]{9,10}$/)]],
      estado: [true, Validators.required],
      idUser: [null]
    });
  }

  cargarUsuarios() {
    this.usuarioService.getUsuarios().subscribe({
      next: (data: User[]) => {
        console.log('Usuarios cargados:', data);
        this.usuarios = data;
      },
      error: (error) => {
        console.error('Error al cargar usuarios:', error);
        this.usuarios = [];
        alert('Error al cargar usuarios. Por favor, recarga la página.');
      }
    });
  }

  cargarUsersDisponibles() {
    this.usuarioService.getUsersDisponibles().subscribe({
      next: (data: any[]) => {
        console.log('Users disponibles:', data);
        this.usersDisponibles = data;
      },
      error: (error) => {
        console.error('Error al cargar users disponibles:', error);
        console.error('Status:', error.status);
        console.error('StatusText:', error.statusText);
        console.error('URL:', error.url);
        console.error('Detalles del error:', error.error);
        this.usersDisponibles = [];
        // No mostrar alerta para este error ya que no es crítico
      }
    });
  }

  abrirModalRegistro() {
    this.mostrarModalRegistro = true;
    this.usuarioForm.reset({ estado: true, idUser: null });
  }

  cerrarModalRegistro() {
    this.mostrarModalRegistro = false;
    this.usuarioForm.reset();
  }

  abrirModalEdicion(usuario: User) {
    this.usuarioEditando = usuario;
    this.mostrarModalEdicion = true;
    
    // Cargar los datos del usuario en el formulario
    this.usuarioForm.patchValue({
      nombre: usuario.nombre,
      apellido: usuario.apellido,
      sexo: usuario.sexo,
      direccion: usuario.direccion,
      celular: usuario.celular,
      estado: usuario.estado,
      idUser: usuario.idUser
    });
  }

  cerrarModalEdicion() {
    this.mostrarModalEdicion = false;
    this.usuarioEditando = null;
    this.usuarioForm.reset();
  }

  registrarUsuario() {
    if (this.usuarioForm.valid) {
      this.isLoading = true;
      const nuevoUsuario = this.usuarioForm.value;
      
      this.usuarioService.crearUsuario(nuevoUsuario).subscribe({
        next: (usuario) => {
          // Recargar la lista completa en lugar de solo agregar
          this.cargarUsuarios();
          this.cerrarModalRegistro();
          this.isLoading = false;
          // Aquí podrías mostrar un mensaje de éxito
          alert('Usuario registrado exitosamente');
        },
        error: (error) => {
          this.isLoading = false;
          console.error('Error al registrar usuario:', error);
          alert('Error al registrar usuario. Por favor, inténtalo de nuevo.');
        }
      });
    } else {
      this.marcarCamposInvalidos();
    }
  }

  actualizarUsuario() {
    if (this.usuarioForm.valid && this.usuarioEditando) {
      this.isLoading = true;
      const usuarioActualizado = this.usuarioForm.value;
      
      console.log('Actualizando usuario:', this.usuarioEditando.identificador, usuarioActualizado);
      
      this.usuarioService.actualizarUsuario(this.usuarioEditando.identificador, usuarioActualizado).subscribe({
        next: (usuario) => {
          console.log('Usuario actualizado exitosamente:', usuario);
          this.cargarUsuarios();
          this.cerrarModalEdicion();
          this.isLoading = false;
          alert('Usuario actualizado exitosamente');
        },
        error: (error) => {
          this.isLoading = false;
          console.error('Error al actualizar usuario:', error);
          console.error('Detalles del error:', error.error);
          alert('Error al actualizar usuario: ' + (error.error?.message || error.message || 'Error desconocido'));
        }
      });
    } else {
      this.marcarCamposInvalidos();
    }
  }

  eliminarUsuario(usuario: User) {
    this.usuarioEliminando = usuario;
    this.mostrarModalConfirmacion = true;
  }

  confirmarEliminacion() {
    if (this.usuarioEliminando) {
      this.usuarioEliminandoId = this.usuarioEliminando.identificador;
      console.log('Eliminando usuario:', this.usuarioEliminando.identificador);
      
      this.usuarioService.deleteUsuario(this.usuarioEliminando.identificador).subscribe({
        next: () => {
          console.log('Usuario eliminado exitosamente');
          this.cargarUsuarios();
          this.cerrarModalConfirmacion();
          alert('Usuario eliminado exitosamente');
        },
        error: (error) => {
          this.usuarioEliminandoId = null;
          console.error('Error al eliminar usuario:', error);
          console.error('Status:', error.status);
          console.error('StatusText:', error.statusText);
          console.error('URL:', error.url);
          console.error('Detalles del error:', error.error);
          alert('Error al eliminar usuario: ' + (error.error?.message || error.message || `Error ${error.status}: ${error.statusText}`));
        }
      });
    }
  }

  cerrarModalConfirmacion() {
    this.mostrarModalConfirmacion = false;
    this.usuarioEliminando = null;
    this.usuarioEliminandoId = null;
  }

  marcarCamposInvalidos() {
    Object.keys(this.usuarioForm.controls).forEach(key => {
      const control = this.usuarioForm.get(key);
      if (control?.invalid) {
        control.markAsTouched();
      }
    });
  }

  esCampoInvalido(campo: string): boolean {
    const control = this.usuarioForm.get(campo);
    return control ? (control.invalid && control.touched) : false;
  }

  obtenerMensajeError(campo: string): string {
    const control = this.usuarioForm.get(campo);
    if (control?.errors) {
      if (control.errors['required']) return 'Este campo es requerido';
      if (control.errors['minlength']) return `Mínimo ${control.errors['minlength'].requiredLength} caracteres`;
      if (control.errors['pattern']) return 'Formato inválido';
    }
    return '';
  }
} 