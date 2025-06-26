import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  showError: boolean = false;
  searchTerm: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      rememberMe: [false],
      userType: ['reader', Validators.required]
    });
  }

  ngOnInit(): void {
    // Componente inicializado
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const formData = this.loginForm.value;

      // Simulação de validação (apenas para demonstração)
      if (formData.email && formData.password) {
        // Armazenar informações do usuário no localStorage
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('userEmail', formData.email);
        localStorage.setItem('userType', formData.userType);

        // Redirecionar com base no tipo de usuário
        if (formData.userType === 'admin') {
          this.router.navigate(['/admin']); // ou a rota do admin
        } else {
          this.router.navigate(['/home']);
        }
      } else {
        this.showError = true;
      }
    } else {
      this.showError = true;
    }
  }

  onSearch(event: Event): void {
    event.preventDefault();
    if (this.searchTerm.trim()) {
      // Implementar lógica de busca
      console.log('Buscar por:', this.searchTerm);
      // Redirecionar para página de resultados ou implementar busca
    }
  }

  loginWithFacebook(): void {
    // Implementar login com Facebook
    console.log('Login com Facebook');
  }

  loginWithGoogle(): void {
    // Implementar login com Google
    console.log('Login com Google');
  }
}
