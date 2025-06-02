import { Component, OnInit } from "@angular/core";
import { Noticia } from "../../model/noticia";
import { Router, RouterLink } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

interface CarouselItem {
  id: number;
  imageUrl: string;
  title: string;
  description: string;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // Propriedades do componente
  isLoggedIn: boolean = false;
  termoBusca: string = '';
  carouselItems: CarouselItem[] = [];
  isLoadingCarousel: boolean = true;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.verificarStatusLogin();
    this.carregarImagensCarousel();
  }

  /**
   * Carrega imagens aleatórias para o carousel
   */
  carregarImagensCarousel(): void {
    this.isLoadingCarousel = true;

    // Simulando dados de notícias com imagens aleatórias
    this.carouselItems = [
      {
        id: 1,
        imageUrl: `https://picsum.photos/1200/400?random=1&t=${Date.now()}`,
        title: "Breaking News: Desenvolvimento Tecnológico Acelera",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation."
      },
      {
        id: 2,
        imageUrl: `https://picsum.photos/1200/400?random=2&t=${Date.now()}`,
        title: "Economia Global em Transformação",
        description: "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate."
      },
      {
        id: 3,
        imageUrl: `https://picsum.photos/1200/400?random=3&t=${Date.now()}`,
        title: "Inovações Sustentáveis Ganham Destaque",
        description: "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus."
      }
    ];

    // Simula um pequeno delay de carregamento
    setTimeout(() => {
      this.isLoadingCarousel = false;
    }, 1000);
  }

  /**
   * Recarrega as imagens do carousel com novas imagens aleatórias
   */
  recarregarImagens(): void {
    this.carregarImagensCarousel();
  }

  /**
   * Verifica se o usuário está logado
   */
  verificarStatusLogin(): void {
    if (typeof window !== 'undefined' && localStorage) {
      const isLoggedIn = localStorage.getItem('isLoggedIn');
      this.isLoggedIn = isLoggedIn === 'true';
    }
  }

  /**
   * Função para buscar notícias
   */
  buscarNoticias(): void {
    if (this.termoBusca.trim()) {
      console.log('Buscando por:', this.termoBusca);
      this.router.navigate(['/busca'], {
        queryParams: { q: this.termoBusca }
      });
    }
  }

  /**
   * Método para fazer logout
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

  /**
   * Manipula erros de carregamento de imagem
   */
  onImageError(event: any, itemId: number): void {
    console.log(`Erro ao carregar imagem do item ${itemId}`);
    // Fallback para uma imagem padrão
    event.target.src = `https://via.placeholder.com/1200x400/0d6efd/ffffff?text=Notícia+${itemId}`;
  }
}
