// app.routes.ts
import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login';
import { RegisterComponent } from './components/auth/register/register';
import { ForgotPasswordComponent } from './components/auth/forgot-password/forgot-password';
import { ResetPasswordComponent } from './components/auth/reset-password/reset-password';
import { MainLayoutComponent } from './components/layout/main-layout/main-layout';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard';
import { ComprasListComponent } from './components/dashboard/compras/compras-list/compras-list';
import { AuthGuard } from './guards/auth.guard';
import { UsuarioList } from './components/dashboard/usuario-list/usuario-list';
import { InventarioListComponent } from './components/dashboard/inventario/inventario-list/inventario-list';
import { VentasListComponent } from './components/dashboard/ventas/ventas-list/ventas-list';
import { ClientesListComponent } from './components/dashboard/clientes/clientes-list/clientes-list';
import { ProveedoresListComponent } from './components/dashboard/proveedores/proveedores-list/proveedores-list';
import { PagosListComponent } from './components/dashboard/pagos/pagos-list/pagos-list';
import { ReembolsosListComponent } from './components/dashboard/reembolsos/reembolsos-list/reembolsos-list';
import { UsuariosListComponent } from './components/dashboard/usuarios/usuarios';
import { AgregarAutoComponent } from './components/dashboard/autos/agregar-auto/agregar-auto';
import { GestionarAutosComponent } from './components/dashboard/autos/gestionar-autos/gestionar-autos';
import { AgregarProveedorComponent } from './components/dashboard/proveedores/agregar-proveedor/agregar-proveedor';

export const routes: Routes = [
    // Rutas públicas (sin layout)
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'forgot-password', component: ForgotPasswordComponent },
    { path: 'reset-password', component: ResetPasswordComponent },
    { path: 'reset-password/:token', component: ResetPasswordComponent },

    // Todas las rutas del dashboard dentro del MainLayout
    {
      path: '',
      component: MainLayoutComponent,
      children: [
        { path: '', component: DashboardComponent },
        { path: 'dashboard', component: DashboardComponent },
        { path: 'usuarios', component: UsuariosListComponent },
        { path: 'compras', component: ComprasListComponent },
        { path: 'compra', redirectTo: 'compras' },
        { path: 'inventario', component: InventarioListComponent },
        { path: 'ventas', component: VentasListComponent },
        { path: 'clientes', component: ClientesListComponent },
        { path: 'proveedores', component: ProveedoresListComponent },
        { path: 'proveedores/agregar', component: AgregarProveedorComponent },
        { path: 'pagos', component: PagosListComponent },
        { path: 'reembolsos', component: ReembolsosListComponent },
        { path: 'autos/agregar', component: AgregarAutoComponent },
        { path: 'autos/gestionar', component: GestionarAutosComponent }
      ]
    },

    // Redirección global para rutas no encontradas
    { path: '**', redirectTo: '/' }
];
