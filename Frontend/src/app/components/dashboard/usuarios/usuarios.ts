import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioService } from '../../../services/usuario';
import { User } from '../../../models/user';

@Component({
  selector: 'app-usuarios-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './usuarios.html',
  styleUrls: ['./usuarios.css']
})
export class UsuariosListComponent implements OnInit {
  mostrarModalRegistro: boolean = false;
  usuarios: User[] = [];

  private usuarioService = inject(UsuarioService);

  ngOnInit() {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    this.usuarioService.getUsuarios().subscribe((data: User[]) => {
      this.usuarios = data;
    });
  }

  abrirModalRegistro() {
    this.mostrarModalRegistro = true;
  }

  cerrarModalRegistro() {
    this.mostrarModalRegistro = false;
  }
} 