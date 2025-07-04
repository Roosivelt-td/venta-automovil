import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pagos-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pagos.html',
  styleUrls: ['./pagos-list.css']
})
export class PagosListComponent {
  mostrarModalRegistro: boolean = false;

  abrirModalRegistro() {
    this.mostrarModalRegistro = true;
  }

  cerrarModalRegistro() {
    this.mostrarModalRegistro = false;
  }
} 