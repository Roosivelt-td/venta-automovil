import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-gestionar-autos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './gestionar-autos.html',
  styleUrls: ['./gestionar-autos.css']
})
export class GestionarAutosComponent implements OnInit {
  
  autos = [
    {
      id: 1,
      marca: 'Toyota',
      modelo: 'Corolla',
      anio: 2023,
      color: 'Blanco',
      precio: 25000,
      stock: 5,
      estado: 'Disponible'
    },
    {
      id: 2,
      marca: 'Honda',
      modelo: 'Civic',
      anio: 2022,
      color: 'Negro',
      precio: 28000,
      stock: 3,
      estado: 'Disponible'
    },
    {
      id: 3,
      marca: 'Ford',
      modelo: 'F-150',
      anio: 2024,
      color: 'Gris',
      precio: 45000,
      stock: 2,
      estado: 'Disponible'
    }
  ];

  // Propiedades calculadas
  get totalAutos(): number {
    return this.autos.length;
  }

  get autosDisponibles(): number {
    return this.autos.filter(auto => auto.stock > 0).length;
  }

  get valorTotal(): string {
    const total = this.autos.reduce((sum, auto) => sum + auto.precio, 0);
    return total.toLocaleString();
  }

  get stockTotal(): number {
    return this.autos.reduce((sum, auto) => sum + auto.stock, 0);
  }

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Cargar datos de autos desde el servicio
  }

  navegarAAnadir(): void {
    this.router.navigate(['/autos/agregar']);
  }

  editarAuto(id: number): void {
    console.log('Editar auto con ID:', id);
    // Aquí iría la lógica para editar
  }

  eliminarAuto(id: number): void {
    if (confirm('¿Estás seguro de que quieres eliminar este auto?')) {
      console.log('Eliminar auto con ID:', id);
      // Aquí iría la lógica para eliminar
    }
  }

  verDetalles(id: number): void {
    console.log('Ver detalles del auto con ID:', id);
    // Aquí iría la lógica para ver detalles
  }
} 