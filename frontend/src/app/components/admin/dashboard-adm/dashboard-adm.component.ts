import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

interface Stats {
  totalNews: number;
  activeCategories: number;
  registeredUsers: number;
  todayViews: number;
}

interface Activity {
  title: string;
  description: string;
  time: string;
}

interface News {
  id: number;
  title: string;
  category: string;
  date: Date;
  status: string;
  views: number;
}

@Component({
  selector: 'app-dashboard-adm',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard-adm.component.html',
  styleUrls: ['./dashboard-adm.component.css']
})
export class DashboardAdmComponent implements OnInit {

  constructor(private router: Router) { }

  stats: Stats = {
    totalNews: 127,
    activeCategories: 8,
    registeredUsers: 1847,
    todayViews: 15742
  };

  recentActivities: Activity[] = [
    {
      title: 'Nova notícia publicada',
      description: 'Artigo "Copa do Mundo 2024" foi publicado na categoria Esportes',
      time: 'Há 2 horas'
    },
    {
      title: 'Usuário registrado',
      description: 'João Silva se registrou no portal',
      time: 'Há 4 horas'
    },
    {
      title: 'Comentário moderado',
      description: 'Comentário removido por violação das regras',
      time: 'Há 6 horas'
    },
    {
      title: 'Categoria criada',
      description: 'Nova categoria "Tecnologia" foi adicionada',
      time: 'Ontem'
    }
  ];

  latestNews: News[] = [
    {
      id: 1,
      title: 'Copa do Mundo 2024 inicia amanhã',
      category: 'Esportes',
      date: new Date('2024-05-22'),
      status: 'Publicado',
      views: 1250
    },
    {
      id: 2,
      title: 'Novos investimentos em tecnologia',
      category: 'Economia',
      date: new Date('2024-05-21'),
      status: 'Rascunho',
      views: 0
    },
    {
      id: 3,
      title: 'Festival de cinema internacional',
      category: 'Lazer',
      date: new Date('2024-05-20'),
      status: 'Publicado',
      views: 890
    }
  ];

  ngOnInit(): void {
    // Inicialização do componente
  }

  getCategoryBadgeClass(category: string): string {
    const classes: { [key: string]: string } = {
      'Esportes': 'badge-success',
      'Economia': 'badge-info',
      'Lazer': 'badge-primary',
      'Política': 'badge-warning',
      'Internacional': 'badge-secondary'
    };
    return classes[category] || 'badge-secondary';
  }

  getStatusBadgeClass(status: string): string {
    const classes: { [key: string]: string } = {
      'Publicado': 'badge-success',
      'Rascunho': 'badge-warning',
      'Rejeitado': 'badge-danger'
    };
    return classes[status] || 'badge-secondary';
  }

  editNews(id: number): void {
    console.log('Editando notícia:', id);
    this.router.navigate(['/editar-noticia', id]);
  }

  deleteNews(id: number): void {
    if (confirm('Tem certeza que deseja excluir esta notícia?')) {
      console.log('Excluindo notícia:', id);
      // Implementar lógica de exclusão
      this.latestNews = this.latestNews.filter(news => news.id !== id);
    }
  }

  navigateToSection(section: string): void {
    this.router.navigate([`/${section}`]);
  }

  logout(): void {
    if (confirm('Tem certeza que deseja sair?')) {
      this.router.navigate(['/login']);
    }
  }
}
