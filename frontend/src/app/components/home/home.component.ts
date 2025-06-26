import { Component, OnInit } from "@angular/core";
import { Noticia } from "../../model/noticia";
import { Router, RouterLink, ActivatedRoute } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { NoticiaService } from '../../service/noticia.service';
import { CategoriaService } from '../../service/categoria.service';
import { PublicidadeService } from '../../service/publicidade.service';
import { Publicidade } from '../../model/publicidade';
import { SmartSlicePipe } from '../../shared/smart-slice.pipe';
import { lastValueFrom } from 'rxjs';

interface CarouselItem {
  id: number;
  imageUrl: string;
  title: string;
  description: string;
  fallbackImageUsed?: boolean;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // Propriedades do componente original
  isLoggedIn: boolean = false;
  termoBusca: string = '';
  carouselItems: CarouselItem[] = [];
  isLoadingCarousel: boolean = true;

  // Lista de serviços de imagens alternativas
  private imageServices = [
    'https://picsum.photos',
    'https://source.unsplash.com',
    'https://images.unsplash.com'
  ];

  // Cores para imagens de fallback
  private fallbackColors = [
    '#0d6efd', '#6f42c1', '#d63384', '#dc3545', '#fd7e14',
    '#ffc107', '#198754', '#20c997', '#0dcaf0', '#6c757d'
  ];

  // Propriedades do primeiro componente
  id: any;
  noticia$: any;
  categoria$: any;
  ultimaNoticia: any;

  noticias: any;           // todas as notícias
  noticiasPaginadas: Noticia[] = [];  // notícias visíveis na página atual
  paginaAtual: number = 1;
  itensPorPagina: number = 5;
  totalPaginas: number = 0;

  publicidadeTopo: Publicidade[] = [];
  publicidadeRodape: Publicidade[] = [];
  publicidadeSidebarE: Publicidade[] = [];
  publicidadeSidebarD: Publicidade[] = [];

  constructor(
    private router: Router,
    private noticiaService: NoticiaService,
    private categoriaService: CategoriaService,
    private publicidadeService: PublicidadeService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.verificarStatusLogin();
    this.carregarImagensCarousel();
    
    // Métodos do primeiro componente
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id']; // Captura o parâmetro 'id'      
      this.getNoticias();
      this.getUltimaNoticia();
    });

    this.publicidadeService.getPorPosicao('Top').subscribe(data => {
      this.publicidadeTopo = data;
    });

    this.publicidadeService.getPorPosicao('Down').subscribe(data => {
      this.publicidadeRodape = data;
    });

    this.publicidadeService.getPorPosicao('Left').subscribe(data => {
      this.publicidadeSidebarE = data;
    });

    this.publicidadeService.getPorPosicao('Right').subscribe(data => {
      this.publicidadeSidebarD = data;
    });
  }

  // Métodos do primeiro componente
  public async getNoticias() {
    if (this.id) {
      this.noticias = await lastValueFrom(this.noticiaService.listarNoticiasPorCategoria(this.id));
    } else {
      this.noticias = await lastValueFrom(this.noticiaService.get());
    }

    this.totalPaginas = Math.ceil(this.noticias.length / this.itensPorPagina);
    this.atualizarPagina();
  }

  public async getUltimaNoticia() {
    console.log(this.id);
    if (this.id) {
      this.ultimaNoticia = await lastValueFrom(this.noticiaService.findOneByUltimaNoticiaByCategoria(this.id));
    } else {
      this.ultimaNoticia = await lastValueFrom(this.noticiaService.findOneByUltimaNoticiaByTodas());
    }
  }

  public abrirDetalhes(id: any) {
    this.router.navigate(['detalhes/', id]);
  }

  carregarNoticias(): void {
    this.noticiaService.get().subscribe((data: any) => {
      this.noticias = data;
      this.totalPaginas = Math.ceil(this.noticias.length / this.itensPorPagina);
      this.atualizarPagina();
    });
  }

  atualizarPagina(): void {
    const inicio = (this.paginaAtual - 1) * this.itensPorPagina;
    const fim = inicio + this.itensPorPagina;
    this.noticiasPaginadas = this.noticias.slice(inicio, fim);
  }

  paginaAnterior(): void {
    if (this.paginaAtual > 1) {
      this.paginaAtual--;
      this.atualizarPagina();
    }
  }

  proximaPagina(): void {
    if (this.paginaAtual < this.totalPaginas) {
      this.paginaAtual++;
      this.atualizarPagina();
    }
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
        imageUrl: this.gerarUrlImagem(1200, 400, 1),
        title: "Breaking News: Desenvolvimento Tecnológico Acelera",
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation."
      },
      {
        id: 2,
        imageUrl: this.gerarUrlImagem(1200, 400, 2),
        title: "Economia Global em Transformação",
        description: "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate."
      },
      {
        id: 3,
        imageUrl: this.gerarUrlImagem(1200, 400, 3),
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
   * Gera URL de imagem com fallback
   */
  private gerarUrlImagem(width: number, height: number, seed: number): string {
    const timestamp = Date.now();
    return `${this.imageServices[0]}/${width}/${height}?random=${seed}&t=${timestamp}`;
  }

  /**
   * Cria uma imagem de fallback usando SVG Data URI
   */
  private criarImagemFallbackSvg(width: number, height: number, itemId: number, texto: string = ''): string {
    const color = this.fallbackColors[itemId % this.fallbackColors.length];
    const textColor = '#ffffff';
    const displayText = texto || `Notícia ${itemId}`;
    
    const svg = `
      <svg width="${width}" height="${height}" xmlns="http://www.w3.org/2000/svg">
        <rect width="100%" height="100%" fill="${color}"/>
        <text x="50%" y="50%" font-family="Arial, sans-serif" font-size="24" 
              fill="${textColor}" text-anchor="middle" dominant-baseline="middle">
          ${displayText}
        </text>
      </svg>
    `;
    
    return `data:image/svg+xml;base64,${btoa(svg)}`;
  }

  /**
   * Cria uma imagem de fallback usando Canvas
   */
  private criarImagemFallbackCanvas(width: number, height: number, itemId: number, texto: string = ''): string {
    try {
      const canvas = document.createElement('canvas');
      canvas.width = width;
      canvas.height = height;
      
      const ctx = canvas.getContext('2d');
      if (!ctx) {
        return this.criarImagemFallbackSvg(width, height, itemId, texto);
      }
      
      const color = this.fallbackColors[itemId % this.fallbackColors.length];
      const displayText = texto || `Notícia ${itemId}`;
      
      // Desenha o fundo
      ctx.fillStyle = color;
      ctx.fillRect(0, 0, width, height);
      
      // Desenha o texto
      ctx.fillStyle = '#ffffff';
      ctx.font = '24px Arial, sans-serif';
      ctx.textAlign = 'center';
      ctx.textBaseline = 'middle';
      ctx.fillText(displayText, width / 2, height / 2);
      
      return canvas.toDataURL();
    } catch (error) {
      console.warn('Erro ao criar imagem canvas, usando SVG:', error);
      return this.criarImagemFallbackSvg(width, height, itemId, texto);
    }
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
   * Manipula erros de carregamento de imagem com fallback inteligente
   */
  onImageError(event: any, itemId: number): void {
    console.log(`Erro ao carregar imagem do item ${itemId}`);
    
    const imgElement = event.target;
    const currentSrc = imgElement.src;
    
    // Encontra o item no carousel para atualizar
    const carouselItem = this.carouselItems.find(item => item.id === itemId);
    
    // Se já tentou um fallback, use imagem gerada
    if (carouselItem?.fallbackImageUsed || currentSrc.includes('data:image')) {
      imgElement.src = this.criarImagemFallbackCanvas(1200, 400, itemId);
      return;
    }
    
    // Tenta próximo serviço de imagem
    if (currentSrc.includes('picsum.photos')) {
      imgElement.src = `https://source.unsplash.com/1200x400/?news,${itemId}&t=${Date.now()}`;
      if (carouselItem) {
        carouselItem.fallbackImageUsed = true;
      }
    } else if (currentSrc.includes('source.unsplash')) {
      imgElement.src = `https://images.unsplash.com/photo-1504711434969-e33886168f5c?w=1200&h=400&fit=crop&crop=center&t=${Date.now()}`;
      if (carouselItem) {
        carouselItem.fallbackImageUsed = true;
      }
    } else {
      // Último recurso: imagem gerada
      imgElement.src = this.criarImagemFallbackCanvas(1200, 400, itemId);
      if (carouselItem) {
        carouselItem.fallbackImageUsed = true;
      }
    }
  }

  /**
   * Manipula erros de imagem para imagens menores (thumbnails, previews)
   */
  onSmallImageError(event: any, itemId: number, width: number = 400, height: number = 250): void {
    console.log(`Erro ao carregar imagem pequena do item ${itemId}`);
    
    const imgElement = event.target;
    const currentSrc = imgElement.src;
    
    // Se já é um fallback, não tenta novamente
    if (currentSrc.includes('data:image') || currentSrc.includes('base64')) {
      return;
    }
    
    // Tenta próximo serviço
    if (currentSrc.includes('picsum.photos')) {
      imgElement.src = `https://source.unsplash.com/${width}x${height}/?random&t=${Date.now()}`;
    } else {
      // Usa imagem gerada como último recurso
      imgElement.src = this.criarImagemFallbackCanvas(width, height, itemId);
    }
  }

  /**
   * Pré-carrega as imagens para detectar problemas antecipadamente
   */
  private precarregarImagens(): void {
    this.carouselItems.forEach(item => {
      const img = new Image();
      img.onload = () => {
        console.log(`Imagem ${item.id} carregada com sucesso`);
      };
      img.onerror = () => {
        console.warn(`Falha ao carregar imagem ${item.id}, usando fallback`);
        item.imageUrl = this.criarImagemFallbackCanvas(1200, 400, item.id);
        item.fallbackImageUsed = true;
      };
      img.src = item.imageUrl;
    });
  }
}