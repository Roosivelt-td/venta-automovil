import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-proveedores-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './proveedores-list.html',
  styleUrl: './proveedores-list.css'
})
export class ProveedoresListComponent {
  mostrarModalRegistro: boolean = false;

  abrirModalRegistro() {
    this.mostrarModalRegistro = true;
  }

  cerrarModalRegistro() {
    this.mostrarModalRegistro = false;
  }
}