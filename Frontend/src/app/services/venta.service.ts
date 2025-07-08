import {inject, Injectable} from '@angular/core';
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
  metodoPago?: string;
  observaciones?: string;
}

export interface VentaRequest {
  idCliente: number;
  idAuto: number;
  idUsuario: number;
  fecha: string;
  precioVenta: number;
  metodoPago: string;
  observaciones?: string;
}

@Injectable({
  providedIn: 'root'
})
export class VentaService {
  private http = inject(HttpClient);
  private apiUrlService = inject(ApiUrlService);

  obtenerTodasLasVentas(): Observable<Venta[]> {
   const url = this.apiUrlService.getUrl('ventas') + '/todos';
    return this.http.get<Venta[]>(url);
  }

  obtenerVentaPorId(id: number): Observable<Venta> {
    const url = this.apiUrlService.getUrl('ventas') + `/${id}`;
    return this.http.get<Venta>(url);
  }

  crearVenta(venta: VentaRequest): Observable<void> {
    const url = this.apiUrlService.getUrl('ventas') + '/create';
    return this.http.post<void>(url, venta);
  }

  actualizarVenta(id: number, venta: VentaRequest): Observable<void> {
    const url = this.apiUrlService.getUrl('ventas') + `/update/${id}`;
    return this.http.put<void>(url, venta);
  }

  eliminarVenta(id: number): Observable<void> {
    const url = this.apiUrlService.getUrl('ventas') + `/delete/${id}`;
    return this.http.delete<void>(url);
  }

  buscarVentasPorCliente(nombreCliente: string): Observable<Venta[]> {
    const url = this.apiUrlService.getUrl('ventas') + `/buscar/cliente/${encodeURIComponent(nombreCliente)}`;
    return this.http.get<Venta[]>(url);
  }

  buscarVentasPorAuto(marca: string, modelo: string): Observable<Venta[]> {
    const url = this.apiUrlService.getUrl('ventas') + `/buscar/auto?marca=${encodeURIComponent(marca)}&modelo=${encodeURIComponent(modelo)}`;
    return this.http.get<Venta[]>(url);
  }

  buscarVentasPorUsuario(nombreUsuario: string): Observable<Venta[]> {
    const url = this.apiUrlService.getUrl('ventas') + `/buscar/usuario/${encodeURIComponent(nombreUsuario)}`;
    return this.http.get<Venta[]>(url);
  }

  buscarVentasPorFecha(fechaInicio: string, fechaFin: string): Observable<Venta[]> {
    const url = this.apiUrlService.getUrl('ventas') + `/buscar/fecha?fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`;
    return this.http.get<Venta[]>(url);
  }

  buscarVentasPorPrecio(precioMin: number, precioMax: number): Observable<Venta[]> {
    const url = this.apiUrlService.getUrl('ventas') + `/buscar/precio?precioMin=${precioMin}&precioMax=${precioMax}`;
    return this.http.get<Venta[]>(url);
  }

  buscarVentasPorTermino(termino: string): Observable<Venta[]> {
    const url = this.apiUrlService.getUrl('ventas') + `/buscar/termino/${encodeURIComponent(termino)}`;
    return this.http.get<Venta[]>(url);
  }
}
