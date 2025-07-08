import {inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ApiUrlService} from '../config/ApiUrlService';
import {Router} from '@angular/router';

export interface Proveedor {
  identificador?: number;
  nombreEmpresa: string;
  ruc: string;
  contacto: string;
  telefono: string;
  direccion?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private apiUrl = inject(ApiUrlService);
  // Crear un nuevo proveedor
  crearProveedor(proveedor: Proveedor): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, proveedor);
  }

  // Obtener todos los proveedores
  obtenerTodosLosProveedores(): Observable<Proveedor[]> {
    return this.http.get<Proveedor[]>(`${this.apiUrl}/todos`);
  }

  // Obtener proveedor por ID
  obtenerProveedorPorId(id: number): Observable<Proveedor> {
    return this.http.get<Proveedor>(`${this.apiUrl}/${id}`);
  }

  // Actualizar proveedor
  actualizarProveedor(id: number, proveedor: Proveedor): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${id}`, proveedor);
  }

  // Eliminar proveedor
  eliminarProveedor(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${id}`);
  }

  // Buscar proveedores por nombre de empresa
  buscarProveedoresPorEmpresa(nombreEmpresa: string): Observable<Proveedor[]> {
    return this.http.get<Proveedor[]>(`${this.apiUrl}/buscar?nombreEmpresa=${nombreEmpresa}`);
  }

  // Buscar proveedores por RUC
  buscarProveedoresPorRuc(ruc: string): Observable<Proveedor[]> {
    return this.http.get<Proveedor[]>(`${this.apiUrl}/buscar?ruc=${ruc}`);
  }
}
