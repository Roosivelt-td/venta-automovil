import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-autos-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './autos-list.html',
  styleUrls: ['./autos-list.css']
})
export class AutosListComponent {
  autos = [
    {
      id: 1,
      anio: 2022,
      color: 'Rojo',
      descripcion: 'Auto deportivo en excelente estado',
      estado: 'Disponible',
      imagen_url: 'https://via.placeholder.com/100',
      kilometraje: 15000,
      marca: 'Toyota',
      modelo: 'Supra',
      precio: 55000.00,
      tipo: 'Deportivo'
    }
  ];
} 