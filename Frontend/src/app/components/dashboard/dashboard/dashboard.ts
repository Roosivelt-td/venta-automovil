import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { interval, Subscription } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';

// Interfaces temporales
interface DashboardStats {
  totalAutos: number;
  autosVendidos: number;
  stockDisponible: number;
  totalClientes: number;
  totalVentas: number;
  totalPagos: number;
  totalProveedores: number;
  totalCompras: number;
  totalReembolsos: number;
  ingresosTotales: number;
  promedioVenta: number;
  autosSinStock: number;
}

interface ActividadReciente {
  id: number;
  tipo: string;
  descripcion: string;
  fecha: string;
  icono: string;
  color: string;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class DashboardComponent implements OnInit, OnDestroy {
  stats: DashboardStats = {
    totalAutos: 0,
    autosVendidos: 0,
    stockDisponible: 0,
    totalClientes: 0,
    totalVentas: 0,
    totalPagos: 0,
    totalProveedores: 0,
    totalCompras: 0,
    totalReembolsos: 0,
    ingresosTotales: 0,
    promedioVenta: 0,
    autosSinStock: 0
  };

  actividades: ActividadReciente[] = [];
  alertas: any[] = [];
  topAutosVendidos: any[] = [];
  estadisticasVentas: any[] = [];
  
  loading = false;
  errorMessage = '';
  private refreshSubscription?: Subscription;

  constructor(
    // private dashboardService: DashboardService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cargarDatos();
    
    // Actualizar datos cada 30 segundos
    this.refreshSubscription = interval(30000).subscribe(() => {
      this.cargarDatos();
    });
  }

  ngOnDestroy(): void {
    if (this.refreshSubscription) {
      this.refreshSubscription.unsubscribe();
    }
  }

  cargarDatos(): void {
    this.loading = true;
    this.errorMessage = '';

    // Datos temporales para que compile
    this.stats = {
      totalAutos: 25,
      autosVendidos: 12,
      stockDisponible: 13,
      totalClientes: 8,
      totalVentas: 12,
      totalPagos: 12,
      totalProveedores: 3,
      totalCompras: 5,
      totalReembolsos: 1,
      ingresosTotales: 150000,
      promedioVenta: 12500,
      autosSinStock: 2
    };

    this.actividades = [
      {
        id: 1,
        tipo: 'auto',
        descripcion: 'Auto agregado: Honda Civic 2023',
        fecha: new Date().toISOString(),
        icono: 'fas fa-car',
        color: 'blue'
      },
      {
        id: 2,
        tipo: 'venta',
        descripcion: 'Venta completada: Toyota Camry',
        fecha: new Date(Date.now() - 3600000).toISOString(),
        icono: 'fas fa-dollar-sign',
        color: 'green'
      }
    ];

    this.alertas = [
      {
        tipo: 'warning',
        titulo: 'Stock Bajo',
        mensaje: '2 auto(s) con stock bajo (≤2 unidades)',
        icono: 'fas fa-exclamation-triangle'
      }
    ];

    this.topAutosVendidos = [
      {
        nombre: 'Honda Civic',
        ventas: 3,
        ingresos: 45000
      },
      {
        nombre: 'Toyota Camry',
        ventas: 2,
        ingresos: 35000
      }
    ];

    this.loading = false;
  }

  // Navegación a diferentes secciones
  irAAutos(): void {
    this.router.navigate(['/autos']);
  }

  irAVentas(): void {
    this.router.navigate(['/ventas']);
  }

  irAClientes(): void {
    this.router.navigate(['/clientes']);
  }

  irAPagos(): void {
    this.router.navigate(['/pagos']);
  }

  irAProveedores(): void {
    this.router.navigate(['/proveedores']);
  }

  // Formatear tiempo relativo
  formatearTiempoRelativo(fecha: string): string {
    const ahora = new Date();
    const fechaActividad = new Date(fecha);
    const diferencia = ahora.getTime() - fechaActividad.getTime();
    
    const minutos = Math.floor(diferencia / (1000 * 60));
    const horas = Math.floor(diferencia / (1000 * 60 * 60));
    const dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));

    if (minutos < 60) {
      return `Hace ${minutos} min`;
    } else if (horas < 24) {
      return `Hace ${horas} hora${horas > 1 ? 's' : ''}`;
    } else {
      return `Hace ${dias} día${dias > 1 ? 's' : ''}`;
    }
  }

  // Obtener color de alerta
  getColorAlerta(tipo: string): string {
    switch (tipo) {
      case 'success': return 'green';
      case 'warning': return 'yellow';
      case 'danger': return 'red';
      default: return 'blue';
    }
  }

  // Obtener icono de alerta
  getIconoAlerta(tipo: string): string {
    switch (tipo) {
      case 'success': return 'fas fa-check-circle';
      case 'warning': return 'fas fa-exclamation-triangle';
      case 'danger': return 'fas fa-times-circle';
      default: return 'fas fa-info-circle';
    }
  }
}
