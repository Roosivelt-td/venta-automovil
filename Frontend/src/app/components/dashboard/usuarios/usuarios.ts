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
  usuarios: User[] = [];
  usersDisponibles: any[] = [];
  usuarioForm!: FormGroup;
  isLoading: boolean = false;

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
        this.usersDisponibles = [];
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

  registrarUsuario() {
    if (this.usuarioForm.valid) {
      this.isLoading = true;
      const nuevoUsuario = this.usuarioForm.value;
      
      this.usuarioService.crearUsuario(nuevoUsuario).subscribe({
        next: (usuario) => {
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