import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiUrlService } from '../config/ApiUrlService';

export interface Venta {
  identificador?: number;
  cliente: any;
  auto: any;
  usuario: any;
  fecha: string;
  precioVenta: number;
}

export interface VentaRequest {
  idCliente: number;
  idAuto: number;
  idUsuario: number;
  fecha: string;
  precioVenta: number;
  metodoPago: string;
}

@Injectable({
  providedIn: 'root'
})
export class VentaService {
  private apiUrl: string;

  constructor(private http: HttpClient, private apiUrlService: ApiUrlService) {
    this.apiUrl = 'http://localhost:8080/api';
  }

  // Obtener todas las ventas
  obtenerTodasLasVentas(): Observable<Venta[]> {
    return this.http.get<Venta[]>(`${this.apiUrl}/ventas/todos`);
  }

  // Obtener venta por ID
  obtenerVentaPorId(id: number): Observable<Venta> {
    return this.http.get<Venta>(`${this.apiUrl}/ventas/${id}`);
  }

  // Crear nueva venta
  crearVenta(venta: VentaRequest): Observable<void> {
    console.log('URL de venta:', `${this.apiUrl}/ventas/create`);
    console.log('Datos de venta a enviar:', JSON.stringify(venta, null, 2));
    return this.http.post<void>(`${this.apiUrl}/ventas/create`, venta);
  }

  // Actualizar venta
  actualizarVenta(id: number, venta: VentaRequest): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/ventas/update/${id}`, venta);
  }

  // Eliminar venta
  eliminarVenta(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/ventas/delete/${id}`);
  }
} 