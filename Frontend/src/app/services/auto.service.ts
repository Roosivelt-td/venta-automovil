import {inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ApiUrlService} from '../config/ApiUrlService';
import {Router} from '@angular/router';

export interface Auto {
  id?: number;
  marca: string;
  modelo: string;
  anio: number;
  color: string;
  kilometraje: number;
  tipoCombustible: string;
  transmision: string;
  cilindrada: number;
  potencia: number;
  stock: number;
  precio: number;
  descripcion?: string;
  imagenUrl?: string;
  estado?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AutoService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private apiUrlService = inject(ApiUrlService);

  private get baseUrl(): string {
    return this.apiUrlService.getUrl('autos'); // Asegúrate que 'autos' exista en api.config.ts
  }

  crearAuto(auto: Auto): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, auto);
  }

  obtenerTodosLosAutos(): Observable<Auto[]> {
    return this.http.get<Auto[]>(`${this.baseUrl}/`);
  }

  obtenerAutoPorId(id: number): Observable<Auto> {
    return this.http.get<Auto>(`${this.baseUrl}/${id}`);
  }

  actualizarAuto(id: number, auto: Auto): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/${id}`, auto);
  }

  eliminarAuto(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`);
  }

  obtenerAutosPorMarca(marca: string): Observable<Auto[]> {
    return this.http.get<Auto[]>(`${this.baseUrl}/marca/${marca}`);
  }

  buscarAutosPorMarcaYModelo(marca: string, modelo: string): Observable<Auto[]> {
    return this.http.get<Auto[]>(`${this.baseUrl}/buscar?marca=${marca}&modelo=${modelo}`);
  }

  actualizarStock(id: number, cantidad: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}/stock?cantidad=${cantidad}`, {});
  }

}
