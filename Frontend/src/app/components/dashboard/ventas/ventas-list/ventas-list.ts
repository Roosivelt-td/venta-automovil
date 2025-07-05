import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { VentaService, Venta } from '../../../../services/venta.service';

@Component({
  selector: 'app-ventas-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ventas-list.html',
  styleUrl: './ventas-list.css'
})
export class VentasListComponent implements OnInit {
  ventas: Venta[] = [];
  filtroVentas: string = '';
  loading = false;

  constructor(
    private router: Router,
    private ventaService: VentaService
  ) { }

  ngOnInit(): void {
    this.cargarVentas();
  }

  cargarVentas(): void {
    this.loading = true;
    this.ventaService.obtenerTodasLasVentas().subscribe({
      next: (ventas) => {
        this.ventas = ventas;
        this.loading = false;
        console.log('Ventas cargadas:', ventas);
      },
      error: (error) => {
        console.error('Error al cargar ventas:', error);
        this.loading = false;
        alert('Error al cargar las ventas');
      }
    });
  }

  abrirModalRegistro(): void {
    this.router.navigate(['/ventas/registrar']);
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
