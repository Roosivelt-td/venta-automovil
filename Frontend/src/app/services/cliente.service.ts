import {inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiUrlService } from '../config/ApiUrlService';

export interface Cliente {
  identificador?: number;
  nombre: string;
  apellidos?: string;
  dni: number;
  telefono: string;
  direccion: string;
  correo: string;
}

export interface ClienteRequest {
  nombre: string;
  // apellidos?: string; // Comentado porque no existe en la BD
  dni: number;
  telefono: string;
  direccion: string;
  correo: string;
}

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private http = inject(HttpClient);
  private apiUrlService = inject(ApiUrlService);

  private get baseUrl(): string {
    return this.apiUrlService.getUrl('clientes');
  }

  obtenerTodosLosClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.baseUrl}/todos`);
  }

  obtenerClientePorId(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.baseUrl}/${id}`);
  }

  crearCliente(cliente: ClienteRequest): Observable<Cliente> {
    console.log('URL de creación:', `${this.baseUrl}/create`);
    return this.http.post<Cliente>(`${this.baseUrl}/create`, cliente);
  }

  actualizarCliente(id: number, cliente: ClienteRequest): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.baseUrl}/update/${id}`, cliente);
  }

  eliminarCliente(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

  buscarClientesPorDni(dni: number): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.baseUrl}/buscar?dni=${dni}`);
  }
}
