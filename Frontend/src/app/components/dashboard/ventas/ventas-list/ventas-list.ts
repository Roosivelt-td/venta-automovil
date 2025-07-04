import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ventas-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ventas-list.html',
  styleUrl: './ventas-list.css'
})
export class VentasListComponent {
  filtroVentas: string = '';
  mostrarModalRegistro: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  abrirModalRegistro(): void {
    this.mostrarModalRegistro = true;
  }

  cerrarModalRegistro(): void {
    this.mostrarModalRegistro = false;
  }

  registrarNuevaVenta(): void {
    // Lógica para registrar una nueva venta
    console.log('Registrar nueva venta');
    this.cerrarModalRegistro(); // Cerrar modal después de registrar
  }

  buscarVenta(): void {
    // Lógica para buscar ventas
    console.log('Buscar venta:', this.filtroVentas);
  }

  editarVenta(id: number): void {
    // Lógica para editar una venta
    console.log('Editar venta:', id);
  }

  eliminarVenta(id: number): void {
    // Lógica para eliminar una venta
    console.log('Eliminar venta:', id);
  }
}
