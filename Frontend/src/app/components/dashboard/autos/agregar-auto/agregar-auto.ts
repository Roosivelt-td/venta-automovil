import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-auto',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './agregar-auto.html',
  styleUrls: ['./agregar-auto.css']
})
export class AgregarAutoComponent implements OnInit {
  mostrarModal = false;
  autoForm: FormGroup;
  marcas = [
    'Toyota', 'Honda', 'Ford', 'Chevrolet', 'Nissan', 'BMW', 'Mercedes-Benz', 
    'Audi', 'Volkswagen', 'Hyundai', 'Kia', 'Mazda', 'Subaru', 'Lexus', 
    'Acura', 'Infiniti', 'Volvo', 'Jaguar', 'Land Rover', 'Porsche'
  ];
  
  colores = [
    'Blanco', 'Negro', 'Gris', 'Plateado', 'Azul', 'Rojo', 'Verde', 
    'Amarillo', 'Naranja', 'Marrón', 'Beige', 'Dorado', 'Púrpura'
  ];
  
  tiposCombustible = [
    'Gasolina', 'Diesel', 'Eléctrico', 'Híbrido', 'Gas Natural', 'Hidrógeno'
  ];
  
  transmisiones = [
    'Manual', 'Automática', 'CVT', 'Semi-automática'
  ];

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {
    this.autoForm = this.fb.group({
      marca: ['', Validators.required],
      modelo: ['', Validators.required],
      año: ['', [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear() + 1)]],
      color: ['', Validators.required],
      precio: ['', [Validators.required, Validators.min(0)]],
      kilometraje: ['', [Validators.required, Validators.min(0)]],
      tipoCombustible: ['', Validators.required],
      transmision: ['', Validators.required],
      cilindrada: ['', [Validators.required, Validators.min(0)]],
      potencia: ['', [Validators.required, Validators.min(0)]],
      stock: ['', [Validators.required, Validators.min(0)]],
      descripcion: ['', Validators.maxLength(500)],
      imagen: ['']
    });
  }

  ngOnInit(): void {
    // Abrir modal automáticamente al cargar el componente
    this.mostrarModal = true;
  }

  abrirModal(): void {
    this.mostrarModal = true;
  }

  cerrarModal(): void {
    this.mostrarModal = false;
    this.router.navigate(['/autos/gestionar']);
  }

  onSubmit(): void {
    if (this.autoForm.valid) {
      console.log('Datos del auto:', this.autoForm.value);
      // Aquí iría la lógica para enviar los datos al backend
      
      // Simular envío exitoso
      alert('Auto añadido exitosamente');
      this.cerrarModal();
    } else {
      this.marcarCamposInvalidos();
    }
  }

  marcarCamposInvalidos(): void {
    Object.keys(this.autoForm.controls).forEach(key => {
      const control = this.autoForm.get(key);
      if (control?.invalid) {
        control.markAsTouched();
      }
    });
  }

  esCampoInvalido(campo: string): boolean {
    const control = this.autoForm.get(campo);
    return control ? (control.invalid && control.touched) : false;
  }

  obtenerMensajeError(campo: string): string {
    const control = this.autoForm.get(campo);
    if (control?.errors) {
      if (control.errors['required']) return 'Este campo es requerido';
      if (control.errors['min']) return `El valor mínimo es ${control.errors['min'].min}`;
      if (control.errors['max']) return `El valor máximo es ${control.errors['max'].max}`;
      if (control.errors['maxlength']) return `Máximo ${control.errors['maxlength'].requiredLength} caracteres`;
    }
    return '';
  }
} 