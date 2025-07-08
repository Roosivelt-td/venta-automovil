import {inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ApiUrlService} from '../config/ApiUrlService';
import {Router} from '@angular/router';

export interface Pago {
  identificador?: number;
  idVenta: number;
  metodoPago: string;
  monto: number;
  fecha: string;
  venta?: any; // Información de la venta asociada
}

export interface PagoRequest {
  idVenta: number;
  metodoPago: string;
  monto: number;
  fecha: string;
}

@Injectable({
  providedIn: 'root'
})
export class PagoService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private apiUrl = inject(ApiUrlService);
  // Obtener todos los pagos
  obtenerTodosLosPagos(): Observable<Pago[]> {
    return this.http.get<Pago[]>(`${this.apiUrl}/todos`);
  }

  // Obtener pago por ID
  obtenerPagoPorId(id: number): Observable<Pago> {
    return this.http.get<Pago>(`${this.apiUrl}/${id}`);
  }

  // Crear nuevo pago
  crearPago(pago: PagoRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/create`, pago);
  }

  // Actualizar pago
  actualizarPago(id: number, pago: PagoRequest): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/update/${id}`, pago);
  }

  // Eliminar pago
  eliminarPago(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
