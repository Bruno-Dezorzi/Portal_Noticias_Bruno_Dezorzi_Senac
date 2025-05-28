import { Component, OnInit } from "@angular/core";
import { Noticia } from "../../model/noticia";
import { Router, RouterLink } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-home',
  standalone: true,                    // ← Habilitado
  imports: [CommonModule, FormsModule, RouterLink], // ← Imports diretos
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // Propriedades do componente
  isLoggedIn: boolean = false;
  termoBusca: string = '';

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.verificarStatusLogin();
  }

  /**
   * Verifica se o usuário está logado
   */
  verificarStatusLogin(): void {
    // Simulação de verificação de login usando localStorage
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    this.isLoggedIn = isLoggedIn === 'true';
  }

  /**
   * Função para buscar notícias
   */
  buscarNoticias(): void {
    if (this.termoBusca.trim()) {
      // Aqui você pode implementar a lógica de busca
      console.log('Buscando por:', this.termoBusca);

      // Exemplo de navegação para página de resultados
      this.router.navigate(['/busca'], {
        queryParams: { q: this.termoBusca }
      });
    }
  }

  /**
   * Método para fazer logout (se necessário)
   */
  logout(): void {
    localStorage.removeItem('isLoggedIn');
    this.isLoggedIn = false;
  }

  /**
   * Método para navegar para uma categoria específica
   */
  navegarParaCategoria(categoria: string): void {
    this.router.navigate(['/categoria', categoria]);
  }

  /**
   * Método para navegar para uma notícia específica
   */
  navegarParaNoticia(id: number): void {
    this.router.navigate(['/noticia', id]);
  }
}

/*import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  constructor(private router: Router) {}

  ngOnInit(): void {
  }

  redirectToAdmin() {
    this.router.navigate(['/admin']);
  }

}*/
